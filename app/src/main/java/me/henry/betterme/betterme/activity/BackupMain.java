package me.henry.betterme.betterme.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.fragment.GirlFragment;
import me.henry.betterme.betterme.fragment.LoveFragment;
import me.henry.betterme.betterme.fragment.MusicFragment;
import me.henry.betterme.betterme.fragment.NoteFragment;
import me.henry.betterme.betterme.presenter.MainPresenter;
import me.henry.betterme.betterme.view.OptimizeViewpager;

/**
 * Created by zj on 2017/4/28.
 * me.henry.betterme.betterme.activity
 */

public class BackupMain {

    //aaa
//    @BindView(R.id.tabs)
//    TabLayout tabs;
//    @BindView(R.id.viewpager)
//    OptimizeViewpager viewpager;
//
//
//    Toolbar mToolbar;
//    private List<Fragment> mFragments = new ArrayList<>();
//    private MainActivity.MainPagerAdapter mPagerAdapter;
//    private GirlFragment girlFragment;
//    private MusicFragment musicFragment;
//    private NoteFragment noteFragment;
//    private LoveFragment loveFragment;
//    @Override
//    protected MainPresenter initPresenter() {
//        return new MainPresenter(this);
//    }
//
//    @Override
//    protected int getLayout() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    protected void initViewEventDataInCreate() {
//        mToolbar= (Toolbar) findViewById(R.id.mToolbar);
//        mToolbar.setLogo(R.drawable.icon_add);
//        //init tabs
//        List<String> titles = new ArrayList<>();
//        titles.add("Girl");
//        titles.add("Music");
//        titles.add("Note");
//        titles.add("Love");
//        for (int i = 0; i < titles.size(); i++) {
//
//            tabs.addTab(tabs.newTab().setText(titles.get(i)));
//        }
//        //init pager-----------------
//        girlFragment = new GirlFragment(this);
//        musicFragment = new MusicFragment(this);
//        noteFragment = new NoteFragment(this);
//        loveFragment = new LoveFragment();
//        mFragments.add(girlFragment);
//        mFragments.add(musicFragment);
//        mFragments.add(noteFragment);
//        mFragments.add(loveFragment);
//        mPagerAdapter = new MainActivity.MainPagerAdapter(getSupportFragmentManager());
//        viewpager.setAdapter(mPagerAdapter);
//        //关联tabs---------
//        tabs.setupWithViewPager(viewpager);
//        tabs.getTabAt(0).setText(titles.get(0));
//        tabs.getTabAt(1).setText(titles.get(1));
//        tabs.getTabAt(2).setText(titles.get(2));
//        tabs.getTabAt(3).setText(titles.get(3));
//    }
//
//
//    @Override
//    public void showTips() {
//        Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show();
//    }
//
//    class MainPagerAdapter extends FragmentPagerAdapter {
//        public MainPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//    }

}
