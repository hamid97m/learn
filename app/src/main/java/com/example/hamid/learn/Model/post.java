package com.example.hamid.learn.Model;

/**
 * Created by Hamid on 7/13/2017.
 */

public class post {
    private int id;
    private String imageView;
    private String textView;
    private double value;
    private String mark;
    private String summery;
    private boolean farvar=false;




    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public boolean isFarvar() {
        return farvar;
    }

    public void setFarvar(boolean farvar) {
        this.farvar = farvar;
    }
}
