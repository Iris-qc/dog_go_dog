package com.dog.model;
/*
    cell表示图层中的单元格，是图层的基本单元
    一个单元格有两个基本的状态:1.空（没有牌） 2.有牌
    当前单元格有牌的状态下，如果被上层盖住,则显示灰色的图片,否则显示正常的图片
 */
public class Cell {
    private Integer state;    //单元格默认是空的（没有牌）
    private Brand brand;       // 初始值为null，表示单元格中默认没有牌。
    //创建一个新的Cell对象
    public Cell(Brand brand) {
        this.brand = brand;
        this.state = 2 ;
        //如果传入了brand，则设置单元格的状态为2（有牌），并将brand赋值给this.brand
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}