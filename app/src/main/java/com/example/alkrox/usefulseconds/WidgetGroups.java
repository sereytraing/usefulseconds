package com.example.alkrox.usefulseconds;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.List;

/**
 * Created by vincent on 23/07/2017.
 */
public class WidgetGroups extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName widget = new ComponentName(context, WidgetGroups.class);
        int[] widgetsIds = appWidgetManager.getAppWidgetIds(widget);
        for(int widgetId : widgetsIds){
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_groups_layout);
            if(getNumberOfAssoc(context, HomeActivity.CATEGORY1) + getNumberOfAssoc(context, HomeActivity.CATEGORY2) + getNumberOfAssoc(context, HomeActivity.CATEGORY3) != 0){
                views.setOnClickPendingIntent(R.id.category1, this.goToRewardVideo(context, 1, HomeActivity.CATEGORY1));
                views.setOnClickPendingIntent(R.id.category2, this.goToRewardVideo(context, 2, HomeActivity.CATEGORY2));
                views.setOnClickPendingIntent(R.id.category3, this.goToRewardVideo(context, 3, HomeActivity.CATEGORY3));
            }
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }
    public PendingIntent goToRewardVideo(Context context, int statusCode, String category){
        Intent intent = new Intent(context, RewardedVideoActivity.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        intent.putExtra("numberAssociation", getNumberOfAssoc(context, category));
        intent.putExtra("title", category);
        intent.putExtra("isCategoryClicked", true);

        return PendingIntent.getActivity(context, statusCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public int getNumberOfAssoc(Context context, String category) {
        AssociationDatabase associationDatabase = new AssociationDatabase(context);
        List<Association> listeAssociation = associationDatabase.getAssocInCategory(category);
        String tabName[] = new String[listeAssociation.size()];
        for (int i = 0; i < listeAssociation.size(); i++) {
            tabName[i] = listeAssociation.get(i).getName();
        }
        return tabName.length;
    }

}