package com.dog.test;

import com.dog.model.Brand;
import com.dog.model.Cell;
import com.dog.model.Layer;

import java.util.Random;

public class TestLayer {

    public  static String[] brandNames={
            "巴哥","比格犬","边牧",
            "柴犬","贵宾犬","柯基",
            "拉布拉多","萨摩耶","约克夏",
            "电子狗","澳牧","杜宾",
            "西施犬","吉娃娃"
    };


    /*
        参数cellNumx,cellnumy表示构建的图层每行多少单元格，每列多少单元格。
        两个参数的乘积 结果需要被3整除，否则会出现异常。
     */
    public static Layer buildLayer(int cellNumx, int cellnumy){
        Layer layer =new Layer(cellNumx,cellnumy);
//
        Cell[][]  cells = layer.getCells();

        Brand[]  brands =new Brand[layer.getCapacity()];

        //  1  从牌的种类当中随机取一个牌名
        //  2  根据随机取到的牌名创建3个相同的牌对象

        for (int i = 0; i < brands.length; i=i+3) {
            int  rand =new  Random().nextInt(brandNames.length);
            String brandName=brandNames[rand];
            Brand brand1=new Brand(brandName);

            Brand brand2=new Brand(brandName);

            Brand brand3=new Brand(brandName);

            brands[i]=brand1;
            brands[i+1]=brand2;
            brands[i+2]=brand3;
        }

        //   3  把一维数组当中的牌  打乱顺序
        for (int i = 0; i < brands.length; i++) {
            Brand brand = brands[i];

            int  rand =new  Random().nextInt(brands.length);
            brands[i]= brands[rand];
            brands[rand]=brand;

        }

        int count =0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                Brand brand =brands[count];
                Cell cell = new Cell(brand);
                brand.setCell(cell);

                cells[i][j]=cell;
                count++;

            }
        }

        layer.setSize(count);

        layer.show();

        return layer;
    }



    public static void main(String[] args) {

        Layer layer =new Layer(3,5);
        Cell[][]  cells = layer.getCells();

        // 把牌放入到cells数组中。
        Brand[]   brands =new Brand[layer.getCapacity()];

        //  1  从牌的种类当中随机取一个牌名

        //  2  根据随机取到的牌名创建3个相同的牌对象

        for (int i = 0; i < brands.length; i=i+3) {
            int  rand =new  Random().nextInt(brandNames.length);
            String brandName=brandNames[rand];
            System.out.println(brandName);
            Brand brand1=new Brand(brandName);
            Brand brand2=new Brand(brandName);
            Brand brand3=new Brand(brandName);

            brands[i]=brand1;
            brands[i+1]=brand1;
            brands[i+2]=brand1;
        }
        System.out.println("存入一维数组的牌：");
        for (Brand brand : brands) {
            System.out.print(brand.getName()+"  ");
        }
        System.out.println();

        //   4   把一维数组当中的牌打乱顺序
        for (int i = 0; i < brands.length; i++) {
            Brand brand = brands[i];

            int  rand =new  Random().nextInt(brands.length);
            brands[i]= brands[rand];
            brands[rand]=brand;

        }
        //  5   输出打乱顺序后的一维数组
        System.out.println("--------------------");
        System.out.println(" 输出打乱顺序后的一维数组的牌：");
        for (Brand brand : brands) {
            System.out.print(brand.getName()+"  ");
        }
        System.out.println();

        //   6  把一维数组当中的数据，存入二维数组cells当中。
        int count =0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j]=new Cell(brands[count++]);
            }
        }
        layer.setSize(count);
        //   7  遍历当前图层的二维数据
        System.out.println("------------------------");

        for (int i = 0; i < layer.getCapacity(); i++) {
            Cell cell= layer.getIndex(i);
            System.out.print(cell.getBrand().getName());

        }

    }

}