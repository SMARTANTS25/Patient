package com.pifss.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ResetPassword extends AppCompatActivity {

//
//    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =  new  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); 
//                 public static boolean validate(String emailStr) { 
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr); 
//        return matcher.find();     }  


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        //
        EditText email = (EditText) findViewById(R.id.ResetPassword_emailTF);
        Button submit = (Button) findViewById(R.id.ResetPassword_Button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ResetPassword.this, "Check your email", Toast.LENGTH_LONG).show();

            }
        });
    }
}


//
//          import android.app.ProgressDialog;
//         import android.content.Intent;
//         import android.content.SharedPreferences;
//         import android.graphics.Color; 
//        import android.support.v7.app.AppCompatActivity;
//         import android.os.Bundle;
//         import android.support.v7.widget.Toolbar; 
//        import android.view.View; 
//        import android.view.WindowManager;
//         import android.widget.Button; 
//        import android.widget.EditText; 
//        import android.widget.Toast;
//          import com.android.volley.Request;
//         import com.android.volley.RequestQueue;
//         import com.android.volley.Response;
//         import com.android.volley.VolleyError;
//         import com.android.volley.toolbox.JsonObjectRequest;
//         import com.google.gson.Gson;
//         import com.pifss.doctor.Model.Doctor; 
//        import com.pifss.doctor.R;
//         import com.pifss.doctor.RequestQueueSingleTon; 
//        import com.pifss.doctor.links;
//          import org.json.JSONException; 
//        import org.json.JSONObject; 
//         import java.util.regex.Matcher;
//         import java.util.regex.Pattern;
//          public class ForgetPasswordActivity extends AppCompatActivity {
//          
//            public static final Pattern VALID_EMAIL_ADDRESS_REGEX =   Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); 
//                 public static boolean validate(String emailStr) { 
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr); 
//        return matcher.find();     }  
//    @Override     protected void onCreate(Bundle savedInstanceState) { 
//        super.onCreate(savedInstanceState); 
//        setContentView(R.layout.activity_forget_password); 
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
//                 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
//        toolbar.setTitle(R.string.ForgetPasswordAct);
//                 toolbar.setTitleTextColor(Color.WHITE); 
//        setSupportActionBar(toolbar); 
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//                  Button sendEmail = (Button) findViewById(R.id.btnForget); 
//        sendEmail.setOnClickListener(new View.OnClickListener() { 
//            @Override 
//            public void onClick(View v) { 
//                RequestQueue queue= RequestQueueSingleTon.getInstance().getRequestQueue(ForgetPasswordActivity.this); 
//                final EditText email = (EditText) findViewById(R.id.txtDrEmail); 
//                final EditText civilID = (EditText) findViewById(R.id.txtDrCivil);  
                //validate email 
                // if (validate(email.getText().toString()) == false){ 
                // Toast.makeText(ForgetPasswordActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show(); 
                // return; 
                // }  
                // final JSONObject jsonBody = new JSONObject();  
                // try { 
                // jsonBody.put("username",email.getText().toString());  
                // jsonBody.put("civilid",civilID.getText().toString()); 
                // } catch (JSONException e) { 
                // e.printStackTrace();                 }  
                //  
                // String URL = links.resetPassword;   
                // final ProgressDialog progressDialog = new ProgressDialog(ForgetPasswordActivity.this);  
                // JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                //  
                // @Override 
                // public void onResponse(JSONObject response) { 
                // progressDialog.hide(); 
                // try {  
                // Toast.makeText(ForgetPasswordActivity.this, "hi "+response.toString(), Toast.LENGTH_SHORT).show();  
                // if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){
                //  
                // email.setText("");   
                // Toast.makeText(ForgetPasswordActivity.this, "Email sent successfully", Toast.LENGTH_SHORT).show();  
                // }else { 
//
                // Toast.makeText(ForgetPasswordActivity.this, "Connection failed", Toast.LENGTH_SHORT).show(); 
                // }  
                // }
                // catch (JSONException e) { 
                // e.printStackTrace(); 
                // } 
                // } 
                // }, new Response.ErrorListener() { 
                // @Override 
                // public void onErrorResponse(VolleyError error) { 
                // progressDialog.hide(); 
                // } 
                // });  
                // progressDialog.setMessage("Sending Email..."); 
                // progressDialog.show(); 
                // queue.add(jsonObjRequest);             } 
                // });        }  
                // @Override 
                // public boolean onSupportNavigateUp() { 
                // onBackPressed(); 
                // return true; 
                // } } 
