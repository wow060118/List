package com.example.yfr.demo.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午3:30 2019/1/3
 * @Modified_By:
 */
public class SharedPreferenceUtil {
    public static SharedPreferenceUtil util;
    public static SharedPreferences sp;
    public static Context mContext;
    // 单例模式
    public static SharedPreferenceUtil getInstance(Context context,String name) {
        if (util == null) {
            util = new SharedPreferenceUtil(context,name);
        }
        return util;
    }

    public SharedPreferenceUtil(Context context,String name) {
        mContext=context.getApplicationContext();

        sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    public void setParam(String key, Object o) {
        String type = o.getClass().getSimpleName();
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) o);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) o);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) o);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) o);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) o);
        }
        boolean success = editor.commit();
        if (!success) {
            editor.apply();
        }

    }
    public  <T> T getParam(String key, T defaultValue) {
        return (T)get(key,defaultValue);
    }

    private static Object get(String key, Object defaultValue) {
        String type = defaultValue.getClass().getSimpleName();

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultValue);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    public static void clearAll() {
        SharedPreferences.Editor editor = sp.edit();
        boolean success = editor.commit();
        if (!success) {
            editor.apply();
        }
    }

    public static void clear(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        boolean success = editor.commit();
        if (!success) {
            editor.apply();
        }
    }

}
