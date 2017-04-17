package com.pifss.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.settingsToolbar);

        toolbar.setTitle(R.string.Home_Sittings);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Button btnEng = (Button) findViewById(R.id.Sitting_English);
        Button btnArb = (Button) findViewById(R.id.Sitting_Arb);

                final Configuration config = getResources().getConfiguration();


                btnEng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                              if (getSharedPreferences("sittings",MODE_PRIVATE).getString("language","ar").equals("ar"))

                            {
                            //    Toast.makeText(Settings.this, isChecked + "", Toast.LENGTH_SHORT).show();


                                config.locale = new Locale("en");

                                Toast.makeText(Settings.this, "is english", Toast.LENGTH_SHORT).show();

                                getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                                SharedPreferences langShared = getSharedPreferences("sittings", MODE_PRIVATE);
                                SharedPreferences.Editor editor = langShared.edit();
                                editor.putString("language", "en");

                                Intent intent = new Intent(Settings.this, Home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                finish();
                                startActivity(intent);
                            }  }
                });

              btnArb.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {



                    Toast.makeText(Settings.this, "العربية", Toast.LENGTH_SHORT).show();

                    config.locale = new Locale("ar");
                    getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                    SharedPreferences langShared = getSharedPreferences("sittings",MODE_PRIVATE);
                    SharedPreferences.Editor editor = langShared.edit();
                    editor.putString("language","ar");

                    Intent intent = new Intent(Settings.this,Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                    startActivity(intent);

                  }
              });
                }



}
