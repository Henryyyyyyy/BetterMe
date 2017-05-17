package me.henry.betterme.betterme.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.adapter.MeiZhiAdapter;
import me.henry.betterme.betterme.common.BaseFragment;
import me.henry.betterme.betterme.model.MeiZhiJsonData;
import me.henry.betterme.betterme.presenter.GirlPresenter;
import me.henry.betterme.betterme.presenter.contracts.GirlContract;

public class GirlFragment extends BaseFragment<GirlContract.View, GirlPresenter> implements GirlContract.View {
    public static final String TAG = "GirlFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private List<MeiZhiJsonData.MeiZhi> datas = new ArrayList<>();
    private MeiZhiAdapter mAdapter;
    private int loadPage = 1;
    private StaggeredGridLayoutManager layoutManager;
    private static final int PRELOAD_SIZE = 10;
    private boolean mIsFirstTimeTouchBottom = true;
    public GirlFragment(Context Context) {
        super(Context);
    }
    @Override
    protected GirlPresenter initPresenter() {
        return new GirlPresenter(getActivity());
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_girl;
    }
    @Override
    protected void initViewEventDataInCreate() {
        mAdapter = new MeiZhiAdapter(mContext, datas, R.layout.item_meizhi);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(mAdapter);
        initRefreshEvents();

    }
    private void initRefreshEvents() {
        swipeRefresh.setColorSchemeResources(R.color.refresh_progress_3, R.color.refresh_progress_2, R.color.refresh_progress_1);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG,"onRefresh");
                //  mPresenter.startRefreshData(1);
                swipeRefresh.setRefreshing(false);
            }
        });
        rv.addOnScrollListener(getOnBottomListener(layoutManager));
    }

    RecyclerView.OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager manager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom = manager.findLastCompletelyVisibleItemPositions(new int[2])[1] >= mAdapter.getItemCount() - PRELOAD_SIZE;
                if (!swipeRefresh.isRefreshing() && isBottom) {
                    Log.e(TAG,"onScrolled111，(!swipeRefresh.isRefreshing() && isBottom)="+(!swipeRefresh.isRefreshing() && isBottom));
                    if (!mIsFirstTimeTouchBottom) {
                        Log.e(TAG,"onScrolled111，mIsFirstTimeTouchBottom=false");
                        swipeRefresh.setRefreshing(true);
                        loadPage += 1;
                        mPresenter.startRefreshData(loadPage);
                    } else {
                        Log.e(TAG,"onScrolled111，mIsFirstTimeTouchBottom=true");
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    //初始化fragment的时候加载数据
    @Override
    protected void firstLoadData() {
        mPresenter.startRefreshData(loadPage);
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void refreshError() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void refreshFinished(List<MeiZhiJsonData.MeiZhi> datas) {
        mAdapter.addDataSet(datas);
        swipeRefresh.postDelayed(new Runnable() {
            @Override public void run() {
                if (swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }
        }, 800);

    }

}
