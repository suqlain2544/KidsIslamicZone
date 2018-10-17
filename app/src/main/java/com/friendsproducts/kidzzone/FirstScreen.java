package com.friendsproducts.kidzzone;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.Helper_Classes.URLs;
import com.friendsproducts.kidzzone.Helper_Classes.VolleySingleton;
import com.friendsproducts.kidzzone.student.activities.main.StudentHome;
import com.friendsproducts.kidzzone.teacher.activities.TeacherHome;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FirstScreen extends AppCompatActivity {

    final int REQUEST_PERMISSION_CODE = 1000;


    RadioButton rStd, rTchr;
    EditText editTextUsername, editTextPassword;
    ProgressDialog progressDialog;

    Sharepref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_first_screen);

        sp = new Sharepref(FirstScreen.this);
        //permision start
        if (checkPermissionFromDevice()) {

        } else {
            requestPermission();
        }


        //permision end

        rStd = (RadioButton) findViewById(R.id.radioStd);
        rTchr = (RadioButton) findViewById(R.id.radioTchr);
        progressDialog = new ProgressDialog(this);

        editTextUsername = (EditText) findViewById(R.id.unameET);
        editTextPassword = (EditText) findViewById(R.id.upassET);

        }catch (Exception e)
        {

        }

    }

    private void stdUserLogin() {
        //first getting the values
        final String useremail = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(useremail)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(FirstScreen.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Preparing to Login...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_STD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String unique_id = obj.getString("id");
                            String username = obj.getString("stdUserName");
                            String unique_name = obj.getString("stdUniqueUser");
                            String email = obj.getString("stdEmail");
                            String password1 = obj.getString("stdPassword");
                            String gender = obj.getString("stdGender");
                            String age = obj.getString("stdAge");
                            String nmbr = obj.getString("stdPhNmbr");
                            String image = obj.getString("stdimage");
                            String tecEmail = obj.getString("tecEmail");
                            String tecUniqueName = obj.getString("tecUniqueName");
                            String reqStatus = obj.getString("reqStatus");

                            if (username.equals(username) && password.equals(password1)) {
                                // To Dismiss progress dialog
                                progressDialog.dismiss();


                                sp.setStr(sp.UNIQUE_ID, unique_id);
                                sp.setStr(sp.STD_USER_NAME, username);
                                sp.setStr(sp.STD_UNIQUE_USER, unique_name);
                                sp.setStr(sp.STD_EMAIL, email);
                                sp.setStr(sp.STD_PASS, password1);
                                sp.setStr(sp.STD_GENDER, gender);
                                sp.setStr(sp.STD_AGE, age);
                                sp.setStr(sp.STD_PH, nmbr);
                                sp.setStr(sp.STD_IMAGE, image);
                                sp.setStr(sp.TECH_EMAIL, tecEmail);
                                sp.setStr(sp.TECH_UNIQUE_NAME, tecUniqueName);
                                sp.setStr(sp.REQ_STATUS, reqStatus);

                                sp.setStr(sp.WHO, "std");


                                Intent i = new Intent(getApplicationContext(), StudentHome.class);
                                startActivity(i);
                                finish();
                            } else {
                                // To Dismiss progress dialog
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Email or Password incorrect", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            //Toast.makeText(getApplicationContext(), "Incorrect User Name or Password", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Email or Password incorrect", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // To Dismiss progress dialog
                        progressDialog.dismiss();

                        //error.toString()
                        //Toast.makeText(FirstScreen.this, "Cheeck your Internet Connection", Toast.LENGTH_LONG).show();
                        Toast.makeText(FirstScreen.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("useremail", useremail);
                map.put("userpass", password);
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void tecUserLogin() {
        //first getting the values
        final String useremail = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(useremail)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(FirstScreen.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Preparing to Login...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_TCHR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String unique_id = obj.getString("id");
                            String username = obj.getString("tecName");
                            String usernameunique = obj.getString("tecUniqueName");
                            String email = obj.getString("tecEmail");
                            String password1 = obj.getString("tecPass");
                            String qual = obj.getString("tecQual");
                            String nmbr = obj.getString("tecNmbr");
                            String gender = obj.getString("tecGender");
                            String image = obj.getString("tecUrl");

                            if (username.equals(username) && password.equals(password1)) {
                                // To Dismiss progress dialog
                                progressDialog.dismiss();


                                sp.setStr(sp.UNIQUE_ID, unique_id);
                                sp.setStr(sp.TEC_UNIQUE_NAME, username);
                                sp.setStr(sp.TECH_UNIQUE_NAME, usernameunique);
                                sp.setStr(sp.TEC_EMAIL, email);
                                sp.setStr(sp.TEC_PASS, password1);
                                sp.setStr(sp.TEC_GENDER, gender);
                                sp.setStr(sp.TEC_QUALI, qual);
                                sp.setStr(sp.TEC_NMBR, nmbr);
                                sp.setStr(sp.TEC_IMAGE, image);
                                sp.setStr(sp.WHO, "tec");


                                Intent i = new Intent(getApplicationContext(), TeacherHome.class);
                                startActivity(i);
                                finish();
                            } else {
                                // To Dismiss progress dialog
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            //Toast.makeText(getApplicationContext(), "Incorrect User Name or Password", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // To Dismiss progress dialog
                        progressDialog.dismiss();

                        //error.toString()
                        //Toast.makeText(FirstScreen.this, "Cheeck your Internet Connection", Toast.LENGTH_LONG).show();
                        Toast.makeText(FirstScreen.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("useremail", useremail);
                map.put("userpass", password);
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void clicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.loginBtn:
                if (rStd.isChecked()) {
                    if (checkInternet()) {
                        try{
                            stdUserLogin();
                        }catch (Exception e)
                        {

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Internet required", Toast.LENGTH_SHORT).show();
                    }


                } else if (rTchr.isChecked()) {
                    if (checkInternet()) {
                        tecUserLogin();
                    } else {
                        Toast.makeText(getApplicationContext(), "Internet required", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.registerTv:
                if (checkInternet()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Chose One")
                            .setCancelable(true)
                            .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent std = new Intent(FirstScreen.this, RegisterTeacher.class);
                                    startActivity(std);
                                }
                            })
                            .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent std = new Intent(FirstScreen.this, RegisterStudent.class);
                                    startActivity(std);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    Toast.makeText(getApplicationContext(), "Internet required", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.guestTv:
                Intent guest = new Intent(FirstScreen.this, StudentHome.class);
                startActivity(guest);
                break;
        }
    }

    //start permission

    public void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT);

                }
            }
            break;
        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int internet_result = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED && internet_result == PackageManager.PERMISSION_GRANTED;

    }

    //end permission

    public boolean checkInternet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }
}
