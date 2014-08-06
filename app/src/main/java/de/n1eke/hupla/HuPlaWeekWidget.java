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

    // TODO beim draufklicken App öffnen

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
        addImageFromHuPlaEntryToView(views, mondayMorningEntry, R.id.image_view_week_widget_monday_morning);
        addImageFromHuPlaEntryToView(views, mondayNoonEntry, R.id.image_view_week_widget_monday_noon);
        addImageFromHuPlaEntryToView(views, mondayEveningEntry, R.id.image_view_week_widget_monday_evening);

        // Images for tuesday
        addImageFromHuPlaEntryToView(views, tuesdayMorningEntry, R.id.image_view_week_widget_tuesday_morning);
        addImageFromHuPlaEntryToView(views, tuesdayNoonEntry, R.id.image_view_week_widget_tuesday_noon);
        addImageFromHuPlaEntryToView(views, tuesdayEveningEntry, R.id.image_view_week_widget_tuesday_evening);

        // Images for wednesday
        addImageFromHuPlaEntryToView(views, wednesdayMorningEntry, R.id.image_view_week_widget_wednesday_morning);
        addImageFromHuPlaEntryToView(views, wednesdayNoonEntry, R.id.image_view_week_widget_wednesday_noon);
        addImageFromHuPlaEntryToView(views, wednesdayEveningEntry, R.id.image_view_week_widget_wednesday_evening);

        // Images for thursday
        addImageFromHuPlaEntryToView(views, thursdayMorningEntry, R.id.image_view_week_widget_thursday_morning);
        addImageFromHuPlaEntryToView(views, thursdayNoonEntry, R.id.image_view_week_widget_thursday_noon);
        addImageFromHuPlaEntryToView(views, thursdayEveningEntry, R.id.image_view_week_widget_thursday_evening);

        // Images for friday
        addImageFromHuPlaEntryToView(views, fridayMorningEntry, R.id.image_view_week_widget_friday_morning);
        addImageFromHuPlaEntryToView(views, fridayNoonEntry, R.id.image_view_week_widget_friday_noon);
        addImageFromHuPlaEntryToView(views, fridayEveningEntry, R.id.image_view_week_widget_friday_evening);

        // Images for saturday
        addImageFromHuPlaEntryToView(views, saturdayMorningEntry, R.id.image_view_week_widget_saturday_morning);
        addImageFromHuPlaEntryToView(views, saturdayNoonEntry, R.id.image_view_week_widget_saturday_noon);
        addImageFromHuPlaEntryToView(views, saturdayEveningEntry, R.id.image_view_week_widget_saturday_evening);

        // Images for sunday
        addImageFromHuPlaEntryToView(views, sundayMorningEntry, R.id.image_view_week_widget_sunday_morning);
        addImageFromHuPlaEntryToView(views, sundayNoonEntry, R.id.image_view_week_widget_sunday_noon);
        addImageFromHuPlaEntryToView(views, sundayEveningEntry, R.id.image_view_week_widget_sunday_evening);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static void addImageFromHuPlaEntryToView(RemoteViews views, HuPlaEntry entry, int imageID) {
        if(entry != null) {
            views.setImageViewResource(imageID, entry.getHuPlaType().getDrawbaleId());
        }else {
            views.setImageViewResource(imageID, HuPlaType.NA.getDrawbaleId());
        }
    }
}


