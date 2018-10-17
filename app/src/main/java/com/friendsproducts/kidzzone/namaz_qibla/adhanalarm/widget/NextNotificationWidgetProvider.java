package com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.namaz_qibla.Muazzin;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.Schedule;
import com.friendsproducts.kidzzone.namaz_qibla.adhanalarm.util.LocaleManager;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class NextNotificationWidgetProvider extends AppWidgetProvider {
    private static final int[] times = new int[] { R.string.fajr, R.string.sunrise, R.string.dhuhr,
            R.string.asr, R.string.maghrib, R.string.ishaa, R.string.next_fajr };

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        setNextTime(context, appWidgetManager, appWidgetIds);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public static void setNextTime(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context,
                NextNotificationWidgetProvider.class));
        setNextTime(context, appWidgetManager, appWidgetIds);
    }

    private static void setNextTime(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        LocaleManager lm = LocaleManager.getInstance(context, false);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", lm.getLocale(context));
        if (DateFormat.is24HourFormat(context)) {
            timeFormat = new SimpleDateFormat("k:mm", lm.getLocale(context));
        }
        final int nextTimeIndex = Schedule.today(context).nextTimeIndex();
        final GregorianCalendar nextTime = Schedule.today(context).getTimes()[nextTimeIndex];
        for (int i = 0; i < appWidgetIds.length; i++) {
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_next_notification);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,
                    Muazzin.class), 0);
            views.setOnClickPendingIntent(R.id.widget_next_notification, pendingIntent);

            views.setTextViewText(R.id.time_name, context.getString(times[nextTimeIndex]));
            views.setTextViewText(R.id.next_notification, timeFormat.format(nextTime.getTime()));

            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
        }
    }
}
