package com.esprit.app.utils;

import com.codename1.io.ConnectionRequest;

public class DataSource {
    private static DataSource instance;
    private ConnectionRequest request;

    public DataSource() {
        request = new ConnectionRequest();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public ConnectionRequest getRequest() {
        return request;
    }

}
