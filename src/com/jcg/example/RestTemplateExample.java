  package com.jcg.example;

  import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.jcg.example.bean.Item;
import com.jcg.example.bean.UserBean;
  
/*@JsonIgnoreProperties(ignoreUnknown = true)*/
  public class RestTemplateExample
  {
  		public static void main(String[] args)
  		{
  			 RestTemplate restTemplate = new RestTemplate();
  			 String url = "https://api.github.com/search/repositories?q=discover+language:assembly&sort=stars&order=desc";
  			 List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
  			 MappingJacksonHttpMessageConverter map = new MappingJacksonHttpMessageConverter();
/*  			ObjectMapper mapper = new ObjectMapper();
  			SerializationConfig serializationConfig = mapper.getSerializationConfig();
  			mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);*/
  			
  			 messageConverters.add(map);
  			 restTemplate.setMessageConverters(messageConverters);
  			/*ObjectMapper objectMapper = new ObjectMapper();
  			objectMapper.configure(
  			    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);*/
System.out.println("Before restTemplate.getForObject(url, UserBean.class");
  			 UserBean bean = restTemplate.getForObject(url, UserBean.class);
  			System.out.println(bean.getItems());
  			

  			 System.out.println("The object received from REST call : "+bean);
  		}
  }
