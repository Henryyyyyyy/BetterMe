package me.henry.betterme.betterme.demo.greendaoo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.demo.greendaoo.wrap.DataBaseManager;

public class GreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);
        //1```````````````````````````````
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final HenryUser profile1 = new HenryUser("qwe","qas",true,1357036);
                ((HenryUserDao)DataBaseManager.getInstance().getDao(HenryUser.class)).insert(profile1);
//                ((HenryUserDao)DataBaseManager.getInstance().getDao(HenryUser.class)).
                final HenryUser profile2 = new HenryUser("dsfds","fdsf",true,2321);
                DataBaseManager.getInstance().getHenryUserDao().insert(profile2);
            }
        }, 2000);
        //2```````````````````````````````
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final TemPassword ts2 = new TemPassword(222l,"zen","{datas}");
                DataBaseManager.getInstance().getTemPasswordDao().insert(ts2);
            }
        }, 3000);

    }
}
