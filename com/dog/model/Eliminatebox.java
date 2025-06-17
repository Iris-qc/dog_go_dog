package com.dog.model;

import com.dog.view.Start;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消除区域
 */
public class Eliminatebox   {


    private Integer step = 5;


    private  static final List<Brand> slot = new ArrayList<>();


    /**
     * 清空集合
     * 使用迭代器清空集合
     * @param name
     */
    public void deleteByBrandName(String name){
        Iterator<Brand> iterator= slot.iterator();
        while(iterator.hasNext()){
            Brand  brand=iterator.next();
            if(brand.getName().equals(name)){
                brand.getParent().remove(brand);
                iterator.remove();
            }
        }
    }

    /**
     * 鼠标无法点击已经点过的图片
     * @param brand
     */
    private void noMouseListener(Brand brand){
        MouseListener[] mouseListeners = brand.getMouseListeners();
        //消除区域框里图形无法点击
        if (mouseListeners!=null){
            for (MouseListener mouseListener : mouseListeners) {
                brand.removeMouseListener(mouseListener);
            }
        }
    }


    public void addSlot(Brand brand){
        slot.add(brand);
        noMouseListener(brand);
        //消除区域图形排序
        slot.sort(Comparator.comparing(Brand::getName));
        //消除
        Map<String, List<Brand>> map = slot.stream().collect(Collectors.groupingBy(Brand::getName));
        Set<String> keys = map.keySet();

        for (String key : keys) {
            List<Brand> brands = map.get(key);
            if (brands.size() == 3) {
                deleteByBrandName(key);//执行消除
                break;
            }
        }
        paint();
        over(brand);
    }

    //绘制到消除区域
    public void paint(){

        for (int i =0;i<slot.size();i++){

            Brand brand = slot.get(i);
            int x = step+i*brand.getWidth()+5/2+10;
            brand.setBounds(x,600,50,50);

        }

    }
    /**
     * 游戏结束
     * @param brand
     */
    private void over(Brand brand){
        if (slot.size()>=7){
            JOptionPane.showMessageDialog(brand,"over");
            System.exit(0);
        }
    }
}
