package com.pifss.patient;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Switch;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // k

        Toolbar toolbar = (Toolbar) findViewById(R.id.settingsToolbar);
        toolbar.setTitle("Settings");
        toolbar.setNavigationIcon(R.mipmap.abplus);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Switch switchLocale = (Switch) findViewById(R.id.switchLanguage);

        switchLocale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String defaultLocale = Locale.getDefault().getDisplayLanguage();
                String languageToLoad = "en_US";
                if (defaultLocale == "en_US") {
                    languageToLoad = "ar";
                }

                setLocale(languageToLoad);


            }
        });


    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Settings.class);
        startActivity(refresh);
        finish();
    }
}
