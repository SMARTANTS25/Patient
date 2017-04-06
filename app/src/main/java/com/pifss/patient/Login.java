package com.pifss.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button login = (Button) findViewById(R.id.loginButton);
        final EditText emailText = (EditText) findViewById(R.id.loginEmailText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordLoginText);
        TextView signup = (TextView) findViewById(R.id.signupTextView);
        TextView forgetPassword = (TextView) findViewById(R.id.forgetpasswordTV);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login(emailText.getText().toString(), passwordText.getText().toString());



                        String username =emailText.getText().toString();
                        String password=passwordText.getText().toString();
                        login( username,  password);



                //Toast.makeText(Login.this,"You Have been Loggned in Successfuly", Toast.LENGTH_LONG);

                            Intent i = new Intent(Login.this, Home.class);


                            startActivity(i);



                            Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_LONG);
                           // login.setEnabled(false);

            }
        });




                    signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(Login.this, RegistrationPage1.class);
                            startActivity(i);
                        }
                    });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, ResetPassword.class);
                startActivity(i);
            }
        });

    }

    private void login(final String emailText, String passwordText) {
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/login/";
        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();
        try {
            obj.put("username", emailText);
            obj.put("password", passwordText);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(final JSONObject response) {
                    Toast.makeText(Login.this, "response received (login)", Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedpreferences = getSharedPreferences("patient", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    // put info (shared preferences
                    Patient p = new Gson().fromJson(response.toString(), Patient.class);
                    editor.putString("username", p.getEmail());
                    editor.putString("firstName", p.getFirstName());
                    editor.putString("middleName", p.getMiddleName());
                    editor.putString("lastName", p.getLastName());
                    editor.commit();

                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
