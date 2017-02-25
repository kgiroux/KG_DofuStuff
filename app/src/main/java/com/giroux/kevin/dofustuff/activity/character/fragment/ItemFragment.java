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

public class ItemFragment extends Fragment {

    public ItemFragment(){}

    public static ItemFragment newInstance(){
        return  new ItemFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }
}
