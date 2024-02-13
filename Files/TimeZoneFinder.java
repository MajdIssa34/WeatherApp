import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeZoneFinder {
    
    
    public String getTimeZone(String city){

        try {
            URL url = new URL("http://api.timezonedb.com/v2.1/get-time-zone?key=" + "Z5KTANBV2G3X" + "&format=json&by=zone&zone=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            String jsonResponse = response.toString();
            String timestamp = jsonResponse.split("\"formatted\": \"")[1].split("\"}")[0];
            
            // Convert the timestamp to LocalDateTime
            //LocalDateTime cityDateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Print the current date and time of the given city
            return timestamp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

    