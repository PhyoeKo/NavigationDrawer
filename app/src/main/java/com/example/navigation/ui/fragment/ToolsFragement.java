package com.example.navigation.ui.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.navigation.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragement extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    public NavController navController;
    BottomNavigationView bot_nav_view;

    public ToolsFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bot_nav_view = view.findViewById(R.id.bot_nav);

        navController = Navigation.findNavController(getActivity(), R.id.bot_fragment);
        bot_nav_view.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_bot_camera:
                Toast.makeText(getActivity(),"Camera",Toast.LENGTH_LONG).show();
                navController.navigate(R.id.bot_camera);
                break;


            case  R.id.nav_bot_share:
                navController.navigate(R.id.bot_Share);
                Toast.makeText(getActivity(),"Share",Toast.LENGTH_LONG).show();
                break;
        }


        return false;
    }
}
