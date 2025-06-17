package com.dog.model;
/*
    对于游戏当中牌的抽象
 */
import com.dog.view.Start;
import com.dog.util.ImageUtil;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;
public class Brand extends Component{   //继承Component作用：1.可以通过add方法把自己定义的牌添加到当前窗口  2.事件机制（牌的消除等）
    private  String  name;          // 牌的名称，作用：1.三个相同的牌消除  2.通过name在imgs中找到图片文件
    private  String  id;
    private  Integer x=0;//左上角坐标
    private  Integer y=0;
    private Image   image;//正常图片
    private Image   grayImage;//灰色图片
    private Boolean isGray=false;    //是否置灰
    private Cell cell;
    public Brand(String name) {
        this.name = name;
        this.image = ImageUtil.get(name+".png");
        this.grayImage = ImageUtil.get(name+"_gray.png");
        this.id= UUID.randomUUID().toString();//使用UUID保证牌id的全局唯一性
        Brand self=this;
        //鼠标点击监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Brand  brand = (Brand) e.getSource();
                if(brand.getGray()||brand.getName().equals("消除区域")||brand.getName().equals("游戏机背景")){}//过滤无效点击
                else{
                    System.out.println(brand.getName()+"被点击啦.....");
                    eliminatebox.addSlot(brand);
                    self.getCell().setState(1);
                    self.getCell().setBrand(null);
                    self.setCell(null);
                    Start.map.grayDecide();
                }
            }
        });
    }
    Eliminatebox eliminatebox  =new Eliminatebox();
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(isGray){
            g.drawImage(this.getGrayImage(),x,y,null);//绘制灰色图片
        }else{
            g.drawImage(this.getImage(),x,y,null);//绘制正常图片
        }
    }
    public Cell getCell() {
        return cell;
    }
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    public Boolean getGray() {
        return isGray;
    }
    public void setGray(Boolean gray) {
        isGray = gray;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Image getGrayImage() {
        return grayImage;
    }
    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }
}
