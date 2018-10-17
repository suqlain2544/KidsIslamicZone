package com.friendsproducts.kidzzone.student.activities.main;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.student.activities.gkactivities.SliderActivity;

import java.io.IOException;
import java.io.InputStream;

public class NamazDetail extends AppCompatActivity {

    String[] data;
    ImageView namaz_img,audioImg;
    TextView arabicTxt,urduTxt;
    Bitmap bitmap;
    int countR = 0 , countP;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_namaz);

        data=getIntent().getStringArrayExtra("data");

        namaz_img = (ImageView) findViewById(R.id.namaz_img);
        audioImg = (ImageView) findViewById(R.id.audioImg);
        arabicTxt = (TextView) findViewById(R.id.arabicTxt);
        urduTxt = (TextView) findViewById(R.id.urduTxt);
        bitmap = getBitmapFromAsset(data[0]);
        namaz_img.setImageBitmap(bitmap);

        arabicTxt.setText(data[1]);
        urduTxt.setText(data[2]);
        audioImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCountP() == 0) {
                    audioImg.setImageDrawable(getResources().getDrawable(R.drawable.stopicon));
                    countP = 1;
                    mediaPlayer = new MediaPlayer();
                    try {
                        AssetFileDescriptor afd= getAssets().openFd(data[0]+".mp3");
                        mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                } else if (getCountP() == 1) {
                    audioImg.setImageDrawable(getResources().getDrawable(R.drawable.playicon));
                    countP = 0;
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                }
            }
        });

    }
    public int getCountP() {
        return countP;
    }

    private Bitmap getBitmapFromAsset(String id) {
        AssetManager assetManager = getAssets();
        InputStream stream = null;
        try {
            stream = assetManager.open(id + ".jpg");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
            mediaPlayer.stop();
            mediaPlayer.release();

        return true;
    }
}
