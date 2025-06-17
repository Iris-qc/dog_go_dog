package com.dog.util;

import java.awt.*;

public class ImageUtil {

    public  static Image get(String fileName){
        return  Toolkit.getDefaultToolkit().getImage("imgs\\"+fileName);
    }

}