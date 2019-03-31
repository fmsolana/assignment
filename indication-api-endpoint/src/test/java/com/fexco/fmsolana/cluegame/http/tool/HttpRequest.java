package com.fexco.fmsolana.cluegame.http.tool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

	public static String sendPost(String protocol, String domain, int port, String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(protocol, domain, port, url).openConnection();
//		byte[] postDataBytes = (paramName + "=" + paramValue).getBytes("UTF-8");

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
//		conn.getOutputStream().write(postDataBytes);

		return HttpReadBody.StringBodyFromConection(conn);
	}
}
