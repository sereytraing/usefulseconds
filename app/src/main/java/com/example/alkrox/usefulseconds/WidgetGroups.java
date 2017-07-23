package com.example.alkrox.usefulseconds;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;

/**
 * Created by vincent on 22/07/2017.
 */

public class WidgetGroups extends AppWidgetProvider {

    private static final String MyOnClick = "myOnClickTag";
    public static final String WIDGET_GROUPS = "WidgetGroups";

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        int appwidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,-1);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        updateWidget(context,appWidgetManager,appwidgetId);
        Log.i(WIDGET_GROUPS,"onReceive");

    }

    public static void updateWidget(Context context,
                                    AppWidgetManager appWidgetManager,
                                    int appWidgetId){

        RemoteViews widgetView = new RemoteViews(context.getPackageName(),R.layout.widget_groups_layout);

        Intent refreshIntent = new Intent(context,WidgetGroups.class);
        refreshIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        Intent intent = new Intent(context,WidgetGroupConfigure.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context,appWidgetId,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        widgetView.setOnClickPendingIntent(R.id.category1,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId,widgetView);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(WIDGET_GROUPS,"onUpdate");
        for (int appWidgetId : appWidgetIds){
            updateWidget(context,appWidgetManager,appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i(WIDGET_GROUPS,"onDeleted");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i(WIDGET_GROUPS,"onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i(WIDGET_GROUPS,"onDisabled");
    }
}
