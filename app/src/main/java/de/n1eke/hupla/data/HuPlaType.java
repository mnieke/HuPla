package de.n1eke.hupla.data;

import de.n1eke.hupla.R;

/**
 * Created by michi on 04.08.14.
 */
public enum HuPlaType {

    NA, BLUE, GREEN, RED, ORANGE, YELLOW, GREY, HEART, SHEEP, DOG, WOLF;

    public int getDrawbaleId() {
        switch (this) {
            case NA:
                return R.drawable.empty;
            case BLUE:
                return R.drawable.circle_blue;
            case GREEN:
                return R.drawable.circle_green;
            case RED:
                return R.drawable.circle_red;
            case ORANGE:
                return R.drawable.circle_orange;
            case YELLOW:
                return R.drawable.circle_yellow;
            case GREY:
                return R.drawable.circle_grey;
            case HEART:
                return R.drawable.heart;
            case SHEEP:
                return R.drawable.sheep;
            case DOG:
                return R.drawable.dog;
            case WOLF:
                return R.drawable.wolf;
            default:
                return R.drawable.empty;
        }
    }

    public int getDatabaseID() {
        switch (this) {
            case NA:
                return 0;
            case BLUE:
                return 1;
            case GREEN:
                return 2;
            case RED:
                return 3;
            case ORANGE:
                return 4;
            case YELLOW:
                return 5;
            case GREY:
                return 6;
            case HEART:
                return 7;
            case SHEEP:
                return 8;
            case DOG:
                return 9;
            case WOLF:
                return 10;
            default:
                return R.drawable.empty;
        }
    }

    public static HuPlaType getHuPlaTypeByDatabaseID(int databaseID) {
        switch (databaseID) {
            case 0:
                return NA;
            case 1:
                return BLUE;
            case 2:
                return GREEN;
            case 3:
                return RED;
            case 4:
                return ORANGE;
            case 5:
                return YELLOW;
            case 6:
                return GREY;
            case 7:
                return HEART;
            case 8:
                return SHEEP;
            case 9:
                return DOG;
            case 10:
                return WOLF;
            default:
                return NA;
        }
    }

    public static HuPlaType getHuPlaTypeByDrawableID(int drawableID) {
        switch (drawableID) {
            case R.drawable.empty:
                return NA;
            case R.drawable.circle_blue:
                return BLUE;
            case R.drawable.circle_green:
                return GREEN;
            case R.drawable.circle_red:
                return RED;
            case R.drawable.circle_orange:
                return ORANGE;
            case R.drawable.circle_yellow:
                return YELLOW;
            case R.drawable.circle_grey:
                return GREY;
            case R.drawable.heart:
                return HEART;
            case R.drawable.sheep:
                return SHEEP;
            case R.drawable.dog:
                return DOG;
            case R.drawable.wolf:
                return WOLF;
            default:
                return NA;
        }
    }
}
