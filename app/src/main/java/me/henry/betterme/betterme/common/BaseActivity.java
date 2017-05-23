package me.henry.betterme.betterme.common;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.henry.betterme.betterme.app.BetterMeApplication;

/**
 * Created by Henry on 2016/12/21.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    public String TAG ;
    protected Unbinder unbinder;
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (isTranslucentStatusBar()) {
//                Window window = getWindow();

//                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//        }

        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
        BetterMeApplication.getInstance().addActivity(this);
        initViewEventDataInCreate();

    }
    private boolean isTranslucentStatusBar() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        TAG = getComponentName().getShortClassName();
        Log.d(TAG, "mView...=" + "onResume");
        initInResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mPresenter.dettach();
        BetterMeApplication.getInstance().removeActivity(this);
    }

    protected abstract T initPresenter();

    protected abstract int getLayout();

    protected abstract void initViewEventDataInCreate();


    /**
     * 用于在onresume拓展
     */
    protected void initInResume() {

    }
}
