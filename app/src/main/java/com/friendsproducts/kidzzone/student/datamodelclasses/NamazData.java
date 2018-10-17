package com.friendsproducts.kidzzone.student.datamodelclasses;

/**
 * Created by Ali Hassan on 8/27/2018.
 */

public class NamazData {

    String img,arabic,urdu,audio;

    public NamazData(String img, String arabic, String urdu, String audio) {
        this.img = img;
        this.arabic = arabic;
        this.urdu = urdu;
        this.audio = audio;
    }

    public String getImg() {
        return img;
    }

    public String getArabic() {
        return arabic;
    }

    public String getUrdu() {
        return urdu;
    }

    public String getAudio() {
        return audio;
    }
}
