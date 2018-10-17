package com.friendsproducts.kidzzone.student.datamodelclasses;

/**
 * Created by Ali Hassan on 5/15/2018.
 */

public class MainListData {

    private String english,urdu,image;

    public MainListData(String english, String urdu, String image) {
        this.english = english;
        this.urdu = urdu;
        this.image = image;
    }

    public String getEnglish() {
        return english;
    }

    public String getUrdu() {
        return urdu;
    }

    public String getImage() {
        return image;
    }
}
