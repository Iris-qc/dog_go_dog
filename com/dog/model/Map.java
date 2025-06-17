package com.dog.model;
import java.util.ArrayList;
import java.util.List;
public class Map {
    private  Integer  x;  //绘制地图左上角的x坐标
    private  Integer  y;  //绘制地图左上角的y坐标
    private  Integer  floorHeight;   //地图的层高，即地图有多少层
    private List<Layer> layers = new ArrayList<>();   //存储Layer对象，layers的数组长度和层高是一致的
    public Map() {
    }
    public Integer getX() {
        return x;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public Integer getY() {
        return y;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    public Integer getFloorHeight() {
        return floorHeight;
    }
    public void setFloorHeight(Integer floorHeight) {
        this.floorHeight = floorHeight;
    }
    public List<Layer> getLayers() {
        return layers;
    }
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }


    //判定当前的所有图片是否置灰
    //这个方法的调用时机 1.第一次map构建的时候；2.某一张牌移动的时候
    public void grayDecide(){
        // 最上层索引为0，不用判定牌是否被遮盖。
        List<Layer> list=this.getLayers();
        if (list == null || list.isEmpty()) {
            return; //如果图层列表为空，直接返回
        }
        //从第2层（索引为1的层）开始判定
        for (int i = 1; i < list.size(); i++) {
            Layer  layer = list.get(i);
            for (int j = 0; j < layer.getCapacity(); j++) {
                Cell cell= layer.getIndex(j);
                if(cell.getState() == 2){
                    //单元格当中有牌才进行置灰判定
                    Brand  brand = cell.getBrand();
                    //和上层的所有牌进行交集判定
                    boolean flag= this.brand2layer(brand,layer.getParentLayer());
                    brand.setGray(flag);
                }
            }
        }
    }
    private boolean brand2layer(Brand brand , Layer layer){    //判断brand是否被layer中的牌遮盖
        for (int j = 0; j < layer.getCapacity(); j++) {
            Cell cell = layer.getIndex(j);
            if(cell.getState()==2){
                Brand temp =cell.getBrand();
                boolean flag = brand.getBounds().intersects(temp.getBounds());  //分别获取brand和temp对象的边界信息，intersects方法是Rectangle类的一个方法，作用是判断两个矩形区域是否有重叠部分
                if (flag){   //如果两个矩形有重叠部分，则返回true
                    return flag;  //只要有一个元素相交就是被遮盖了
                }
            }
        }
        //当整个for循环都结束，说明当前的牌和上层的图层没有相交
        //继续和上层图层的上层图层比较
        if(layer.getParentLayer() == null){
            //说明没有上层图层，都比较完毕了，没有被遮盖
            return false;
        }else{  //说明有上层图层，递归调用，继续比较
            return brand2layer(brand, layer.getParentLayer());
        }
    }
}
