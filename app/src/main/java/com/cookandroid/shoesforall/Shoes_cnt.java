package com.cookandroid.shoesforall;

public class Shoes_cnt {

    private String shoesSize_txt;
    private String shoesSize_cnt;

    public Shoes_cnt(String shoesSize_txt, String shoesSize_cnt) {
        this.shoesSize_txt = shoesSize_txt;
        this.shoesSize_cnt = shoesSize_cnt;
    }

    public String getShoesSize_txt() {
        return shoesSize_txt;
    }

    public void setShoesSize_txt(String shoesSize_txt) {
        this.shoesSize_txt = shoesSize_txt;
    }

    public String getShoesSize_cnt() {
        return shoesSize_cnt;
    }

    public void setShoesSize_cnt(String shoesSize_cnt) {
        this.shoesSize_cnt = shoesSize_cnt;
    }
}
