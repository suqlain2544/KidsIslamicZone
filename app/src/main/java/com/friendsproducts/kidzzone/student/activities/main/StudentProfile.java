package com.friendsproducts.kidzzone.student.activities.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.R;

public class StudentProfile extends AppCompatActivity {

    TextView dbname, dbemail, dbnmbr, dbenrol, dbgender;
    ImageView profilePic;
    Sharepref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbname = (TextView) findViewById(R.id.dbname);
        dbemail = (TextView) findViewById(R.id.dbemail);
        dbnmbr = (TextView) findViewById(R.id.dbnmbr);
        dbenrol = (TextView) findViewById(R.id.dbenrol);
        dbgender = (TextView) findViewById(R.id.dbgender);
        profilePic = (ImageView) findViewById(R.id.profilePic);

        /*Show name*/
        sp = new Sharepref(this);

        String username = sp.getStr(sp.STD_USER_NAME);


        String uemail = sp.getStr(sp.STD_EMAIL);

        String ugender = sp.getStr(sp.STD_GENDER);

        String upass = sp.getStr(sp.STD_PASS);

        String age = sp.getStr(sp.STD_AGE);

        String nmbr = sp.getStr(sp.STD_PH);

        String image = sp.getStr(sp.STD_IMAGE);
        String tecEmail = sp.getStr(sp.TECH_EMAIL);
        String reqStatus = sp.getStr(sp.REQ_STATUS);

        dbname.setText(username);
        dbemail.setText(uemail);
        dbgender.setText(ugender);
        dbnmbr.setText(nmbr);
        dbenrol.setText(age);

        Glide.with(getApplicationContext())
                .load(image)
                .into(profilePic);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }
}
