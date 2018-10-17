package com.friendsproducts.kidzzone.namaz_qibla;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.friendsproducts.kidzzone.BuildConfig;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.CONSTANT;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.Preferences;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.Schedule;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.dialog.CalculationSettingsDialog;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.util.LocaleManager;

import java.util.Calendar;


public class Muazzin extends AppCompatActivity {
    // private static final String TAG = Muazzin.class.getSimpleName();
    private static final int REQUEST_ACCESS_FINE_LOCATION = 1001;
    private Preferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = Preferences.getInstance(this);
        LocaleManager.getInstance(this, true);


        setContentView(R.layout.activity_muazzin);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        // Set up the ViewPager, attaching the adapter
        MuazzinAdapter muazzinAdapter = new MuazzinAdapter(getApplicationContext(),
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(muazzinAdapter);
        final SlidingTabLayout indicator = (SlidingTabLayout) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (Utils.isRestartNeeded) {
                restartSelf();
                return;
            }

            if (mPreferences.isLocationSet()) {
                TextView notes = (TextView) findViewById(R.id.notes);
                assert notes != null;
                notes.setText(null);
            }
        }
    }

    /**
     * Restarts the app to apply new settings changes
     */
    private void restartSelf() {
//        Utils.updateWidgets(this);
        Utils.isRestartNeeded = false;

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().getTimeInMillis() + 300,
                PendingIntent.getActivity(this, 0, getIntent(), PendingIntent.FLAG_ONE_SHOT
                        | PendingIntent.FLAG_CANCEL_CURRENT));

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_muazzin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!BuildConfig.DEBUG) {
            menu.removeGroup(R.id.menu_group_controller);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        short time = Schedule.today(this).nextTimeIndex();
        switch (item.getItemId()) {
            case R.id.menu_location_calculation:
                new CalculationSettingsDialog(this).show();
                break;
            case R.id.menu_previous:
                time--;
                if (time < CONSTANT.FAJR) {
                    time = CONSTANT.ISHAA;
                }
                if (CONSTANT.SUNRISE == time && mPreferences.dontNotifySunrise()) {
                    time = CONSTANT.FAJR;
                }
                NotificationService.notify(this, time, Schedule.today(this).getTimes()[time].getTimeInMillis());
                break;
            case R.id.menu_next:
                if (CONSTANT.SUNRISE == time && mPreferences.dontNotifySunrise()) {
                    time = CONSTANT.DHUHR;
                }
                NotificationService.notify(this, time, Schedule.today(this).getTimes()[time].getTimeInMillis());
                break;
            case R.id.menu_stop:
                NotificationService.cancelAll(this);
                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.isRestartNeeded) {
            restartSelf();
            return;
        }
        Utils.setIsForeground(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.setIsForeground(false);
    }
}
