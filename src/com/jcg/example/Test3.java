package com.jcg.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.example.Item;
import com.example.Match;
import com.example.TextMatch;
import com.example.UserBean1;

public class Test3 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		URL crunchifyUrl = new URL(
				"https://api.github.com/search/code?q=addClass+extension:js+language:js+repo:jquery/jquery");
		//https://api.github.com/repositories/167174/contents/src/attributes/classes.js?ref=305f193aa57014dc7d8fa0739a3fefd47166cd4
		HttpURLConnection crunchifyHttp = (HttpURLConnection) crunchifyUrl
				.openConnection();
		/*crunchifyHttp.setRequestProperty("Accept",
				"application/vnd.github.VERSION.raw");*/
		crunchifyHttp.setRequestProperty("Accept",
				"application/vnd.github.v3.text-match+json");
		

		// application/vnd.github.v3.text-match+json
		Map<String, List<String>> crunchifyHeader = crunchifyHttp
				.getHeaderFields();
		System.out.println("crunchifyHeader" + crunchifyHeader);
		InputStream crunchifyStream = crunchifyHttp.getInputStream();
		String crunchifyResponse = crunchifyGetStringFromStream(crunchifyStream);
		// System.out.println(crunchifyResponse);
		String jsonString = crunchifyResponse;
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserBean1 userBean = mapper.readValue(jsonString, UserBean1.class);
			// System.out.println(userBean);
			SerializationConfig serializationConfig = mapper
					.getSerializationConfig();
			mapper.configure(
					SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);
			/* mapper.enable(serializationConfig.Feature.INDENT_OUTPUT); */
			jsonString = mapper.writeValueAsString(userBean);
			File file1 = new File("file1.txt");
			File file2 = new File("file2.txt");
			File file3 = new File("file3.txt");
			if (!file1.exists()||!file2.exists()||!file3.exists()) {
				file1.createNewFile();
				file2.createNewFile();
				file3.createNewFile();
			}
			FileWriter fileWritter1 = new FileWriter(file1.getName(), true);
			/*FileWriter fileWritter2 = new FileWriter(file2.getName(), true);
			FileWriter fileWritter3 = new FileWriter(file3.getName(), true);*/
			/*List<String> lines = Arrays.asList("The Third line", "The second line");*/
			Path file11 = Paths.get("C:\\dev\\the-file-name.txt");
			Path file22=Paths.get("C:\\dev\\the-file-name1.txt");
            List<String> list=new ArrayList<String>();
            List<String> list1=new ArrayList<String>();
			/*BufferedWriter bufferWritter = new BufferedWriter(fileWritter1);*/
		
			for (Item i : userBean.getItems()) {
				System.out.println("===========It is start of json==========");
				//Files.write(file, lines, Charset.forName("UTF-8"));
				list.add(i.getRepository().getFullName()+"/"+i.getPath());
				list1.add(i.getRepository().getFullName()+"/"+i.getPath());
				
				//System.out.println("This is list value"+list);
				//fileWritter2.write(i.getRepository().getFullName());
				  //i.getRepository().getFullName();
				System.out.println("This is ths path"+i.getPath());
				System.out.println("The full name"+i.getName());
				for (TextMatch t : i.getTextMatches()) {
					System.out.println("This is object url" + t.getObjectUrl());
					System.out.println("This is property" + t.getProperty());
					System.out.println("This is Object type"
							+ t.getObjectType());
					parsefile(t.getObjectUrl(), "addClass",list1);
					//list1.add(t.getFragment());
					System.out.println("it is fragment"+t.getFragment());
					//fileWritter3.write(t.getFragment());
					for (Match m : t.getMatches()) {
						System.out.println("It is a text" + m.getText());
						System.out.println("+++It is a indices+++"
								+ m.getIndices());
					}
				}
				System.out.println("===========It is END of json==========");
				// bufferWritter.write(i.getName());
				// System.out.println(i.getRepository().getFullName());
				// bufferWritter.write(i.getFullName());
			}
			
			Files.write(file11, list, Charset.forName("UTF-8"));
			Files.write(file22, list1, Charset.forName("UTF-8"));
			
			
			// System.out.println(jsonString);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	private static String crunchifyGetStringFromStream(
			InputStream crunchifyStream) throws IOException {
		if (crunchifyStream != null) {
			Writer crunchifyWriter = new StringWriter();

			char[] crunchifyBuffer = new char[2048];
			try {
				Reader crunchifyReader = new BufferedReader(
						new InputStreamReader(crunchifyStream, "UTF-8"));
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

	private static void parsefile(String crunchifyResponse, String matchvalue,List list)
			throws IOException {
		URL crunchifyUrl = new URL(
				crunchifyResponse);
		HttpURLConnection connection=(HttpURLConnection) crunchifyUrl
				.openConnection();
		connection.setRequestProperty("Accept",
				"application/vnd.github.VERSION.raw");
		URL oath=new URL("https://api.github.com/users/pranav13?client_id=8ac5733d41692c02cd76&client_secret=a01aa51e3bf1441001ff6f5e2210c181d02891f4");
		HttpURLConnection oathconn=(HttpURLConnection)oath.openConnection();
		Map<String, List<String>> crunchifyHeader1 = connection
				.getHeaderFields();
		System.out.println("crunchifyHeader" + crunchifyHeader1);
		InputStream crunchifyStream = connection.getInputStream();
		String crunchifyResponse11 = crunchifyGetStringFromStream(crunchifyStream);
		FileWriter fw = new FileWriter("abc.txt");
		fw.write(crunchifyResponse11);
		fw.close();
		File file = new File("abc.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
			int lineno = 0;
			while (in.hasNext()) {
				String line = in.nextLine();
				lineno++;
				// System.out.println(line);
				System.out.println("The match value is coming"+matchvalue);
				if (line.contains(matchvalue)){
					System.out.println(line+lineno++);
				list.add(line+"Line Number"+lineno++);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
