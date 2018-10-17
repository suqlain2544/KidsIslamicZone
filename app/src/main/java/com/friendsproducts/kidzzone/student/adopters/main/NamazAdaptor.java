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
import com.friendsproducts.kidzzone.student.activities.main.NamazDetail;
import com.friendsproducts.kidzzone.student.datamodelclasses.MainListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class NamazAdaptor extends RecyclerView.Adapter<NamazAdaptor.MyView> {

    private Context ctx;
    private List<MainListData> mainListData;

    public NamazAdaptor(Context ctx, List<MainListData> mainListData) {
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
                    if (ol_Data.getEnglish().equals("Niyat")) {

                       /* Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String data[] = {ol_Data.getImage(),"اَللّٰه اَكْبَر","اللہ سب سے بڑا ہے۔"};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);*/
                    } else if (ol_Data.getEnglish().equals("Takbeer")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String data[] = {ol_Data.getImage(), "اَللّٰه اَكْبَر","اللہ سب سے بڑا ہے۔ " , ol_Data.getEnglish()};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Qayam")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                " سُبْحٰنَكَ اللّٰهُمَّ وَبِـحَمْدِكَ وَتَـبَارَكَ اسْمُكَ وَتَـعَالٰي جَدُّكَ وَلَآ اِلٰهَ غَيْـرُك" +
                                        "\n \n" +
                                        " اَعُوْذُ بِاللّٰهِ مِنَ الشَّيْـطٰنِ الرَّجِيْمِ مِنْ نَـفْخِهٖ وَنَـفْثِهٖ وَهَـمْزِهٖ" +
                                        "\n \n" +
                                        " بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ"
                                        + "\n \n" +
                                        " اَلْـحَمْدُ لِلّٰهِ رَبِّ الْعٰلَمِيْنَ - ۙالرَّحْمٰنِ الرَّحِيْمِ- مٰلِكِ يَوْمِ الدِّيْنِ- اِيَّاكَ نَعْبُدُ وَاِيَّاكَ نَسْتَعِيْنُ- اِھْدِنَا الصِّرَاطَ الْمُسْتَـقِيْمَ- صِرَاطَ الَّذِيْنَ اَنْعَمْتَ عَلَيْهِمْ- غَيْرِ الْمَغْضُوْبِ عَلَيْهِمْ وَلَا الضَّاۗلِّيْنََ" +
                                        "\n \n" +
                                        " قُلْ هُوَ اللّٰهُ اَحَدٌ اَللّٰهُ الصَّمَد.. لَمْ يَلِدْ ڏ وَلَمْ يُوْلَد... وَلَمْ يَكُنْ لَّهٗ كُفُوًا اَحَدٌ";
                        String u = " اے اﷲ! میں تیری حمد کے ساتھ تیری پاکیزگی بیان کرتا ہوں اور تیرا نام بہت بابرکت ہے اور تیری شان بلند ہے اور تیرے سوا کوئی معبود نہیں۔"
                                + "\n \n" +
                                " مںو شیطان مردود سے، اس کی پھونک، اس کے تھوک اور اس کے وسوسے سے اﷲ کی پناہ مانگتا ہوں۔"
                                + "\n \n" +
                                "شروع اﷲ کے نام سے جو نہایت رحم کرنے والا، بڑا مہربان ہے۔" +
                                "\n \n" +
                                "تمام تعریفیں اﷲ ہی کے لیے ہیں جو پالنے والا ہے تمام جہانوں کا۔ نہایت رحم کرنے والا، بڑا مہربان ہے۔ مالک ہے یومِ جزا کا۔ تیری ہی ہم عبادت کرتے ہیں اور تجھ ہی سے ہم مدد چاہتے ہیں۔ دِکھا ہمیں سیدھا رستہ، ان لوگوں کا رستہ جن پر تو نے انعام کیا، جن پر تیرا غصب نہیں ہوا اور نہ وہ گمراہ ہوئے۔" +
                                "\n \n" +
                                "آپ کہہ دیجے: وہ اﷲ ایک ہے، اﷲ بے نیاز ہے، اس کی کوئی اولاد نہیں اور نہ وہ کسی کی اولاد ہے اور نہ اس کا کوئی ہم پلہ ہے";

                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Ruku")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَللّٰه اَكْبَر" +
                                        "\n \n" +
                                        "سُبْحَانَ رَبِّيَ الْعَظِيْمِ";

                        String u = " اللہ سب سے بڑا ہے۔"
                                + "\n \n" +
                                " پاک ہے میرا رب عظمت والا۔";

                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Qaumah")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "سَمِعَ اللّٰهُ لِمَنْ حَمِدَهٗ" +
                                        "\n \n" +
                                        "سُبْحٰنَكَ اللّٰهُمَّ رَبَّنَا وَبِحَمْدِكَ اللّٰهُمَّ اغْفِرْلِيْ."+
                                "\n \n"+ "رَبَّنَا وَلَكَ الْحَمْدُ حَمْدًا كَثِيْرًا طَيِّبًا مُّبَارَكًا فِيْهِ";

                        String u = "اﷲ نے سن لی جس نے اس کی تعریف کی۔"
                                + "\n \n" +
                                "پاک ہے تو اے اﷲ! اے ہمارے رب! اپنی تعریف کے ساتھ، اے اﷲ! مجھے معاف فرمادے"+
                                "\n \n"+"اے ہمارے رب! تیرے ہی لیے ہر قسم کی تعریف ہے۔ تعریف بہت زیادہ، پاکیزہ جس میں برکت کی گئی ہے";

                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Sajdah")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَللّٰه اَكْبَرٗ" +
                                        "\n \n" +
                                        "سُبْحَانَ رَبِّيَ الْاَعْلٰى";

                        String u = " اللہ سب سے بڑا ہے۔"
                                + "\n \n" +
                                "پاک ہے میرا رب جو سب سے بلند ہے۔";
                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Jalsa")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        String data[] = {ol_Data.getImage(), "رَبِّ اغْفِرْلِيْ، رَبِّ اغْفِرْلِي", "اے میرے رب! مجھے معاف کردے۔ اے میرے رب! مجھے معاف کردے"};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    }else if (ol_Data.getEnglish().equals("Sajdah")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَللّٰه اَكْبَرٗ" +
                                        "\n \n" +
                                        "سُبْحَانَ رَبِّيَ الْاَعْلٰى";

                        String u = " اللہ سب سے بڑا ہے۔"
                                + "\n \n" +
                                "پاک ہے میرا رب جو سب سے بلند ہے۔";
                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    }else if (ol_Data.getEnglish().equals("Qayam")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                        " اَلْـحَمْدُ لِلّٰهِ رَبِّ الْعٰلَمِيْنَ - ۙالرَّحْمٰنِ الرَّحِيْمِ- مٰلِكِ يَوْمِ الدِّيْنِ- اِيَّاكَ نَعْبُدُ وَاِيَّاكَ نَسْتَعِيْنُ- اِھْدِنَا الصِّرَاطَ الْمُسْتَـقِيْمَ- صِرَاطَ الَّذِيْنَ اَنْعَمْتَ عَلَيْهِمْ- غَيْرِ الْمَغْضُوْبِ عَلَيْهِمْ وَلَا الضَّاۗلِّيْنََ" +
                                        "\n \n" +
                                        " قُلْ هُوَ اللّٰهُ اَحَدٌ اَللّٰهُ الصَّمَد.. لَمْ يَلِدْ ڏ وَلَمْ يُوْلَد... وَلَمْ يَكُنْ لَّهٗ كُفُوًا اَحَدٌ";
                        String u =
                                "تمام تعریفیں اﷲ ہی کے لیے ہیں جو پالنے والا ہے تمام جہانوں کا۔ نہایت رحم کرنے والا، بڑا مہربان ہے۔ مالک ہے یومِ جزا کا۔ تیری ہی ہم عبادت کرتے ہیں اور تجھ ہی سے ہم مدد چاہتے ہیں۔ دِکھا ہمیں سیدھا رستہ، ان لوگوں کا رستہ جن پر تو نے انعام کیا، جن پر تیرا غصب نہیں ہوا اور نہ وہ گمراہ ہوئے۔" +
                                "\n \n" +
                                "آپ کہہ دیجے: وہ اﷲ ایک ہے، اﷲ بے نیاز ہے، اس کی کوئی اولاد نہیں اور نہ وہ کسی کی اولاد ہے اور نہ اس کا کوئی ہم پلہ ہے";

                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Ruku")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَللّٰه اَكْبَر" +
                                        "\n \n" +
                                        "سُبْحَانَ رَبِّيَ الْعَظِيْمِ";

                        String u = " اللہ سب سے بڑا ہے۔"
                                + "\n \n" +
                                " پاک ہے میرا رب عظمت والا۔";

                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Qaumah")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "سَمِعَ اللّٰهُ لِمَنْ حَمِدَهٗ" +
                                        "\n \n" +
                                        "سُبْحٰنَكَ اللّٰهُمَّ رَبَّنَا وَبِحَمْدِكَ اللّٰهُمَّ اغْفِرْلِيْ."+
                                        "\n \n"+ "رَبَّنَا وَلَكَ الْحَمْدُ حَمْدًا كَثِيْرًا طَيِّبًا مُّبَارَكًا فِيْهِ";

                        String u = "اﷲ نے سن لی جس نے اس کی تعریف کی۔"
                                + "\n \n" +
                                "پاک ہے تو اے اﷲ! اے ہمارے رب! اپنی تعریف کے ساتھ، اے اﷲ! مجھے معاف فرمادے"+
                                "\n \n"+"اے ہمارے رب! تیرے ہی لیے ہر قسم کی تعریف ہے۔ تعریف بہت زیادہ، پاکیزہ جس میں برکت کی گئی ہے";

                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Sajdah")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَللّٰه اَكْبَرٗ" +
                                        "\n \n" +
                                        "سُبْحَانَ رَبِّيَ الْاَعْلٰى";

                        String u = " اللہ سب سے بڑا ہے۔رکوع کی دعائیں"
                                + "\n \n" +
                                "پاک ہے میرا رب جو سب سے بلند ہے۔";
                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    } else if (ol_Data.getEnglish().equals("Jalsa")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        String data[] = {ol_Data.getImage(), "رَبِّ اغْفِرْلِيْ، رَبِّ اغْفِرْلِي", "اے میرے رب! مجھے معاف کردے۔ اے میرے رب! مجھے معاف کردے"};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    }else if (ol_Data.getEnglish().equals("Sajdah")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَللّٰه اَكْبَرٗ" +
                                        "\n \n" +
                                        "سُبْحَانَ رَبِّيَ الْاَعْلٰى";

                        String u = " اللہ سب سے بڑا ہے۔"
                                + "\n \n" +
                                "پاک ہے میرا رب جو سب سے بلند ہے۔";
                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    }else if (ol_Data.getEnglish().equals("Tashad")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String a =
                                "اَلتَّحِيَّاتُ لِلّٰهِ وَالصَّلَوٰتُ وَالطَّـيِّـبَاتُ السَّلَامُ عَلَيْكَ اَيُّـهَا الـنَّبِىُّ وَرَحْمَةُ اللّٰهِ وَبَـرَكَاتُهٗ اَلسَّلَامُ عَـلَـيْـنَا وَعَلٰى عِبَادِ اللّٰهِ الصّٰلِحِيْنَ اَشْهَدُ اَنْ لَّآ اِلٰهَ اِلَّا اللّٰهُ وَاَشْهَدُ اَنَّ مُـحَمَّدًا عَـبْدُهٗ وَرَسُوْلُهٗ." +
                                        "\n \n" +
                                        "اَللّٰهُمَّ صَلِّ عَلىٰ مُـحَمَّدٍ وَّ عَليٰٓ اٰلِ مُـحَمَّدٍ كَمَا صَلَّــيْتَ عَليٰٓ اِبْـرَاهِيْمَ وَ عَليٰٓ اٰلِ اِبْـرَاهِيْمَ اِنَّكَ حَمِيْدٌ مَّـجِيْدٌ اَللّٰهُمَّ بَارِكْ عَلىٰ مُـحَمَّدٍ وَّ عَليٰٓ اٰلِ مُـحَمَّدٍ كَمَا بَارَكْتَ عَليٰٓ اِبْـرَاهِيْمَ وَ عَليٰٓ اٰلِ اِبْـرَاهِيْمَ اِنَّكَ حَمِيْدٌ مَّـجِيْدٌ."+
                                        "\n \n" +"اَللّٰهُمَّ اِنِّيْ ظَلَمْتُ نَـفْسِىْ ظُلْمًا كَثِيْرًا وَّلَا يَغْفِرُ الذُّنُوْبَ اِلَّآ اَنْتَ فَاغْفِرْ لِيْ مَغْفِرَةً مِّنْ عِنْدِ كَ وَارْحَمْنِيْ اِنَّكَ اَنْتَ الْـغَـفُوْرُ الرَّحِيْمُ"
                                ;

                        String u = "(میری) تمام قولی، فعلی اور مالی عبادتیں اﷲ ہی کے لیے ہیں، اے نبی! آپ پر سلام ہو اور اﷲ کی رحمت اور اس کی برکات ہوں، ہم پر اور اﷲ کے (دیگر) نیک بندوں پر بھی سلام ہو، میں گواہی دیتا ہوں کہ اﷲ کے سوا کوئی معبود نہیں اور میں گواہی دیتا ہوں کہ محمد ﷺ اس کے بندے اور اس کے رسول ہیں۔تشہد اور درود وسلام"
                                + "\n \n" +
                                "اے اﷲ! رحمت نازل فرما محمد (ﷺ) پر اور آلِ محمد (ﷺ) پر جیسے تونے رحمت نازل فرمائی ابراہیم علیہ السلام پر اور آلِ ابراہیم علیہ السلام پر، یقیناً تو قابلِ تعریف، بڑی شان والا ہے۔ اے اﷲ! برکت نازل فرما محمد (ﷺ) پر اور آلِ محمد (ﷺ) پر جیسے تونے برکت نازل فرمائی ابراہیم علیہ السلام پر اور آلِ ابراہیم علیہ السلام پر، یقیناً تو قابلِ تعریف، بڑی شان والا ہے۔تشہد اور درود وسلام"+
                                "\n \n"+ "اے اﷲ! بلاشبہ میں نے اپنی جان پر بہت زیادہ ظلم کیا اور تیرے سوا کوئی گناہوں کو معاف نہیں کرسکتا، پس تو اپنی خاص بخشش سے مجھے معاف فرمادے اور مجھ پر رحم فرما، یقیناً تو بہت بخشنے والا، انتہائی مہربان ہے۔تشہد اور درود وسلام";
                        String data[] = {ol_Data.getImage(), a, u};
                        cour.putExtra("data", data);
                        ctx.startActivity(cour);
                    }else if (ol_Data.getEnglish().equals("Salam")) {

                        Intent cour = new Intent(ctx, NamazDetail.class);
                        cour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        String data[] = {ol_Data.getImage(), "اَلسَّلَامُ عَلَيْکُمْ وَرَحْمَةُ اللّٰهِ", "سلام ہو تم پر اور رحمت اﷲ کی۔"};
                        cour.putExtra("data", data);
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
            stream = assetManager.open(id + ".jpg");
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
