package me.henry.betterme.betterme.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.common.BaseActivity;
import me.henry.betterme.betterme.common.MyConstants;
import me.henry.betterme.betterme.fragment.GirlFragment;
import me.henry.betterme.betterme.fragment.MusicFragment;
import me.henry.betterme.betterme.fragment.NoteFragment;
import me.henry.betterme.betterme.model.MusicInfo;
import me.henry.betterme.betterme.presenter.MainPresenter;
import me.henry.betterme.betterme.presenter.contracts.MainContract;
import me.henry.betterme.betterme.service.MusicPlayer;
import me.henry.betterme.betterme.utils.MusicUtil;
import me.henry.betterme.betterme.view.OptimizeViewpager;
import me.henry.betterme.betterme.view.customRowView.ContainerView;
import me.henry.betterme.betterme.view.customRowView.RowActionEnum;
import me.henry.betterme.betterme.view.customRowView.describer.BaseRowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.GroupDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.RowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.RowProfileDescriber;
import me.henry.betterme.betterme.view.customRowView.onRowClickListener;
import xyz.matteobattilana.library.Common.Constants;
import xyz.matteobattilana.library.WeatherView;


//waitzj这里写太多东西了
public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements MainContract.View, View.OnClickListener {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    OptimizeViewpager viewpager;
    @BindView(R.id.rlMusicPanel)
    RelativeLayout rlMusicPanel;
    @BindView(R.id.ivCurMusicCover)
    ImageView ivCurMusicCover;
    @BindView(R.id.tvCurMusicName)
    TextView tvCurMusicName;
    @BindView(R.id.tvCurMusicSinger)
    TextView tvCurMusicSinger;
    @BindView(R.id.ivNext)
    ImageView ivNext;
    @BindView(R.id.ivPrevious)
    ImageView ivPrevious;
    @BindView(R.id.ivPlayOrPause)
    ImageView ivPlayOrPause;
    @BindView(R.id.ivMusicMode)
    ImageView ivMusicMode;
    @BindView(R.id.mContainerView)
    ContainerView mContainerView;
    @BindView(R.id.weather)
    WeatherView mWeatherView;

    private BroadcastReceiver mMusicBro;
    Toolbar mToolbar;
    private List<Fragment> mFragments = new ArrayList<>();
    private MainPagerAdapter mPagerAdapter;
    private GirlFragment girlFragment;
    private MusicFragment musicFragment;
    private NoteFragment noteFragment;
    private MusicInfo currentMusicInfo;


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewEventDataInCreate() {
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mToolbar.setLogo(R.drawable.icon_add);
        //init weatherView
        //Optional
        mWeatherView.setWeather(Constants.weatherStatus.RAIN)
                .setCurrentLifeTime(6000)
                .setCurrentFadeOutTime(4000)
                .setCurrentParticles(8)
                .setFPS(80)
                .setRainAngle(1)
                 .setSnowAngle(1)
                .setOrientationMode(Constants.orientationStatus.ENABLE)
                .startAnimation();
        //init tabs
        List<String> titles = new ArrayList<>();
        titles.add("World");
        titles.add("Music");
        titles.add("Channel");
        for (int i = 0; i < titles.size(); i++) {
            tabs.addTab(tabs.newTab().setText(titles.get(i)));
        }

        //init pager-----------------
        girlFragment = new GirlFragment(this);
        musicFragment = new MusicFragment(this);
        noteFragment = new NoteFragment(this);
        mFragments.add(girlFragment);
        mFragments.add(musicFragment);
        mFragments.add(noteFragment);
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mPagerAdapter);
        //关联tabs---------
        tabs.setupWithViewPager(viewpager);
        tabs.getTabAt(0).setText(titles.get(0));
        tabs.getTabAt(1).setText(titles.get(1));
        tabs.getTabAt(2).setText(titles.get(2));
        ivNext.setOnClickListener(this);
        ivPlayOrPause.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivMusicMode.setOnClickListener(this);
        initBrocast();
        freshMusicPanel(MusicPlayer.getCurrentMusicInfo());
        //自定义ContainerView
        ArrayList<BaseRowDescriber> rowDiscribers1 = new ArrayList<>();
        rowDiscribers1.add(new RowDescriber(R.drawable.ic_launcher, "草草", R.drawable.ic_launcher, RowActionEnum.MY_FIRST));
        rowDiscribers1.add(new RowDescriber(R.drawable.ic_launcher, "妮妮", R.drawable.ic_launcher, RowActionEnum.MY_SECOND));
        ArrayList<BaseRowDescriber> rowDiscribers2 = new ArrayList<>();
        rowDiscribers2.add(new RowProfileDescriber(R.drawable.empty_song, "WODE TIAN", R.drawable.ic_launcher, RowActionEnum.THIRD));
        mContainerView.add(new GroupDescriber(rowDiscribers1));
        mContainerView.add(new GroupDescriber(rowDiscribers2));
        mContainerView.setOnRowClickListener(new onRowClickListener() {
            @Override
            public void onRowClick(RowActionEnum action) {
                if (action.equals(RowActionEnum.MY_FIRST)) {
                    Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                } else if (action.equals(RowActionEnum.MY_SECOND)) {
                    Toast.makeText(MainActivity.this, "haha22222", Toast.LENGTH_SHORT).show();
                } else if (action.equals(RowActionEnum.THIRD)) {
                    Toast.makeText(MainActivity.this, "haha333", Toast.LENGTH_SHORT).show();

                }
            }
        });
        mContainerView.notifyDataChange();

    }

    private void initBrocast() {
        mMusicBro = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(MyConstants.Action_updateMusicInfo)) {
                    currentMusicInfo = intent.getParcelableExtra("music");
                    freshMusicPanel(currentMusicInfo);
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyConstants.Action_updateMusicInfo);
        registerReceiver(mMusicBro, filter);
    }

    private void freshMusicPanel(MusicInfo info) {
        if (info != null) {
            Log.e("suck", "currentMusicInfo=" + info.toString());
            tvCurMusicName.setText(info.musicName);
            tvCurMusicSinger.setText(info.artist);
            Glide.with(this)
                    .load(MusicUtil.getAlbumArtUri(info.albumId))
                    .placeholder(R.drawable.stayaway)
                    .into(ivCurMusicCover);
        }
    }

    @Override
    public void showTips() {

        Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivNext:
                MusicPlayer.next();
                break;
            case R.id.ivPrevious:
                MusicPlayer.previous();
                break;
            case R.id.ivPlayOrPause:
                MusicPlayer.PlayOrPause();
                break;
            case R.id.ivMusicMode:
                setPlayingMode();
                break;

        }
    }

    //模式点击顺序:顺序播放，随机播放，循环播放
    private int modeIndex = 1;

    private void setPlayingMode() {
        if (modeIndex == 1) {
            modeIndex = modeIndex+1;
            ivMusicMode.setImageResource(R.drawable.play_icn_shuffle);
            MusicPlayer.setPlayMode(MyConstants.PlayMode_Shuffle);
            return;
        }
        else if (modeIndex == 2) {
            modeIndex = modeIndex+1;
            ivMusicMode.setImageResource(R.drawable.play_icn_one);
            MusicPlayer.setPlayMode(MyConstants.PlayMode_OnlyOne);
            return;
        }
        else if (modeIndex == 3) {
            modeIndex = 1;
            ivMusicMode.setImageResource(R.drawable.play_icn_loop);
            MusicPlayer.setPlayMode(MyConstants.PlayMode_Loop);
            return;
        }
    }

    class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMusicBro);
    }
}
