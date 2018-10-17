package com.friendsproducts.kidzzone.teacher.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRequestList extends AppCompatActivity {

    RecyclerView recyclerView;
    DbListDataAdopter adaptor;
    List<DbListData> data;
    ProgressDialog progressDialog;
    Sharepref sp;
    String uemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_list);
        sp = new Sharepref(this);
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REQ_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray tecs = new JSONArray(response);
                    for (int i = 0; i < tecs.length(); i++) {
                        JSONObject techObject = tecs.getJSONObject(i);
                        String name = techObject.getString("stdUserName");
                        String stdUniqueUser = techObject.getString("stdUniqueUser");
                        String email = techObject.getString("stdEmail");
                        String age = techObject.getString("stdAge");
                        String Nmbr = techObject.getString("stdPhNmbr");
                        String gender = techObject.getString("stdGender");
                        String image = techObject.getString("stdimage");
                        if (!name.equals("")) {
                            // To Dismiss progress dialog
                            progressDialog.dismiss();

                        } else {
                            // To Dismiss progress dialog
                            progressDialog.dismiss();
                            Toast.makeText(StudentRequestList.this, "No data found", Toast.LENGTH_LONG).show();
                        }
                        DbListData tecData = new DbListData(name, stdUniqueUser, email, age, Nmbr, gender, image);
                        data.add(tecData);

                    }
                    adaptor = new DbListDataAdopter(StudentRequestList.this, data, "StudentRequestList");
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
                Toast.makeText(StudentRequestList.this, "Internet Slow/Not Connected", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                uemail = sp.getStr(sp.TEC_EMAIL);
                Map<String, String> map = new HashMap<String, String>();
                map.put("tecEmail", uemail);
                map.put("reqState", "0");
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
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
                Intent hm = new Intent(StudentRequestList.this, TeacherHome.class);
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
