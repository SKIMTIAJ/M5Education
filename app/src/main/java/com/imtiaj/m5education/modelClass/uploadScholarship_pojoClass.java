package com.imtiaj.m5education.modelClass;

public class uploadScholarship_pojoClass {

    private String uploadImage;
    private String uploadName;
    private String Organization;
    private String Eligiblity;
    private String Website;
    private String ScholarshipAmount;
    private String ScholarshipMode;

    public uploadScholarship_pojoClass() {

    }

    public uploadScholarship_pojoClass(String uploadImage, String uploadName, String organization, String eligiblity, String website, String scholarshipAmount, String scholarshipMode) {
        this.uploadImage = uploadImage;
        this.uploadName = uploadName;
        Organization = organization;
        Eligiblity = eligiblity;
        Website = website;
        ScholarshipAmount = scholarshipAmount;
        ScholarshipMode = scholarshipMode;
    }

    public uploadScholarship_pojoClass(String uploadName, String organization, String eligiblity, String website, String scholarshipAmount, String scholarshipMode) {
        this.uploadName = uploadName;
        Organization = organization;
        Eligiblity = eligiblity;
        Website = website;
        ScholarshipAmount = scholarshipAmount;
        ScholarshipMode = scholarshipMode;
    }

    public String getScholarshipMode() {
        return ScholarshipMode;
    }

    public void setScholarshipMode(String scholarshipMode) {
        ScholarshipMode = scholarshipMode;
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

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getEligiblity() {
        return Eligiblity;
    }

    public void setEligiblity(String eligiblity) {
        Eligiblity = eligiblity;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getScholarshipAmount() {
        return ScholarshipAmount;
    }

    public void setScholarshipAmount(String scholarshipAmount) {
        ScholarshipAmount = scholarshipAmount;
    }
}
