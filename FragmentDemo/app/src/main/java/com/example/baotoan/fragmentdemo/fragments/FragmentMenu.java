package com.example.baotoan.fragmentdemo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baotoan.fragmentdemo.R;
import com.example.baotoan.fragmentdemo.activities.DetailActivity;

/**
 * Created by BaoToan on 11/14/2016.
 */

public class FragmentMenu extends Fragment implements View.OnClickListener {
    private Button btnOne, btnTwo, btnThree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_menu, container, true);

        // Mapping components
        btnOne = (Button) fragmentView.findViewById(R.id.btn_one);
        btnTwo = (Button) fragmentView.findViewById(R.id.btn_two);
        btnThree = (Button) fragmentView.findViewById(R.id.btn_three);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_one:
                showInfo("Button 1 clicked");
                break;
            case R.id.btn_two:
                showInfo("Button 2 clicked");
                break;
            case R.id.btn_three:
                showInfo("Button 3 clicked");
                break;
        }
    }

    private void showInfo(String info) {
        FragmentDetail fragmentDetail = (FragmentDetail) getFragmentManager().findFragmentById(R.id.fragment_detail);
        if(null == fragmentDetail || !fragmentDetail.isInLayout()) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("INFO", info);
            startActivity(intent);
        } else {
            fragmentDetail.showInformation(info);
        }
    }
}
