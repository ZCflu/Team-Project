package KitchenGUI;

import javax.script.ScriptContext;
import javax.swing.*;
import java.awt.*;

public class scaleImage {

    public ImageIcon scaleImg(ImageIcon i,int Scale){
        Image image = i.getImage();
        Image newImage = image.getScaledInstance(Scale,Scale,java.awt.Image.SCALE_SMOOTH);
        i = new ImageIcon(newImage);
        return i;
    }
}
