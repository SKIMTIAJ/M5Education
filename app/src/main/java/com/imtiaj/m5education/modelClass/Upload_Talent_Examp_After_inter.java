package com.imtiaj.m5education.modelClass;

public class Upload_Talent_Examp_After_inter {

    private String NameExmap;
    private String ConductName;
    private String InventedName ;
    private String NotificationName ;
    private String ModeName ;
    private String WebName;

    public Upload_Talent_Examp_After_inter() {

    }


    public Upload_Talent_Examp_After_inter(String NameSimpleCourse) {

        NameExmap = NameSimpleCourse;

    }

    public Upload_Talent_Examp_After_inter(String nameExmap, String conductName, String inventedName, String notificationName, String modeName, String webName) {
        NameExmap = nameExmap;
        ConductName = conductName;
        InventedName = inventedName;
        NotificationName = notificationName;
        ModeName = modeName;
        WebName = webName;
    }

    public String getNameExmap() {
        return NameExmap;
    }

    public void setNameExmap(String nameExmap) {
        NameExmap = nameExmap;
    }

    public String getConductName() {
        return ConductName;
    }

    public void setConductName(String conductName) {
        ConductName = conductName;
    }

    public String getInventedName() {
        return InventedName;
    }

    public void setInventedName(String inventedName) {
        InventedName = inventedName;
    }

    public String getNotificationName() {
        return NotificationName;
    }

    public void setNotificationName(String notificationName) {
        NotificationName = notificationName;
    }

    public String getModeName() {
        return ModeName;
    }

    public void setModeName(String modeName) {
        ModeName = modeName;
    }

    public String getWebName() {
        return WebName;
    }

    public void setWebName(String webName) {
        WebName = webName;
    }

}
