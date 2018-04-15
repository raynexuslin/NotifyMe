import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import javax.websocket.Session;

public class WeatherAPI extends Thread{
	private Session mSession;
	
	public WeatherAPI(Session s) {
		this.mSession = s;
		this.start();
	}
	
	public void run() {
		String weatherURL = "http://api.openweathermap.org/data/2.5/weather?id=5368381&APPID=afbcfd3e650101530d7d00ed5956e2e0";
		try {
			URL url = new URL(weatherURL);
	        URLConnection yc = url.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        String response = "";
	        while ((inputLine = in.readLine()) != null) {
	        		response += inputLine;
	        }
	        System.out.println(response);
	        String weatherData = "Weather|" + response;
	        mSession.getBasicRemote().sendText(weatherData);
	            
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
}
