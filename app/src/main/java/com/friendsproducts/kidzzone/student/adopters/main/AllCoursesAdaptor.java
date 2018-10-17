package com.friendsproducts.kidzzone.student.adopters.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.RegisterStudent;
import com.friendsproducts.kidzzone.student.activities.gkactivities.SliderActivity;
import com.friendsproducts.kidzzone.student.activities.main.DuaName;
import com.friendsproducts.kidzzone.student.activities.main.Namazz;
import com.friendsproducts.kidzzone.student.activities.main.NuraniQaida;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class AllCoursesAdaptor extends RecyclerView.Adapter<AllCoursesAdaptor.MyView> {

    private Context ctx;
    private List<MainListData> mainListData;

    public AllCoursesAdaptor(Context ctx, List<MainListData> mainListData) {
        this.ctx = ctx;
        this.mainListData = mainListData;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        view = layoutInflater.inflate(R.layout.mainlist, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView holder, final int position) {
        final MainListData ol_Data = mainListData.get(position);

        holder.listEng.setText(mainListData.get(position).getEnglish());
        holder.listUrdu.setText(mainListData.get(position).getUrdu());
        Bitmap bitmap = getBitmapFromAsset(mainListData.get(position).getImage());
        holder.listiv.setImageBitmap(bitmap);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent tp = new Intent(ctx, TeacherProfile.class);
                ctx.startActivity(tp);*/
                try {
                    if (ol_Data.getEnglish().equals("Nurani Qaida")) {
                        Intent cour = new Intent(ctx.getApplicationContext(), NuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Duaain")) {
                        Intent cour = new Intent(ctx.getApplicationContext(), DuaName.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // String ar[] = {"npic1", "npic2", "npic3", "npic4", "npic5"};
                        // cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Namaz")) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                        builder.setMessage("Chose One")
                                .setCancelable(true)
                                .setPositiveButton("Male", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //finish();
                                        Intent cour = new Intent(ctx.getApplicationContext(), Namazz.class);
                                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        String img[] = {"1niyat_m", "2takbeer_m", "3qayam_m", "4ruku_m", "5qaumah_m", "6sajdah_m", "7jalsa_m", "8sajdah_m", "9qayam_m", "10ruku_m", "11qauma_m", "12sajda_m", "13jalsa_m", "14sajdah_m","15tashad_m","16salam_m","17salam_m","18dua_m"};
                                        String arabic[] = {"Niyat" , "Takbeer", "Qayam", "Ruku", "Qaumah", "Sajdah", "Jalsa"
                                                , "Sajdah", "Qayam"
                                                , "Ruku", "Qaumah","Sajda","Jalsa","Sajdah", "Tashad","Salam","Salam", "Dua"};
                                        String urdu[] = {"نیت", "تکبیر", "قیام", "رکوع", "قیامہ", "سجدہ", "جلسہ", "سجدہ", "قیام", "رکوع", "قیامہ", "سجدہ", "جلسہ", "سجدہ","تشہد","سلام","سلام","دعا"};
                                        cour.putExtra("img", img);
                                        cour.putExtra("arabic", arabic);
                                        cour.putExtra("urdu", urdu);
                                        ctx.startActivity(cour);
                                    }
                                })
                                .setNegativeButton("Female", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent cour = new Intent(ctx.getApplicationContext(), Namazz.class);
                                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        String img[] = {"1niyat_f", "2takbeer_f", "3qayam_f", "4ruku_f", "5qauma_f", "6sajdah_f", "7jalsa_f", "8sajdah_f", "9qayam_f", "10ruku_f", "11qauma_f", "12sajda_f", "13jalsa_f", "14sajdah_f","15tashad_f","16salam_f","17salam_f"};
                                        String arabic[] = {"Niyat" , "Takbeer", "Qayam", "Ruku", "Qaumah", "Sajdah", "Jalsa"
                                                , "Sajdah", "Qayam"
                                                , "Ruku", "Qaumah","Sajda","Jalsa","Sajdah", "Tashad","Salam","Salam"};
                                        String urdu[] = {"نیت", "تکبیر", "قیام", "رکوع", "قیامہ", "سجدہ", "جلسہ", "سجدہ", "قیام", "رکوع", "قیامہ", "سجدہ", "جلسہ", "سجدہ","تشہد","سلام","سلام"};
                                        cour.putExtra("img", img);
                                        cour.putExtra("arabic", arabic);
                                        cour.putExtra("urdu", urdu);
                                        ctx.startActivity(cour);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();


                    } else if (ol_Data.getEnglish().equals("Kalmay")) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"kpic1", "kpic2", "kpic3", "kpic4", "kpic5", "kpic6"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    }
                } catch (Exception e) {
                    Toast.makeText(ctx, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainListData.size();
    }

    private Bitmap getBitmapFromAsset(String id) {
        AssetManager assetManager = ctx.getAssets();
        InputStream stream = null;
        try {
            stream = assetManager.open(id + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class MyView extends RecyclerView.ViewHolder {

        CardView cardView;
        private ImageView listiv;
        private TextView listEng, listUrdu;

        public MyView(View itemView) {
            super(itemView);
            listiv = (ImageView) itemView.findViewById(R.id.listiv);
            listEng = (TextView) itemView.findViewById(R.id.listEngtv);
            listUrdu = (TextView) itemView.findViewById(R.id.listUrdutv);
            cardView = (CardView) itemView.findViewById(R.id.listcardview);

        }
    }

}
