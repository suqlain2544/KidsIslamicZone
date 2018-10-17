package com.friendsproducts.kidzzone.student.datamodelclasses;

/**
 * Created by Ali Hassan on 5/23/2018.
 */

public class DuaNameData {

    String dua_name_urdu,dua__name_english,duaArebic,duaUrdu,duaEng;

    public DuaNameData(String dua_name_urdu, String dua__name_english, String duaArebic, String duaUrdu, String duaEng) {
        this.dua_name_urdu = dua_name_urdu;
        this.dua__name_english = dua__name_english;
        this.duaArebic = duaArebic;
        this.duaUrdu = duaUrdu;
        this.duaEng = duaEng;
    }

    public String getDua_name_urdu() {
        return dua_name_urdu;
    }

    public String getDua__name_english() {
        return dua__name_english;
    }

    public String getDuaArebic() {
        return duaArebic;
    }

    public String getDuaUrdu() {
        return duaUrdu;
    }

    public String getDuaEng() {
        return duaEng;
    }
}
