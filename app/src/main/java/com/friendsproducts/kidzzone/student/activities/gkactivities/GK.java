package com.friendsproducts.kidzzone.student.activities.gkactivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.RegisterStudent;
import com.friendsproducts.kidzzone.RegisterTeacher;
import com.friendsproducts.kidzzone.student.activities.main.Chat;
import com.friendsproducts.kidzzone.student.activities.main.StudentHome;
import com.friendsproducts.kidzzone.student.activities.main.StudentProfile;
import com.friendsproducts.kidzzone.student.activities.main.TeacherList;
import com.friendsproducts.kidzzone.student.adopters.main.GKAdaptor;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import java.util.ArrayList;
import java.util.List;

public class GK extends AppCompatActivity {

    String[] e_list = {"1-Imaniyat o Aqaid", "2-Over Beloved  Prophet ", "3-Members of Islam","4-Angels","5-","6-","7-Heavenly books","8-Holy Quran","9-","10-","11-","12-","13-Adahn","14-Conditions of Prayer"};
    String[] u_list = {"1-ایمانیات و عقائد", "2-محبوب نبی صلی اللہ علیہ وسلم", "3-ارکان اسلام","4-فرشتے","5-","6-","7-آسمانی کتابیں","8-قرآن مجید","9-تلاوت قرآن مجید کے آداب","10-صحابہ کرام","11-اولیاۓ کرام","12-عبادات","13-اذان","14-نماز کی شرائط"};
    String[] image = {"image", "image", "image","image", "image", "image","image", "image", "image","image", "image", "image","image", "image"};

    FloatingActionButton list_chat;

    //guest interface start
    ImageView profileIv, logoutIv;
    SharedPreferences sp;
    String username, uemail, tecEmail, reqStatus;
    //guest interface end

    RecyclerView recyclerView;
    GKAdaptor ol_adaptor;
    List<MainListData> o_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gk);
        //guest start
        profileIv = (ImageView) findViewById(R.id.profileBtn);
        logoutIv = (ImageView) findViewById(R.id.logoutBtn);
        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        username = sp.getString("stdUserName", "");
        uemail = sp.getString("stdEmail", "");
        tecEmail = sp.getString("tecEmail", "");
        reqStatus = sp.getString("reqStatus", "");
        if (username.equals("") && uemail.equals("")) {
            profileIv.setVisibility(View.INVISIBLE);
            logoutIv.setVisibility(View.INVISIBLE);
        }
        //guest end
        list_chat = (FloatingActionButton) findViewById(R.id.list_chat);
        o_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.gk_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(GK.this));
        for (int i = 0; i < e_list.length; i++) {
            MainListData tecData1 = new MainListData(e_list[i], u_list[i], image[i]);
            o_List.add(tecData1);
        }


        ol_adaptor = new GKAdaptor(getApplicationContext(), o_List);
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(GK.this);
                            builder.setMessage("Chose One")
                                    .setCancelable(true)
                                    .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            GK.this.finish();
                                            Intent std = new Intent(GK.this, RegisterTeacher.class);
                                            startActivity(std);
                                        }
                                    })
                                    .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            GK.this.finish();
                                            Intent std = new Intent(GK.this, RegisterStudent.class);
                                            startActivity(std);
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            Intent list_chat_intent = new Intent(GK.this, TeacherList.class);
                            startActivity(list_chat_intent);
                        }
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("0")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(GK.this, "Request Pending " + reqStatus, Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("1")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkInternet()) {
                            Intent cs = new Intent(GK.this, Chat.class);
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
                        if (checkInternet()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GK.this);
                            builder.setMessage("Chose One")
                                    .setCancelable(true)
                                    .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            GK.this.finish();
                                            Intent std = new Intent(GK.this, RegisterTeacher.class);
                                            startActivity(std);
                                        }
                                    })
                                    .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            GK.this.finish();
                                            Intent std = new Intent(GK.this, RegisterStudent.class);
                                            startActivity(std);
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Required", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Intent list_chat_intent = new Intent(GK.this, TeacherList.class);
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
                Intent pb = new Intent(GK.this, StudentProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:

                Intent hm = new Intent(GK.this, StudentHome.class);
                finish();
                startActivity(hm);
                break;
            case R.id.logoutBtn:
                SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(GK.this, FirstScreen.class);
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

}
