package com.fexco.fmsolana.cluegame.http.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;

public class HttpReadBody {

	public static String StringBodyFromConection(HttpURLConnection con) throws IOException {
		BufferedReader bodyReponse = new BufferedReader(new InputStreamReader(con.getInputStream()));
		return bodyReponse.lines().collect(Collectors.joining());
	}

}
