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
import me.henry.betterme.betterme.common.Constants;
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
import me.henry.betterme.betterme.view.customRowView.describer.BaseRowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.GroupDescriber;
import me.henry.betterme.betterme.view.customRowView.RowActionEnum;
import me.henry.betterme.betterme.view.customRowView.describer.RowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.RowProfileDescriber;
import me.henry.betterme.betterme.view.customRowView.onRowClickListener;


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
    @BindView(R.id.mContainerView)
    ContainerView mContainerView;

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
        initBrocast();

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
                Log.e("exome","onRowClick");
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
                if (intent.getAction().equals(Constants.Action_updateMusicInfo)) {
                    currentMusicInfo = intent.getParcelableExtra("music");
                    freshMusicPanel();
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.Action_updateMusicInfo);
        registerReceiver(mMusicBro, filter);
    }

    private void freshMusicPanel() {
        if (currentMusicInfo != null) {
            tvCurMusicName.setText(currentMusicInfo.musicName);
            tvCurMusicSinger.setText(currentMusicInfo.artist);
            Glide.with(this)
                    .load(MusicUtil.getAlbumArtUri(currentMusicInfo.albumId))
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
