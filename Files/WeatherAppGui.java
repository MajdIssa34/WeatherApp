import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WeatherAppGui extends JFrame{

    public WeatherAppGui(){
        super("Majd's Weather App");
        Image img = Toolkit.getDefaultToolkit().getImage("Pictures\\Bakcground.png");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
               super.paintComponent(g);
               g.drawImage(img, -10, -10, null);
            }
        });
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 750);

        setLocationRelativeTo(null);

        setLayout(null);
        setResizable(false);
        
        addIcons();
    }

    public void addIcons(){
        addSearchText();
        addSearchIcon();
        addWeatherIcon();
        addDegreeCelcuis();
        addWeatherConditions();
        addHumidityIcon();
        addHumidityText();
        addWindspeedIcon();
        addWindspeedText();
    }

    public void addSearchText(){
        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 400, 45);
        searchText.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        add(searchText);
    }
        
    public void addSearchIcon(){    
        JButton searchIcon = new JButton(displayIcon("Pictures\\search.png"));
        searchIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchIcon.setBounds(425, 15, 45, 45);
        searchIcon.setBorderPainted(false); 
        searchIcon.setContentAreaFilled(false);
        add(searchIcon);
    }

    public void addWeatherIcon(){
        JLabel weatherIcon = new JLabel(displayIcon("Pictures\\cloudy.png"));
        weatherIcon.setBounds(0, 100, 500, 150);
        weatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherIcon);
    }

    public void addDegreeCelcuis(){
        JLabel degreeCelcuis = new JLabel("10°C");
        degreeCelcuis.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        degreeCelcuis.setBounds(0,300,450,36);
        degreeCelcuis.setHorizontalAlignment(SwingConstants.CENTER);
        add(degreeCelcuis);
    }

    public void addWeatherConditions(){
        JLabel weatherConditions = new JLabel("Cloudy");
        weatherConditions.setFont(new Font("Times New Roman", Font.BOLD, 34));
        weatherConditions.setBounds(0,350,450,36);
        weatherConditions.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditions);
    }

    public void addHumidityIcon(){
        JLabel humidityIcon = new JLabel(displayIcon("Pictures\\humidity.png"));
        humidityIcon.setBounds(15, 500, 100, 85);
        add(humidityIcon);
    }

    public void addHumidityText(){
        JLabel humidityText = new JLabel("Humidity: 100%");
        humidityText.setBounds(15, 550, 125, 85);
        humidityText.setFont(new Font("TImes New Roman", Font.BOLD, 14));
        add(humidityText);
    }

    public void addWindspeedIcon(){
        JLabel windspeedIcon = new JLabel(displayIcon("Pictures\\windspeed.png"));
        windspeedIcon.setBounds(375, 500, 75, 75);
        add(windspeedIcon);
    }

    public void addWindspeedText(){
        JLabel windspeedText = new JLabel("Windspeed: 100km/h");
        windspeedText.setBounds(350, 550, 150, 85);
        windspeedText.setFont(new Font("TImes New Roman", Font.BOLD, 14));
        add(windspeedText);
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