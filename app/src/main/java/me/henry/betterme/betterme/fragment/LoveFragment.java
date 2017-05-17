package me.henry.betterme.betterme.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.henry.betterme.betterme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoveFragment extends Fragment {


    public LoveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_love, container, false);
    }

}
