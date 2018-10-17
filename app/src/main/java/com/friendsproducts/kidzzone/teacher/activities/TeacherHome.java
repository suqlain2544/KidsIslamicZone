package com.friendsproducts.kidzzone.teacher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.teacher.adopters.TecOwnListAdaptor;
import com.friendsproducts.kidzzone.teacher.datamodelclasses.TecOwnListData;

import java.util.ArrayList;
import java.util.List;

public class TeacherHome extends AppCompatActivity {

    RecyclerView recyclerView;
    TecOwnListAdaptor ol_adaptor;
    List<TecOwnListData> o_List;
    Sharepref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        sp = new Sharepref(this);

        o_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.thome_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(TeacherHome.this));
        TecOwnListData tecData1 = new TecOwnListData("Students List", "طلباء کی فہرست", "stlist");
        TecOwnListData tecData2 = new TecOwnListData("Students Request", "طالب علموں کی درخواستیں", "stdreq");
        TecOwnListData tecData3 = new TecOwnListData("Hijri Calender", "حجری کیلنڈر", "hcalender");
        TecOwnListData tecData4 = new TecOwnListData("Namaz Alarm", "نماز الارم", "namazalarm");
        o_List.add(tecData1);
        o_List.add(tecData2);
        o_List.add(tecData3);
        o_List.add(tecData4);

        ol_adaptor = new TecOwnListAdaptor(TeacherHome.this, o_List);
        recyclerView.setAdapter(ol_adaptor);

    }

    public void headerClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.profileBtn:
                Intent pb = new Intent(getApplicationContext(), TeacherProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:
                finish();
                Intent hm = new Intent(TeacherHome.this, TeacherHome.class);
                startActivity(hm);
                break;
            case R.id.logoutBtn:
                sp.clearPref();
                finish();
                Intent i = new Intent(getApplicationContext(), FirstScreen.class);
                startActivity(i);
                break;
        }
    }

}
