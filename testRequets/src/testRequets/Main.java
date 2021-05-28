package testRequets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

	
	public static void main(String[] args) throws IOException {
		
		Map<String, String> mapData = new LinkedHashMap<String, String>();
		mapData.put("lg", "aaaaa");
		mapData.put("parent", "bbbbb");
		mapData.put("source", "ccccc");
		mapData.put("style", "dddddd");
		mapData.put("target", "eeeee");
		mapData.put("value", "fffff");
		
		RequestPost requestPost = new RequestPost();
		
		requestPost.sendPOST(mapData);
//		
	}

	

}
