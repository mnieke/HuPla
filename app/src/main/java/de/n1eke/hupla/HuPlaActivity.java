package de.n1eke.hupla;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import de.n1eke.hupla.data.DataHolder;
import de.n1eke.hupla.data.HuPlaEntry;
import de.n1eke.hupla.data.HuPlaEntryDataSource;
import de.n1eke.hupla.data.HuPlaTime;
import de.n1eke.hupla.data.HuPlaType;


public class HuPlaActivity extends Activity implements ViewPager.OnPageChangeListener{


    // TODO Settings, Widget

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private WeekFragment weekFragment;
    private MonthFragment monthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hu_pla);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(this);


        HuPlaEntryDataSource dataSource = new HuPlaEntryDataSource(this);
        dataSource.open();
        DataHolder.getInstance().setEntryList(dataSource.getAllHuPlaEntries());
        dataSource.close();


    }

    @Override
    public void onPause() {
        Intent dayWidgetIntent = new Intent(this, HuPlaDayWidget.class);
        dayWidgetIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int dayWidgetIds[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), HuPlaDayWidget.class));
        dayWidgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, dayWidgetIds);
        sendBroadcast(dayWidgetIntent);

        Intent weekWidgetIntent = new Intent(this, HuPlaWeekWidget.class);
        weekWidgetIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int weekWidgetIds[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), HuPlaWeekWidget.class));
        weekWidgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, weekWidgetIds);
        sendBroadcast(weekWidgetIntent);

        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hu_pla, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                if(weekFragment != null)
                    weekFragment.updateEntries();
            case 1:
                if(monthFragment != null)
                    monthFragment.updateEntries();
                default:
                    if(weekFragment != null)
                        weekFragment.updateEntries();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            boolean onePopupOpened = false;

            if(weekFragment.isPopupOpened()) {
                onePopupOpened = true;
                weekFragment.closePopup();
            }
            if(monthFragment.isPopupOpened()) {
                onePopupOpened = true;
                monthFragment.closePopup();
            }
            if(onePopupOpened) {
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch (position) {
                case 0:
                    weekFragment = WeekFragment.newInstance(position+1);
                    return weekFragment;
                case 1:
                    monthFragment = MonthFragment.newInstance(position + 1);
                    return monthFragment;
                default:
                    weekFragment = WeekFragment.newInstance(position+1);
                    return weekFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

}
