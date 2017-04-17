package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewPatientProfile extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ImageView img = (ImageView) findViewById(R.id.ViewPatientProfile_PatientImage);

        Button editButton=(Button) findViewById(R.id.ViewPatientProfile_editPersonalInfoButton);
        Button myDrButton=(Button) findViewById(R.id.ViewPatientProfile_myDoctorsButton);
        Button medicalProfileButton = (Button) findViewById(R.id.ViewPatientProfile_medicalInfoViewButton);


        final TextView nameText=(TextView) findViewById(R.id.ViewPatientProfile_PatientNameTV);
        TextView genderText=(TextView) findViewById(R.id.ViewPatientProfile_GenderVT);
        final TextView birthdateText=(TextView) findViewById(R.id.ViewPatientProfile_BirthDateVT);
        final TextView emailText=(TextView) findViewById(R.id.ViewPatientProfile_emailVT);
        final TextView phoneText=(TextView) findViewById(R.id.ViewPatientProfile_phoneVT);
        final TextView emergencyNoText=(TextView) findViewById(R.id.ViewPatientProfile_emergencyNumberTV);

        final TextView editImage=(TextView) findViewById(R.id.textViewEditImage);


        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
        String patientProfile = pref1.getString("Patient1","notfound");
        Patient currentPatientObject = new Gson().fromJson(patientProfile,Patient.class);


        Picasso.with(ViewPatientProfile.this).load(currentPatientObject.getImageUrl()).placeholder(R.mipmap.profile_icon).into(img);

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        String PatientProfile  = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1"," ");

        // Step 2 converting String to Json Object

        try {
            JSONObject obj=new JSONObject(PatientProfile);

            String fname = obj.getString("firstName");
            String lname = obj.getString("lastName");
            String Fullname = obj.getString("firstName") +" "+obj.getString("lastName");
            nameText.setText(Fullname);

            System.out.print(fname +" "+ lname);

            String gender = obj.getString("gender");

      //      Toast.makeText(this, obj.getString("patientId")+"", Toast.LENGTH_SHORT).show();
            System.out.print(obj.getString("patientId"));
            if (gender.equals("M"))
                genderText.setText("male");
            else genderText.setText("female");

            String email = obj.getString("email");
            emailText.setText(email);
            String phone = obj.getString("phoneNumber");
            String emergency = obj.getString("emergencyNumber");
            phoneText.setText(phone);
            emergencyNoText.setText(emergency);
            String birthDate = obj.getString("dateOfBirth");
            birthdateText.setText(birthDate);

            Toast.makeText(ViewPatientProfile.this, "the patient name: " + fname, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        myDrButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(ViewPatientProfile.this,MyDoctors.class);
////                startActivity(i);
//                Toast.makeText(ViewPatientProfile.this, "my Doctors", Toast.LENGTH_SHORT).show();
//            }
//        });



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewPatientProfile.this, EditPatientProfile.class);
                startActivity(i);
            }
        });

        medicalProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewPatientProfile.this, ViewMedicalInfo.class);
                startActivity(i);
            }
        });

        myDrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ViewPatientProfile.this, MyDoctors.class);
                startActivity(i);
            }
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALLARY = 2;
    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ViewPatientProfile.this);
        builder.setTitle("Upload Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }else if (options[item].equals("Choose from Gallery"))
                {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , REQUEST_IMAGE_GALLARY);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("did enter onActivityResult!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        ImageView img = (ImageView) findViewById(R.id.ViewPatientProfile_PatientImage);
        Bitmap imageBitmap = null;
        try{


            if (resultCode != RESULT_OK){
                return;
            }
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
            }

            if (requestCode == REQUEST_IMAGE_GALLARY && resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                // Toast.makeText(this, "Path: "+getRealPathFromURI(selectedImage), Toast.LENGTH_SHORT).show();
                //System.out.println("Base64:"+convert(rotateBitmapOrientation(getRealPathFromURI(selectedImage))));
                System.out.println("Image path " );
                System.out.println("Image path: "+getRealPathFromURI(selectedImage) );
                imageBitmap = rotateBitmapOrientation(getRealPathFromURI(selectedImage));



                //img.setImageURI(selectedImage);
            }

            RequestQueue queue = MySingleton.getInstance().getRequestQueue(ViewPatientProfile.this);
            final ProgressDialog progressDialog = new ProgressDialog(ViewPatientProfile.this);

            try {

                JSONObject jsonBody = new JSONObject();
                jsonBody.put("appID","patient");
                jsonBody.put("imgData",convert(imageBitmap));
                System.out.println(jsonBody.toString());

                final Bitmap finalImageBitmap = imageBitmap;
                String url =  "http://34.196.107.188:8081/MhealthWeb/addimg";

                StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.hide();
                                try {

                                    JSONObject res = new JSONObject(response);
                                    if (res.getString("errorMsgEn").equalsIgnoreCase("Done")){
                                        Toast.makeText(ViewPatientProfile.this, "Upload successfully", Toast.LENGTH_SHORT).show();
                                        if (res.has("imgPath")){
                                            String imgPath = res.getString("imgPath");
                                            updatePatientProfileWithImage(imgPath);
                                        }
                                        System.out.println("response: "+response.toString());
                                    }else{
                                        Toast.makeText(ViewPatientProfile.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewPatientProfile.this, "Upload Failed", Toast.LENGTH_SHORT).show();

                        progressDialog.hide();
                    }
                }) {

                    @Override
                    public String getBodyContentType() {
                        return "application/x-www-form-urlencoded; charset=UTF-8";
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("appID", "doctor");
                        params.put("imgData", convert(finalImageBitmap));
                        return params;
                    }

                };

                progressDialog.setMessage("Uploading...");
                progressDialog.show();
                queue.add(jsonObjRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }catch (OutOfMemoryError e){

            Toast.makeText(this, "Error, The image size is too large. Use another Image", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error Cannot continue, Try again later.", Toast.LENGTH_SHORT).show();

        }
        img.setImageBitmap(imageBitmap);

    }


    public void updatePatientProfileWithImage(String imgPath){

        RequestQueue queue = MySingleton.getInstance().getRequestQueue(ViewPatientProfile.this);
        final ProgressDialog progressDialog = new ProgressDialog(ViewPatientProfile.this);


        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
        String patientProfile = pref1.getString("Patient1","notfound");
        final Patient currentPatientObject = new Gson().fromJson(patientProfile,Patient.class);
        try {
        currentPatientObject.setImageUrl(imgPath);
        JSONObject jsonBody = new JSONObject(currentPatientObject.toJSONString());

        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/"+currentPatientObject.getPatientId();

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.hide();
                try {
                    if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){

                        Toast.makeText(ViewPatientProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();

                        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
                        SharedPreferences.Editor Ed1 = pref1.edit();
                        //profile
                        Ed1.putString("Patient1",currentPatientObject.toJSONString());
                        Ed1.commit();

                    }else{
                        Toast.makeText(ViewPatientProfile.this, "Error, can't update profile", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("response: "+response.toString());

                //Toast.makeText(EditDoctorProfileActivity.this, "response: "+response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(ViewPatientProfile.this, "Error, Connection failed", Toast.LENGTH_SHORT).show();

            }
        });


        progressDialog.setMessage("Updating Profile...");
        progressDialog.show();
        queue.add(jsonObjRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public Bitmap rotateBitmapOrientation(String photoFilePath) {
        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(photoFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        // Return result
        return rotatedBitmap;
    }
    public String convert(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        System.gc();
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
}
