package com.giroux.kevin.dofustuff.activity.character.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giroux.kevin.dofustuff.R;

/**
 * Created by kevin on 15/12/2016.
 */

public class DetailsFragment extends Fragment {


    public DetailsFragment(){}


    public static DetailsFragment newInstance(){
        return new DetailsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("TEST",String.valueOf(getArguments().get("idCharacter")));

        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}
