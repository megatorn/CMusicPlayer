package project.player.music.utils;

import android.util.Log;

import project.player.music.BuildConfig;

/**
 * Created by lijie9 on 2016/11/15.
 */

public class LogTool
{
    private static final String LOG_TAG_BASIC = "cmp_";
    private static final int LOG_BASIC_LENGTH = LOG_TAG_BASIC.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    public static String makeLogTag(String str)
    {
        if(str.length() > MAX_LOG_TAG_LENGTH - LOG_BASIC_LENGTH)
        {
            return LOG_TAG_BASIC + str.substring(0, MAX_LOG_TAG_LENGTH-LOG_BASIC_LENGTH-1);
        }

        return LOG_TAG_BASIC + str;
    }

    public static String makeLogTag(Class cls)
    {
        return makeLogTag(cls.getSimpleName());
    }

    //只打印DEBUG版本信息
    public static void v(String tag, Object...message)
    {
        if(BuildConfig.DEBUG)
        {
            log(tag, Log.VERBOSE, null, message);
        }
    }

    public static void d(String tag, Object...message)
    {
        if(BuildConfig.DEBUG)
        {
            log(tag, Log.DEBUG, null, message);
        }
    }

    public static void i(String tag, Object...message)
    {
        log(tag, Log.INFO, null, message);
    }

    public static void w(String tag, Object...message)
    {
        log(tag, Log.WARN, null, message);
    }

    public static void w(String tag, Throwable t, Object...message)
    {
        log(tag, Log.WARN, t, message);
    }

    public static void e(String tag, Object...message)
    {
        log(tag, Log.ERROR, null, message);
    }

    public static void e(String tag, Throwable t, Object...message)
    {
        log(tag, Log.ERROR, t, message);
    }

    public static void log(String tag, int level, Throwable t, Object...message)
    {
        if(Log.isLoggable(tag, level))
        {
            String tMesg;
            if(t == null && message != null && message.length == 1)
            {
                tMesg = message[0].toString();
            }
            else
            {
                StringBuilder sb = new StringBuilder();
                if(message != null)
                {
                    for(Object m : message)
                    {
                        sb.append(m);
                    }
                }

                if(t != null)
                {
                    sb.append("\n").append(Log.getStackTraceString(t));
                }

                tMesg = sb.toString();
            }

            Log.println(level, tag, tMesg);
        }
    }
}
