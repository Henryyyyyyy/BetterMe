package me.henry.betterme.betterme.view.customRowView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.view.customRowView.describer.BaseRowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.RowDescriber;

/**
 * Created by zj on 2017/5/16.
 * me.henry.betterme.betterme.view.customRowView
 */

public class RowView extends BaseRowView implements View.OnClickListener {
    public Context mContext;

    public ImageView ivRowLogo;
    public TextView tvRowTitle;
    public ImageView ivRowEntrance;
    public onRowClickListener mListener;
    private RowDescriber mDescriber;
    public RowView(Context context) {
    super(context);
        this.mContext = context;
        initializeView();
    }


    public RowView(Context context, AttributeSet attrs) {
      super(context,attrs);
        this.mContext = context;
        initializeView();
    }

    public RowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initializeView();
    }
    /**
     * 初始化rowview
     */
    private void initializeView() {
        LayoutInflater.from(mContext).inflate(R.layout.widget_row_view, this);
        ivRowEntrance = (ImageView) findViewById(R.id.ivRowEntrance);
        tvRowTitle = (TextView) findViewById(R.id.tvRowTitle);
        ivRowLogo = (ImageView) findViewById(R.id.ivRowLogo);
        setOrientation(HORIZONTAL);
    }

    /**
     * 初始化数据，用discriber来装
     * @param describer
     */
    public void initializeData(BaseRowDescriber describer) {
        this.mDescriber= (RowDescriber) describer;
    }

    /**
     * 要调用这个方法，rowview才会set数据进去
     */
    public void notifyDataChange(){
        ivRowEntrance.setImageResource(mDescriber.entranceRes);
        ivRowLogo.setImageResource(mDescriber.logoRes);
        tvRowTitle.setText(mDescriber.title);
        if (mDescriber.action!=null){
            setOnClickListener(this);
        }
    }

    /**
     * 设置监听
     * @param listener
     */
    public void setOnRowClickListener(onRowClickListener listener){
        this.mListener=listener;
    }
    @Override
    public void onClick(View v) {
        if (mListener!=null){
            mListener.onRowClick(mDescriber.action);
        }
    }
}
