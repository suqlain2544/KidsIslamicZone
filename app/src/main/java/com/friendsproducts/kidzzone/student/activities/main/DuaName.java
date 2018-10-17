package com.friendsproducts.kidzzone.student.activities.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.FirstScreen;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.RegisterStudent;
import com.friendsproducts.kidzzone.RegisterTeacher;
import com.friendsproducts.kidzzone.student.adopters.main.DuaNameAdaptor;
import com.friendsproducts.kidzzone.student.datamodelclasses.DuaNameData;

import java.util.ArrayList;
import java.util.List;

public class DuaName extends AppCompatActivity {

    String nameurdu[] = {" سوتے وقت کی دعا",
            " نیند سے بیدار ہونے کی دعا", " بیت الخلا میں داخل ہونے کی دعا", " بیت الخلا سے نکلنے کی دعا", " مسجد میں داخل ہونے کی دعا",
            " مسجد سے نکلنے کی دعا", " وضو سے پہلے کی دعا", " وضو کے بعد کی دعائیں ", " اذان کے بعد درودشریف اور مسنون دعا", " مشکلات کے حل کی دعا", " قرض سے نجات کی دعائیں",
            " گھبراہٹ کے وقت کی دعا", " غصہ آجانے کے وقت کی دعا", " شرک سے محفوظ رہنے کی دعا", " کھانے سے فراغت کے بعد کی دعائیں",
            " گھر سے نکلتے وقت کی دعائیں", " خوشی کے وقت کی دعا", " بچوں کو اﷲ کی پناہ میں دینے کی دعا"};

    String nameeng[] = {"Supplications at the time of sleeping", " Supplication upon Awakening "
            , " Supplication before entering Lavatory ", " Supplication after leaving Lavatory "
            , " Supplication to enter mosque ", " Supplication when leaving mosque ", " Supplication before ablution ",
            " Supplication after ablution", "Supplication and recitation of Durood after Azan",
            " Supplication for solving Problems/Troubles ", " Supplications to be free from debts/loans ",
            " Supplication when in anxiety ", " Supplication when angry ", " Supplication to be safe from polytheism ",
            " Supplication after Lunch/Dinner ", " Supplication exiting home ", " Supplication upon happiness "
            , " Supplication to seek refuge of Allah for children"};

    String dua_arebic[] = {" بِاسْمِکَ اَمُوْتُ وَاَحْیَا اللّٰھُمَّ",
            " اَلْحَمْدُ لِلّٰہِ الَّذِیْٓ اَحْیَانَا بَعْدَ مَآ اَمَاتَنَا وَاِلَیْہِ النُّشُوْرُ ",
            " بِسْمِ اللّٰہِ اَللّٰھُمَّ اِنِّیْٓ اَعُوْذُبِکَ مِنَ الْخُبْثِ وَالْخَبَآئِثِ ", "غُفْرَانَکَ ",
            " اَعُوْذُ بِاللّٰہِ الْعَظِیْمِ وَبِوَجْھِہِ الْکَرِیْمِ وَسُلْطَانِہِ الْقَدِیْمِ مِنَ الشَّیْطَانِ الرَّجِیْمِ بِسْمِ اللّٰہِ وَالصَّلٰوۃُ وَالسَّلَامُ عَلٰی رَسُوْلِ اللّٰہِ اَللّٰھُمَّ افْتَحْ لِیْ اَبْوَابَ رَحْمَتِکَ ",
            " بِسْمِ اللّٰہِ وَالصَّلٰوۃُ وَالسَّلَامُ عَلٰی رَسُوْلِ اللّٰہِ اَللّٰھُمَّ اِنِّیْٓ اَسْئَلُکَ مِنْ فَضْلِکَ اَللّٰھُمَّ اعْصِمْنِیْ مِنَ الشَّیْطَانِ الرَّجِیْمِ ",
            " بِسْمِ اللّٰہِ ",
            " اَشْھَدُ اَنْ لَّآ اِلٰہَ اِلَّا اللّٰہُ وَحْدَہٗ لَاشَرِیْکَ لَہٗ وَاَشْھَدُ اَنَّ مُحَمَّدًا عَبْدُہٗ وَرَسُوْلُہٗ ",
            " اَللّٰھُمَّ رَبَّ ھٰذِہِ الدَّعْوَۃِ التَّآمَّۃِ وَالصَّلٰوۃِ الْقَآئِمَۃِ اٰتِ مُحَمَّدَ ۨ  الْوَسِیْلَۃَ وَالْفَضِیْلَۃَ وَابْعَثْہُ مَقَامًا مَّحْمُودَ  ۨ الَّذِیْ وَعَدْتَّہٗ (اِنَّکَ لَا تُخْلِفُ الْمِیْعَادَ ",
            " اَللّٰھُمَّ لَا سَھْلَ اِلَّا مَا جَعَلْتَہٗ سَھْلًا وَّاَنْتَ تَجْعَلُ الْحَزَنَ اِذَا شِئْتَ سَھْلًا ",
            " اَللّٰھُمَّ اکْفِنِیْ بِحَلَالِکَ عَنْ حَرَامِکَ وَاَغْنِنِیْ بِفَضْلِکَ عَمَّنْ سِوَاکَ ",
            " لَآ اِلٰہَ اِلَّا اللّٰہُ ",
            " اَعُوْذُ بِاللّٰہِ مِنَ الشَّیْطٰنِ الرَّجِیْمِ ",
            " اَللّٰھُمَّ اِنِّیْٓ اَعُوْذُبِکَ اَنْ اُشْرِکَ بِکَ وَاَنَا اَعْلَمُ وَاسْتَغْفِرُکَ لِمَا لَآ اَعْلَمُ ",
            " اَلْحَمْدُ لِلّٰہِ الَّذِیْٓ اَطْعَمَنِیْ ھٰذَا وَ رَزَقَنِیْہِ مِنْ غَیْرِ حَوْلٍ مِّنِّیْ وَلَا قُوَّۃٍ ",
            " بِسْمِ اللّٰہِ تَوَکَّلْتُ عَلَی اللّٰہِ لَا حَوْلَ وَلَا قُوَّۃَ اِلَّا بِاللّٰہِ ",
            " سُبْحَانَ اللّٰہِ اَللّٰہُ اَکْبَرُ", "ABC"
    };

    String duaUrdu[] = {"تیرے ہی نام کے ساتھ اے اﷲ! میں مرتا او رزندہ ہوتا ہوں۔", " ہر قسم کی تعریف اﷲ ہی کے لیے ہے جس نے ہمیں زندہ کیا، بعد اس کے کہ اس نے ہمیں مار دیا تھا اور اسی کی طرف اٹھ کر جانا ہے۔ ", " اﷲ کے نام کے ساتھ، اے اﷲ! میں تیری پناہ میں آتا ہوں خبیثوں اور خبیثیوں سے ",
            "(اے اﷲ! میں) تیری بخشش (چاہتا ہوں۔",
            "میں شیطان مردود سے عظمت والے اﷲ کی، اس کے کریم چہرے اور اس کی قدیم سلطنت کی پناہ مانگتا ہوں۔ اﷲ کے نام کے ساتھ (داخل ہوتا ہوں) اور درود و سلام ہو رسول اﷲ ﷺ پر۔ اے اﷲ! میرے لیے اپنی رحمت کے دروازے کھول دے",
            " اﷲ کے نام کے ساتھ (میں نکلتا ہوں) اور درود و سلام ہو رسول اﷲ ﷺ پر۔ اے اﷲ! میرے لیے اپنے فضل کے دروازے کھول دے۔ اے اﷲ! مجھے شیطان مردود سے بچا کے رکھ ", " اﷲ تعالیٰ کے نام کے ساتھ "
            , " میں شہادت دیتا ہوں کہ اﷲ کے سوا کوئی (سچا) معبود نہیں، وہ اکیلا ہے، اس کا کوئی شریک نہیں اور میں گواہی دیتا ہوں کہ محمد (ﷺ) اس کے بندے اور اس کے رسول ہیں ",
            " اے اﷲ! اس دعوتِ کامل اور قائم ہونے والی نماز کے رب، تُو محمد ﷺ کو خاص تقرب او رخاص فضیلت عطا کر اور انہیں اس مقام محمود پر فائز فرما جس کا تونے ان سے وعدہ کیا ہے۔ یقیناً تو وعدہ خلافی نہیں کرتا ",
            " اے اﷲ! کوئی کام آسان نہیں ہے مگر وہی جسے تو آسان کردے اور تو مشکل کام جب چاہے، آسان کردیتا ہے ",
            " اے اﷲ! تو مجھے اپنے حلال کے ساتھ اپنی حرام (کردہ) چیزوں سے کافی ہوجا اور مجھے اپنے فضل سے، اپنے ماسوا سے بے نیاز کردے۔ ", " اﷲ کے سوا کوئی معبود نہیں۔",
            "میں اﷲ کی پناہ میں آتا ہوں شیطان مردود سے ",
            " اے اﷲ! بے شک میں تیری پناہ میں آتا ہوں کہ میں (کسی کو) تیرا شریک ٹھہراؤں جب کہ میں جانتا بھی ہوں۔ اور میں تجھ سے ان غلطیوں کی بخشش مانگتا ہوں جنہیں میں نہیں جانتا ",
            " ہر قسم کی تعریف اﷲ ہی کے لیے ہے جس نے یہ (کھانا) مجھے کھلایا اور مجھے یہ (کھانا) عطا کیا بغیر میری کسی طاقت کے اور بغیر میری کسی قوت کے ", " (میں اس گھر سے) اﷲ کے نام کے ساتھ (نکل رہا ہوں) میں نے اﷲ پر بھروسا کیا اور گناہ سے بچنے کی ہمت ہے نہ نیکی کرنے کی طاقت مگر اﷲ ہی کی توفیق سے ", "اﷲ پاک ہے،(1) اﷲ سب سے بڑا ہے ",
            " میں تم دونوں کو اﷲ تعالیٰ کے مکمل کلمات کی پناہ میں دیتا ہوں، ہر شیطان اور زہریلے جانور سے اور ہر لگ جانے والی نظر سے۔"

    };


    String duaEng[] = {"In Your Name , O Allah , I die and I live",
            " Praise is to Allah Who gives us life after He has caused us to die and to Him is the return ",
            "(Before entering) [In the Name of Allah] . (Then) O Allah , I seek protection in You from the male and female unclean spirits. "
            , " I seek Your forgiveness. ", "I seek refuge in Almighty Allah, by His Noble Face, by His primordial power, from Satan the outcast.1 [In the Name of Allah, and blessings.]2 [And peace be upon the Messenger of Allah.]3 O Allah, open before me the doors of Your mercy",
            " In the Name of Allah, and peace and blessings be upon the Messenger of Allah. O Allah, I ask for Your favor, O Allah, protect me from Satan the outcast ",
            " In the Name of Allah ", " I bear witness that none has the right to be worshipped but Allah alone, Who has no partner; and I bear witness that Muhammad is His slave and His Messenger",
            " O Allah , Lord of this perfect call and established prayer. Grant Muhammad the intercession and favor, and raise him to the honored station You have promised him, [verily You do not neglect promises ",
            " O Allah, there is no ease other than what You make easy. If You please You ease sorrow", "There is no god but Allah", "(Say:) I seek refuge in Allah from Satan the outcast (then spit to your left). (Do this three times reciting in Arabic.) ", " O Allah, I seek refuge in You lest I associate anything with You knowingly, and I seek Your forgiveness for what I know not. ", " Praise is to Allah Who has given me this food and sustained me with it though I was unable to doit and powerless ", " In the Name of Allah, I have placed my trust in Allah, there is no might and no power except by Allah. ", "Allah is the greatest", " seek protection for you in the Perfect Words of Allah from every devil and every beast , and from every envious blameworthy eye.", "ABC"};


    FloatingActionButton list_chat;

    //guest interface start
    ImageView profileIv, logoutIv;
    Sharepref sp;
    String username, uemail, tecEmail, reqStatus;
    //guest interface end

    RecyclerView recyclerView;
    DuaNameAdaptor ol_adaptor;
    List<DuaNameData> o_List;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua_name);


        //guest start
        profileIv = (ImageView) findViewById(R.id.profileBtn);
        logoutIv = (ImageView) findViewById(R.id.logoutBtn);

        sp = new Sharepref(this);
        username = sp.getStr(sp.STD_USER_NAME);
        uemail = sp.getStr(sp.STD_EMAIL);
        tecEmail = sp.getStr(sp.TECH_EMAIL);
        reqStatus = sp.getStr(sp.REQ_STATUS);

        if (username.equals("") && uemail.equals("")) {
            profileIv.setVisibility(View.INVISIBLE);
            logoutIv.setVisibility(View.INVISIBLE);
        }
        //guest end

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        list_chat = (FloatingActionButton) findViewById(R.id.list_chat);
        o_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.dn_recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DuaName.this));
        for (int i = 0; i < nameurdu.length; i++) {
            DuaNameData tecData1 = new DuaNameData(nameurdu[i], nameeng[i], dua_arebic[i], duaUrdu[i], duaEng[i]);
            o_List.add(tecData1);
        }


        ol_adaptor = new DuaNameAdaptor(getApplicationContext(), o_List);
        recyclerView.setAdapter(ol_adaptor);

        /*Toast.makeText(DuaName.this, "nameurdu" + nameurdu.length, Toast.LENGTH_LONG).show();
        Toast.makeText(DuaName.this, "nameeng" + nameeng.length, Toast.LENGTH_LONG).show();
        Toast.makeText(DuaName.this, "dua_arebic" + dua_arebic.length, Toast.LENGTH_LONG).show();
        Toast.makeText(DuaName.this, "dua_urdu" + duaUrdu.length, Toast.LENGTH_LONG).show();
        Toast.makeText(DuaName.this, "dua_eng" + duaEng.length, Toast.LENGTH_LONG).show();
*/
         /* aghr user Guest nai ha tu if ma in ho ga aur check kary ga k tecEmail null ha ya nagi aghr null ha tu
        student list ma ly k jaye ga aghr null nai ha tu uss ka stust chek kry ga
        aghr status 0 ha tu pending request aur agher status 1 ha tu Chat screen open ho gi*/
   /*Agher user guest ha tu uss ko register screen per ly k jaye ga*/

        if (!username.equals("") && !uemail.equals("")) {
            if (tecEmail.equals("null") && reqStatus.equals("null")) {
                // list_chat.setImageDrawable(getResources().getDrawable(R.drawable.addtecicon));
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        list_chat.setImageDrawable(getResources().getDrawable(R.drawable.addtecicon));
                        if (username.equals("") && uemail.equals("")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DuaName.this);
                            builder.setMessage("Chose One")
                                    .setCancelable(true)
                                    .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            DuaName.this.finish();
                                            Intent std = new Intent(DuaName.this, RegisterTeacher.class);
                                            startActivity(std);
                                        }
                                    })
                                    .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            DuaName.this.finish();
                                            Intent std = new Intent(DuaName.this, RegisterStudent.class);
                                            startActivity(std);
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            Intent list_chat_intent = new Intent(DuaName.this, TeacherList.class);
                            startActivity(list_chat_intent);
                        }
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("0")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(DuaName.this, "Request Pending " + reqStatus, Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (!tecEmail.equals("null") && reqStatus.equals("1")) {
                list_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkInternet()) {
                            Intent cs = new Intent(DuaName.this, Chat.class);
                            startActivity(cs);
                        } else {
                            Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        } else {
            list_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list_chat.setImageDrawable(getResources().getDrawable(R.drawable.addtecicon));
                    if (username.equals("") && uemail.equals("")) {
                        if (checkInternet()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DuaName.this);
                            builder.setMessage("Chose One")
                                    .setCancelable(true)
                                    .setPositiveButton("Teacher", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            DuaName.this.finish();
                                            Intent std = new Intent(DuaName.this, RegisterTeacher.class);
                                            startActivity(std);
                                        }
                                    })
                                    .setNegativeButton("Student", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            DuaName.this.finish();
                                            Intent std = new Intent(DuaName.this, RegisterStudent.class);
                                            startActivity(std);
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Required", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Intent list_chat_intent = new Intent(DuaName.this, TeacherList.class);
                        startActivity(list_chat_intent);
                    }
                }
            });
        }
        //end of ifs and elses

    }

    public void headerClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.profileBtn:
                Intent pb = new Intent(DuaName.this, StudentProfile.class);
                startActivity(pb);
                break;
            case R.id.homeBtn:

                Intent hm = new Intent(DuaName.this, StudentHome.class);
                finish();
                startActivity(hm);
                break;
            case R.id.logoutBtn:
                sp.clearPref();
                Intent i = new Intent(DuaName.this, FirstScreen.class);
                finish();
                startActivity(i);
                break;
        }
    }

    public boolean checkInternet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }
}
