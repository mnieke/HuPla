package de.n1eke.hupla.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by michi on 03.08.14.
 */
public class DataHolder {

    private static volatile DataHolder instance;

    private List<HuPlaEntry> entryList;

    private DataHolder() {
        entryList = new ArrayList<HuPlaEntry>();
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            synchronized (DataHolder.class) {
                if (instance == null) {
                    instance = new DataHolder();
                }
            }
        }

        return instance;
    }


    public List<HuPlaEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<HuPlaEntry> entryList) {
        this.entryList = entryList;
    }

    /**
     * Searches the list of HuPlaEntries for an entry matching the date and time. May be null if not available
     *
     * @param year  Year which the entry should have
     * @param month month which the entry should have
     * @param day   Day which the entry should have
     * @param time  Time which the entry should have
     * @return The entry having the specified date and time. May be null if not available
     */
    public HuPlaEntry findHuPlaEntryByDate(int year, int month, int day, HuPlaTime time) {
        HuPlaEntry huPlaEntry = null;

        synchronized (entryList) {
            for (HuPlaEntry entry : entryList) {
                if (entry.equalDates(year, month, day) && entry.getHuPlaTime().equals(time)) {
                    return entry;
                }
            }
        }

        return huPlaEntry;
    }

    public HuPlaEntry findHuPlaEntryByCalendar(Calendar calendar, HuPlaTime huPlaTime) {
        return findHuPlaEntryByDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), huPlaTime);
    }
}
