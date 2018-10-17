package com.friendsproducts.kidzzone.student.adopters.nuraniqaida;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.student.datamodelclasses.NQData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class ayatAdaptor extends RecyclerView.Adapter<ayatAdaptor.MyView> {
    private Context ctx;
    private List<NQData> nqData;
    String array[] ;

    public ayatAdaptor(Context ctx, List<NQData> nqData, String[] array) {
        this.ctx = ctx;
        this.nqData = nqData;
        this.array = array;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        view=layoutInflater.inflate(R.layout.aayaat_cv,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView holder, final int position) {
        Bitmap bitmap = getBitmapFromAsset(nqData.get(position).getImage());
        holder.card_iv.setImageBitmap(bitmap);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] nameArray  = array; //  Toast.makeText(ctx,"Id is " +nqData.get(position).getImageID(),Toast.LENGTH_LONG).show();
                int i = 0;
                final MediaPlayer mp = new MediaPlayer();
                String arayId="",clickId="";
                String arayname="",clickName="";
                while (nameArray[i]!=nqData.get(position).getImage()) {
                    i++;
                    arayId=nameArray[i];
                    clickId=nqData.get(position).getImage();
                }
                i=0;
                while (nameArray[i]!=nqData.get(position).getName()) {
                    i++;
                    arayname=nameArray[i];
                    clickName=nqData.get(position).getName();
                }
                if (arayId==clickId && arayname==clickName){

                    Toast.makeText(ctx,clickName+".mp3" + arayId + " " + clickId,Toast.LENGTH_LONG).show();
                    try {
                        mp.reset();
                        AssetFileDescriptor afd= ctx.getAssets().openFd(clickName+".mp3");
                        mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        mp.prepare();
                        mp.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return nqData.size();
    }

    public static class MyView extends RecyclerView.ViewHolder{

        private ImageView card_iv;
        CardView cardView;
        public MyView(View itemView) {
            super(itemView);
            card_iv = (ImageView) itemView.findViewById(R.id.ayatimage);
            cardView = (CardView) itemView.findViewById(R.id.ayat_cv);
        }
    }

    private Bitmap getBitmapFromAsset(String id) {
        AssetManager assetManager = ctx.getAssets();
        InputStream stream = null;
        try {
            stream = assetManager.open(id + ".jpg");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
