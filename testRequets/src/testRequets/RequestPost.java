package testRequets;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestPost {

	private static final String GET_URL = "http://localhost:8087/api/mx-cells/";
	private static final String POST_URL = "http://localhost:8087/api/mx-cells/";
	private static final String TOKEN_POST = "http://localhost:8087/api/authenticate";

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	
	private void sendGET() throws IOException {
		
		String token = tokenPOST(PASSWORD, USERNAME);
		
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", token);

		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}

	public void sendPOST(Map<String, String> mapData ) throws IOException {

		String token = tokenPOST(PASSWORD, USERNAME);

		try {
			URL url = new URL(POST_URL);

			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Authorization", "Bearer " + token);
			http.setRequestProperty("Content-Type", "application/json");

			
//			System.out.println(mapData.keySet());
//			System.out.println(mapData.values());
			String data = "";
			data += "{";

			// convert map to string
			int sizeData = mapData.size();
			for (Map.Entry entry : mapData.entrySet()) {
				data += "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"";
				if (sizeData > 1) {
					data += ",";
					sizeData--;
				}
			}
			data += "}";
//			System.out.println(data);

			byte[] out = data.getBytes(StandardCharsets.UTF_8);
			
			// test for read byte[]
//			ByteArrayInputStream byteArrayInputStr = new ByteArrayInputStream(out);
//
//			int b = 0;
//			while ((b = byteArrayInputStr.read()) != -1) {
//				// Convert byte to character
//				char ch = (char) b;
//
//				// Print the character
//				System.out.println("Char : " + ch);
//			}

			OutputStream stream = http.getOutputStream();
			
			stream.write(out);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			http.disconnect();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * 
	 * @param password
	 * @param username
	 * @return
	 * @throws IOException
	 */
	private String tokenPOST(String password, String username) throws IOException {

		String token = null;

		try {

			URL url = new URL(TOKEN_POST);

			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/json");

			String data = "{  \"password\": \"" + password + "\",  \"rememberMe\": \"true\",  \"username\": \""
					+ username + "\"}";

			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			System.out.println(http.getResponseCode() + " TOKEN " + http.getResponseMessage());
			if (http.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				String rps = response.toString();
				rps = rps.replace("{", "");
				rps = rps.replace("}", "");
				rps = rps.replace("\"", "");
				String[] tabRps;
				tabRps = rps.split(":");
				token = tabRps[1];
			}
			http.disconnect();

			return token;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return token;
		}

	}

}
