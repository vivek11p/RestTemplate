package com.jcg.example;

import java.util.Scanner;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
	    System.out.println("Search the critera that you want to serach( repositories,code,issue,user)");
		String criteria=sc.next();
		String value=null;
		System.out.println(criteria);
		/*if(criteria.equals("repositories"))
		System.out.println(buildrepourl("tetris", null, null, null,">5", "assembly", "desc"));
		if(criteria.equals("code"))
		System.out.println(builduserurl("tom","user","login",">42",null,"js",">1000"));*/
		System.out.println(buildcodeurl("addclass", "file", "js", ">5", "test", "js", "jquery/jquery"));
		
		/*((string == null) ? "" : string));*/
	}

	private static String buildrepourl(String value,String in,String size,String user,String fork,String language,String order) {
		// TODO Auto-generated method stub
		String gitrepourl="https://api.github.com/search/repositories"+"?"+"q="+value+"+";
		if(size!=null)
			 gitrepourl=gitrepourl+"size:"+size+"+";
		if(fork!=null)
			gitrepourl=gitrepourl+"fork:"+fork+"+";
		if(in!=null)
			gitrepourl=gitrepourl+"in:"+in+"+";
		if(user!=null)
			gitrepourl=gitrepourl+"user:"+user+"+";
		if(language!=null)
			gitrepourl=gitrepourl+"language:"+language+"+";
		if(order!=null)
			gitrepourl=gitrepourl+"&sort="+"&order="+order;
		//String gitrepourl="https://api.github.com/search/repositories"+"?"+"q="+value+"+"+"size:"+size+"user:"+user+"stars"+stars+"language:"+language+"&sort="+"&order="+order;
		//System.out.println(gitrepourl);
		return gitrepourl;
	}
	private static String builduserurl(String username,String type,String in,String repos,String location,String language,String followers){
		String gituserurl="https://api.github.com/search/users"+"?"+"q="+username+"+";
		if(type!=null)
			gituserurl=gituserurl+"type:"+type+"+";
		if(in!=null)
			gituserurl=gituserurl+"in:"+in+"+";
		if(repos!=null)
			gituserurl=gituserurl+"repos:"+repos+"+";
		if(location!=null)
			gituserurl=gituserurl+"location:"+location+"+";
		if(language!=null)
			gituserurl=gituserurl+"language:"+language+"+";
		if(followers!=null)
			gituserurl=gituserurl+"followers:"+followers;
		return gituserurl;
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

}
