package com.friendsproducts.kidzzone.student.adopters.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.student.activities.gkactivities.SliderActivity;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class GKAdaptor extends RecyclerView.Adapter<GKAdaptor.MyView> {


    private Context ctx;
    private List<MainListData> mainListData;

    public GKAdaptor(Context ctx, List<MainListData> mainListData) {
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
                String[] e_list = {"1-Imaniyat o Aqaid", "2-Over Beloved  Prophet ", "3-Members of Islam","4-Angels","5-","6-","7-Heavenly books","8-Holy Quran","9-","10-","11-","12-","13-Adahn","14-Conditions of Prayer"};
                try {
                    if (ol_Data.getEnglish().equals(e_list[0])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk1"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[1])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk2"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[2])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk3","gk4"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[3])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk5"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[4])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk6"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[5])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk7","gk8"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[6])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk9"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[7])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk10"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[8])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk11","gk12","gk13"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[9])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk14","gk15"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[10])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk16","gk17"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[11])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk18","gk19","gk20"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[12])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk21"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[13])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk22","gk23","gk24","gk25","gk26","gk27","gk28"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[14])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk29","gk30","gk31","gk32","gk33","gk34"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[15])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk35"};
                        cour.putExtra("imagearray",x);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[16])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), SliderActivity.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String x[] = {"gk36","gk37","gk38","gk30"};
                        cour.putExtra("imagearray",x);
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
