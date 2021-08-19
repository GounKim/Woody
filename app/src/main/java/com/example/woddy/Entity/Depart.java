package com.example.woddy.Entity;

public class Depart {
    private int postingNumber; //글 번호
    private String depart; //기관 이름
    private String tel; //기관 전화번호
    private String url; //기관 url
    private String content; //글 내용

    public Depart(){}

    public String getDepart() {
        return depart;
    }

    public String getTel(){
        return tel;
    }

    public String getUrl(){
        return url;
    }

    public String getContent() {
        return content;
    }


}
