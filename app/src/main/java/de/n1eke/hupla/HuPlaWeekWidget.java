package de.n1eke.hupla;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.n1eke.hupla.data.DataHolder;
import de.n1eke.hupla.data.HuPlaEntry;
import de.n1eke.hupla.data.HuPlaEntryDataSource;
import de.n1eke.hupla.data.HuPlaTime;
import de.n1eke.hupla.data.HuPlaType;


/**
 * Implementation of App Widget functionality.
 */
public class HuPlaWeekWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.hu_pla_week_widget);

        GregorianCalendar startDate = new GregorianCalendar();
        startDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DataHolder dataHolder = DataHolder.getInstance();
        HuPlaEntryDataSource dataSource = new HuPlaEntryDataSource(context);
        dataSource.open();
        dataHolder.setEntryList(dataSource.getAllHuPlaEntries());

        views.setTextViewText(R.id.text_view_week_widget_date, "Hundeplan for: "+startDate.get(Calendar.DAY_OF_MONTH)+"."+(startDate.get(Calendar.MONTH)+1)+"."+startDate.get(Calendar.YEAR)+" - "+endDate.get(Calendar.DAY_OF_MONTH)+"."+(endDate.get(Calendar.MONTH)+1)+"."+endDate.get(Calendar.YEAR));

        // Entries for monday
        HuPlaEntry mondayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry mondayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry mondayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Entries for tuesday
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry tuesdayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry tuesdayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry tuesdayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Entries for wednesday
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry wednesdayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry wednesdayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry wednesdayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Entries for thursday
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry thursdayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry thursdayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry thursdayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Entries for friday
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry fridayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry fridayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry fridayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Entries for saturday
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry saturdayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry saturdayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry saturdayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Entries for sunday
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        HuPlaEntry sundayMorningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.MORNING);
        HuPlaEntry sundayNoonEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.NOON);
        HuPlaEntry sundayEveningEntry = dataHolder.findHuPlaEntryByCalendar(startDate, HuPlaTime.EVENING);

        // Set new images for ImageViews

        // Images for monday
        if(mondayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_monday_morning, mondayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_monday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(mondayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_monday_noon, mondayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_monday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(mondayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_monday_evening, mondayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_monday_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Images for tuesday
        if(tuesdayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_tuesday_morning, tuesdayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_tuesday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(tuesdayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_tuesday_noon, tuesdayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_tuesday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(tuesdayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_tuesday_evening, tuesdayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_tuesday_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Images for wednesday
        if(wednesdayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_wednesday_morning, wednesdayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_wednesday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(wednesdayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_wednesday_noon, wednesdayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_wednesday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(wednesdayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_wednesday_evening, wednesdayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_wednesday_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Images for thursday
        if(thursdayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_thursday_morning, thursdayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_thursday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(thursdayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_thursday_noon, thursdayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_thursday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(thursdayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_thursday_evening, thursdayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_thursday_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Images for friday
        if(fridayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_friday_morning, fridayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_friday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(fridayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_friday_noon, fridayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_friday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(fridayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_friday_evening, fridayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_friday_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Images for saturday
        if(saturdayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_saturday_morning, saturdayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_saturday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(saturdayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_saturday_noon, saturdayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_saturday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(saturdayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_saturday_evening, saturdayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_saturday_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Images for sunday
        if(sundayMorningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_sunday_morning, sundayMorningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_sunday_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(sundayNoonEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_sunday_noon, sundayNoonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_sunday_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(sundayEveningEntry != null) {
            views.setImageViewResource(R.id.image_view_week_widget_sunday_evening, sundayEveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_week_widget_sunday_evening, HuPlaType.NA.getDrawbaleId());
        }



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}


