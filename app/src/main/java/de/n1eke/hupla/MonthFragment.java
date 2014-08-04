package de.n1eke.hupla;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TableRow;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.n1eke.hupla.data.DataHolder;
import de.n1eke.hupla.data.HuPlaEntry;
import de.n1eke.hupla.data.HuPlaTime;
import de.n1eke.hupla.data.HuPlaType;
import de.n1eke.hupla.data.HuPlaEntryDataSource;

/**
 * Created by michi on 02.08.14.
 */
public class MonthFragment extends Fragment implements CalendarView.OnDateChangeListener{

    private ImageButton imageButtonMorning;
    private ImageButton imageButtonNoon;
    private ImageButton imageButtonEvening;
    private int lastYear;
    private int lastMonth;
    private int lastDay;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MonthFragment newInstance(int sectionNumber) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MonthFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_month, container, false);

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(this);

        TableRow imageButtonRow = (TableRow) rootView.findViewById(R.id.table_layout_month).findViewById(R.id.table_row_month_image_buttons);
        imageButtonMorning = (ImageButton) imageButtonRow.findViewById(R.id.image_button_morning);
        imageButtonNoon = (ImageButton) imageButtonRow.findViewById(R.id.image_button_noon);
        imageButtonEvening = (ImageButton) imageButtonRow.findViewById(R.id.image_button_evening);

        GregorianCalendar calendar = new GregorianCalendar();
        onSelectedDayChange(null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        return rootView;
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        if(lastYear == year && lastMonth == month && lastDay == dayOfMonth)
            return;

        lastYear = year;
        lastMonth = month;
        lastDay = dayOfMonth;

        DataHolder dataHolder = DataHolder.getInstance();

        HuPlaEntry morningEntry = dataHolder.findHuPlaEntryByDate(year, month, dayOfMonth, HuPlaTime.MORNING);
        HuPlaEntry noonEntry = dataHolder.findHuPlaEntryByDate(year, month, dayOfMonth, HuPlaTime.NOON);
        HuPlaEntry eveningEntry = dataHolder.findHuPlaEntryByDate(year, month, dayOfMonth, HuPlaTime.EVENING);

        if(morningEntry != null) {
            imageButtonMorning.setImageResource(morningEntry.getHuPlaType().getDrawbaleId());
        } else {
            imageButtonMorning.setImageResource(HuPlaType.NA.getDrawbaleId());
        }

        if(noonEntry != null) {
            imageButtonNoon.setImageResource(noonEntry.getHuPlaType().getDrawbaleId());
        } else {
            imageButtonNoon.setImageResource(HuPlaType.NA.getDrawbaleId());
        }

        if(eveningEntry != null) {
            imageButtonEvening.setImageResource(eveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            imageButtonEvening.setImageResource(HuPlaType.NA.getDrawbaleId());
        }

    }
}
