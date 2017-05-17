//package me.henry.betterme.betterme.view.refreshlayout;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
//
//import me.henry.betterme.betterme.R;
//import me.henry.betterme.betterme.utils.DensityUtil;
//
///**
// * Created by zj on 2017/3/30.
// * me.henry.betterme.betterme.view.refreshlayout
// */
//
//public class SpreadiRefreshLayout extends RelativeLayout implements PullListener{
//    //波浪的高度,最大扩展高度
//    protected float mMaxHeadHeight;
//    protected float mMaxBottomHeight;
//
//
//
//    //允许的越界回弹的高度
//    protected float mOverScrollHeight;
//
//    //子控件
//    private View mChildView;
//
//    //头部layout
//    protected FrameLayout mHeadLayout;
//    //整个头部
//    private FrameLayout mExtraHeadLayout;
//    //头部的高度
//    protected float mHeadHeight;
//    //附加顶部高度
//    private int mExHeadHeight = 0;
//
//    //底部layout
//    private FrameLayout mBottomLayout;
//    //底部高度
//    private float mBottomHeight;
////顶部刷新view
//    private IHeaderView mHeadView;
//    //底部刷新view
//    private IBottomView mBottomView;
//
//    //是否刷新视图可见
//    protected boolean isRefreshVisible = false;
//
//    //是否加载更多视图可见
//    protected boolean isLoadingVisible = false;
//
//    //是否需要加载更多,默认需要
//    protected boolean enableLoadmore = true;
//    //是否需要下拉刷新,默认需要
//    protected boolean enableRefresh = true;
//    //是否在越界回弹的时候显示下拉图标
//    protected boolean isOverScrollTopShow = true;
//    //是否在越界回弹的时候显示上拉图标
//    protected boolean isOverScrollBottomShow = true;
//
//    //是否隐藏刷新控件,开启越界回弹模式(开启之后刷新控件将隐藏)
//    protected boolean isPureScrollModeOn = false;
//
//    //是否自动加载更多
//    protected boolean autoLoadMore = false;
//
//    //是否开启悬浮刷新模式
//    protected boolean floatRefresh = false;
//
//    //是否允许进入越界回弹模式
//    protected boolean enableOverScroll = true;
//
//    private CoContext cp;
//    private int mTouchSlop;
//    public SpreadiRefreshLayout(Context context) {
//        this(context, null, 0);
//    }
//
//    public SpreadiRefreshLayout(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SpreadiRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpreadiRefreshLayout, defStyleAttr, 0);
//        try {
//            mMaxHeadHeight = a.getDimensionPixelSize(R.styleable.SpreadiRefreshLayout_sr_max_head_height, (int) DensityUtil.dp2px(context, 120));
//            mHeadHeight = a.getDimensionPixelSize(R.styleable.SpreadiRefreshLayout_sr_head_height, (int) DensityUtil.dp2px(context, 80));
//            mMaxBottomHeight = a.getDimensionPixelSize(R.styleable.SpreadiRefreshLayout_sr_max_bottom_height, (int) DensityUtil.dp2px(context, 120));
//            mBottomHeight = a.getDimensionPixelSize(R.styleable.SpreadiRefreshLayout_sr_bottom_height, (int) DensityUtil.dp2px(context, 60));
//            mOverScrollHeight = a.getDimensionPixelSize(R.styleable.SpreadiRefreshLayout_sr_overscroll_height, (int) mHeadHeight);
//            enableLoadmore = a.getBoolean(R.styleable.SpreadiRefreshLayout_sr_enable_loadmore, true);
//            isPureScrollModeOn = a.getBoolean(R.styleable.SpreadiRefreshLayout_sr_pureScrollMode_on, false);
//            isOverScrollTopShow = a.getBoolean(R.styleable.SpreadiRefreshLayout_sr_overscroll_top_show, true);
//            isOverScrollBottomShow = a.getBoolean(R.styleable.SpreadiRefreshLayout_sr_overscroll_bottom_show, true);
//            enableOverScroll = a.getBoolean(R.styleable.SpreadiRefreshLayout_sr_enable_overscroll, true);
//        } finally {
//            a.recycle();
//        }
//        //超过这个移动距离才开始触发控件
//        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
//        cp = new CoContext();
//        addHeader();
//        addFooter();
//        setPullListener(this);
//    }
//
//
//    //设置拖动屏幕的监听器
//    private PullListener pullListener;
//
//    private void setPullListener(PullListener pullListener) {
//        this.pullListener = pullListener;
//    }
//
//
//
//    @Override
//    public void onPullingDown(SpreadiRefreshLayout refreshLayout, float fraction) {
//
//    }
//
//    @Override
//    public void onPullingUp(SpreadiRefreshLayout refreshLayout, float fraction) {
//    }
//
//    @Override
//    public void onPullDownReleasing(SpreadiRefreshLayout refreshLayout, float fraction) {
//
//    }
//
//    @Override
//    public void onPullUpReleasing(SpreadiRefreshLayout refreshLayout, float fraction) {
//
//    }
//
//    @Override
//    public void onRefresh(SpreadiRefreshLayout refreshLayout) {
//
//    }
//
//    @Override
//    public void onLoadMore(SpreadiRefreshLayout refreshLayout) {
//
//    }
//
//    @Override
//    public void onFinishRefresh() {
//
//    }
//
//    @Override
//    public void onFinishLoadMore() {
//
//    }
//
//    @Override
//    public void onRefreshCanceled() {
//
//    }
//
//    @Override
//    public void onLoadmoreCanceled() {
//
//    }
//    public class CoContext {
//        private AnimProcessor animProcessor;
//
//        private final static int PULLING_TOP_DOWN = 0;
//        private final static int PULLING_BOTTOM_UP = 1;
//        private int state = PULLING_TOP_DOWN;
//
//        private static final int EX_MODE_NORMAL = 0;
//        private static final int EX_MODE_FIXED = 1;
//        private int exHeadMode = EX_MODE_NORMAL;
//
//
//        public CoContext() {
//            animProcessor = new AnimProcessor(this);
//        }
//
//        public void init() {
//            if (isPureScrollModeOn) {
//                setOverScrollTopShow(false);
//                setOverScrollBottomShow(false);
//                if (mHeadLayout != null) mHeadLayout.setVisibility(GONE);
//                if (mBottomLayout != null) mBottomLayout.setVisibility(GONE);
//            }
//        }
//
//        public AnimProcessor getAnimProcessor() {
//            return animProcessor;
//        }
//
//        public float getMaxHeadHeight() {
//            return mMaxHeadHeight;
//        }
//
//        public int getHeadHeight() {
//            return (int) mHeadHeight;
//        }
//
//        public int getExtraHeadHeight() {
//            return mExtraHeadLayout.getHeight();
//        }
//
//        public int getMaxBottomHeight(){
//            return (int) mMaxBottomHeight;
//        }
//        public int getBottomHeight() {
//            return (int) mBottomHeight;
//        }
//
//        public int getOsHeight() {
//            return (int) mOverScrollHeight;
//        }
//
//        public View getTargetView() {
//            return mChildView;
//        }
//
//        public View getHeader() {
//            return mHeadLayout;
//        }
//
//        public View getFooter() {
//            return mBottomLayout;
//        }
//
//        public int getTouchSlop() {
//            return mTouchSlop;
//        }
//
//        public void resetHeaderView() {
//            if (mHeadView != null) mHeadView.reset();
//        }
//
//        public void resetBottomView() {
//            if (mBottomView != null) mBottomView.reset();
//        }
//
//        public View getExHead() {
//            return mExtraHeadLayout;
//        }
//
//        public void setExHeadNormal() {
//            exHeadMode = EX_MODE_NORMAL;
//        }
//
//        public void setExHeadFixed() {
//            exHeadMode = EX_MODE_FIXED;
//        }
//
//        public boolean isExHeadNormal() {
//            return exHeadMode == EX_MODE_NORMAL;
//        }
//
//        public boolean isExHeadFixed() {
//            return exHeadMode == EX_MODE_FIXED;
//        }
//
//        /**
//         * 在添加附加Header前锁住，阻止一些额外的位移动画
//         */
//        private boolean isExHeadLocked = true;
//
//        public boolean isExHeadLocked() {
//            return isExHeadLocked;
//        }
//
//        //添加了额外头部时触发
//        public void onAddExHead() {
//            isExHeadLocked = false;
//            LayoutParams params = (LayoutParams) mChildView.getLayoutParams();
//            params.addRule(BELOW, mExtraHeadLayout.getId());
//            mChildView.setLayoutParams(params);
//            requestLayout();
//        }
//
//
//        /**
//         * 主动刷新、加载更多、结束
//         */
//        public void startRefresh() {
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    setStatePTD();
//                    if (!isPureScrollModeOn && mChildView != null) {
//                        setRefreshing(true);
//                        animProcessor.animHeadToRefresh();
//                    }
//                }
//            });
//        }
//
//        public void startLoadMore() {
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    setStatePBU();
//                    if (!isPureScrollModeOn && mChildView != null) {
//                        setLoadingMore(true);
//                        animProcessor.animBottomToLoad();
//                    }
//                }
//            });
//        }
//
//        public void finishRefreshing() {
//            onFinishRefresh();
//        }
//
//        public void finishRefreshAfterAnim() {
//            if (isRefreshVisible() && mChildView != null) {
//                setRefreshing(false);
//                animProcessor.animHeadBack();
//            }
//        }
//
//        public void finishLoadmore() {
//            onFinishLoadMore();
//            if (isLoadingVisible() && mChildView != null) {
//                setLoadingMore(false);
//                animProcessor.animBottomBack();
//            }
//        }
//
//        //TODO 支持分别设置头部或者顶部允许越界
//        //private boolean enableOverScrollTop = false, enableOverScrollBottom = false;
//
//        public boolean enableOverScroll() {
//            return enableOverScroll;
//        }
//
//        public boolean allowPullDown() {
//            return enableRefresh || enableOverScroll;
//        }
//
//        public boolean allowPullUp() {
//            return enableLoadmore || enableOverScroll;
//        }
//
//        public boolean enableRefresh(){
//            return enableRefresh;
//        }
//
//        public boolean enableLoadmore(){
//            return enableLoadmore;
//        }
//
//        public boolean allowOverScroll() {
//            return (!isRefreshVisible && !isLoadingVisible);
//        }
//
//        public boolean isRefreshVisible() {
//            return isRefreshVisible;
//        }
//
//        public boolean isLoadingVisible() {
//            return isLoadingVisible;
//        }
//
//        public void setRefreshing(boolean refreshing) {
//            isRefreshVisible = refreshing;
//        }
//
//        public void setLoadingMore(boolean loadingMore) {
//            isLoadingVisible = loadingMore;
//        }
//
//        public boolean isOpenFloatRefresh() {
//            return floatRefresh;
//        }
//
//        public boolean autoLoadMore() {
//            return autoLoadMore;
//        }
//
//        public boolean isPureScrollModeOn() {
//            return isPureScrollModeOn;
//        }
//
//        public boolean isOverScrollTopShow() {
//            return isOverScrollTopShow;
//        }
//
//        public boolean isOverScrollBottomShow() {
//            return isOverScrollBottomShow;
//        }
//
//        public void onPullingDown(float offsetY) {
//            pullListener.onPullingDown(SpreadiRefreshLayout.this, offsetY / mHeadHeight);
//        }
//
//        public void onPullingUp(float offsetY) {
//            pullListener.onPullingUp(SpreadiRefreshLayout.this, offsetY / mBottomHeight);
//        }
//
//        public void onRefresh() {
//            pullListener.onRefresh(SpreadiRefreshLayout.this);
//        }
//
//        public void onLoadMore() {
//            pullListener.onLoadMore(SpreadiRefreshLayout.this);
//        }
//
//        public void onFinishRefresh() {
//            pullListener.onFinishRefresh();
//        }
//
//        public void onFinishLoadMore() {
//            pullListener.onFinishLoadMore();
//        }
//
//        public void onPullDownReleasing(float offsetY) {
//            pullListener.onPullDownReleasing(SpreadiRefreshLayout.this, offsetY / mHeadHeight);
//        }
//
//        public void onPullUpReleasing(float offsetY) {
//            pullListener.onPullUpReleasing(SpreadiRefreshLayout.this, offsetY / mBottomHeight);
//        }
//
//        public void onRefreshCanceled() {
//            pullListener.onRefreshCanceled();
//        }
//
//        public void onLoadmoreCanceled() {
//            pullListener.onLoadmoreCanceled();
//        }
//
//        public void setStatePTD() {
//            state = PULLING_TOP_DOWN;
//        }
//
//        public void setStatePBU() {
//            state = PULLING_BOTTOM_UP;
//        }
//
//        public boolean isStatePTD() {
//            return PULLING_TOP_DOWN == state;
//        }
//
//        public boolean isStatePBU() {
//            return PULLING_BOTTOM_UP == state;
//        }
//    }
//}
