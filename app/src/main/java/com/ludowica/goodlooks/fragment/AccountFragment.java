package com.ludowica.goodlooks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.activity.LoginActivity;
import com.ludowica.goodlooks.services.SharedPreferencesManager;

public class AccountFragment extends Fragment {

    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        Button logout = rootView.findViewById(R.id.btn_logout);
        name = rootView.findViewById(R.id.lbl_name);
        setName();

        logout.setOnClickListener(view -> btnLogoutClicked());

        return rootView;
    }

    public void btnLogoutClicked() {

        SharedPreferencesManager.deleteAll(getContext());

        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    public void setName(){

        String username = SharedPreferencesManager.getUsername(getContext());

        name.setText("Hi, " + username + "!");
    }


}
