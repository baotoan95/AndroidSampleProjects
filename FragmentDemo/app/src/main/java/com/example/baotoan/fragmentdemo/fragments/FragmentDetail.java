package com.example.baotoan.fragmentdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baotoan.fragmentdemo.R;

/**
 * Created by BaoToan on 11/14/2016.
 */

public class FragmentDetail extends Fragment {
    TextView txtDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_detail, container, true);
        txtDetails = (TextView) viewFragment.findViewById(R.id.txt_details);
        txtDetails.setText("Nothing");
        return viewFragment;
    }

    public void showInformation(String info) {
        txtDetails.setText(info);
    }
}
