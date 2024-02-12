import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.JSONObject;

public class WeatherAppGui extends JFrame{

    private JSONObject weatherData;

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
        //addSearchFunction();
        // addSearchText();
        // addSearchIcon();
        //addWeatherIcon();
        //addDegreeCelcuis();
        //addWeatherConditions();
        addHumidityIcon();
        //addHumidityText();
        addWindspeedIcon();
        //addWindspeedText();
        addGUIComponents();
        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 400, 45);
        searchText.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        add(searchText);
    }

    public void addGUIComponents(){
        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 400, 45);
        searchText.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        add(searchText);
      
        JLabel weatherIcon = new JLabel(displayIcon("Pictures\\cloudy.png"));
        weatherIcon.setBounds(0, 150, 500, 150);
        weatherIcon.setSize(500, 200);
        weatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherIcon);

        JLabel degreeCelcuis = new JLabel("10°C");
        degreeCelcuis.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        degreeCelcuis.setBounds(0,400,450,36);
        degreeCelcuis.setHorizontalAlignment(SwingConstants.CENTER);
        add(degreeCelcuis);

        JLabel weatherConditions = new JLabel("Cloudy");
        weatherConditions.setFont(new Font("Times New Roman", Font.BOLD, 34));
        weatherConditions.setBounds(0,450,450,80);
        weatherConditions.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditions);

        JLabel humidityText = new JLabel("Humidity: 100%");
        humidityText.setBounds(15, 550, 125, 85);
        humidityText.setFont(new Font("TImes New Roman", Font.BOLD, 14));
        add(humidityText);

        JLabel windspeedText = new JLabel("Windspeed: 100km/h");
        windspeedText.setBounds(350, 550, 150, 85);
        windspeedText.setFont(new Font("TImes New Roman", Font.BOLD, 14));
        add(windspeedText);

        // JButton searchIcon = new JButton(displayIcon("Pictures\\search.png"));
        // searchIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // searchIcon.setBounds(425, 15, 45, 45);
        // searchIcon.setBorderPainted(false); 
        // searchIcon.setContentAreaFilled(false);
        // add(searchIcon);
        JButton searchButton = new JButton(displayIcon("Pictures\\search.png"));

        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(425, 15, 45, 45);
        searchButton.setBorderPainted(false); 
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchText.getText();

                // validate input - remove whitespace to ensure non-empty text
                if(userInput.replaceAll("\\s", "").length() <= 0){
                    return;
                }

                // retrieve weather data
                weatherData = WeatherAppFunctionality.getWeatherData(userInput);

                // update gui

                // update weather image
                String weatherCondition = (String) weatherData.get("weather_condition");

                // depending on the condition, we will update the weather image that corresponds with the condition
                switch(weatherCondition){
                    case "Clear":
                        weatherIcon.setIcon(displayIcon("Pictures\\clear.png"));
                        break;
                    case "Cloudy":
                    weatherIcon.setIcon(displayIcon("Pictures\\cloudy.png"));
                        break;
                    case "Rain":
                    weatherIcon.setIcon(displayIcon("Pictures\\rain.png"));
                        break;
                    case "Snow":
                    weatherIcon.setIcon(displayIcon("Pictures\\snow.pngImage"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                degreeCelcuis.setText(temperature + " C");

                // update weather condition text
                weatherConditions.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

                // update windspeed text
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");
            }
        });
        add(searchButton);
    }
    

    // public void addWeatherIcon(){
    //     JLabel weatherIcon = new JLabel(displayIcon("Pictures\\cloudy.png"));
    //     weatherIcon.setBounds(0, 100, 500, 150);
    //     weatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
    //     add(weatherIcon);
    // }

    // public void addDegreeCelcuis(){
    //     JLabel degreeCelcuis = new JLabel("10°C");
    //     degreeCelcuis.setFont(new Font("Times New Roman", Font.PLAIN, 34));
    //     degreeCelcuis.setBounds(0,300,450,36);
    //     degreeCelcuis.setHorizontalAlignment(SwingConstants.CENTER);
    //     add(degreeCelcuis);
    // }

    // public void addWeatherConditions(){
    //     JLabel weatherConditions = new JLabel("Cloudy");
    //     weatherConditions.setFont(new Font("Times New Roman", Font.BOLD, 34));
    //     weatherConditions.setBounds(0,350,450,36);
    //     weatherConditions.setHorizontalAlignment(SwingConstants.CENTER);
    //     add(weatherConditions);
    // }

    public void addHumidityIcon(){
        JLabel humidityIcon = new JLabel(displayIcon("Pictures\\humidity.png"));
        humidityIcon.setBounds(15, 500, 100, 85);
        add(humidityIcon);
    }

    // public void addHumidityText(){
    //     JLabel humidityText = new JLabel("Humidity: 100%");
    //     humidityText.setBounds(15, 550, 125, 85);
    //     humidityText.setFont(new Font("TImes New Roman", Font.BOLD, 14));
    //     add(humidityText);
    // }

    public void addWindspeedIcon(){
        JLabel windspeedIcon = new JLabel(displayIcon("Pictures\\windspeed.png"));
        windspeedIcon.setBounds(375, 500, 75, 75);
        add(windspeedIcon);
    }

    // public void addWindspeedText(){
    //     JLabel windspeedText = new JLabel("Windspeed: 100km/h");
    //     windspeedText.setBounds(350, 550, 150, 85);
    //     windspeedText.setFont(new Font("TImes New Roman", Font.BOLD, 14));
    //     add(windspeedText);
    // }

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