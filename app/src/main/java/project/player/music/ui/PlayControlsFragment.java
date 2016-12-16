package project.player.music.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import project.player.music.R;
import project.player.music.utils.LogTool;

/**
 * Created by lijie9 on 2016/12/16.
 */

public class PlayControlsFragment extends Fragment
{
    private static final String TAG = LogTool.makeLogTag(PlayControlsFragment.class);

    private ImageButton mPlayPause;
    private TextView mTitle;
    private TextView mSubTitle;
    private TextView mExtraInfo;
    private ImageView mAlbumArt;
    private String mArtUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_play_controls, container, false);

        mPlayPause = (ImageButton) rootView.findViewById(R.id.play_pause);
        mPlayPause.setEnabled(true);
        mPlayPause.setOnClickListener(mButtonListener);

        mTitle = (TextView) rootView.findViewById(R.id.title);
        mSubTitle = (TextView) rootView.findViewById(R.id.artist);
        mExtraInfo = (TextView) rootView.findViewById(R.id.extra_info);
        mAlbumArt = (ImageView) rootView.findViewById(R.id.album_art);

        rootView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO
            }
        });

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    View.OnClickListener mButtonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };
}
