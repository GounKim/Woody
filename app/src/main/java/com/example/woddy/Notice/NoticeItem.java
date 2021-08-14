package com.example.woddy.Notice;

public class NoticeItem {

    private String writer;
    private String message;
    private String boardName;
    private String writtenTime;
    private String likeNum;


    public NoticeItem(String writer, String message, String boardName,String writtenTime, String likeNum){

        this.writer = writer;
        this.message = message;
        this.boardName = boardName;
        this.writtenTime = writtenTime;
        this.likeNum = likeNum;
    }


    public String getWriter() {
        return writer;
    }

    public String getMessage() { return message; }

    public String getBoardName() {
        return boardName;
    }

    public String getWrittenTime() {
        return writtenTime;
    }

    public String getLikeNum() {
        return likeNum;
    }
}

