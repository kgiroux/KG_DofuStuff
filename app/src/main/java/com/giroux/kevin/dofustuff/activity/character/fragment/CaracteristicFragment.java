package com.giroux.kevin.dofustuff.activity.character.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giroux.kevin.dofustuff.R;

/**
 * Created by kevin on 15/12/2016.
 */

public class CaracteristicFragment extends Fragment {

    public CaracteristicFragment(){}

    public static CaracteristicFragment newInstance(){
        return new CaracteristicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caracteristic, container, false);
    }
}
