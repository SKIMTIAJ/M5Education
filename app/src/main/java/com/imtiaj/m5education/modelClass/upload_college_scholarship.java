package com.imtiaj.m5education.modelClass;

public class upload_college_scholarship {


    private String uploadImage;
    private String uploadName;
    private String uploadLocation;
    private String uploadAffiliated;
    private String uploadPhone;
    private String Imagcotrol;
    private String ImagControlId;
    private String uploadSub;
    private String uploadRating;
    private String uploadWebsite;
    private String uploadType;
    private String uploadUnder;

    public upload_college_scholarship(){

    }

    public upload_college_scholarship(String imagcotrol, String imagControlId) {
        Imagcotrol = imagcotrol;
        ImagControlId = imagControlId;
    }

    public upload_college_scholarship(String uploadName, String uploadLocation, String uploadAffiliated, String uploadPhone, String uploadSub, String uploadRating, String uploadWebsite, String uploadType, String uploadUnder) {
        this.uploadName = uploadName;
        this.uploadLocation = uploadLocation;
        this.uploadAffiliated = uploadAffiliated;
        this.uploadPhone = uploadPhone;
        this.uploadSub = uploadSub;
        this.uploadRating = uploadRating;
        this.uploadWebsite = uploadWebsite;
        this.uploadType = uploadType;
        this.uploadUnder = uploadUnder;
    }

    public upload_college_scholarship(String uploadImage, String uploadName, String uploadLocation, String uploadAffiliated, String uploadPhone, String uploadSub, String uploadRating, String uploadWebsite
    , String uploadType, String uploadUnder) {
        this.uploadImage = uploadImage;
        this.uploadName = uploadName;
        this.uploadLocation = uploadLocation;
        this.uploadAffiliated = uploadAffiliated;
        this.uploadPhone = uploadPhone;
        this.uploadSub = uploadSub;
        this.uploadRating = uploadRating;
        this.uploadWebsite = uploadWebsite;
        this.uploadType = uploadType;
        this.uploadUnder = uploadUnder;
    }



    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getUploadUnder() {
        return uploadUnder;
    }

    public void setUploadUnder(String uploadUnder) {
        this.uploadUnder = uploadUnder;
    }

    public String getUploadWebsite() {
        return uploadWebsite;
    }

    public void setUploadWebsite(String uploadWebsite) {
        this.uploadWebsite = uploadWebsite;
    }

    public String getUploadSub() {
        return uploadSub;
    }

    public void setUploadSub(String uploadSub) {
        this.uploadSub = uploadSub;
    }

    public String getUploadRating() {
        return uploadRating;
    }

    public void setUploadRating(String uploadRating) {
        this.uploadRating = uploadRating;
    }

    public String getImagcotrol() {
        return Imagcotrol;
    }

    public void setImagcotrol(String imagcotrol) {
        Imagcotrol = imagcotrol;
    }

    public String getImagControlId() {
        return ImagControlId;
    }

    public void setImagControlId(String imagControlId) {
        ImagControlId = imagControlId;
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



}



