package com.zerohour.model;

import java.io.Serializable;

/**
 * Created by Shankar on 5/9/2017.
 */

public class NoticeBoardItem implements Serializable {
    private String title;
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
