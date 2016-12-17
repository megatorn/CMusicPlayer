package project.player.music.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import project.player.music.R;
import project.player.music.utils.LogTool;

/**
 * Created by lijie9 on 2016/11/15.
 */

public class MusicPlayerActivity extends AppCompatActivity
{
    private static final String TAG = LogTool.makeLogTag(MusicPlayerActivity.class);

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LogTool.i(TAG, "Main activity created!");
        setContentView(R.layout.activity_main);
    }
}
