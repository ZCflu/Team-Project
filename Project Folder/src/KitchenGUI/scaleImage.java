package KitchenGUI;

import javax.script.ScriptContext;
import javax.swing.*;
import java.awt.*;

/**
 * Class to scale the image of an ImageIcon
 */
public class scaleImage {
    /**
     * Method to scale an ImageIcon depending on the scale input as a parameter.
     * @param i An ImageIcon.
     * @param Scale Preferred scale of the image.
     * @return A re-scaled ImageIcon.
     */
    public ImageIcon scaleImg(ImageIcon i,int Scale){
        Image image = i.getImage();
        Image newImage = image.getScaledInstance(Scale,Scale,java.awt.Image.SCALE_SMOOTH);
        i = new ImageIcon(newImage);
        return i;
    }
}
