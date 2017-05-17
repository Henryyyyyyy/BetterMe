package me.henry.betterme.betterme.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.model.Person;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;
import me.henry.betterme.betterme.view.Recycler.version1.TestAdapter;

public class ObservableActivity extends AppCompatActivity implements BaseRvAdapter.OnRecyclerViewItemClickListener {
private RecyclerView rv;
    private TestAdapter mAdapter;
    private List<Person>datas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
        rv= (RecyclerView) findViewById(R.id.rv);
        Person p;
        for (int i = 0; i < 30; i++) {
          p=new Person();
            p.name="Person"+i;
            p.age=i*10;
            datas.add(p);
        }
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter=new TestAdapter(this,datas,R.layout.item_test);
        mAdapter.addHeader(R.layout.item_head);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"~~"+datas.get(position).name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
