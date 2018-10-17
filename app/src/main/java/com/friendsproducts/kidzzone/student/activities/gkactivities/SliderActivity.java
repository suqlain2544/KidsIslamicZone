package com.friendsproducts.kidzzone.student.activities.gkactivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
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
import com.friendsproducts.kidzzone.student.adopters.slider.SliderAdoptor;

import java.io.IOException;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class SliderActivity extends AppCompatActivity {
    private String[] XMEN ;
    private static ViewPager mPager;
    private static int currentPage = 0;


    //guest interface end
    //guest interface start
    SharedPreferences sp;
    String username, uemail, tecEmail, reqStatus;
    private ArrayList<String> XMENArray = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gk_multi_image);

        XMEN = getIntent().getStringArrayExtra("imagearray");
        //guest start


        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        username = sp.getString("stdUserName", "");
        uemail = sp.getString("stdEmail", "");
        tecEmail = sp.getString("tecEmail", "");
        reqStatus = sp.getString("reqStatus", "");

        //guest end
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdoptor(getApplicationContext(), XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        if (currentPage == XMEN.length) {
            currentPage = 0;
        }
        mPager.setCurrentItem(currentPage++, true);



    }



}
