package com.giroux.kevin.dofustuff.activity.character;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.constants.Constants;
import com.giroux.kevin.dofustuff.constants.DofusRealmSyncConfiguration;
import com.giroux.kevin.dofustuff.databinding.ActivityCreateBinding;
import com.giroux.kevin.dofustuff.dto.Character;
import com.giroux.kevin.dofustuff.dto.SexType;
import com.giroux.kevin.dofustuff.dto.TypeCharacteristic;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import pl.droidsonroids.gif.GifImageView;

public class CreateActivity extends AppCompatActivity implements Toolbar.OnClickListener, AdapterView.OnItemSelectedListener {
    @BindView(R.id.caracteristicForceValue)
    EditText caracteristicForceValue;
    @BindView(R.id.caracteristicSagesseValue)
    EditText caracteristicSagesseValue;
    @BindView(R.id.caracteristicIntelligenceValue)
    EditText caracteristicIntelligenceValue;
    @BindView(R.id.caracteristicLuckValue)
    EditText caracteristicLuckValue;
    @BindView(R.id.caracteristicAgilityValue)
    EditText caracteristicAgilityValue;
    @BindView(R.id.caracteristicVitalityValue)
    EditText caracteristicVitalityValue;
    private Character character;
    private GifImageView gifImageView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SyncUser user = SyncUser.currentUser();
        final ActivityCreateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create);
        ButterKnife.bind(this);
        SyncConfiguration configSync = null;
        if(user != null){
            configSync  = DofusRealmSyncConfiguration.getInstance().getSyncConfiguration();
        }
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        if(configSync !=null){
            realm = Realm.getInstance(configSync);
        }else{
            realm = Realm.getInstance(config);
        }

        realm.beginTransaction();
        character = new Character();
        binding.setCharacter(character);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(this);
        toolbar.setLogo(R.drawable.arrow_left_bold);
        toolbar.setNavigationOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.classGame);
        gifImageView = (GifImageView) findViewById(R.id.createCharacterGif);
        gifImageView.setImageResource(R.drawable.dofus_logosizechange);

        spinner.setOnItemSelectedListener(this);

        Button createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar) {
            finish();
        } else if (view.getId() == R.id.createButton) {

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSex);
            int id = radioGroup.getCheckedRadioButtonId();
            if(id == R.id.radioFemale){
                character.setSex(SexType.FEMALE.toString());
            }else if(id == R.id.radioMale){
                character.setSex(SexType.MALE.toString());
            }
            if(null != caracteristicForceValue.getText() && !caracteristicForceValue.getText().toString().equals("")){
                character.getCharacteristic(TypeCharacteristic.FORCE.toString()).setCurrentValue(Integer.parseInt(caracteristicForceValue.getText().toString()));
            }
            if(null != caracteristicVitalityValue.getText() && !caracteristicVitalityValue.getText().toString().equals("")){
                character.getCharacteristic(TypeCharacteristic.VITALITE.toString()).setCurrentValue(Integer.parseInt(caracteristicVitalityValue.getText().toString()));
            }
            if(null != caracteristicIntelligenceValue.getText() && !caracteristicIntelligenceValue.getText().toString().equals("")){
                character.getCharacteristic(TypeCharacteristic.INTELLIGENCE.toString()).setCurrentValue(Integer.parseInt(caracteristicIntelligenceValue.getText().toString()));
            }
            if(null != caracteristicSagesseValue.getText() && !caracteristicSagesseValue.getText().toString().equals("")){
                character.getCharacteristic(TypeCharacteristic.SAGESSE.toString()).setCurrentValue(Integer.parseInt(caracteristicSagesseValue.getText().toString()));
            }
            if(null != caracteristicAgilityValue.getText() && !caracteristicAgilityValue.getText().toString().equals("")){
                character.getCharacteristic(TypeCharacteristic.AGILITE.toString()).setCurrentValue(Integer.parseInt(caracteristicAgilityValue.getText().toString()));
            }
            if(null != caracteristicLuckValue.getText() && !caracteristicLuckValue.getText().toString().equals("")){
                character.getCharacteristic(TypeCharacteristic.CHANCE.toString()).setCurrentValue(Integer.parseInt(caracteristicLuckValue.getText().toString()));
            }


            realm.insert(character);
            realm.commitTransaction();
            finish();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        character.setClassGame(getResources().getStringArray(R.array.classGame)[i].toUpperCase());
        int drawableResourceId = getResources().getIdentifier("logo" + StringUtils.stripAccents(character.getClassGame().toLowerCase()), "drawable", getPackageName());
        if (drawableResourceId != -1) {
            gifImageView.getVisibility();
            gifImageView.setImageResource(drawableResourceId);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("REALm","Passage ICI");
        if(realm.isInTransaction()){
            realm.cancelTransaction();
        }
        realm.close();
    }
}