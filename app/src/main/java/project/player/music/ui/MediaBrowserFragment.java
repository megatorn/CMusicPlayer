package project.player.music.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.player.music.R;
import project.player.music.utils.LogTool;

/**
 * Created by lijie9 on 2016/12/23.
 */

public class MediaBrowserFragment extends Fragment
{
    private static final String TAG = LogTool.makeLogTag(MediaBrowserFragment.class);
    private static final String ARG_MEDIA_ID = "media_id";

    private View mErrorMsgView;
    private TextView mErrorMsg;

    private String mMediaId;

    private BrowseAdapter mBrowserAdapter;

    private MediaFragmentListener mMediaFragmentListener;

    private static class BrowseAdapter extends ArrayAdapter<MediaBrowserCompat.MediaItem>
    {
        public BrowseAdapter(Activity context)
        {
            super(context, R.layout.media_list_item, new ArrayList<MediaBrowserCompat.MediaItem>());
        }
    }

    private final MediaControllerCompat.Callback mMediaControllerCallback = new MediaControllerCompat.Callback()
    {
        @Override
        public void onMetadataChanged(MediaMetadataCompat metadata)
        {
            super.onMetadataChanged(metadata);
        }

        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state)
        {
            super.onPlaybackStateChanged(state);
        }
    };

    private final MediaBrowserCompat.SubscriptionCallback mSubscriptionCallback = new MediaBrowserCompat.SubscriptionCallback()
    {
        @Override
        public void onChildrenLoaded(@NonNull String parentId, List<MediaBrowserCompat.MediaItem> children)
        {
            super.onChildrenLoaded(parentId, children);
        }

        @Override
        public void onError(@NonNull String parentId)
        {
            super.onError(parentId);
        }
    };

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        //如果被加载到了不继承MediaFragmentListener的acvitivy，会抛出异常
        mMediaFragmentListener = (MediaFragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LogTool.i(TAG, "fragment onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        mErrorMsgView = rootView.findViewById(R.id.play_error);
        mErrorMsg = (TextView) rootView.findViewById(R.id.error_message);

        mBrowserAdapter = new BrowseAdapter(getActivity());

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(mBrowserAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }
        });

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //填充listview内容
        MediaBrowserCompat mediaBrowser = mMediaFragmentListener.getMediaBrowser();

        LogTool.i(TAG, "fragment.onStart, mediaId=", mMediaId, " onConnected=", mediaBrowser.isConnected());

        if(mediaBrowser.isConnected())
        {
            onConnected();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    public String getMediaId()
    {
        Bundle args = getArguments();
        if(args == null)
        {
            return args.getString(ARG_MEDIA_ID);
        }

        return null;
    }

    public void setMediaId(String mediaID)
    {
        Bundle args = new Bundle(1);
        args.putString(MediaBrowserFragment.ARG_MEDIA_ID, mediaID);
        setArguments(args);
    }

    private void onConnected()
    {
        if(isDetached())
        {
            return;
        }

        mMediaId = getMediaId();
        if(mMediaId == null)
        {
            mMediaId = mMediaFragmentListener.getMediaBrowser().getRoot();
        }
        updateTitle();

        //TODO 不知道干啥
        mMediaFragmentListener.getMediaBrowser().unsubscribe(mMediaId);

        mMediaFragmentListener.getMediaBrowser().subscribe(mMediaId, mSubscriptionCallback);

        MediaControllerCompat controller  = ((FragmentActivity) getActivity()).getSupportMediaController();
        if(controller != null)
        {
            controller.registerCallback(mMediaControllerCallback);
        }
    }

    private void updateTitle()
    {

    }

    public interface MediaFragmentListener extends MediaBrowserProvider
    {
        void onMediaItemSelected(MediaBrowserCompat.MediaItem item);
        void setToolbarTitle(CharSequence title);
    }
}
