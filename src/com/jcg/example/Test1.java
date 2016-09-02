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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.jcg.example.bean.Repository;

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
		//https://api.github.com/search/code?q=addClass+extension:js+language:js+repo:jquery/jquery
		String link=buildcodeurl("addclass", "file", "js", ">5", "test", "js", "jquery/jquery");
		URL crunchifyUrl = new URL("https://api.github.com/search/code?q=addClass+extension:js+language:js+repo:jquery/jquery");
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
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter1);
		
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
					list1.add(t.getFragment());
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
	private static String buildcodeurl(String codevalue,String in,String language,String size,String path,String extension,String repo){
		String gitcodeurl="https://api.github.com/search/code?q="+codevalue+"+";
		if(in!=null)
			gitcodeurl=gitcodeurl+"in:"+in+"+";
		if(language!=null)
			gitcodeurl=gitcodeurl+"language:"+language+"+";
		if(size!=null)
			gitcodeurl=gitcodeurl+"size:"+size+"+";
		if(path!=null)
			gitcodeurl=gitcodeurl+"path:"+path+"+";
		if(extension!=null)
			gitcodeurl=gitcodeurl+"extension:"+extension+"+";
		if(repo!=null)
			gitcodeurl=gitcodeurl+"repo:"+repo;
		return gitcodeurl;
		
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
		String gituserurl = "https://api.github.com/search/users" + "?" + "q="
				+ username + "+";
		if (type != null)
			gituserurl = gituserurl + "type:" + type + "+";
		if (in != null)
			gituserurl = gituserurl + "in:" + in + "+";
		if (repos != null)
			gituserurl = gituserurl + "repos:" + repos + "+";
		if (location != null)
			gituserurl = gituserurl + "location:" + location + "+";
		if (language != null)
			gituserurl = gituserurl + "language:" + language + "+";
		if (followers != null)
			gituserurl = gituserurl + "followers:" + followers;
		return gituserurl;
	}
	

}
