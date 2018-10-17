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

import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.RegisterStudent;
import com.friendsproducts.kidzzone.RegisterTeacher;
import com.friendsproducts.kidzzone.student.adopters.main.AllCoursesAdaptor;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import java.util.ArrayList;
import java.util.List;

public class AllCourses extends AppCompatActivity {


    FloatingActionButton list_chat;

    RecyclerView recyclerView;
    AllCoursesAdaptor ol_adaptor;
    List<MainListData> o_List;
    //guest interface start
    ImageView profileIv, logoutIv;
    Sharepref sp;
    String username, uemail, tecEmail, reqStatus;

    //guest interface end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);

        //guest start

        profileIv = (ImageView) findViewById(R.id.profileBtn);
        logoutIv = (ImageView) findViewById(R.id.logoutBtn);

        sp = new Sharepref(this);
        username = sp.getStr(sp.STD_USER_NAME);
        uemail = sp.getStr(sp.STD_EMAIL);
        tecEmail = sp.getStr(sp.TECH_EMAIL);
        reqStatus = sp.getStr(sp.REQ_STATUS);


        if (username.equals("") && uemail.equals("")) {
            profileIv.setVisibility(View.INVISIBLE);
            logoutIv.setVisibility(View.INVISIBLE);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //guest end
        list_chat = (FloatingActionButton) findViewById(R.id.list_chat);
        o_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.ac_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllCourses.this));
        MainListData tecData1 = new MainListData("Nurani Qaida", "نورانی قائدہ", "nq");
        MainListData tecData2 = new MainListData("Namaz", "نماز", "namaz");
        MainListData tecData3 = new MainListData("Kalmay", "کلمے", "kalmay");
        MainListData tecData4 = new MainListData("Duaain", "دعائیں", "dua");
        o_List.add(tecData1);
        o_List.add(tecData2);
        o_List.add(tecData3);
        o_List.add(tecData4);

        ol_adaptor = new AllCoursesAdaptor(AllCourses.this, o_List);
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(AllCourses.this);
                                builder.setMessage("Chose One")
                                        .setCancelable(true)
                                        .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                AllCourses.this.finish();
                                                Intent std = new Intent(AllCourses.this, RegisterTeacher.class);
                                                startActivity(std);
                                            }
                                        })
                                        .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                AllCourses.this.finish();
                                                Intent std = new Intent(AllCourses.this, RegisterStudent.class);
                                                startActivity(std);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Internet Required", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Intent list_chat_intent = new Intent(AllCourses.this, TeacherList.class);
                            startActivity(list_chat_intent);
                        }
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("0")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(AllCourses.this, "Request Pending " + reqStatus, Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("1")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkInternet()) {
                            Intent cs = new Intent(AllCourses.this, Chat.class);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(AllCourses.this);
                        builder.setMessage("Chose One")
                                .setCancelable(true)
                                .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        AllCourses.this.finish();
                                        Intent std = new Intent(AllCourses.this, RegisterTeacher.class);
                                        startActivity(std);
                                    }
                                })
                                .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        AllCourses.this.finish();
                                        Intent std = new Intent(AllCourses.this, RegisterStudent.class);
                                        startActivity(std);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        Intent list_chat_intent = new Intent(AllCourses.this, TeacherList.class);
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
                Intent pb = new Intent(AllCourses.this, StudentProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:

                Intent hm = new Intent(AllCourses.this, StudentHome.class);
                finish();
                startActivity(hm);
                break;
            case R.id.logoutBtn:

                sp.clearPref();
                Intent i = new Intent(AllCourses.this, FirstScreen.class);
                finish();
                startActivity(i);
                break;
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }
}
