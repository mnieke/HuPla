package de.n1eke.hupla.data;

/**
 * Created by michi on 04.08.14.
 */
public enum HuPlaTime {

    MORNING, NOON, EVENING;

    @Override
    public String toString() {
        switch (this) {
            case MORNING:
                return "morning";
            case NOON:
                return "noon";
            case EVENING:
                return "evening";
            default:
                return "";
        }
    }

    public static HuPlaTime getTimeFromString(String s) {
        if (s.equals(MORNING.toString())) {
            return MORNING;
        } else if (s.equals(NOON.toString())) {
            return NOON;
        } else if (s.equals(EVENING.toString())) {
            return EVENING;
        } else {
            return null;
        }
    }
}
