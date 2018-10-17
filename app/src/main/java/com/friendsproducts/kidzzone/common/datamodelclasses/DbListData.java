package com.friendsproducts.kidzzone.common.datamodelclasses;

/**
 * Created by Ali Hassan on 5/17/2018.
 */

public class DbListData {
    String name,uniquename,email,age_qual,nmbr,gender,image;

    public DbListData(String name, String uniquename, String email, String age_qual, String nmbr, String gender, String image) {
        this.name = name;
        this.uniquename = uniquename;
        this.email = email;
        this.age_qual = age_qual;
        this.nmbr = nmbr;
        this.gender = gender;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getUniquename() {
        return uniquename;
    }

    public String getEmail() {
        return email;
    }

    public String getAge_qual() {
        return age_qual;
    }

    public String getNmbr() {
        return nmbr;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }
}
