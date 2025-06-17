package com.dog.test;
import com.dog.model.Layer;
import com.dog.model.Map;
import java.util.List;
import java.util.ArrayList;
public class TestMap {
    public static Map buildMap(int difficulty) {
        Map map = new Map();
        List<Layer> layers = new ArrayList<>();
        switch (difficulty) {
            case 1: //简单模式
                map.setFloorHeight(2);
                layers.add(TestLayer.buildLayer(3, 5)); // Layer 0 (顶层)
                layers.add(TestLayer.buildLayer(4, 3)); // Layer 1
                break;
            case 2: //普通模式
                map.setFloorHeight(3);
                layers.add(TestLayer.buildLayer(6, 6)); // Layer 0
                layers.add(TestLayer.buildLayer(5, 3)); // Layer 1
                layers.add(TestLayer.buildLayer(4, 3)); // Layer 2
                break;
            case 3: //困难模式
                map.setFloorHeight(4);
                layers.add(TestLayer.buildLayer(7, 6)); // Layer 0
                layers.add(TestLayer.buildLayer(6, 6));  // Layer 1
                layers.add(TestLayer.buildLayer(5, 6));  // Layer 2
                layers.add(TestLayer.buildLayer(4, 6));  // Layer 3
                break;
        }
        for (int i = 1; i < layers.size(); i++) {
            layers.get(i).setParentLayer(layers.get(i - 1));    //将当前图层的前一个图层设置为上层图层
        }
        map.setLayers(layers);
        return map;
    }

}
