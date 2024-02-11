import javax.swing.SwingUtilities;

public class WeatherApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}
