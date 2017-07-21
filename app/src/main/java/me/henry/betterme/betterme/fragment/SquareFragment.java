package me.henry.betterme.betterme.fragment;

import android.content.Context;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.common.BaseFragment;
import me.henry.betterme.betterme.presenter.SquarePresenter;
import me.henry.betterme.betterme.presenter.contracts.SquareContract;
import me.henry.betterme.betterme.utils.MyImageLoader;




public class SquareFragment extends BaseFragment<SquareContract.View, SquarePresenter> {
    public SquareFragment(Context Context) {
        super(Context);
    }
    @BindView(R.id.banner)
    Banner banner;


    @Override
    protected int getLayout() {
        return R.layout.fragment_square;
    }
    @Override
    protected SquarePresenter initPresenter() {
        return new SquarePresenter(getActivity());
    }
    @Override
    protected void initViewEventDataInCreate() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner11);
        images.add(R.drawable.banner22);
        images.add(R.drawable.banner33);
        //简单使用
        banner.setImages(images)
                .setImageLoader(new MyImageLoader())
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .start();
    }
    @Override
    protected void firstLoadData() {

    }
}
