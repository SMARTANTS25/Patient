package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginBtn = (Button) findViewById(R.id.loginButton);
        final EditText emailText = (EditText) findViewById(R.id.login_EmailTF);
        final EditText passwordText = (EditText) findViewById(R.id.Login_PasswordTV);
        TextView signup = (TextView) findViewById(R.id.Login_signupTV);
        TextView forgetPassword = (TextView) findViewById(R.id.Login_forgetpasswordTV);

        // TODO: 13/04/17 for testing remove when done
//        emailText.setText("relat@gmail.com");
//        passwordText.setText("112233");

        //login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username =emailText.getText().toString();
                String password=passwordText.getText().toString();

                if (username.length() == 0 || password.length() == 0){
                    Toast.makeText(Login.this, "Please fill all field", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginBtn.setEnabled(false);
                loginFunction( username,  password);

            }
        });


        String LoginData = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1", "ERROR");
        JSONObject obj;

        String sharedEmail = "";
        String sharedPassword="";

        if (!LoginData.equals("ERROR")) {

            try {

                obj = new JSONObject(LoginData);

                sharedEmail = obj.getString("email");

                sharedPassword = obj.getString("password");

            } catch (JSONException e) {
                e.printStackTrace();
            }

//            loginFunction( sharedEmail,  sharedPassword);
            emailText.setText(sharedEmail);
            passwordText.setText(sharedPassword);
            loginBtn.callOnClick();
        }


        //Sign up screen button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, RegistrationPage1.class);
                startActivity(i);
            }
        });

        //forgot password screen
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, ResetPassword.class);
                startActivity(i);
            }
        });

    }

    private void loginFunction(final String emailText, String passwordText) {
        final Button loginBtn = (Button) findViewById(R.id.loginButton);
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);

        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/login/";
        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();

        try {
            obj.put("username", emailText);
            obj.put("password", passwordText);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("request Login: "+obj.toString());
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                //  Toast.makeText(Login.this, response.toString()+" ", Toast.LENGTH_SHORT).show();
                System.out.println("response Login: "+response.toString());
                loginBtn.setEnabled(true);
                progressDialog.hide();

                try {
                    if (!response.has("errorCode")){
                        Toast.makeText(Login.this, "Connection failed" , Toast.LENGTH_LONG).show();

                        return;
                    }
                    if(response.getInt("errorCode") == 0) {

                        Patient profile ;
                        profile = new Gson().fromJson(response.getString("items"),Patient.class);

                        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
                        SharedPreferences.Editor Ed1 = pref1.edit();
                        //profile
                        Ed1.putString("Patient1",profile.toJSONString());
                        Ed1.commit();
                        Toast.makeText(Login.this, "Welcome " + profile.getFirstName(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Login.this, "Username or password is wrong" , Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error Login: "+error.toString());
                loginBtn.setEnabled(true);
                progressDialog.hide();

                Toast.makeText(Login.this, "No Internet Connection, please connect internet connection", Toast.LENGTH_LONG);
                //Toast.makeText(Login.this, error.toString()+"", Toast.LENGTH_SHORT).show();
            }
        });
//        if (!haveNetworkConniction)
        if (!isNetworkAvailable()){
            Toast.makeText(this, "you do not have Internet Connection!!!!!!", Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("Login...");
        progressDialog.show();
        queue.add(req);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
}
