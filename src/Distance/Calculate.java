package Distance;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Никита on 23.01.2017.
 */
public class Calculate {
    private static final double EARTH_RADIUS = 6371.;
    HttpURLConnection connection = null;
    public void getXML (String query, String fileName){
        try{
            connection = (HttpURLConnection)new URL(query.replace(" " ,"%20")).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            connection.connect();

            StringBuilder sb = new StringBuilder();
            File file = new File(fileName);
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                PrintWriter writer = new PrintWriter(file.getAbsoluteFile());
                String line;
                while ((line = reader.readLine()) != null){
                    sb.append(line);
                    sb.append("\n");
                    //System.out.println(sb.toString());
                }
                writer.write(sb.toString());
                writer.flush();
                writer.close();
            } else {
                JOptionPane.showMessageDialog(null, "fail:" + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch(Throwable cause){
            cause.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    public double calculateTheDistance(Point pointOfDeparture, Point pointOfArrival){

        final double dlng = deg2rad(pointOfDeparture.getLng() - pointOfArrival.getLng());
        final double dlat = deg2rad(pointOfDeparture.getLat() - pointOfArrival.getLat());
        final double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(deg2rad(pointOfArrival.lat))
                * Math.cos(deg2rad(pointOfDeparture.lat)) * Math.sin(dlng / 2) * Math.sin(dlng / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return c * EARTH_RADIUS;
    }

    public static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }
}
