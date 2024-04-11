package KitchenGUI;

import javax.swing.*;
import java.awt.*;

public class GUIMenu extends JFrame{
    public GridBagConstraints gbc;
    public ImageIcon bgImage;
    public JLabel background;
    public GUIMenu(){
        frameAttributes();
    }
    private void frameAttributes(){
        gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Kitchen Software");
        setSizeFrame();
        setLocationRelativeTo(null);
        setBackground();
    }
    private void setBackground(){
        bgImage = new ImageIcon("Project Folder/data/Images/background.jpg");
        background = new JLabel(addImages(bgImage,2,2));
        background.setLayout(new GridBagLayout());
        add(background,BorderLayout.CENTER);

    }
    private void setSizeFrame(){
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) screenSize.getSize().width-200, screenSize.getSize().height-200);
    }
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

}