package de.n1eke.hupla.data;

import java.util.Date;

/**
 * Created by michi on 04.08.14.
 */
public class HuPlaEntry {

    private HuPlaType huPlaType;
    private Date date;
    private HuPlaTime huPlaTime;
    private long databaseID;


    protected HuPlaEntry(long databaseID, Date date, HuPlaTime huPlaTime, HuPlaType huPlaType){
        this.date = date;
        this.huPlaType = huPlaType;
        this.huPlaTime = huPlaTime;
        this.databaseID = databaseID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if(date != null)
            this.date = date;
    }

    public HuPlaType getHuPlaType() {
        return huPlaType;
    }

    public void setHuPlaType(HuPlaType huPlaType) {
        if(huPlaType != null)
            this.huPlaType = huPlaType;
    }

    public HuPlaTime getHuPlaTime() {
        return huPlaTime;
    }

    public void setHuPlaTime(HuPlaTime huPlaTime) {
        if(huPlaTime != null)
            this.huPlaTime = huPlaTime;
    }

    public long getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(long databaseID) {
        this.databaseID = databaseID;
    }
}
