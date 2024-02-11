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
        
        addTextField();
        addSearchButton();
    }

    public void addTextField(){
        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 351, 45);
        searchText.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        add(searchText);
        
    }

    public void addSearchButton(){
        JButton searchButton = new JButton(displayIcon("C:\\Users\\fayez\\Desktop\\Testing Space GitHub\\WeatherApp\\WeatherApp\\Pictures\\Search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 45, 45);
    }

    private ImageIcon displayIcon(String path){
        try{
            BufferedImage icon = ImageIO.read(new File(path));
            return new ImageIcon(icon);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Can't Load Icon");
            return null;
        }
    }
}