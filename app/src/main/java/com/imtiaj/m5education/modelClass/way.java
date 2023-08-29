package com.imtiaj.m5education.modelClass;

public class way {


    private String Title;
    private String Description;
    private String Image;
    private String WayId;

    /*public way(String Title) {
        this.Title = Title;

    }*/


    public String getWayId() {
        return WayId;
    }

    public void setWayId(String wayId) {
        WayId = wayId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public way() {
    }

    public way(String title, String description, String image) {
        if (title.trim().equals("") && description.trim().equals("")){
            title = "No Title";
            description = "No Description";
        }
        Title = title;
        Description = description;
        Image = image;
    }


    public way(String title, String description, String image, String wayId) {
        Title = title;
        Description = description;
        Image = image;
        WayId = wayId;
    }
}
