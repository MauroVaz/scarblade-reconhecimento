package com.unip.tcc.scarblade.factory;
//package com.meusmix.portal.factory;
//
//import java.io.Serializable;
//import java.net.ProxySelector;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpClient.Version;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandlers;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class HttpClientFactory implements Serializable{
//	
//	private static final Logger LOGGER = LogManager.getLogger(HttpClientFactory.class);
//	private static final long serialVersionUID = 1L;
//	
//	private static final String DOUBLE_SLASH = "://";
//	private static final char DOUBLE_DOT = ':';
//
//	public static void checkServerWithoutProxy(final String protocol,final String url, final Integer port, final String context) {
//		try {			
//			
//			StringBuilder requestStringBuilder = new StringBuilder();			
//			requestStringBuilder.append(protocol).append(DOUBLE_SLASH).append(url).append(DOUBLE_DOT).append(port).append(context);
//
//			HttpClient httpClient = HttpClient.newBuilder().proxy(ProxySelector.getDefault()).version(Version.HTTP_2).build();
//			
//			HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(requestStringBuilder.toString())).GET().build();
//			
//			HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());
//			
//			LOGGER.info("Response status code: {}", response.statusCode());
//			LOGGER.info("Response headers: {}", response.headers());
//			LOGGER.info("Response body: {}", response.body());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//	}
//	
//}
