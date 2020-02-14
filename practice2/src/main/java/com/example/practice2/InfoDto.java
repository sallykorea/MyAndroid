package com.example.practice2;

public class InfoDto {
    private int num;
    private String name;
    private String digit;

    public InfoDto(){}

    public InfoDto(int num, String name, String digit) {
        this.num = num;
        this.name = name;
        this.digit = digit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }
}
