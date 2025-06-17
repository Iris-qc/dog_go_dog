package com.dog.util;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageColor {

    /**
     * @Description: 变灰
     * @param  imgPath 图片路径
     * @param  fileUrl 输出图片路径
     * @throws
     */
    public static void inverse(String imgPath, String fileUrl){
        try {
            FileInputStream fileInputStream = new FileInputStream(imgPath);
            BufferedImage image = ImageIO.read(fileInputStream);
            //生成字符图片
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage imageBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);;
            // 绘制字符
            for (int y = 0; y < h; y++) {
                for (int x = 0; x< w; x++) {
                    int rgb = image.getRGB(x, y);
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0x00ff00) >> 8;
                    int B = rgb & 0x0000ff;

                    int gray = (int)(R * 0.299 + G * 0.587 + B * 0.114);
                    int newPixel=colorToRGB(gray,gray,gray);
                    imageBuffer.setRGB(x,y,newPixel);
                }
            }
            ImageIO.write(imageBuffer, "png", new File(fileUrl)); //输出图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 颜色转rgb值
     * @throws
     */
    public static int colorToRGB(int red,int green,int blue){
        int newPixel=0;
        newPixel=newPixel << 8;
        newPixel+=red;
        newPixel=newPixel << 8;
        newPixel+=green;
        newPixel=newPixel << 8;
        newPixel+=blue;
        return  newPixel;

    }

    public static void main(String[] args) throws IOException {
        inverse("F:\\编程相关\\Java代码\\sheep\\imgs\\约克夏.png", "F:\\编程相关\\Java代码\\sheep\\imgs\\约克夏_gray.png");

    }
}
