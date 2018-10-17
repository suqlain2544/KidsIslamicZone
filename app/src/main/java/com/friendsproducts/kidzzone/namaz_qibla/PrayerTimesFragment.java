package com.friendsproducts.kidzzone.namaz_qibla;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.CONSTANT;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.Preferences;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.Schedule;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.receiver.StartNotificationReceiver;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.util.LocaleManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;


/**
 * Prayer times fragment that displays time table for the day's prayer
 * times. In the future, we may add some extra days...
 */
public class PrayerTimesFragment extends Fragment {
    private final ArrayList<HashMap<String, String>> mTimeTable = new ArrayList<>(7);
    private SimpleAdapter mTimetableView;
    private TextView mNotes;
    private TextView mTodaysDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (short i = CONSTANT.FAJR; i <= CONSTANT.NEXT_FAJR; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("time_name", getString(CONSTANT.TIME_NAMES[i]));
            mTimeTable.add(i, map);
        }
        mTimetableView = new SimpleAdapter(getActivity(), mTimeTable, R.layout.timetable_row,
                new String[] { "time_name", "time" }, new int[] { R.id.time_name, R.id.time });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_today, container, false);
        mNotes = (TextView) rootView.findViewById(R.id.notes);
        try {
            Preferences.getInstance(getActivity()).initCalculationDefaults(getActivity());
        } catch (NullPointerException npe) {
            mNotes.setText(getString(R.string.location_not_set));
        }
        mTodaysDate = (TextView) rootView.findViewById(R.id.today);

        ListView lv = (ListView) rootView.findViewById(R.id.timetable);
        lv.setAdapter(mTimetableView);
        lv.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            // Set zebra stripes
            private int numChildren = 0;

            @Override
            public void onChildViewAdded(View parent, View child) {
                TextView tv;
                if (++numChildren % 2 == 0) {
                    child.setBackgroundResource(R.color.darker_gray);
                    tv = (TextView) child.findViewById(R.id.time_name);
                    tv.setTextColor(0xff000000);
                    tv = (TextView) child.findViewById(R.id.time);
                    tv.setTextColor(0xff000000);
                } else {
                    child.setBackgroundResource(android.R.color.transparent);
                }
                if (numChildren > CONSTANT.NEXT_FAJR) {
                    // Reached the last row, reset for next time
                    numChildren = 0;
                }
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
            }
        });

        final Intent alarmIntent = Utils.getDefaultAlarmsIntent(rootView.getContext());
        if (alarmIntent != null) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(alarmIntent);
                }
            });
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTodaysTimetable();
    }

    private void updateTodaysTimetable() {
        Context context = getActivity();
        LocaleManager localeManager = LocaleManager.getInstance(context, false);
        StartNotificationReceiver.setNext(context);
        Schedule today = Schedule.today(context);
        mTodaysDate.setText(today.hijriDateToString(context));
        GregorianCalendar[] schedule = today.getTimes();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a",
                localeManager.getLocale(context));
        if (DateFormat.is24HourFormat(context)) {
            timeFormat = new SimpleDateFormat("HH:mm ", localeManager.getLocale(context));
        }

        for (short i = CONSTANT.FAJR; i <= CONSTANT.NEXT_FAJR; i++) {
            String fullTime = timeFormat.format(schedule[i].getTime());
            mTimeTable.get(i)
                    .put("time", today.isExtreme(i) ? fullTime.concat(" *") : fullTime);
            if (today.isExtreme(i)) {
                // FIXME: this is getting cleared if
                // Preferences.isLocationSet() is true
                mNotes.setText(R.string.extreme);
            }
        }

        final short next = today.nextTimeIndex();
        mTimeTable.get(next).put(
                "time_name",
                getString(R.string.next_time_marker).concat(
                        getString(CONSTANT.TIME_NAMES[next])));
        mTimetableView.notifyDataSetChanged();
    }
}
