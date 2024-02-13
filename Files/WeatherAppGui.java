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
    //private TimeZoneFinder timeZone = new TimeZoneFinder();

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
        
        addHumidityIcon();
        addWindspeedIcon();
        addGUIComponents();

        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 400, 45);
        searchText.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        add(searchText);

        
    }

    public void addGUIComponents(){
        JTextField searchText = new JTextField();
        searchText.setBounds(15, 15, 400, 45);
        searchText.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 34));
        add(searchText);
      
        JLabel weatherIcon = new JLabel(displayIcon("Pictures\\Cloudy.png"));
        weatherIcon.setBounds(0, 55, 200, 200);
        weatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherIcon);

        JLabel cityLocation = new JLabel("Please enter a city.");
        cityLocation.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 34));
        cityLocation.setBounds(0,250,500,36);
        cityLocation.setForeground(Color.BLACK);
        cityLocation.setHorizontalAlignment(SwingConstants.CENTER);
        add(cityLocation);

        JLabel degreeCelcuis = new JLabel("10 °C");
        degreeCelcuis.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 34));
        degreeCelcuis.setBounds(185,85,150,100);
        degreeCelcuis.setForeground(Color.BLACK);
        degreeCelcuis.setHorizontalAlignment(SwingConstants.CENTER);
        add(degreeCelcuis);

        JLabel weatherConditions = new JLabel("Cloudy");
        weatherConditions.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 34));
        weatherConditions.setBounds(175,150,150,100);
        weatherConditions.setForeground(Color.BLACK);
        weatherConditions.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditions);

        JLabel humidityText = new JLabel("Humidity: 100%");
        humidityText.setBounds(15, 550, 125, 85);
        humidityText.setFont(new Font("TImes New Roman", Font.ROMAN_BASELINE, 14));
        humidityText.setForeground(Color.BLACK);
        add(humidityText);

        JLabel windspeedText = new JLabel("Windspeed: 100km/h");
        windspeedText.setBounds(350, 550, 150, 85);
        windspeedText.setFont(new Font("TImes New Roman", Font.ROMAN_BASELINE, 14));
        windspeedText.setForeground(Color.BLACK);
        add(windspeedText);

        JLabel timeAtLocation = new JLabel("No time");
        timeAtLocation.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 34));
        timeAtLocation.setBounds(0,300,500,36);
        timeAtLocation.setForeground(Color.BLACK);
        timeAtLocation.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeAtLocation);

        JButton searchButton = new JButton(displayIcon("Pictures\\Search.png"));

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
                        weatherIcon.setIcon(displayIcon("Pictures\\Clear.png"));
                        break;
                    case "Cloudy":
                    weatherIcon.setIcon(displayIcon("Pictures\\Cloudy.png"));
                        break;
                    case "Rain":
                    weatherIcon.setIcon(displayIcon("Pictures\\Rain.png"));
                        break;
                    case "Snow":
                    weatherIcon.setIcon(displayIcon("Pictures\\Snow.png"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                degreeCelcuis.setText(temperature + " °C");

                // update weather condition text
                weatherConditions.setText(weatherCondition);

                //update timezone
                String location = (String) weatherData.get("location");
                timeAtLocation.setText(WeatherAppFunctionality.getTimeZone(location));
                cityLocation.setText(location);

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
    
    public void addHumidityIcon(){
        JLabel humidityIcon = new JLabel(displayIcon("Pictures\\Humidity.png"));
        humidityIcon.setBounds(15, 500, 100, 85);
        add(humidityIcon);
    }

    public void addWindspeedIcon(){
        JLabel windspeedIcon = new JLabel(displayIcon("Pictures\\Wind.png"));
        windspeedIcon.setBounds(375, 500, 75, 75);
        add(windspeedIcon);
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