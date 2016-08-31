package com.jcg.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.jcg.example.bean.Item;
import com.jcg.example.bean.User;
import com.jcg.example.bean.UserBean;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String link = "https://api.github.com/search/code?q=addClass+in:file+language:js+repo:jquery/jquery";
		URL crunchifyUrl = new URL(link);
		HttpURLConnection crunchifyHttp = (HttpURLConnection) crunchifyUrl.openConnection();
		Map<String, List<String>> crunchifyHeader = crunchifyHttp.getHeaderFields();
 
		// If URL is getting 301 and 302 redirection HTTP code then get new URL link.
		// This below for loop is totally optional if you are sure that your URL is not getting redirected to anywhere
		for (String header : crunchifyHeader.get(null)) {
			if (header.contains(" 302 ") || header.contains(" 301 ")) {
				link = crunchifyHeader.get("Location").get(0);
				crunchifyUrl = new URL(link);
				crunchifyHttp = (HttpURLConnection) crunchifyUrl.openConnection();
				crunchifyHeader = crunchifyHttp.getHeaderFields();
			}
		}
		InputStream crunchifyStream = crunchifyHttp.getInputStream();
		String crunchifyResponse = crunchifyGetStringFromStream(crunchifyStream);
		System.out.println(crunchifyResponse);
		String jsonString=crunchifyResponse;
		ObjectMapper mapper = new ObjectMapper();
		try {
	         UserBean userBean  = mapper.readValue(jsonString, UserBean.class);
	         System.out.println(userBean);
	         SerializationConfig serializationConfig = mapper.getSerializationConfig();
	         mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);
	         /* mapper.enable(serializationConfig.Feature.INDENT_OUTPUT);*/
	         jsonString = mapper.writeValueAsString(userBean);
	         File file =new File("src/resources/file.txt");
	         if(!file.exists()){
	    			file.createNewFile();
	    		}
	         FileWriter fileWritter = new FileWriter(file.getName(),true);
 	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
 	      
 	      
	        for(Item i:userBean.getItems()){
	        	System.out.println(i.getName());
	        	  //bufferWritter.write(i.getName());
	        	System.out.println(i.getRepository().getFullName());
	        	// bufferWritter.write(i.getFullName());
	        }
	         System.out.println(jsonString);

	      } 
	      catch (JsonParseException e) { e.printStackTrace(); } 
	      catch (JsonMappingException e) { e.printStackTrace(); }
	      catch (IOException e) { e.printStackTrace(); }
	}
 
        // ConvertStreamToString() Utility - we name it as crunchifyGetStringFromStream()
	private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
		if (crunchifyStream != null) {
			Writer crunchifyWriter = new StringWriter();
 
			char[] crunchifyBuffer = new char[2048];
			try {
				Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
				int counter;
				while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
					crunchifyWriter.write(crunchifyBuffer, 0, counter);
				}
			} finally {
				crunchifyStream.close();
			}
			return crunchifyWriter.toString();
		} else {
			return "No Contents";
		}
	}

}
