package com.example.travelukraine.model;

public class CategoryItem {

    String background;
    String profileName;
    String profileDescribe;
    String profileLongDescribe;
    String profileOnMap;
    String profileCatID;

    public CategoryItem() {
    }



    public CategoryItem(String background, String profileName, String profileDescribe, String profileLongDescribe, String profileOnMap, String profileCatID) {
        this.background = background;
        this.profileName = profileName;
        this.profileDescribe = profileDescribe;
        this.profileLongDescribe = profileLongDescribe;
        this.profileOnMap = profileOnMap;
        this.profileCatID = profileCatID;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileDescribe() {
        return profileDescribe;
    }
    public String getProfileOnMap() {
        return profileOnMap;
    }

    public void setProfileOnMap(String profileOnMap) {
        this.profileOnMap = profileOnMap;
    }

    public void setProfileDescribe(String profileDescribe) {
        this.profileDescribe = profileDescribe;
    }

    public String getProfileLongDescribe() {
        return profileLongDescribe;
    }

    public void setProfileLongDescribe(String profileLongDescribe) {
        this.profileLongDescribe = profileLongDescribe;
    }
}
