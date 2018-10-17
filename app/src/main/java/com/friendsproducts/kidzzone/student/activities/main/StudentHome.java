package com.friendsproducts.kidzzone.student.activities.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.Helper_Classes.URLs;
import com.friendsproducts.kidzzone.Helper_Classes.VolleySingleton;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.RegisterStudent;
import com.friendsproducts.kidzzone.RegisterTeacher;
import com.friendsproducts.kidzzone.student.adopters.main.MainAdaptor;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentHome extends AppCompatActivity {

    //guest interface start
    ImageView profileIv, logoutIv;
    Sharepref sp;
    String username, uemail, upass, tecEmail, reqStatus;
    String[] guestString = {"Courses", "Hijri Calender", "Namaz Alarm", "General Knowledge"};
    //guest interface end
    RecyclerView recyclerView;
    MainAdaptor ol_adaptor;
    List<MainListData> o_List;
    FloatingActionButton list_chat;
    String[] stdString = {"Courses", "Hijri Calender", "Namaz Alarm", "General Knowledge"};
    String[] stdStringUrdu = {"اسباق", "حجری کیلنڈر", "نماز الارم", "معلومات عامہ"};
    String[] stdStringImage = {"Courses", "hcalender", "namazalarm", "General Knowledge"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        // updateUserInfo();

        //guest start
        profileIv = (ImageView) findViewById(R.id.profileBtn);
        logoutIv = (ImageView) findViewById(R.id.logoutBtn);
        sp = new Sharepref(this);
        username = sp.getStr(sp.STD_USER_NAME);
        uemail = sp.getStr(sp.STD_EMAIL);
        tecEmail = sp.getStr(sp.TECH_EMAIL);
        reqStatus = sp.getStr(sp.REQ_STATUS);

        updateUserInfo();

        if (username.equals("") && uemail.equals("")) {
            profileIv.setVisibility(View.INVISIBLE);
            logoutIv.setVisibility(View.INVISIBLE);
        }
        //guest end
        list_chat = (FloatingActionButton) findViewById(R.id.list_chat);
        o_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.sh_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentHome.this));
        if (username.equals("") && uemail.equals("")) {
            for (int ig = 0; ig < guestString.length; ig++) {
                o_List.add(new MainListData(guestString[ig], stdStringUrdu[ig], stdStringImage[ig]));
            }
        } else {
            for (int ig = 0; ig < stdString.length; ig++) {
                o_List.add(new MainListData(stdString[ig], stdStringUrdu[ig], stdStringImage[ig]));
            }
        }

        ol_adaptor = new MainAdaptor(getApplicationContext(), o_List);
        recyclerView.setAdapter(ol_adaptor);
   /* aghr user Guest nai ha tu if ma in ho ga aur check kary ga k tecEmail null ha ya nagi aghr null ha tu
        student list ma ly k jaye ga aghr null nai ha tu uss ka stust chek kry ga
        aghr status 0 ha tu pending request aur agher status 1 ha tu Chat screen open ho gi*/
   /*Agher user guest ha tu uss ko register screen per ly k jaye ga*/

        if (!username.equals("") && !uemail.equals("")) {
            if (tecEmail.equals("null") && reqStatus.equals("null")) {
                // list_chat.setImageDrawable(getResources().getDrawable(R.drawable.addtecicon));
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        list_chat.setImageDrawable(getResources().getDrawable(R.drawable.addtecicon));
                        if (username.equals("") && uemail.equals("")) {
                            if (checkInternet()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(StudentHome.this);
                                builder.setMessage("Chose One")
                                        .setCancelable(true)
                                        .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                StudentHome.this.finish();
                                                Intent std = new Intent(StudentHome.this, RegisterTeacher.class);
                                                startActivity(std);
                                            }
                                        })
                                        .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                StudentHome.this.finish();
                                                Intent std = new Intent(StudentHome.this, RegisterStudent.class);
                                                startActivity(std);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Internet Required", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Intent list_chat_intent = new Intent(StudentHome.this, TeacherList.class);
                            startActivity(list_chat_intent);
                        }
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("0")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(StudentHome.this, "Request Pending " + reqStatus, Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("1")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkInternet()) {
                            Intent cs = new Intent(StudentHome.this, Chat.class);
                            startActivity(cs);
                        } else {
                            Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }


        } else {
            list_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list_chat.setImageDrawable(getResources().getDrawable(R.drawable.addtecicon));
                    if (username.equals("") && uemail.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentHome.this);
                        builder.setMessage("Chose One")
                                .setCancelable(true)
                                .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        StudentHome.this.finish();
                                        Intent std = new Intent(StudentHome.this, RegisterTeacher.class);
                                        startActivity(std);
                                    }
                                })
                                .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        StudentHome.this.finish();
                                        Intent std = new Intent(StudentHome.this, RegisterStudent.class);
                                        startActivity(std);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        Intent list_chat_intent = new Intent(StudentHome.this, TeacherList.class);
                        startActivity(list_chat_intent);
                    }
                }
            });
        }
        //end of ifs and elses
    }

    public void headerClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.profileBtn:
                Intent pb = new Intent(StudentHome.this, StudentProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:
               /* finish();
                Intent hm = new Intent(StudentHome.this, StudentHome.class);
                startActivity(hm);*/
                break;
            case R.id.logoutBtn:
                sp.clearPref();
                finish();
                Intent i = new Intent(StudentHome.this, FirstScreen.class);
                startActivity(i);
                break;
        }
    }


    public void updateUserInfo() {

        final String username1 = sp.getStr(sp.STD_USER_NAME);
        final String password1 = sp.getStr(sp.STD_PASS);

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
                            String password = obj.getString("stdPassword");
                            String gender = obj.getString("stdGender");
                            String age = obj.getString("stdAge");
                            String nmbr = obj.getString("stdPhNmbr");
                            String image = obj.getString("stdimage");
                            String tecEmail = obj.getString("tecEmail");
                            String tecUniqueName = obj.getString("tecUniqueName");
                            String reqStatus = obj.getString("reqStatus");

                            if (username1.equals(username) && password1.equals(password)) {

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


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("useremail", uemail);
                map.put("userpass", upass);
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

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
