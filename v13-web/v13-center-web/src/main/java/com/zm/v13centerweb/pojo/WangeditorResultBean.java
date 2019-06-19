package com.zm.v13centerweb.pojo;

import java.util.Arrays;

public class WangeditorResultBean {
    private String errno;
    private String[] data;

    public WangeditorResultBean() {
    }

    public WangeditorResultBean(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WangeditorResultBean{" +
                "errno='" + errno + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
