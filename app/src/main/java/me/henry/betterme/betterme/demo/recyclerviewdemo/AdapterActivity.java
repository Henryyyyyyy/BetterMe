package me.henry.betterme.betterme.demo.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.view.Recycler.version2.BaseHolder;
import me.henry.betterme.betterme.view.Recycler.version2.BaseMutiTypeAdapter;

public class AdapterActivity extends AppCompatActivity implements BaseMutiTypeAdapter.OnItemClickListener, BaseMutiTypeAdapter.OnViewClickListener {
    private RecyclerView rv;
    private List<TestData> datas = new ArrayList<>();
    private FsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        rv = (RecyclerView) findViewById(R.id.rv);
        setDatas();
        mAdapter = new FsAdapter(this, datas);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnViewClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
    }

    private void setDatas() {
        TestData data;
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                data = new TestData();
                data.leftName = "left" + i;
                data.type = 0;
            } else {
                data = new TestData();
                data.rightName = "right" + i;
                data.type = 1;
            }
            datas.add(data);
        }
    }


    @Override
    public void onItemClick(View view, BaseHolder holder, int position) {
         Toast.makeText(this,"haha.click="+position,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onViewClick(View view, BaseHolder holder, int position) {
        switch (view.getId()) {
            case R.id.tvTestItem:
                Toast.makeText(this, "haha.click tvTestItem=" + position, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
