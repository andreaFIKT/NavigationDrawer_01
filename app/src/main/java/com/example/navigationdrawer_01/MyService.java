package com.example.navigationdrawer_01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Inellipse10 on 09/09/2016.
 */
public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        try {
            mTimer.cancel();
//            timerTask.cancel();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Intent intent = new Intent("com.example.navigationdrawer_01");
        intent.putExtra("yourValue","toRestore");
        sendBroadcast(intent);

        super.onDestroy();
        Toast.makeText(this,"Service stopped",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        mTimer = new Timer();
//        notifiy();
        /*mTimer.schedule(timerTask,5000,5*1000);*/
    }
    private Timer mTimer;
   /* TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.e("Log","Running");

        }
    };*/

  /* public  void notifiy(){
        IntentFilter  intentFilter = new IntentFilter();
        intentFilter.addAction("Action");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),0,myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("New Message")
                .setContentText("You have reminder")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.reminder_icon)
                .setDefaults(Notification.DEFAULT_SOUND);


        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);

    }*/

}
