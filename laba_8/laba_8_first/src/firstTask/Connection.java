package firstTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Connection {
    public static void main(String[] args){
        String urlName = "https://it.belstu.by";
        int timeout = 10_000;
        URL url;

        try{
            url = new URL(urlName);
            final URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(timeout);
            System.out.println(urlName +
                    "\nContent type: " + urlConnection.getContentType()+
                    "\nContent class: " + urlConnection.getClass() +
                    "\nContent length: " + urlConnection.getContentLength());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        URL belstu = null;

        try{
            belstu = new URL(urlName);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }

        if(belstu ==null){
            throw new RuntimeException();
        }

        try (BufferedReader d = new BufferedReader
                (new InputStreamReader(belstu.openStream())))
        {
            String line = "";
            while ((line = d.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace(); }
    }
}
