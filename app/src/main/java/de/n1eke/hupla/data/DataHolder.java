package de.n1eke.hupla.data;

import java.util.ArrayList;
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
        if(instance == null) {
            synchronized (DataHolder.class) {
                if(instance == null) {
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
}
