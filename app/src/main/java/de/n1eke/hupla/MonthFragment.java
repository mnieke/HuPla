package de.n1eke.hupla;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ScrollView;
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
import de.n1eke.hupla.data.PopupWindowButtonHandler;


import android.view.ViewGroup.LayoutParams;

/**
 * Created by michi on 02.08.14.
 */
public class MonthFragment extends Fragment implements CalendarView.OnDateChangeListener, ImageSelectedListener, View.OnClickListener {

    private View rootView;

    private ImageButton imageButtonMorning;
    private ImageButton imageButtonNoon;
    private ImageButton imageButtonEvening;
    private int lastYear;
    private int lastMonth;
    private int lastDay;
    private View buttonSelected;

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
        rootView = inflater.inflate(R.layout.fragment_month, container, false);

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(this);

        imageButtonMorning = (ImageButton) rootView.findViewById(R.id.image_button_morning);
        imageButtonMorning.setOnClickListener(this);
        imageButtonNoon = (ImageButton) rootView.findViewById(R.id.image_button_noon);
        imageButtonEvening = (ImageButton) rootView.findViewById(R.id.image_button_evening);

        GregorianCalendar calendar = new GregorianCalendar();
        onSelectedDayChange(null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        return rootView;
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        if (lastYear == year && lastMonth == month && lastDay == dayOfMonth)
            return;

        lastYear = year;
        lastMonth = month;
        lastDay = dayOfMonth;

        updateImages();
    }

    private void updateImages() {
        DataHolder dataHolder = DataHolder.getInstance();

        HuPlaEntry morningEntry = dataHolder.findHuPlaEntryByDate(lastYear, lastMonth, lastDay, HuPlaTime.MORNING);
        HuPlaEntry noonEntry = dataHolder.findHuPlaEntryByDate(lastYear, lastMonth, lastDay, HuPlaTime.NOON);
        HuPlaEntry eveningEntry = dataHolder.findHuPlaEntryByDate(lastYear, lastMonth, lastDay, HuPlaTime.EVENING);

        if (morningEntry != null) {
            imageButtonMorning.setImageResource(morningEntry.getHuPlaType().getDrawbaleId());
        } else {
            imageButtonMorning.setImageResource(HuPlaType.NA.getDrawbaleId());
        }

        if (noonEntry != null) {
            imageButtonNoon.setImageResource(noonEntry.getHuPlaType().getDrawbaleId());
        } else {
            imageButtonNoon.setImageResource(HuPlaType.NA.getDrawbaleId());
        }

        if (eveningEntry != null) {
            imageButtonEvening.setImageResource(eveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            imageButtonEvening.setImageResource(HuPlaType.NA.getDrawbaleId());
        }
    }

    @Override
    public void onClick(View view) {
        LayoutInflater layoutInflater
                = (LayoutInflater) getActivity().getBaseContext()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup_window_image_selection, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        buttonSelected = view;

        PopupWindowButtonHandler popupWindowButtonHandler = new PopupWindowButtonHandler((android.widget.TableLayout) popupView.findViewById(R.id.table_layout_popup), popupWindow, this);

        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void imageWasSelected(HuPlaType huPlaType) {
        // TODO zurück button im Popup, Settings, Heute Button

        DataHolder dataHolder = DataHolder.getInstance();
        HuPlaEntry entry = null;
        HuPlaEntryDataSource dataSource = new HuPlaEntryDataSource(getActivity());
        dataSource.open();

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(lastYear, lastMonth, lastDay);

        if(buttonSelected.equals(imageButtonMorning)) {
            entry = dataHolder.findHuPlaEntryByDate(lastYear, lastMonth, lastDay, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.equals(imageButtonNoon)) {
            entry = dataHolder.findHuPlaEntryByDate(lastYear, lastMonth, lastDay, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.equals(imageButtonEvening)) {
            entry = dataHolder.findHuPlaEntryByDate(lastYear, lastMonth, lastDay, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        }

        dataSource.close();
        buttonSelected = null;
        updateImages();
    }
}
