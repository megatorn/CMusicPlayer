package project.player.music.utils;

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

        }
    }

    public static void log()
    {

    }
}
