package de.n1eke.hupla;

/**
 * Created by michi on 03.08.14.
 */
public class DataHolder {

    private static volatile DataHolder instance;

    private DataHolder() {

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

}
