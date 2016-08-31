package com.jcg.example;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jcg.example.bean.User;

@Service
public class ApplicationService {

	/**
	 * @param args
	 * @return 
	 */
	private static final String GITHUB_WATCHER_URI =
	        "https://api.github.com/users/octocat";

	    @Inject private RestTemplate restTemplate;

	    public User[] getGithubRepoWatchers() {
	        return restTemplate.getForObject(GITHUB_WATCHER_URI, User[].class);
	    }

}
