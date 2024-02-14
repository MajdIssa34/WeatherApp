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
    JTextField searchText;
    JLabel weatherIcon;
    JLabel cityLocation;
    JLabel degreeCelcuis;
    JLabel weatherConditions;
    JLabel humidityText;
    JLabel windspeedText;
    JLabel timeAtLocation;
    JButton searchButton;
    JLabel windspeedIcon;
    JLabel humidityIcon;
    int hourAtLocation;

    public WeatherAppGui(){
        super("Majd's Weather App");
        paintBackground("Pictures\\Bakcground.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLocationRelativeTo(null);

        setLayout(null);
        setResizable(false);
        
        addGUIComponents();
    }
    //add a background
    public void paintBackground(String path){
        Image img = Toolkit.getDefaultToolkit().getImage(path);
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
               super.paintComponent(g);
               g.drawImage(img, -10, -10, null);
            }
        });
        pack();
        this.revalidate();
        this.repaint();
    }
    //method to add all components at one time
    public void addGUIComponents(){    
        addSearchText();
        addWeatherIcon();
        addCityLocation();
        addDegreeCelcuis();
        addHumidityIcon();
        addHumidityText();
        addWindspeedText();
        addWindspeedIcon();
        addTimeAtLocation();
        addSearchButton();
    }

    public void addSearchText(){
        searchText = new JTextField();
        searchText.setBounds(15, 15, 400, 45);
        searchText.setFont(new Font("Fira Code", Font.PLAIN, 34));
        add(searchText);
    }

    public void addWeatherIcon(){  
        weatherIcon = new JLabel(displayIcon("Pictures\\Cloudy.png"));
        weatherIcon.setBounds(0, 250, 200, 200);
        weatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherIcon);
    }

    public void addCityLocation(){
        cityLocation = new JLabel("Please enter a city.");
        cityLocation.setFont(new Font("Fira Code", Font.PLAIN, 36));
        cityLocation.setBounds(0,125,500,36);
        cityLocation.setHorizontalAlignment(JLabel.CENTER);
        cityLocation.setForeground(Color.BLACK);
        add(cityLocation);
    }

    public void addDegreeCelcuis(){
        degreeCelcuis = new JLabel("10°");
        degreeCelcuis.setFont(new Font("Fira Code", Font.BOLD, 85));
        degreeCelcuis.setBounds(125,170,250,100);
        degreeCelcuis.setHorizontalAlignment(JLabel.CENTER);
        degreeCelcuis.setForeground(Color.BLACK);
        add(degreeCelcuis);
    }

    public void addTimeAtLocation(){
        timeAtLocation = new JLabel("No time");
        timeAtLocation.setFont(new Font("Fira Code", Font.PLAIN, 20));
        timeAtLocation.setBounds(140,80,200,30);
        timeAtLocation.setHorizontalAlignment(JLabel.CENTER);
        timeAtLocation.setForeground(Color.BLACK);
        add(timeAtLocation);
    }

    public void addSearchButton(){
        searchButton = new JButton(displayIcon("Pictures\\Search.png"));
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
                degreeCelcuis.setText(temperature + "°");

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
        humidityIcon = new JLabel(displayIcon("Pictures\\Humidity.png"));
        humidityIcon.setBounds(250, 275, 100, 85);
        add(humidityIcon);
    }

    public void addWindspeedIcon(){
        windspeedIcon = new JLabel(displayIcon("Pictures\\Wind.png"));
        windspeedIcon.setBounds(250, 325, 100, 85);
        add(windspeedIcon);
    }

    public void addHumidityText(){
        humidityText = new JLabel("Humidity: 100%");
        humidityText.setBounds(325, 275, 125, 85);
        humidityText.setFont(new Font("Fira Code", Font.BOLD, 14));
        humidityText.setForeground(Color.BLACK);
        add(humidityText);
    }

    public void addWindspeedText(){
        windspeedText = new JLabel("Windspeed: 100km/h");
        windspeedText.setBounds(325, 325, 155, 85);
        windspeedText.setFont(new Font("Fira Code", Font.BOLD, 14));
        windspeedText.setForeground(Color.BLACK);
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