package com.giroux.kevin.dofustuff.activity.administration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.giroux.kevin.dofustuff.R;

public class AdministrationActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        ButterKnife.bind(this);

        button2.setOnClickListener(v -> {
            /*realm.where(Characteristic.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();*/
        });
        button3.setOnClickListener(v -> {
            /*realm.where(CharacteristicClass.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();*/
        });
        button4.setOnClickListener(v -> {
            /*realm.where(Character.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();*/
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
