package com.pifss.patient;

import android.content.Intent;
import android.content.SharedPreferences;
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
        emailText.setText("waw@gmail.com");
        passwordText.setText("111111");

        //login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username =emailText.getText().toString();
                String password=passwordText.getText().toString();

                loginFunction( username,  password);

            }
        });



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
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/login/";
        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();

        try {
            obj.put("username", emailText);
            obj.put("password", passwordText);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                //  Toast.makeText(Login.this, response.toString()+" ", Toast.LENGTH_SHORT).show();

                try {

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

                //                JSONObject o = null;
//                //array of object
//                String one ;
//                try {
//                    one = response.getString("items");
//                    o = new JSONObject(one);
//                    String name = o.getString("firstName");
//                    SharedPreferences sharedpreferences = getSharedPreferences("PatientData1", MODE_PRIVATE);
//
//
//                    sharedpreferences.edit()
//                            .putString("Patient1" , o.toString())
//                            .apply();
//
//
//                    Intent intent = new Intent(Login.this, Home.class);
//                    Toast.makeText(Login.this, "welcome "+ name +" ", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                // Toast.makeText(Login.this, one+"", Toast.LENGTH_SHORT).show();

                //put the array in one obj

                //test





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "No Internet Connection, please connect internet connection", Toast.LENGTH_LONG);
                //Toast.makeText(Login.this, error.toString()+"", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(req);
    }

    
}
