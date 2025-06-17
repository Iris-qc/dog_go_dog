package com.dog.model;
/*
    图层是承载单元格的容器
    某个图层的左上角位置是有一定的偏移量的，偏移的数值随机，这样可以形成堆叠并露出部分下层图案的效果
    如果没有一定的偏移，那么就完全盖住了下层的组件，无法形成交错效果
 */
import java.util.Random;
public class Layer {
    private  Integer  offset;   //偏移量
    private  Integer  x;        //通过偏移量计算后图层左上角在水平方向上的坐标值
    private  Integer  y;        //通过偏移量计算后图层左上角在垂直方向上的坐标值
    private  Integer  cellNumx;   //本图层中竖向有多少单元格
    private  Integer  cellNumy;     //本图层中横向有多少个单元格
    private  Cell [][]   cells;
    private  Integer  capacity ;    //容量
    private  Integer  size = 0;   //当前图层有多少组件，即有多少个单元格被填充或使用，如果为0，说明当前图片没有组件, 都被消除完毕了，可以从map当中删除掉
    private  Layer   parentLayer;   //上层图层
    public Layer(Integer cellNumx, Integer cellNumy) {
        this.cellNumx = cellNumx;
        this.cellNumy = cellNumy;
        this.capacity = this.cellNumx * this.cellNumy;
        this.cells = new Cell[cellNumx][cellNumy];
        //偏移量设置
        this.offset= new Random().nextInt(100);
    }
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() { return y; }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getCellNumx() {
        return cellNumx;
    }

    public void setCellNumx(Integer cellNumx) {
        this.cellNumx = cellNumx;
    }

    public Integer getCellNumy() {
        return cellNumy;
    }

    public void setCellNumy(Integer cellNumy) {
        this.cellNumy = cellNumy;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setParentLayer(Layer parentLayer) {
        this.parentLayer = parentLayer;
    }

    public void show(){  //打印图层中每个单元格内牌的名称
        if(cells == null) return;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j].getBrand().getName());
            }
            System.out.println();
        }
    }
    public Cell getIndex(int index){
        if (this.cells == null || index < 0 || index >= this.capacity) { //无牌或者索引无效
            return null;
        }
        int index_x= index / this.getCellNumy();
        int index_y= index % this.getCellNumy();
        return this.cells[index_x][index_y];
    }
    public Layer getParentLayer() {
        return this.parentLayer;
    }
}
