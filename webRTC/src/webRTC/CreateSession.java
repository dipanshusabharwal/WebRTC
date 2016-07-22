package webRTC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/CreateSession")

public class CreateSession extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getting room name entered at welcomepage.jsp
		String roomName = request.getParameter("room");

		int apiKey = 45618232 ;
		String apiSecret = "09f6db96849092b8c0584c63c63e495ebd82961f";		
	
		String sessionID = null;
		String token = null;
		String role = "Moderator";
		
		try{
			
			String roomURL = "http://webservicetokbox.cloud.cms500.com/rest/sessioncredentials/create?RoomDetails="+roomName;
			
			URL url = new URL(roomURL);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			String output;
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(output);
				
				sessionID = (String) json.get("SessionID");
				System.out.println("SessionID from Server .... "+sessionID);
				token = (String) json.get("Token");
				System.out.println("Token from Server .... "+token);
			}

			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Welcome.jsp");
	        requestDispatcher.forward(request, response);
		  }
				
		//setting data to forward it to another page - videopage.jsp
		request.setAttribute("APIKEY", apiKey);
		request.setAttribute("APISECRET", apiSecret);
		request.setAttribute("SESSIONID", sessionID);
		request.setAttribute("TOKEN", token);
		request.setAttribute("Role", role);
		
		request.getRequestDispatcher("/VideoPage.jsp").forward(request,response);
	}
}