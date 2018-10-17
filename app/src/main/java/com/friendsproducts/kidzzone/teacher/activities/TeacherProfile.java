package com.friendsproducts.kidzzone.teacher.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.R;

public class TeacherProfile extends AppCompatActivity {


    TextView dbname, dbemail, dbnmbr, dbenrol, dbgender;
    Sharepref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbname = (TextView) findViewById(R.id.dbname);
        dbemail = (TextView) findViewById(R.id.dbemail);
        dbnmbr = (TextView) findViewById(R.id.dbnmbr);
        dbenrol = (TextView) findViewById(R.id.dbenrol);
        dbgender = (TextView) findViewById(R.id.dbgender);

        /*Show name*/
        String username = sp.getStr(sp.TEC_NAME);
        String tecUniqueName = sp.getStr(sp.TEC_UNIQUE_NAME);
        String uemail = sp.getStr(sp.TEC_EMAIL);
        String ugender = sp.getStr(sp.TEC_GENDER);
        String upass = sp.getStr(sp.TEC_PASS);
        String uqual = sp.getStr(sp.TEC_QUALI);
        String unmbr = sp.getStr(sp.TEC_NMBR);
        String uimage = sp.getStr(sp.TEC_IMAGE);

        dbname.setText(username);
        dbemail.setText(uemail);
        dbgender.setText(ugender);
        dbnmbr.setText(unmbr);
        dbenrol.setText(uqual);

    }
}
