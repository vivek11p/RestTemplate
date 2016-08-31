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
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.http.HttpHeaders;

import com.example.Item;
import com.example.Match;
import com.example.TextMatch;
import com.example.UserBean1;

;

public class Test1 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String link = "https://api.github.com/search/repositories?q=tetris+language:assembly+&sort=&order=desc";
		// https://api.github.com/search/code?q=addClass+in:file+language:js+repo:jquery/jquery
		String link="";
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Search the critera that you want to serach( repositories,code,issue,user)");
		String criteria = sc.next();
		String value = null;
		System.out.println(criteria);
		if (criteria.equals("repositories")){
			System.out.println(buildrepourl("tetris", null, null, null, ">5","assembly", "desc"));
			link=buildrepourl("tetris", null, null, null, ">5","assembly", "desc");
			}
		if (criteria.equals("code")){
			System.out.println(builduserurl("tom", "user", "login", ">42",
					null, "js", ">1000"));
			link=builduserurl("tom", "user", "login", ">42",
					null, "js", ">1000");
		}
		URL crunchifyUrl = new URL(link);
		HttpURLConnection crunchifyHttp = (HttpURLConnection) crunchifyUrl
				.openConnection();
		crunchifyHttp.setRequestProperty("Accept",
				"application/vnd.github.v3.text-match+json");
		// application/vnd.github.v3.text-match+json
		Map<String, List<String>> crunchifyHeader = crunchifyHttp
				.getHeaderFields();
		System.out.println("crunchifyHeader" + crunchifyHeader);
		InputStream crunchifyStream = crunchifyHttp.getInputStream();
		String crunchifyResponse = crunchifyGetStringFromStream(crunchifyStream);
		System.out.println(crunchifyResponse);
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
			File file = new File("src/resources/file.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

			for (Item i : userBean.getItems()) {
				System.out.println("===========It is start of json==========");
				System.out.println(i.getName());
				for (TextMatch t : i.getTextMatches()) {
					System.out.println("This is object url" + t.getObjectUrl());
					System.out.println("This is property" + t.getProperty());
					System.out.println("This is Object type"
							+ t.getObjectType());
					System.out.println("it is fragment"+t.getFragment());
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

	private static String buildrepourl(String value, String in, String size,
			String user, String fork, String language, String order) {
		// TODO Auto-generated method stub
		String gitrepourl = "https://api.github.com/search/repositories" + "?"
				+ "q=" + value + "+";
		if (size != null)
			gitrepourl = gitrepourl + "size:" + size + "+";
		if (fork != null)
			gitrepourl = gitrepourl + "fork:" + fork + "+";
		if (in != null)
			gitrepourl = gitrepourl + "in:" + in + "+";
		if (user != null)
			gitrepourl = gitrepourl + "user:" + user + "+";
		if (language != null)
			gitrepourl = gitrepourl + "language:" + language + "+";
		if (order != null)
			gitrepourl = gitrepourl + "&sort=" + "&order=" + order;
		// String
		// gitrepourl="https://api.github.com/search/repositories"+"?"+"q="+value+"+"+"size:"+size+"user:"+user+"stars"+stars+"language:"+language+"&sort="+"&order="+order;
		// System.out.println(gitrepourl);
		return gitrepourl;
	}

	private static String builduserurl(String username, String type, String in,
			String repos, String location, String language, String followers) {
		String gitcodeurl = "https://api.github.com/search/users" + "?" + "q="
				+ username + "+";
		if (type != null)
			gitcodeurl = gitcodeurl + "type:" + type + "+";
		if (in != null)
			gitcodeurl = gitcodeurl + "in:" + in + "+";
		if (repos != null)
			gitcodeurl = gitcodeurl + "repos:" + repos + "+";
		if (location != null)
			gitcodeurl = gitcodeurl + "location:" + location + "+";
		if (language != null)
			gitcodeurl = gitcodeurl + "language:" + language + "+";
		if (followers != null)
			gitcodeurl = gitcodeurl + "followers:" + followers;
		return gitcodeurl;
	}

}
