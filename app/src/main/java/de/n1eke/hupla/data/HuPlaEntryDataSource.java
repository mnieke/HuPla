package de.n1eke.hupla.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by michi on 04.08.14.
 */
public class HuPlaEntryDataSource {

    private SQLiteDatabase database;
    private HuPlaDataSQLiteHelper dbHelper;
    private String[] allColumns = {HuPlaDataSQLiteHelper.COLUMN_ID,
            HuPlaDataSQLiteHelper.COLUMN_DATE, HuPlaDataSQLiteHelper.COLUMN_TIME, HuPlaDataSQLiteHelper.COLUMN_TYPE};

    private boolean opened;

    public HuPlaEntryDataSource(Context context) {
        dbHelper = new HuPlaDataSQLiteHelper(context);
        opened = false;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        opened = true;
    }

    public void close() {
        dbHelper.close();
        opened = false;
    }

    public HuPlaEntry createHuPlaEntry(GregorianCalendar calendar, HuPlaTime time, HuPlaType type) {
        if (!opened)
            return null;


        String dateString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        ContentValues values = new ContentValues();
        values.put(HuPlaDataSQLiteHelper.COLUMN_DATE, dateString);
        values.put(HuPlaDataSQLiteHelper.COLUMN_TIME, time.toString());
        values.put(HuPlaDataSQLiteHelper.COLUMN_TYPE, type.getDatabaseID());
        long insertId = database.insert(HuPlaDataSQLiteHelper.TABLE_HUPLA, null, values);
        Cursor cursor = database.query(HuPlaDataSQLiteHelper.TABLE_HUPLA, allColumns, HuPlaDataSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        HuPlaEntry newEntry = cursorToEntry(cursor);
        cursor.close();
        return newEntry;
    }

    public boolean deleteHuPlaEntry(HuPlaEntry entry) {
        if (!opened)
            return false;

        long id = entry.getDatabaseID();
        System.out.println("HuPlaEntry deleted with id: " + id);
        database.delete(HuPlaDataSQLiteHelper.TABLE_HUPLA, HuPlaDataSQLiteHelper.COLUMN_ID
                + " = " + id, null);
        return true;
    }

    public List<HuPlaEntry> getAllHuPlaEntries() {
        if (!opened)
            return null;

        List<HuPlaEntry> entries = new ArrayList<HuPlaEntry>();

        Cursor cursor = database.query(HuPlaDataSQLiteHelper.TABLE_HUPLA,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HuPlaEntry entry = cursorToEntry(cursor);
            entries.add(entry);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return entries;
    }

    private HuPlaEntry cursorToEntry(Cursor cursor) {
        long id = cursor.getLong(0);
//            Date date = dateFormat.parse(dateString);
        GregorianCalendar calendar = parseDate(cursor.getString(1));
        HuPlaTime time = HuPlaTime.getTimeFromString(cursor.getString(2));
        HuPlaType type = HuPlaType.getHuPlaTypeByDatabaseID(cursor.getInt(3));
        HuPlaEntry entry = new HuPlaEntry(id, calendar, time, type);
        return entry;
    }

    private GregorianCalendar parseDate(String date) {
        String[] dateSplits = date.split("-");
        if (dateSplits.length != 3) {
            return null;
        }

        try {
            int year = Integer.parseInt(dateSplits[0]);
            int month = Integer.parseInt(dateSplits[1]);
            int day = Integer.parseInt(dateSplits[2]);

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            return calendar;
        } catch (NumberFormatException numFormat) {
            numFormat.printStackTrace();
        }

        return null;
    }

    public void setHuPlaTypeForEntry(HuPlaEntry entry, HuPlaType type, HuPlaTime time, GregorianCalendar calendar) {
        DataHolder dataHolder = DataHolder.getInstance();

        if(entry != null && !type.equals(entry.getHuPlaType())) {
            deleteHuPlaEntry(entry);
            dataHolder.getEntryList().remove(entry);

            if(type != HuPlaType.NA) {
                HuPlaEntry newEntry = createHuPlaEntry(entry.getDate(), entry.getHuPlaTime(), type);

                if(newEntry != null) {
                    dataHolder.getEntryList().add(newEntry);
                }
            }


        } else if(entry == null && type != HuPlaType.NA) {
            HuPlaEntry newEntry = createHuPlaEntry(calendar, time, type);

            if(newEntry != null) {
                dataHolder.getEntryList().add(newEntry);
            }
        }
    }
}
