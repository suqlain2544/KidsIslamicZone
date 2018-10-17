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
import com.friendsproducts.kidzzone.student.activities.nqactivities.AdvanceNuraniQaida;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class NuraniQaidaAdaptor extends RecyclerView.Adapter<NuraniQaidaAdaptor.MyView> {


    private Context ctx;
    private List<MainListData> mainListData;

    public NuraniQaidaAdaptor(Context ctx, List<MainListData> mainListData) {
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
                String[] e_list = {"1-The Alphabets", "2-Joint Letters", "3-The Muqattiat Letter", "4-The Movements", "5-The Tanween", "6-The Tanween & Movment", "7-Standing Fatha,Kasrah,Dhuma", "8-The MaddoLeen", "9-Exercise", "10-Sakoon And Jazam", "11-Exercise of Sakoon", "12-The Tashdeed", "13-Exercise of Tashdeed", "14-Tashdeed with Sakoon", "15-Tashdeed with Tashdeed", "16-Tashdeed with Madah", "17-Ending of Rules"};
                try {
                    if (ol_Data.getEnglish().equals(e_list[0])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch1_5", "ch1_4", "ch1_3", "ch1_2", "ch1_1",
                                "ch1_10", "ch1_9", "ch1_8", "ch1_7", "ch1_6",
                                "ch1_15", "ch1_14", "ch1_13", "ch1_12", "ch1_11",
                                "ch1_20", "ch1_19", "ch1_18", "ch1_17", "ch1_16",
                                "ch1_25", "ch1_24", "ch1_23", "ch1_22", "ch1_21",
                                "ch1_30", "ch1_29", "ch1_28", "ch1_27", "ch1_26"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[1])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch2_5","ch2_4","ch2_3","ch2_2","ch2_1",
                                "ch2_10","ch2_9","ch2_8","ch2_7","ch2_6",
                                "ch2_15","ch2_14","ch2_13","ch2_12","ch2_11",
                                "ch2_20","ch2_19","ch2_18","ch2_17","ch2_16",
                                "ch2_25","ch2_24","ch2_23","ch2_22","ch2_21",
                                "ch2_30","ch2_29","ch2_28","ch2_27","ch2_26",
                                "ch2_35","ch2_34","ch2_33","ch2_32","ch2_31",
                                "ch2_40","ch2_39","ch2_38","ch2_37","ch2_36",
                                "ch2_45","ch2_44","ch2_43","ch2_42","ch2_41",
                                "ch2_50","ch2_49","ch2_48","ch2_47","ch2_46",
                                "ch2_55","ch2_54","ch2_53","ch2_52","ch2_51",
                                "ch2_60","ch2_59","ch2_58","ch2_57","ch2_56",
                                "ch2_65","ch2_64","ch2_63","ch2_62","ch2_61",
                                "ch2_70","ch2_69","ch2_68","ch2_67","ch2_66",
                                "ch2_75","ch2_74","ch2_73","ch2_72","ch2_71",
                                "ch2_80","ch2_79","ch2_78","ch2_77","ch2_76",
                                "ch2_85","ch2_84","ch2_83","ch2_82","ch2_81",
                                "ch2_90","ch2_89","ch2_88","ch2_87","ch2_86",
                                "ch2_95","ch2_94","ch2_93","ch2_92","ch2_91",
                                "ch2_100","ch2_99","ch2_98","ch2_97","ch2_96",
                                "ch2_106","ch2_104","ch2_103","ch2_102","ch2_101",
                                "ch2_110","ch2_109","ch2_108","ch2_107","ch2_106",
                                "ch2_115","ch2_114","ch2_113","ch2_112","ch2_111",
                                "ch2_120","ch2_119","ch2_118","ch2_117","ch2_116",
                                "ch2_125","ch2_124","ch2_123","ch2_122","ch2_121",
                                "dummy","dummy","dummy","ch2_127","ch2_126"
                        };
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[2])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch3_5","ch3_4","ch3_3","ch3_2","ch3_1",
                                "ch3_10","ch3_9","ch3_8","ch3_7","ch3_6",
                                "ch3_15","ch3_14","ch3_13","ch3_12","ch3_11"
                        };
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[3])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch4_5","ch4_4","ch4_3","ch4_2","ch4_1",
                                "ch4_10","ch4_9","ch4_8","ch4_7","ch4_6",
                                "ch4_15","ch4_14","ch4_13","ch4_12","ch4_11",
                                "ch4_20","ch4_19","ch4_18","ch4_17","ch4_16",
                                "ch4_25","ch4_24","ch4_23","ch4_22","ch4_21",
                                "ch4_30","ch4_29","ch4_28","ch4_27","ch4_26",
                                "ch4_35","ch4_34","ch4_33","ch4_32","ch4_31",
                                "ch4_40","ch4_39","ch4_38","ch4_37","ch4_36",
                                "ch4_45","ch4_44","ch4_43","ch4_42","ch4_41",
                                "ch4_50","ch4_49","ch4_48","ch4_47","ch4_46",
                                "ch4_55","ch4_54","ch4_53","ch4_52","ch4_51",
                                "ch4_60","ch4_59","ch4_58","ch4_57","ch4_56",
                                "ch4_65","ch4_64","ch4_63","ch4_62","ch4_61",
                                "ch4_70","ch4_69","ch4_68","ch4_67","ch4_66",
                                "ch4_75","ch4_74","ch4_73","ch4_72","ch4_71",
                                "ch4_80","ch4_79","ch4_78","ch4_77","ch4_76",
                                "dummy","ch4_84","ch4_83","ch4_82","ch4_81"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[4])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch5_5","ch5_4","ch5_3","ch5_2","ch5_1",
                                "ch5_10","ch5_9","ch5_8","ch5_7","ch5_6",
                                "ch5_15","ch5_14","ch5_13","ch5_12","ch5_11",
                                "ch5_20","ch5_19","ch5_18","ch5_17","ch5_16",
                                "ch5_25","ch5_24","ch5_23","ch5_22","ch5_21",
                                "ch5_30","ch5_29","ch5_28","ch5_27","ch5_26",
                                "ch5_35","ch5_34","ch5_33","ch5_32","ch5_31",
                                "ch5_40","ch5_39","ch5_38","ch5_37","ch5_36",
                                "ch5_45","ch5_44","ch5_43","ch5_42","ch5_41",
                                "ch5_50","ch5_49","ch5_48","ch5_47","ch5_46",
                                "ch5_55","ch5_54","ch5_53","ch5_52","ch5_51",
                                "ch5_60","ch5_59","ch5_58","ch5_57","ch5_56",
                                "ch5_65","ch5_64","ch5_63","ch5_62","ch5_61",
                                "ch5_70","ch5_69","ch5_68","ch5_67","ch5_66",
                                "ch5_75","ch5_74","ch5_73","ch5_72","ch5_71",
                                "ch5_80","ch5_79","ch5_78","ch5_77","ch5_76",
                                "dummy","ch5_84","ch5_83","ch5_82","ch5_81"};
                                cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[5])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch6_5","ch6_4","ch6_3","ch6_2","ch6_1",
                                "ch6_10","ch6_9","ch6_8","ch6_7","ch6_6",
                                "ch6_15","ch6_14","ch6_13","ch6_12","ch6_11",
                                "ch6_20","ch6_19","ch6_18","ch6_17","ch6_16",
                                "ch6_25","ch6_24","ch6_23","ch6_22","ch6_21",
                                "ch6_30","ch6_29","ch6_28","ch6_27","ch6_26",
                                "ch6_35","ch6_34","ch6_33","ch6_32","ch6_31",
                                "ch6_40","ch6_39","ch6_38","ch6_37","ch6_36",
                                "ch6_45","ch6_44","ch6_43","ch6_42","ch6_41",
                                "ch6_50","ch6_49","ch6_48","ch6_47","ch6_46",
                                "dummy","ch6_54","ch6_53","ch6_52","ch6_51"};
                                cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[6])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch7_5","ch7_4","ch7_3","ch7_2","ch7_1",
                                "ch7_10","ch7_9","ch7_8","ch7_7","ch7_6",
                                "ch7_15","ch7_14","ch7_13","ch7_12","ch7_11",
                                "ch7_20","ch7_19","ch7_18","ch7_17","ch7_16",
                                "ch7_25","ch7_24","ch7_23","ch7_22","ch7_21",
                                "ch7_30","ch7_29","ch7_28","ch7_27","ch7_26",
                                "dummy","dummy","ch7_33","ch7_32","ch7_31"};//  Toast.makeText(ctx,"Id is " +nqData.get(position).getImageID(),Toast.LENGTH_LONG).show();
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[7])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch8_5","ch8_4","ch8_3","ch8_2","ch8_1",
                                "ch8_10","ch8_9","ch8_8","ch8_7","ch8_6",
                                "ch8_15","ch8_14","ch8_13","ch8_12","ch8_11",
                                "ch8_20","ch8_19","ch8_18","ch8_17","ch8_16",
                                "ch8_25","ch8_24","ch8_23","ch8_22","ch8_21",
                                "ch8_30","ch8_29","ch8_28","ch8_27","ch8_26",
                                "ch8_35","ch8_34","ch8_33","ch8_32","ch8_31",
                                "ch8_40","ch8_39","ch8_38","ch8_37","ch8_36",
                                "ch8_45","ch8_44","ch8_43","ch8_42","ch8_41",
                                "ch8_50","ch8_49","ch8_48","ch8_47","ch8_46",
                                "ch8_55","ch8_54","ch8_53","ch8_52","ch8_51",
                                "ch8_60","ch8_59","ch8_58","ch8_57","ch8_56",
                                "ch8_65","ch8_64","ch8_63","ch8_62","ch8_61",
                                "ch8_70","ch8_69","ch8_68","ch8_67","ch8_66",
                                "ch8_75","ch8_74","ch8_73","ch8_72","ch8_71",
                                "ch8_80","ch8_79","ch8_78","ch8_77","ch8_76",
                                "ch8_85","ch8_84","ch8_83","ch8_82","ch8_81",
                                "ch8_90","ch8_89","ch8_88","ch8_87","ch8_86",
                                "ch8_95","ch8_94","ch8_93","ch8_92","ch8_91",
                                "ch8_100","ch8_99","ch8_98","ch8_97","ch8_96",
                                "ch8_105","ch8_104","ch8_103","ch8_102","ch8_101",
                                "ch8_110","ch8_109","ch8_108","ch8_107","ch8_106",
                                "ch8_115","ch8_114","ch8_113","ch8_112","ch8_111",
                                "ch8_120","ch8_119","ch8_118","ch8_117","ch8_116",
                                "ch8_125","ch8_124","ch8_123","ch8_122","ch8_121",
                                "ch8_130","ch8_129","ch8_128","ch8_127","ch8_126",
                                "ch8_135","ch8_134","ch8_133","ch8_132","ch8_131",
                                "ch8_140","ch8_139","ch8_138","ch8_137","ch8_136",
                        };
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[8])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch9_5","ch9_4","ch9_3","ch9_2","ch9_1",
                                "ch9_10","ch9_9","ch9_8","ch9_7","ch9_6",
                                "ch9_15","ch9_14","ch9_13","ch9_12","ch9_11",
                                "ch9_20","ch9_19","ch9_18","ch9_17","ch9_16",
                                "ch9_25","ch9_24","ch9_23","ch9_22","ch9_21",
                                "ch9_30","ch9_29","ch9_28","ch9_27","ch9_26",
                                "ch9_35","ch9_34","ch9_33","ch9_32","ch9_31",
                                "ch9_40","ch9_39","ch9_38","ch9_37","ch9_36",
                                "ch9_45","ch9_44","ch9_43","ch9_42","ch9_41",
                                "ch9_50","ch9_49","ch9_48","ch9_47","ch9_46",
                                "ch9_55","ch9_54","ch9_53","ch9_52","ch9_51",
                                "ch9_60","ch9_59","ch9_58","ch9_57","ch9_56",
                                "ch9_65","ch9_64","ch9_63","ch9_62","ch9_61",
                                "ch9_70","ch9_69","ch9_68","ch9_67","ch9_66",
                                "ch9_75","ch9_74","ch9_73","ch9_72","ch9_71",
                                "ch9_80","ch9_79","ch9_78","ch9_77","ch9_76",
                                "ch9_85","ch9_84","ch9_83","ch9_82","ch9_81",
                                "ch9_90","ch9_89","ch9_88","ch9_87","ch9_86",
                                "ch9_95","ch9_94","ch9_93","ch9_92","ch9_91",
                                "ch9_100","ch9_99","ch9_98","ch9_97","ch9_96",
                                "dummy","dummy","dummy","dummy","ch9_101"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[9])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch10_5","ch10_4","ch10_3","ch10_2","ch10_1",
                                "ch10_10","ch10_9","ch10_8","ch10_7","ch10_6",
                                "ch10_15","ch10_14","ch10_13","ch10_12","ch10_11",
                                "ch10_20","ch10_19","ch10_18","ch10_17","ch10_16",
                                "ch10_25","ch10_24","ch10_23","ch10_22","ch10_21",
                                "ch10_30","ch10_29","ch10_28","ch10_27","ch10_26",
                                "ch10_35","ch10_34","ch10_33","ch10_32","ch10_31",
                                "ch10_40","ch10_39","ch10_38","ch10_37","ch10_36",
                                "ch10_45","ch10_44","ch10_43","ch10_42","ch10_41",
                                "dummy","dummy","ch10_48","ch10_47","ch10_46"};
                                cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[10])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch11_5","ch11_4","ch11_3","ch11_2","ch11_1",
                                "ch11_10","ch11_9","ch11_8","ch11_7","ch11_6",
                                "ch11_15","ch11_14","ch11_13","ch11_12","ch11_11",
                                "ch11_20","ch11_19","ch11_18","ch11_17","ch11_16",
                                "ch11_25","ch11_24","ch11_23","ch11_22","ch11_21",
                                "ch11_30","ch11_29","ch11_28","ch11_27","ch11_26",
                                "ch11_35","ch11_34","ch11_33","ch11_32","ch11_31",
                                "ch11_40","ch11_39","ch11_38","ch11_37","ch11_36",
                                "ch11_45","ch11_44","ch11_43","ch11_42","ch11_41",
                                "ch11_50","ch11_49","ch11_48","ch11_47","ch11_46",
                                "ch11_55","ch11_54","ch11_53","ch11_52","ch11_51",
                                "ch11_60","ch11_59","ch11_58","ch11_57","ch11_56",
                                "ch11_65","ch11_64","ch11_63","ch11_62","ch11_61",
                                "ch11_70","ch11_69","ch11_68","ch11_67","ch11_66",
                                "ch11_75","ch11_74","ch11_73","ch11_72","ch11_71",
                                "ch11_80","ch11_79","ch11_78","ch11_77","ch11_76",
                                "ch11_85","ch11_84","ch11_83","ch11_82","ch11_81",
                                "ch11_90","ch11_89","ch11_88","ch11_87","ch11_86",
                                "ch11_95","ch11_94","ch11_93","ch11_92","ch11_91",
                                "ch11_100","ch11_99","ch11_98","ch11_97","ch11_96",
                                "ch11_105","ch11_104","ch11_103","ch11_102","ch11_101",
                                "ch11_110","ch11_109","ch11_108","ch11_107","ch11_106",
                                "ch11_115","ch11_114","ch11_113","ch11_112","ch11_111",
                                "ch11_120","ch11_119","ch11_118","ch11_117","ch11_116",
                                "ch11_125","ch11_124","ch11_123","ch11_122","ch11_121",
                                "ch11_130","ch11_129","ch11_128","ch11_127","ch11_126",
                                "ch11_135","ch11_134","ch11_133","ch11_132","ch11_131",
                                "ch11_140","ch11_139","ch11_138","ch11_137","ch11_136",
                                "ch11_145","ch11_144","ch11_143","ch11_142","ch11_141",
                                "dummy","dummy","dummy","ch11_147","ch11_146"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[11])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch12_5","ch12_4","ch12_3","ch12_2","ch12_1",
                                "ch12_10","ch12_9","ch12_8","ch12_7","ch12_6",
                                "ch12_15","ch12_14","ch12_13","ch12_12","ch12_11",
                                "ch12_20","ch12_19","ch12_18","ch12_17","ch12_16",
                                "ch12_25","ch12_24","ch12_23","ch12_22","ch12_21",
                                "ch12_30","ch12_29","ch12_28","ch12_27","ch12_26",
                                "ch12_35","ch12_34","ch12_33","ch12_32","ch12_31",
                                "ch12_40","ch12_39","ch12_38","ch12_37","ch12_36",
                                "ch12_45","ch12_44","ch12_43","ch12_42","ch12_41",
                                "ch12_50","ch12_49","ch12_48","ch12_47","ch12_46",
                                "ch12_55","ch12_54","ch12_53","ch12_52","ch12_51",
                                "ch12_60","ch12_59","ch12_58","ch12_57","ch12_56",
                                "ch12_65","ch12_64","ch12_63","ch12_62","ch12_61",
                                "dummy","ch12_69","ch12_68","ch12_67","ch12_66"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[12])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch13_5","ch13_4","ch13_3","ch13_2","ch13_1",
                                "ch13_10","ch13_9","ch13_8","ch13_7","ch13_6",
                                "ch13_15","ch13_14","ch13_13","ch13_12","ch13_11",
                                "ch13_20","ch13_19","ch13_18","ch13_17","ch13_16",
                                "ch13_25","ch13_24","ch13_23","ch13_22","ch13_21",
                                "ch13_30","ch13_29","ch13_28","ch13_27","ch13_26",
                                "ch13_35","ch13_34","ch13_33","ch13_32","ch13_31",
                                "ch13_40","ch13_39","ch13_38","ch13_37","ch13_36",
                                "ch13_45","ch13_44","ch13_43","ch13_42","ch13_41",
                                "ch13_50","ch13_49","ch13_48","ch13_47","ch13_46",
                                "ch13_55","ch13_54","ch13_53","ch13_52","ch13_51",
                                "ch13_60","ch13_59","ch13_58","ch13_57","ch13_56",
                                "dummy","dummy","dummy","ch13_62","ch13_61"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[13])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch14_5","ch14_4","ch14_3","ch14_2","ch14_1",
                                "ch14_10","ch14_9","ch14_8","ch14_7","ch14_6",
                                "ch14_15","ch14_14","ch14_13","ch14_12","ch14_11",
                                "ch14_20","ch14_19","ch14_18","ch14_17","ch14_16",
                                "dummy","dummy","ch14_23","ch14_22","ch14_21"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[14])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch15_5","ch15_4","ch15_3","ch15_2","ch15_1",
                                "ch15_10","ch15_9","ch15_8","ch15_7","ch15_6"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[15])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch16_5","ch16_4","ch16_3","ch16_2","ch16_1",
                                "ch16_10","ch16_9","ch16_8","ch16_7","ch16_6",
                                "dummy","dummy","dummy","dummy","ch16_11"};
                        cour.putExtra("imagearray", ar);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals(e_list[16])) {
                        Intent cour = new Intent(ctx.getApplicationContext(), AdvanceNuraniQaida.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String ar[] = {"ch17_5","ch17_4","ch17_3","ch17_2","ch17_1",
                                "ch17_10","ch17_9","ch17_8","ch17_7","ch17_6",
                                "ch17_15","ch17_14","ch17_13","ch17_12","ch17_11",
                                "ch17_20","ch17_19","ch17_18","ch17_17","ch17_16",
                                "ch17_25","ch17_24","ch17_23","ch17_22","ch17_21",
                                "ch17_30","ch17_29","ch17_28","ch17_27","ch17_26",
                                "ch17_35","ch17_34","ch17_33","ch17_32","ch17_31",
                                "ch17_40","ch17_39","ch17_38","ch17_37","ch17_36",
                                "dummy","dummy","dummy","dummy","ch17_41"};
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
