package com.friendsproducts.kidzzone.teacher.adopters;

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
import com.friendsproducts.kidzzone.hijricalender.CaldroidSampleActivity;
import com.friendsproducts.kidzzone.namaz_qibla.Muazzin;
import com.friendsproducts.kidzzone.teacher.activities.StudentRequestList;
import com.friendsproducts.kidzzone.teacher.activities.StudentsList;
import com.friendsproducts.kidzzone.teacher.datamodelclasses.TecOwnListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class TecOwnListAdaptor extends RecyclerView.Adapter<TecOwnListAdaptor.MyView> {

    private Context ctx;
    private List<TecOwnListData> ownListData;

    public TecOwnListAdaptor(Context ctx, List<TecOwnListData> ownListData) {
        this.ctx = ctx;
        this.ownListData = ownListData;
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
        final TecOwnListData ol_Data = ownListData.get(position);

        holder.listEng.setText(ownListData.get(position).getEnglish());
        holder.listUrdu.setText(ownListData.get(position).getUrdu());
        Bitmap bitmap = getBitmapFromAsset(ownListData.get(position).getImage());
        holder.listiv.setImageBitmap(bitmap);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent tp = new Intent(ctx, TeacherProfile.class);
                ctx.startActivity(tp);*/
                try {
                    if (ol_Data.getEnglish().equals("Students List")) {

                        Intent cour = new Intent(ctx, StudentsList.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Hijri Calender")) {
                        Intent nq = new Intent(ctx, CaldroidSampleActivity.class);
                        nq.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(nq);
                    } else if (ol_Data.getEnglish().equals("Students Request")) {

                        Intent cour = new Intent(ctx, StudentRequestList.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Namaz Alarm")) {

                        Intent cour = new Intent(ctx, Muazzin.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        return ownListData.size();
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
