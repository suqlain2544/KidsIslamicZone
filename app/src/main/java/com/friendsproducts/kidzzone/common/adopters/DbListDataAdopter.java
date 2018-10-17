package com.friendsproducts.kidzzone.common.adopters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.Helper_Classes.URLs;
import com.friendsproducts.kidzzone.Helper_Classes.VolleySingleton;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.common.datamodelclasses.DbListData;
import com.friendsproducts.kidzzone.student.activities.main.StudentHome;
import com.friendsproducts.kidzzone.teacher.activities.Chat;
import com.friendsproducts.kidzzone.teacher.activities.StudentsList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.support.design.R.styleable.RecyclerView;
import static android.support.v7.recyclerview.R.styleable.RecyclerView;
import static android.support.v7.recyclerview.R.styleable.RecyclerView;
import static com.friendsproducts.kidzzone.R.styleable.RecyclerView;

/**
 * Created by Suqlain Ahmad.
 */

public class DbListDataAdopter extends RecyclerView.Adapter<DbListDataAdopter.MyView> {

    ProgressDialog progressDialog;
    String className;
    private Context ctx;
    private List<DbListData> dbDataList;

    public DbListDataAdopter(Context ctx, List<DbListData> dbDataList, String className) {
        this.ctx = ctx;
        this.dbDataList = dbDataList;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        view = layoutInflater.inflate(R.layout.db_list_data_cardview, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView holder, final int position) {

        final DbListData data = dbDataList.get(position);
        Glide.with(ctx)
                .load(data.getImage())
                .into(holder.cv_image);
        holder.cv_name.setText(data.getName());
        holder.cv_email.setText(data.getEmail());
        holder.cv_nmbr.setText(data.getNmbr());
        if (getClassName().equals("TeacherList")) {
            holder.cv_Btn.setText("Send Request");
        } else if (getClassName().equals("StudentsList")) {
            holder.cv_Btn.setText("Chat");
        } else if (getClassName().equals("StudentRequestList")) {
            holder.cv_Btn.setText("Accept");
        }

        if (holder.cv_Btn.getText().equals("Accept")) {
            holder.cv_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Sharepref sp = new Sharepref(ctx);
                    final String tecMail = sp.getStr(sp.TEC_EMAIL);
                    progressDialog = new ProgressDialog(ctx);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_Accept_REQUEST,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        if (response.equals("Request Accepted")) {
                                            // To Dismiss progress dialog
                                            progressDialog.dismiss();

                                           Intent i1 = new Intent(ctx.getApplicationContext(), StudentsList.class);
                                            i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            ctx.startActivity(i1);

                                            Toast.makeText(ctx, response, Toast.LENGTH_LONG).show();
                                        } else {
                                            // To Dismiss progress dialog
                                            progressDialog.dismiss();
                                            Toast.makeText(ctx, response, Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception e) {
                                        progressDialog.dismiss();
                                        //Toast.makeText(getApplicationContext(), "Incorrect User Name or Password", Toast.LENGTH_LONG).show();
                                        Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // To Dismiss progress dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("stdEmail", data.getEmail());
                            params.put("techEmail", tecMail);
                            params.put("reqState","1");
                            return params;
                        }
                    };

                    VolleySingleton.getInstance(ctx).addToRequestQueue(stringRequest);
                    // Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder.cv_Btn.getText().equals("Chat")) {
            holder.db_list_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ctx, "Chat Start", Toast.LENGTH_SHORT).show();
                    Intent cour = new Intent(ctx.getApplicationContext(), Chat.class);
                    cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(ctx,"chat with "+data.getUniquename(),Toast.LENGTH_LONG).show();
                    cour.putExtra("uniqueName",data.getUniquename());
                    ctx.startActivity(cour);
                }
            });

        } else if (holder.cv_Btn.getText().equals("Send Request")) {
            holder.cv_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final SharedPreferences sp = ctx.getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sp.edit();
                    final String stdMail = sp.getString("stdEmail", "");
                    progressDialog = new ProgressDialog(ctx);
                    progressDialog.setMessage("Sending Request...");
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_SEND_REQUEST,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        if (response.equals("Request Sent")) {
                                            // To Dismiss progress dialog
                                            progressDialog.dismiss();
                                            editor.putString("reqStatus", "0");
                                            editor.putString("tecEmail", data.getEmail());
                                            editor.commit();
                                            Intent i1 = new Intent(ctx.getApplicationContext(), StudentHome.class);
                                            i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            ctx.startActivity(i1);

                                            Toast.makeText(ctx, response, Toast.LENGTH_LONG).show();
                                        } else {
                                            // To Dismiss progress dialog
                                            progressDialog.dismiss();
                                            Toast.makeText(ctx, response, Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception e) {
                                        progressDialog.dismiss();
                                        //Toast.makeText(getApplicationContext(), "Incorrect User Name or Password", Toast.LENGTH_LONG).show();
                                        Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // To Dismiss progress dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("stdEmail", stdMail);
                            params.put("techEmail", data.getEmail());
                            params.put("tecUniqueName", data.getUniquename());
                            return params;
                        }
                    };

                    VolleySingleton.getInstance(ctx).addToRequestQueue(stringRequest);
                    // Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dbDataList.size();
    }

    public static class MyView extends RecyclerView.ViewHolder {
        private ImageView cv_image;
        private TextView cv_name, cv_email, cv_nmbr,cv_Btn;
        private CardView db_list_cardview;

        public MyView(View itemView) {
            super(itemView);
            cv_image = (ImageView) itemView.findViewById(R.id.cv_image);
            cv_name = (TextView) itemView.findViewById(R.id.cv_name);
            cv_email = (TextView) itemView.findViewById(R.id.cv_email);
            cv_nmbr = (TextView) itemView.findViewById(R.id.cv_nmbr);
            cv_Btn = (TextView) itemView.findViewById(R.id.cv_btn);
            db_list_cardview = (CardView) itemView.findViewById(R.id.db_list_cardview);

        }
    }

}
