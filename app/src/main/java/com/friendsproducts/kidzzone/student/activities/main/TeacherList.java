package com.friendsproducts.kidzzone.student.activities.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.Helper_Classes.URLs;
import com.friendsproducts.kidzzone.Helper_Classes.VolleySingleton;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.common.adopters.DbListDataAdopter;
import com.friendsproducts.kidzzone.common.datamodelclasses.DbListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeacherList extends AppCompatActivity {

    RecyclerView recyclerView;
    DbListDataAdopter adaptor;
    List<DbListData> data;
    ProgressDialog progressDialog;

    Sharepref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_list);

        sp = new Sharepref(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressDialog = new ProgressDialog(this);////
        data = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.db_list_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading List...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_TCHR_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray tecs = new JSONArray(response);
                    for (int i = 0; i < tecs.length(); i++) {
                        JSONObject techObject = tecs.getJSONObject(i);
                        int id = techObject.getInt("id");
                        String name = techObject.getString("tecName");
                        String tecUniqueName = techObject.getString("tecUniqueName");
                        String email = techObject.getString("tecEmail");
                        String tecQual = techObject.getString("tecQual");
                        String tecNmbr = techObject.getString("tecNmbr");
                        String tecGender = techObject.getString("tecGender");
                        String timage = techObject.getString("tecUrl");
                        if (!name.equals("")) {
                            // To Dismiss progress dialog
                            progressDialog.dismiss();

                        } else {
                            // To Dismiss progress dialog
                            progressDialog.dismiss();
                            // Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                        }
                        DbListData tecData = new DbListData(name, tecUniqueName, email, tecQual, tecNmbr, tecGender, timage);
                        data.add(tecData);

                    }
                    adaptor = new DbListDataAdopter(TeacherList.this, data, "TeacherList");
                    recyclerView.setAdapter(adaptor);


                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(TeacherList.this, "Internet Slow/Not Connected", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void headerClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.profileBtn:
                Intent pb = new Intent(TeacherList.this, StudentProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:
                finish();
                Intent hm = new Intent(TeacherList.this, StudentHome.class);
                startActivity(hm);
                break;
            case R.id.logoutBtn:
                sp.clearPref();
                finish();
                Intent i = new Intent(TeacherList.this, FirstScreen.class);
                startActivity(i);
                break;
        }
    }
}
