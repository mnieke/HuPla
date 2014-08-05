package de.n1eke.hupla.data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by michi on 04.08.14.
 */
public class HuPlaEntry {

    private HuPlaType huPlaType;
    private HuPlaTime huPlaTime;
    private long databaseID;
    private GregorianCalendar date;


    protected HuPlaEntry(long databaseID, GregorianCalendar date, HuPlaTime huPlaTime, HuPlaType huPlaType) {
        this.date = date;
        this.huPlaType = huPlaType;
        this.huPlaTime = huPlaTime;
        this.databaseID = databaseID;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        if (date != null)
            this.date = date;
    }

    public HuPlaType getHuPlaType() {
        return huPlaType;
    }

    public void setHuPlaType(HuPlaType huPlaType) {
        if (huPlaType != null)
            this.huPlaType = huPlaType;
    }

    public HuPlaTime getHuPlaTime() {
        return huPlaTime;
    }

    public void setHuPlaTime(HuPlaTime huPlaTime) {
        if (huPlaTime != null)
            this.huPlaTime = huPlaTime;
    }

    public long getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(long databaseID) {
        this.databaseID = databaseID;
    }

    public boolean equalDates(int year, int month, int day) {
        if (date == null) {
            return false;
        }

        if (year == this.date.get(GregorianCalendar.YEAR) && day == this.date.get(GregorianCalendar.DAY_OF_MONTH) && month == this.date.get(GregorianCalendar.MONTH)) {
            return true;
        }

        return false;
    }
}
