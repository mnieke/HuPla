package de.n1eke.hupla;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.provider.ContactsContract;
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
public class HuPlaDayWidget extends AppWidgetProvider {

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
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.hu_pla_day_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        GregorianCalendar calendar = new GregorianCalendar();
        DataHolder dataHolder = DataHolder.getInstance();
        HuPlaEntryDataSource dataSource = new HuPlaEntryDataSource(context);
        dataSource.open();
        dataHolder.setEntryList(dataSource.getAllHuPlaEntries());

        HuPlaEntry morningEntry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.MORNING);
        HuPlaEntry noonEntry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.NOON);
        HuPlaEntry eveningEntry = dataHolder.findHuPlaEntryByCalendar(calendar, HuPlaTime.EVENING);

        views.setTextViewText(R.id.text_view_day_widget_date, "Hundeplan for: "+calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR));

        if(morningEntry != null) {
            views.setImageViewResource(R.id.image_view_day_widget_morning, morningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_day_widget_morning, HuPlaType.NA.getDrawbaleId());
        }

        if(noonEntry != null) {
            views.setImageViewResource(R.id.image_view_day_widget_noon, noonEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_day_widget_noon, HuPlaType.NA.getDrawbaleId());
        }

        if(eveningEntry != null) {
            views.setImageViewResource(R.id.image_view_day_widget_evening, eveningEntry.getHuPlaType().getDrawbaleId());
        } else {
            views.setImageViewResource(R.id.image_view_day_widget_evening, HuPlaType.NA.getDrawbaleId());
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}


