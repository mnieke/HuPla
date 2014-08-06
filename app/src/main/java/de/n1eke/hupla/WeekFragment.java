package de.n1eke.hupla;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.n1eke.hupla.data.DataHolder;
import de.n1eke.hupla.data.HuPlaEntry;
import de.n1eke.hupla.data.HuPlaEntryDataSource;
import de.n1eke.hupla.data.HuPlaTime;
import de.n1eke.hupla.data.HuPlaType;
import de.n1eke.hupla.data.PopupWindowButtonHandler;

/**
 * Created by michi on 02.08.14.
 */
public class WeekFragment extends HuPlaFragment {

    private View rootView;

    private Button buttonCurrentWeek;

    private GregorianCalendar startDate;
    private GregorianCalendar endDate;

    private ImageButton imageButtonMondayMorning;
    private ImageButton imageButtonMondayNoon;
    private ImageButton imageButtonMondayEvening;

    private ImageButton imageButtonTuesdayMorning;
    private ImageButton imageButtonTuesdayNoon;
    private ImageButton imageButtonTuesdayEvening;


    private ImageButton imageButtonWednesdayMorning;
    private ImageButton imageButtonWednesdayNoon;
    private ImageButton imageButtonWednesdayEvening;


    private ImageButton imageButtonThursdayMorning;
    private ImageButton imageButtonThursdayNoon;
    private ImageButton imageButtonThursdayEvening;


    private ImageButton imageButtonFridayMorning;
    private ImageButton imageButtonFridayNoon;
    private ImageButton imageButtonFridayEvening;


    private ImageButton imageButtonSaturdayMorning;
    private ImageButton imageButtonSaturdayNoon;
    private ImageButton imageButtonSaturdayEvening;


    private ImageButton imageButtonSundayMorning;
    private ImageButton imageButtonSundayNoon;
    private ImageButton imageButtonSundayEvening;

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
    public static WeekFragment newInstance(int sectionNumber) {
        WeekFragment fragment = new WeekFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public WeekFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_week, container, false);

        buttonCurrentWeek = (Button) rootView.findViewById(R.id.button_current_week);
        buttonCurrentWeek.setOnClickListener(this);

        ((ImageButton) rootView.findViewById(R.id.image_button_next_week)).setOnClickListener(this);
        ((ImageButton) rootView.findViewById(R.id.image_button_previous_week)).setOnClickListener(this);

        imageButtonMondayMorning = (ImageButton) rootView.findViewById(R.id.image_button_monday_morning);
        imageButtonMondayMorning.setOnClickListener(this);
        imageButtonMondayNoon = (ImageButton) rootView.findViewById(R.id.image_button_monday_noon);
        imageButtonMondayNoon.setOnClickListener(this);
        imageButtonMondayEvening = (ImageButton) rootView.findViewById(R.id.image_button_monday_evening);
        imageButtonMondayEvening.setOnClickListener(this);

        imageButtonTuesdayMorning = (ImageButton) rootView.findViewById(R.id.image_button_tuesday_morning);
        imageButtonTuesdayMorning.setOnClickListener(this);
        imageButtonTuesdayNoon = (ImageButton) rootView.findViewById(R.id.image_button_tuesday_noon);
        imageButtonTuesdayNoon.setOnClickListener(this);
        imageButtonTuesdayEvening = (ImageButton) rootView.findViewById(R.id.image_button_tuesday_evening);
        imageButtonTuesdayEvening.setOnClickListener(this);

        imageButtonWednesdayMorning = (ImageButton) rootView.findViewById(R.id.image_button_wednesday_morning);
        imageButtonWednesdayMorning.setOnClickListener(this);
        imageButtonWednesdayNoon = (ImageButton) rootView.findViewById(R.id.image_button_wednesday_noon);
        imageButtonWednesdayNoon.setOnClickListener(this);
        imageButtonWednesdayEvening = (ImageButton) rootView.findViewById(R.id.image_button_wednesday_evening);
        imageButtonWednesdayEvening.setOnClickListener(this);

        imageButtonThursdayMorning = (ImageButton) rootView.findViewById(R.id.image_button_thursday_morning);
        imageButtonThursdayMorning.setOnClickListener(this);
        imageButtonThursdayNoon = (ImageButton) rootView.findViewById(R.id.image_button_thursday_noon);
        imageButtonThursdayNoon.setOnClickListener(this);
        imageButtonThursdayEvening = (ImageButton) rootView.findViewById(R.id.image_button_thursday_evening);
        imageButtonThursdayEvening.setOnClickListener(this);

        imageButtonFridayMorning = (ImageButton) rootView.findViewById(R.id.image_button_friday_morning);
        imageButtonFridayMorning.setOnClickListener(this);
        imageButtonFridayNoon = (ImageButton) rootView.findViewById(R.id.image_button_friday_noon);
        imageButtonFridayNoon.setOnClickListener(this);
        imageButtonFridayEvening = (ImageButton) rootView.findViewById(R.id.image_button_friday_evening);
        imageButtonFridayEvening.setOnClickListener(this);

        imageButtonSaturdayMorning = (ImageButton) rootView.findViewById(R.id.image_button_saturday_morning);
        imageButtonSaturdayMorning.setOnClickListener(this);
        imageButtonSaturdayNoon = (ImageButton) rootView.findViewById(R.id.image_button_saturday_noon);
        imageButtonSaturdayNoon.setOnClickListener(this);
        imageButtonSaturdayEvening = (ImageButton) rootView.findViewById(R.id.image_button_saturday_evening);
        imageButtonSaturdayEvening.setOnClickListener(this);

        imageButtonSundayMorning = (ImageButton) rootView.findViewById(R.id.image_button_sunday_morning);
        imageButtonSundayMorning.setOnClickListener(this);
        imageButtonSundayNoon = (ImageButton) rootView.findViewById(R.id.image_button_sunday_noon);
        imageButtonSundayNoon.setOnClickListener(this);
        imageButtonSundayEvening = (ImageButton) rootView.findViewById(R.id.image_button_sunday_evening);
        imageButtonSundayEvening.setOnClickListener(this);

        resetDate();
        updateCurrentWeekButtonText();
        updateEntries();
        return rootView;
    }

    public void nextWeek() {
        changeWeekByDays(7);
    }

    public void previousWeek() {
        changeWeekByDays(-7);
    }

    private void changeWeekByDays(int days) {
        startDate.add(Calendar.DAY_OF_MONTH, days);
        endDate.add(Calendar.DAY_OF_MONTH, days);

        updateCurrentWeekButtonText();
        updateEntries();
    }

    private void updateCurrentWeekButtonText() {
        buttonCurrentWeek.setText(startDate.get(Calendar.DAY_OF_MONTH) + "." + (startDate.get(Calendar.MONTH)+1) + "." + startDate.get(Calendar.YEAR) + " - " + endDate.get(Calendar.DAY_OF_MONTH) + "." + (endDate.get(Calendar.MONTH)+1) + "." + endDate.get(Calendar.YEAR));
    }

    private void resetDate() {
        startDate = new GregorianCalendar();
        startDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        endDate = new GregorianCalendar();
        endDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    }

    public void updateEntries() {
        DataHolder dataHolder = DataHolder.getInstance();
        GregorianCalendar tempCalendar = (GregorianCalendar) startDate.clone();

        // Get entries for monday and set the image button images
        HuPlaEntry mondayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(mondayMorningEntry, imageButtonMondayMorning);

        HuPlaEntry mondayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(mondayNoonEntry, imageButtonMondayNoon);

        HuPlaEntry mondayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(mondayEveningEntry, imageButtonMondayEvening);

        // Get entries for tuesday and set the image button images
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry tuesdayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(tuesdayMorningEntry, imageButtonTuesdayMorning);

        HuPlaEntry tuesdayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(tuesdayNoonEntry, imageButtonTuesdayNoon);

        HuPlaEntry tuesdayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(tuesdayEveningEntry, imageButtonTuesdayEvening);

        // Get entries for wednesday and set the image button images
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry wednesdayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(wednesdayMorningEntry, imageButtonWednesdayMorning);

        HuPlaEntry wednesdayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(wednesdayNoonEntry, imageButtonWednesdayNoon);

        HuPlaEntry wednesdayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(wednesdayEveningEntry, imageButtonWednesdayEvening);

        // Get entries for thursday and set the image button images
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry thursdayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(thursdayMorningEntry, imageButtonThursdayMorning);

        HuPlaEntry thursdayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(thursdayNoonEntry, imageButtonThursdayNoon);

        HuPlaEntry thursdayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(thursdayEveningEntry, imageButtonThursdayEvening);

        // Get entries for friday and set the image button images
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry fridayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(fridayMorningEntry, imageButtonFridayMorning);

        HuPlaEntry fridayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(fridayNoonEntry, imageButtonFridayNoon);

        HuPlaEntry fridayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(fridayEveningEntry, imageButtonFridayEvening);


        // Get entries for saturday and set the image button images
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry saturdayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(saturdayMorningEntry, imageButtonSaturdayMorning);

        HuPlaEntry saturdayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(saturdayNoonEntry, imageButtonSaturdayNoon);

        HuPlaEntry saturdayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(saturdayEveningEntry, imageButtonSaturdayEvening);


        // Get entries for sunday and set the image button images
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry sundayMorningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.MORNING);
        setImageButtonSource(sundayMorningEntry, imageButtonSundayMorning);

        HuPlaEntry sundayNoonEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.NOON);
        setImageButtonSource(sundayNoonEntry, imageButtonSundayNoon);

        HuPlaEntry sundayEveningEntry = dataHolder.findHuPlaEntryByDate(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH), tempCalendar.get(Calendar.DAY_OF_MONTH), HuPlaTime.EVENING);
        setImageButtonSource(sundayEveningEntry, imageButtonSundayEvening);


    }

    private void setImageButtonSource(HuPlaEntry entry, ImageButton imageButton) {
        if (entry != null) {
            imageButton.setImageResource(entry.getHuPlaType().getDrawbaleId());
        } else {
            imageButton.setImageResource(HuPlaType.NA.getDrawbaleId());
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.image_button_next_week) {
            nextWeek();
        } else if (v.getId() == R.id.image_button_previous_week) {
            previousWeek();
        } else if(v.getId() == buttonCurrentWeek.getId()) {
            resetDate();
            updateCurrentWeekButtonText();
            updateEntries();
        } else {
            LayoutInflater layoutInflater
                    = (LayoutInflater) getActivity().getBaseContext()
                    .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popup_window_image_selection, null);
            final PopupWindow popupWindow = new PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonSelected = v;
            this.popupWindow = popupWindow;
            PopupWindowButtonHandler popupWindowButtonHandler = new PopupWindowButtonHandler((android.widget.TableLayout) popupView.findViewById(R.id.table_layout_popup), popupWindow, this);

            popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
            isPopupOpened = true;
        }
    }

    @Override
    public void imageWasSelected(HuPlaType huPlaType) {
        isPopupOpened = false;
        if(huPlaType == null) {
            return;
        }

        DataHolder dataHolder = DataHolder.getInstance();
        HuPlaEntry entry = null;
        HuPlaEntryDataSource dataSource = new HuPlaEntryDataSource(getActivity());
        dataSource.open();


        GregorianCalendar calendar = (GregorianCalendar) startDate.clone();

        if(buttonSelected.getId() == imageButtonMondayMorning.getId()) {
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonMondayNoon.getId()) {
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonMondayEvening.getId()) {
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        } else if(buttonSelected.getId() == imageButtonTuesdayMorning.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonTuesdayNoon.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonTuesdayEvening.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        } else if(buttonSelected.getId() == imageButtonWednesdayMorning.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonWednesdayNoon.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonWednesdayEvening.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        } else if(buttonSelected.getId() == imageButtonThursdayMorning.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonThursdayNoon.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonThursdayEvening.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        } else if(buttonSelected.getId() == imageButtonFridayMorning.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 4);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonFridayNoon.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 4);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonFridayEvening.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 4);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        } else if(buttonSelected.getId() == imageButtonSaturdayMorning.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonSaturdayNoon.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonSaturdayEvening.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        } else if(buttonSelected.getId() == imageButtonSundayMorning.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.MORNING, calendar);
        } else if(buttonSelected.getId() == imageButtonSundayNoon.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.NOON, calendar);
        } else if(buttonSelected.getId() == imageButtonSundayEvening.getId()) {
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            entry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);
            dataSource.setHuPlaTypeForEntry(entry, huPlaType, HuPlaTime.EVENING, calendar);
        }


        dataSource.close();
        buttonSelected = null;
        updateEntries();
    }
}
