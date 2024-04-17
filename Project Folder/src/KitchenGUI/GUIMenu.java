package KitchenGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Base class JFrame that sets attributes of what every frame will look like.
 */
public class GUIMenu extends JFrame{
    public GridBagConstraints gbc;
    public ImageIcon bgImage;
    public JLabel background;

    /**
     * Constructor which executes the frameAttributes() method.
     */
    public GUIMenu(){
        frameAttributes();
    }

    /**
     * Method to set the attributes of the JFrame. Size, colour, icon, layout, title, and location on screen is set.
     */
    private void frameAttributes(){
        getContentPane().setBackground(Color.decode("#3d4547"));
        repaint();
        ImageIcon image = new ImageIcon("Project Folder/data/Images/fxiconkitchen.png");
        Image img = image.getImage();
        setIconImage(img);
        gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Kitchen Software");
        setSizeFrame();
        setLocationRelativeTo(null);
    }

    /**
     * Method that gets the size of the display, and sets the JFrame size to be 200 less than max display resolution.
     */
    private void setSizeFrame(){
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) screenSize.getSize().width-200, screenSize.getSize().height-200);
    }

    /**
     * Method that scales an image to a different size.
     * @param image Image object that gets rescaled.
     * @param widthScale Preferred width of the image.
     * @param heightScale Preferred height of the image.
     * @return A rescaled ImageIcon.
     */
    public ImageIcon addImages(ImageIcon image, int widthScale, int heightScale){
        Image originalImage = image.getImage();
        int width = image.getIconWidth();
        int height = image.getIconHeight();
        height = height/heightScale;
        width = width/widthScale;
        Image scaledImage = originalImage.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }

    /**
     * Method to dispose of the JFrame.
     */
    public void exit(){
        dispose();
    }
}
