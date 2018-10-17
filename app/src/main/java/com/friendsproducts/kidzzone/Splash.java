package com.friendsproducts.kidzzone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.student.activities.main.StudentHome;
import com.friendsproducts.kidzzone.teacher.activities.TeacherHome;

public class Splash extends AppCompatActivity {
    Sharepref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = new Sharepref(this);
        String unique_id = sp.getStr(sp.UNIQUE_ID);
        String username = sp.getStr(sp.STD_USER_NAME);
        String password1 = sp.getStr(sp.STD_PASS);
        String email = sp.getStr(sp.STD_EMAIL);
        String gender = sp.getStr(sp.STD_GENDER);
        final String who = sp.getStr(sp.WHO);

        if (!unique_id.equals("")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //student aur teacher ko diferntaite krna ha
                    if (who.equals("std")) {
                        Intent i = new Intent(Splash.this, StudentHome.class);
                        startActivity(i);
                        finish();
                    } else if (who.equals("tec")) {
                        Intent i = new Intent(Splash.this, TeacherHome.class);
                        startActivity(i);
                        finish();
                    }

                }
            }, 3000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, FirstScreen.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
