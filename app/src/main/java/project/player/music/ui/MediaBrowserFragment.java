package project.player.music.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import project.player.music.R;
import project.player.music.utils.LogTool;

/**
 * Created by lijie9 on 2016/12/23.
 */

public class MediaBrowserFragment extends Fragment
{
    private static final String TAG = LogTool.makeLogTag(MediaBrowserFragment.class);

    private View mErrorMsgView;
    private TextView mErrorMsg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LogTool.i(TAG, "fragment onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        mErrorMsgView = rootView.findViewById(R.id.play_error);
        mErrorMsg = (TextView) rootView.findViewById(R.id.error_message);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        return rootView;
    }

    private static class BrowseAdapter extends ArrayAdapter<MediaBrowserCompat.MediaItem>
    {
        public BrowseAdapter(Activity context)
        {
            super(context, R.layout.media_list_item, new ArrayList<MediaBrowserCompat.MediaItem>());
        }
    }
}
