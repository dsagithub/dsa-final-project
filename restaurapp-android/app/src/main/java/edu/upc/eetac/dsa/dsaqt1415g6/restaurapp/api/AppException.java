package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

/**
 * Created by xavi on 7/12/14.
 */
public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String detailMessage) {
        super(detailMessage);
    }
}

