package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResetPassword extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);;
      boolean validate(String emails) {
        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(emails);
        return matcher.find();
            }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Forget password");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final EditText email = (EditText) findViewById(R.id.ResetPassword_emailTF);
        final EditText civilID = (EditText) findViewById(R.id.ResetPassword_CivilIdTV);
        Button submit = (Button) findViewById(R.id.ResetPassword_Button);
//
       this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//
        submit.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

                final RequestQueue queue= MySingleton.getInstance().getRequestQueue(ResetPassword.this);

                if (!validate(email.getText().toString())) {
                    Toast.makeText(ResetPassword.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                final JSONObject jsonBody = new JSONObject();
                 try{
                 jsonBody.put("username",email.getText().toString());
                 jsonBody.put("civilid",civilID.getText().toString());

                 } catch (JSONException e) {
                 e.printStackTrace();
                 }
                 String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/reset/";

                final ProgressDialog progressDialog = new ProgressDialog(ResetPassword.this);

                JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                         progressDialog.hide();

                        if (response.has("errorMsgEn")) {

                                 Toast.makeText(ResetPassword.this, "password changed, check your email", Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(ResetPassword.this,Login.class);
                                 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                 finish();
                                 startActivity(i);

                             } else {
                                 Toast.makeText(ResetPassword.this, "connection failed", Toast.LENGTH_SHORT).show();
                             }

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(ResetPassword.this, "connection failed", Toast.LENGTH_SHORT).show();
                    }
                });


               if (!isNetworkAvailable()){
                   Toast.makeText(ResetPassword.this, "you do not have Internet Connection!!!!!!", Toast.LENGTH_SHORT).show();
               }

               progressDialog.setMessage("Connecting...");
               progressDialog.show();
               queue.add(jsonObjRequest);
            }

       });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


