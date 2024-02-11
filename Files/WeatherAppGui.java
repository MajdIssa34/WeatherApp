import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WeatherAppGui extends JFrame{

    public WeatherAppGui(){
        super("Majd's Weather App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 750);

        setLocationRelativeTo(null);

        setLayout(null);
        setResizable(false);
        
        addIcons();
    }
    public void addIcons(){
        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 351, 45);
        searchText.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        add(searchText);
        
        JButton searchIcon = new JButton(displayIcon("Pictures\\Search.png"));
        searchIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchIcon.setBounds(375, 13, 45, 47);
        add(searchIcon);
    }

    private ImageIcon displayIcon(String path){
        try{
            BufferedImage icon = ImageIO.read(new File(path));
            return new ImageIcon(icon);
        }catch (IOException e){
            e.printStackTrace();
            
        }
        System.out.println("Can't Load Icon");
        return null;
    }
}