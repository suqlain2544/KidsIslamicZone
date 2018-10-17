package com.friendsproducts.kidzzone.student.activities.nqactivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.student.activities.main.StudentHome;
import com.friendsproducts.kidzzone.student.activities.main.StudentProfile;
import com.friendsproducts.kidzzone.student.adopters.nuraniqaida.AdvanceNuraniQaidaAdaptor;
import com.friendsproducts.kidzzone.student.datamodelclasses.NQData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdvanceNuraniQaida extends AppCompatActivity {

    //guest interface start
    ImageView profileIv, logoutIv;
    SharedPreferences sp;
    String username, uemail;

    //guest interface end
    RecyclerView recyclerView;
    AdvanceNuraniQaidaAdaptor ol_adaptor;
    List<NQData> o_List;
   /*Record Code*/

    int countR = 0 , countP;
    FloatingActionButton rfab, pfab;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    public int getCountR() {
        return countR;
    }

    public int getCountP() {
        return countP;
    }

    /*Recode Code End*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_nuraniqaida);
        //guest start
        profileIv = (ImageView) findViewById(R.id.profileBtn);
        logoutIv = (ImageView) findViewById(R.id.logoutBtn);
        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        username = sp.getString("stdUserName", "");
        uemail = sp.getString("stdEmail", "");
        if (username.equals("") && uemail.equals("")) {
            profileIv.setVisibility(View.INVISIBLE);
            logoutIv.setVisibility(View.INVISIBLE);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //guest end
         /*Record Code*/
        rfab = (FloatingActionButton) findViewById(R.id.recordfab);
        pfab = (FloatingActionButton) findViewById(R.id.playfab);

        rfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCountR() == 0) {
                    try {
                        rfab.setImageDrawable(getResources().getDrawable(R.drawable.stopicon));
                        countR = 1;
                        pathSave = Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/"
                                + "kids_zone.3gp";
                        setupMediaRecorder();
                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else if (getCountR() == 1) {
                    rfab.setImageDrawable(getResources().getDrawable(R.drawable.recordicon));
                    countR = 0;
                    mediaRecorder.stop();
                }
            }
        });

        pfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCountP() == 0) {
                    pfab.setImageDrawable(getResources().getDrawable(R.drawable.stopicon));
                    countP = 1;
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                } else if (getCountP() == 1) {
                    pfab.setImageDrawable(getResources().getDrawable(R.drawable.playicon));
                    countP = 0;
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setupMediaRecorder();
                    }
                }
            }
        });

        /*Record Code END*/
        String[] nameArray = getIntent().getStringArrayExtra("imagearray");
                o_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.eor_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(AdvanceNuraniQaida.this, 5));
        for (int i = 0; i < nameArray.length; i++) {
            o_List.add(new NQData(nameArray[i], nameArray[i]));
        }

        ol_adaptor = new AdvanceNuraniQaidaAdaptor(getApplicationContext(), o_List,nameArray);
        recyclerView.setAdapter(ol_adaptor);
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    public void headerClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.profileBtn:
                Intent pb = new Intent(getApplicationContext(), StudentProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:

                Intent hm = new Intent(getApplicationContext() ,StudentHome.class);
                finish();
                startActivity(hm);
                break;
            case R.id.logoutBtn:
                SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(getApplicationContext(), FirstScreen.class);
                finish();
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
