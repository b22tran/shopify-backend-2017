package com.b22tran.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HttpsUtils {

	private final Logger logger = Logger.getLogger(HttpsUtils.class);
	
	// retrieve json string of orders from orders.json link
	public String retrieveOrders(String param) {
		StringBuilder sb = new StringBuilder();
		String httpsUrl = "https://backend-challenge-fall-2017.herokuapp.com/orders.json";
		if(param!=null){
			httpsUrl += param;
		}
		URL url;

		try {
			url = new URL(httpsUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			if (con != null) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String input;
					while ((input = br.readLine()) != null) {
						sb.append(input);

					}
					br.close();

				} catch (IOException e) {
					logger.error(e);
				}
			}

		} catch (IOException e) {
			logger.error(e);
		}
		return sb.toString();
		
	}

}
