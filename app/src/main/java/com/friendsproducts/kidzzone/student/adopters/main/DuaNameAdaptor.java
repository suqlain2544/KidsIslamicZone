package com.friendsproducts.kidzzone.student.adopters.main;

/**
 * Created by Ali Hassan on 7/23/2018.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.student.activities.main.Duain;
import com.friendsproducts.kidzzone.student.activities.main.NuraniQaida;
import com.friendsproducts.kidzzone.student.datamodelclasses.DuaNameData;

import java.util.List;

public class DuaNameAdaptor extends RecyclerView.Adapter<DuaNameAdaptor.MyView> {

    private Context ctx;
    private List<DuaNameData> ownListData;

    public DuaNameAdaptor(Context ctx, List<DuaNameData> ownListData) {
        this.ctx = ctx;
        this.ownListData = ownListData;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        view = layoutInflater.inflate(R.layout.dua_name_cv, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView holder, final int position) {
        final DuaNameData ol_Data = ownListData.get(position);

        holder.dua_name_urdu.setText(ol_Data.getDua_name_urdu());
        holder.dua_name_english.setText(ol_Data.getDua__name_english());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent cour = new Intent(ctx.getApplicationContext(), Duain.class);
                    cour.putExtra("arabic", ol_Data.getDuaArebic());
                    cour.putExtra("urdu", ol_Data.getDuaUrdu());
                    cour.putExtra("eng", ol_Data.getDuaEng());
                    cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(cour);
                }catch(Exception e)
                {
                    Toast.makeText(ctx.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ownListData.size();
    }

    public static class MyView extends RecyclerView.ViewHolder {

        CardView cardView;
        private TextView dua_name_urdu, dua_name_english;

        public MyView(View itemView) {
            super(itemView);
            dua_name_urdu = (TextView) itemView.findViewById(R.id.dua_name_urdu);
            dua_name_english = (TextView) itemView.findViewById(R.id.dua_name_english);
            cardView = (CardView) itemView.findViewById(R.id.duaname_cv);

        }
    }

}

