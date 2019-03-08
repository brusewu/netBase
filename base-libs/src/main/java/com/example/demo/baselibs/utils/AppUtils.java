package com.example.demo.baselibs.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by wuxiaolong on 2019/3/8.
 * the tool class
 */

public class AppUtils {

    /**
     * get the version
     */
    public static int getVersionCode(Context context){
        int versionCode = 0;
        try{
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        }catch (PackageManager.NameNotFoundException ex){
            versionCode = 0;
        }
        return versionCode;
    }

    /**
     * get the package name
     */
    public static int getVersionCode(Context context,String packageName){
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(packageName,0).versionCode;
        }catch (PackageManager.NameNotFoundException ex){
            versionCode = 0;
        }
        return versionCode;
    }

    /**
     * get the version name
     */
    public static String getVersionName(Context context){
        String name = "";
        try {
            name = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        }catch (PackageManager.NameNotFoundException ex){
            name = "";
        }
        return name;
    }

    /**
     * get the channel information
     */
    public static String getAppChanel(Context context,String channel){
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(),PackageManager.GET_META_DATA
            );
            if (appInfo.metaData != null){
                for (String key : appInfo.metaData.keySet()){
                    if (key.equals(channel)){
                        return appInfo.metaData.get(key).toString();
                    }
                }
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * get the app max memory
     */
    public static long getMaxMemory(){
        return Runtime.getRuntime().maxMemory() / 1024;
    }

    /**
     * check the service whether working
     */
    public static boolean isServiceRunning(Context context,String className){
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : servicesList){
            if (className.equals(si.service.getClassName())){
                isRunning = true;
            }

        }
        return isRunning;
    }

    /**
     * turn on the service
     */
    public static void startService(Context context,Class<?> cls){
        Intent intentService = null;
        try {
            intentService = new Intent(context,cls);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (intentService != null){
            context.startService(intentService);
        }
    }

    /***
     * turn off the service
     */
    public static boolean stopService(Context context, Class<?> cls){
        Intent intentService = null;
        boolean ret = false;
        try {
            intentService = new Intent(context,cls);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (intentService != null){
            ret = context.stopService(intentService);
        }

        return ret;
    }
}
