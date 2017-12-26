package com.giroux.kevin.dofustuff.activity.character;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import com.giroux.kevin.dofustuff.databinding.ActivityCreateBinding;
import com.giroux.kevin.dofustuff.dto.Character;
import com.giroux.kevin.dofustuff.dto.SexType;
import com.giroux.kevin.dofustuff.dto.TypeCharacteristic;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
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
        final ActivityCreateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        character = new Character();
        character.initCharacter();
        binding.setCharacter(character);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(this);
        toolbar.setLogo(R.drawable.arrow_left_bold);
        toolbar.setNavigationOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.classGame);
        gifImageView = findViewById(R.id.createCharacterGif);
        gifImageView.setImageResource(R.drawable.dofus_logosizechange);

        spinner.setOnItemSelectedListener(this);

        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar) {
            finish();
        } else if (view.getId() == R.id.createButton) {

            RadioGroup radioGroup = findViewById(R.id.radioGroupSex);
            int id = radioGroup.getCheckedRadioButtonId();
            handleDataBindingCharacter(id);
            if(realm.isInTransaction()){
                realm.cancelTransaction();
            }

            try{
                realm.executeTransactionAsync(realm -> {
                    Log.i("Insert", "Insert start");
                    realm.insert(character);
                }, () -> {
                    Log.i("Insert", "Insert complete");
                    finish();
                }, error -> Log.i("Insert","Error " + error.getMessage()));

            }catch (Exception e){
                Log.e("Error", "onClick: "+e.getLocalizedMessage() );
            }

        }

    }

    private void handleDataBindingCharacter(int id) {
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

    @Override
    protected void onPause() {
        super.onPause();
        if(realm.isInTransaction()){
            realm.cancelTransaction();
        }
    }
}