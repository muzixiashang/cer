package com.liyunet.common.http;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {
	private static Logger logger = LogManager.getRootLogger();

	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(5000)
			// .setCircularRedirectsAllowed(true)
			// .setProxy(new HttpHost("124.240.187.78", 81))
			.build();

	private static final List<NameValuePair> DEFAULT_HEADERS = new ArrayList<>();
	static {
		// DEFAULT_HEADERS.add(new BasicNameValuePair("User-Agent", "Mozilla/5.0
		// (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
		// Chrome/50.0.2661.102 Safari/537.36"));
		DEFAULT_HEADERS.add(new BasicNameValuePair("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36"));
		DEFAULT_HEADERS.add(new BasicNameValuePair("Accept-Encoding", "gzip, deflate, sdch, br"));
		DEFAULT_HEADERS.add(new BasicNameValuePair("Accept-Language", "zh-CN,zh;q=0.8"));
		DEFAULT_HEADERS.add(new BasicNameValuePair("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
	}

	public static CloseableHttpClient createHttpClient() {
		return HttpClients.custom().setRetryHandler(new DefaultRetryHandler()).build();
	}

	public static String ajax(String url, List<NameValuePair> headers, String charset) {
		logger.info("ajax请求开始...请求地址为:" + url);
		CloseableHttpClient httpClient = createHttpClient();

		if (StringUtils.isBlank(charset))
			charset = "utf-8";
		
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			httpGet.addHeader("X-Requested-With", "XMLHttpRequest");
			if (headers != null && headers.size() != 0) {
				for (NameValuePair pair : DEFAULT_HEADERS) {
					httpGet.addHeader(pair.getName(), pair.getValue());
				}
				for (NameValuePair pair : headers) {
					httpGet.addHeader(pair.getName(), pair.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = null;
		
		if(httpGet != null){
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				json = EntityUtils.toString(entity, charset);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				HttpClientUtils.closeQuietly(response);
				HttpClientUtils.closeQuietly(httpClient);
			}
		}
		return json;
	}

	public static String get(String url, List<NameValuePair> headers, String charset) {
		CloseableHttpClient httpClient = createHttpClient();
		logger.info("get请求开始...请求地址为:" + url);

		if (StringUtils.isBlank(charset))
			charset = "utf-8";

		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);

		if (headers != null && headers.size() != 0) {
			DEFAULT_HEADERS.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
			headers.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
		}

		// httpGet.removeHeader();

		String html = "";
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			html = EntityUtils.toString(entity, charset);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}
		return html;
	}

	
	public static String get(String url, List<NameValuePair> headers, String charset,HttpHost proxy) {
		Asserts.notNull(proxy, "代理不能为null");
		CloseableHttpClient httpClient = createHttpClient();
		if (StringUtils.isBlank(charset))
			charset = "utf-8";
		RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(5000).setProxy(proxy).build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		if (headers != null && headers.size() != 0) {
			DEFAULT_HEADERS.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
			headers.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
		}
		
		logger.info("代理     get请求开始...请求地址为:" + url);
		String html = "";
		
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			html = EntityUtils.toString(entity, charset);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}
		return html;
	}
	
	
	/**
	 * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。如： X-Forwarded-For：192.168.1.110,
	 * 192.168.1.120, 192.168.1.130, 192.168.1.100 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip.contains(",")) {
			String[] temp = ip.split(",");
			ip = temp[0];
		}

		if (StringUtils.isNotBlank(ip))
			ip = ip.trim();

		return ip;
	}
	
	
	public static InputStream download(String url, List<NameValuePair> headers,HttpHost proxy){
		CloseableHttpClient httpClient = createHttpClient();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(5000).setProxy(proxy).build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		if (headers != null && headers.size() != 0) {
			DEFAULT_HEADERS.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
			headers.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
		}
		
		logger.info("代理     get请求开始下载...请求地址为:" + url);
		
		CloseableHttpResponse response = null;
		InputStream is = null;
		try (
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		){
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				logger.info("请求文件成功");

				HttpEntity entity = response.getEntity();
				
				//复制流  
				InputStream temp = entity.getContent();
				IOUtils.copy(temp, baos);
				return new ByteArrayInputStream(baos.toByteArray()); 
			} else {
				logger.error("图片请求失败,错误码->" + statusLine.getStatusCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}
		return is;
	}

	public static File saveImg(File dest, String url, List<NameValuePair> headers,String date) {

		CloseableHttpClient httpClient = createHttpClient();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);

		DEFAULT_HEADERS.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));
		headers.stream().forEach(pair -> httpGet.addHeader(pair.getName(), pair.getValue()));

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				logger.debug("请求图片成功");

				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();

				FileUtils.copyInputStreamToFile(is, dest);

			} else {
				logger.error("图片请求失败,错误码->" + statusLine.getStatusCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}

		return dest;

	}

}
