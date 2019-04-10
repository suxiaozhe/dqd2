package com.example.administrator.dqd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dqd.R;

public class MatchScoreFragment extends MatchFragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_score_fragment, container, false);
        return view;
    }
}
