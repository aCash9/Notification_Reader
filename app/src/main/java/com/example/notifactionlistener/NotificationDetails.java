package com.example.notifactionlistener;

public class NotificationDetails {

    String date;
    String appName;
    String title;
    String text;

    public NotificationDetails() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NotificationDetails(String date, String appName, String title, String text) {
        this.date = date;
        this.appName = appName;
        this.title = title;
        this.text = text;
    }


}
