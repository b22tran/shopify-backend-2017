package com.b22tran.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpsUtils {

	public String retrieveOrders() {
		StringBuilder sb = new StringBuilder();
		String https_url = "https://backend-challenge-fall-2017.herokuapp.com/orders.json";
		URL url;

		try {
			url = new URL(https_url);
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
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb);
		return sb.toString();
		
	}

}
