package project.player.music.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import project.player.music.R;
import project.player.music.utils.LogTool;

/**
 * Created by lijie9 on 2016/11/15.
 */

public class MusicPlayerActivity extends AppCompatActivity
{
    private static final String TAG = LogTool.makeLogTag(MusicPlayerActivity.class);

    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    private boolean mToolbarInitialized;
    private int mItemToOpenWhenDrawerCloses = -1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LogTool.i(TAG, "Main activity created!");
        setContentView(R.layout.activity_main);

        initializeToolbar();

        initializeFromParams(savedInstanceState, getIntent());
    }

    /**菜单点击处理*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        if(item != null && item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**DrawerLayout的监听事件*/
    private final DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener()
    {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset)
        {
            if(mDrawerToggle != null)
            {
                mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
            }
        }

        @Override
        public void onDrawerOpened(View drawerView)
        {
            if(mDrawerToggle != null)
            {
                mDrawerToggle.onDrawerOpened(drawerView);
            }

            if(getSupportActionBar() != null)
            {
                getSupportActionBar().setTitle(R.string.app_name);
            }
        }

        @Override
        public void onDrawerClosed(View drawerView)
        {
            if(mDrawerToggle != null)
            {
                mDrawerToggle.onDrawerClosed(drawerView);
            }

            //后续添加关闭相应的操作
        }

        @Override
        public void onDrawerStateChanged(int newState)
        {
            if(mDrawerToggle != null)
            {
                mDrawerToggle.onDrawerStateChanged(newState);
            }
        }
    };

    private void initializeToolbar()
    {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolBar == null)
        {
            throw new IllegalStateException("Layout require a toolbar");
        }
        mToolBar.inflateMenu(R.menu.main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        if(mDrawerLayout != null)
        {
            mNavigationView = (NavigationView) findViewById(R.id.nav_view);
            if(mNavigationView == null)
            {
                throw new IllegalStateException("Layout require a NavigationView");
            }

            //新建一个toggle控制drawer的开关
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open_content_drawer, R.string.close_content_drawer);

            mDrawerLayout.setDrawerListener(mDrawerListener);

            populateDrawerItems(mNavigationView);
            setSupportActionBar(mToolBar);
            updataDrawerToggle();
        }
        else
        {
            setSupportActionBar(mToolBar);
        }

        mToolbarInitialized = false;
    }

    private void populateDrawerItems(NavigationView view)
    {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                item.setChecked(true);
                mItemToOpenWhenDrawerCloses = item.getItemId();
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //打开相应的Intent
    }

    private void updataDrawerToggle()
    {
        if(mDrawerToggle == null)
        {
            return;
        }

        boolean isRoot = getFragmentManager().getBackStackEntryCount() == 0;
        mDrawerToggle.setDrawerIndicatorEnabled(isRoot);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(!isRoot);
            getSupportActionBar().setDisplayHomeAsUpEnabled(!isRoot);
            getSupportActionBar().setHomeButtonEnabled(!isRoot);
        }

        if(isRoot)
        {
            //将开关图片和actionBar关联
            mDrawerToggle.syncState();
        }
    }

    private void initializeFromParams(Bundle savedInstanceState, Intent intent)
    {
        String mediaId = null;
        //TODO 从语音检索中开发播放
        if(intent.getAction() != null && intent.getAction().equals(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH))
        {
        }

        navigateToBrowser(mediaId);
    }

    private void navigateToBrowser(String sMedisId)
    {
        LogTool.i(TAG, "navigate browser id: " + sMedisId);
    }
}
