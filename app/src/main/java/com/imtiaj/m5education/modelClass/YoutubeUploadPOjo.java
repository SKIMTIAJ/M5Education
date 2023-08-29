package com.imtiaj.m5education.modelClass;

public class YoutubeUploadPOjo {


    private String videoId, title,duration;

    public YoutubeUploadPOjo() {

    }

    public YoutubeUploadPOjo(String videoId, String title, String duration) {
        this.videoId = videoId;
        this.title = title;
        this.duration = duration;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "YoutubeVideoModel{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }


}
