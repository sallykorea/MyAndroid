package com.example.practice2;

import java.io.Serializable;

public class InfoDto implements Serializable {
    private int resId;
    private String name;
    private String addr;
    private String digit;

    public InfoDto(){}

    public InfoDto(int resId, String name, String addr, String digit) {
        this.resId = resId;
        this.name = name;
        this.addr = addr;
        this.digit = digit;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }
}
