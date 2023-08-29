package com.imtiaj.m5education.modelClass;

public class uploadSchool_pojo {

    private String uploadImage;
    private String uploadName;
    private String uploadLocation;
    private String uploadAffiliated;
    private String uploadPhone;
    private String uploadRating;
    private String uploadWebsite;
    private String uploadMedium, uploadClasses;

    public uploadSchool_pojo() {

    }

    public uploadSchool_pojo(String uploadImage, String uploadName, String uploadLocation) {
        this.uploadImage = uploadImage;
        this.uploadName = uploadName;
        this.uploadLocation = uploadLocation;
    }

    public uploadSchool_pojo(String uploadName, String uploadLocation, String uploadAffiliated, String uploadPhone, String uploadWebsite,
                             String uploadMedium, String uploadClasses, String uploadRating) {
        this.uploadName = uploadName;
        this.uploadLocation = uploadLocation;
        this.uploadAffiliated = uploadAffiliated;
        this.uploadPhone = uploadPhone;
        this.uploadWebsite = uploadWebsite;
        this.uploadMedium = uploadMedium;
        this.uploadClasses = uploadClasses;
        this.uploadRating = uploadRating;
    }

    public uploadSchool_pojo(String uploadImage, String uploadName, String uploadLocation, String uploadAffiliated, String uploadPhone, String uploadRating, String uploadWebsite, String uploadMedium, String uploadClasses) {
        this.uploadImage = uploadImage;
        this.uploadName = uploadName;
        this.uploadLocation = uploadLocation;
        this.uploadAffiliated = uploadAffiliated;
        this.uploadPhone = uploadPhone;
        this.uploadRating = uploadRating;
        this.uploadWebsite = uploadWebsite;
        this.uploadMedium = uploadMedium;
        this.uploadClasses = uploadClasses;
    }

    public String getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(String uploadImage) {
        this.uploadImage = uploadImage;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getUploadLocation() {
        return uploadLocation;
    }

    public void setUploadLocation(String uploadLocation) {
        this.uploadLocation = uploadLocation;
    }

    public String getUploadAffiliated() {
        return uploadAffiliated;
    }

    public void setUploadAffiliated(String uploadAffiliated) {
        this.uploadAffiliated = uploadAffiliated;
    }

    public String getUploadPhone() {
        return uploadPhone;
    }

    public void setUploadPhone(String uploadPhone) {
        this.uploadPhone = uploadPhone;
    }

    public String getUploadRating() {
        return uploadRating;
    }

    public void setUploadRating(String uploadRating) {
        this.uploadRating = uploadRating;
    }

    public String getUploadWebsite() {
        return uploadWebsite;
    }

    public void setUploadWebsite(String uploadWebsite) {
        this.uploadWebsite = uploadWebsite;
    }

    public String getUploadMedium() {
        return uploadMedium;
    }

    public void setUploadMedium(String uploadMedium) {
        this.uploadMedium = uploadMedium;
    }

    public String getUploadClasses() {
        return uploadClasses;
    }

    public void setUploadClasses(String uploadClasses) {
        this.uploadClasses = uploadClasses;
    }
}
