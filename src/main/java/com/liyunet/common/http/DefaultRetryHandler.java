package com.liyunet.common.http;

import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class DefaultRetryHandler implements HttpRequestRetryHandler {

	private static Logger logger = LogManager.getRootLogger();

	// 失败重试3次
	@Override
	public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
		//等待1秒再重试
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			
		}
		
		HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        RequestLine requestLine = request.getRequestLine();
        
        String uri = requestLine.getUri();
        
        logger.info("第" + executionCount + "次重试,请求uri为->" + uri);
        
		if (executionCount > 10) {
			logger.error("已经重试10次失败,失败uri为->" + uri);
			return false;
		}

		if (exception instanceof InterruptedIOException) {
			// 超时
			return true;
		}
		if (exception instanceof UnknownHostException) {
			//目标服务器不可达
			logger.error("目标服务器不可达");
			return true;
		}
		if (exception instanceof ConnectTimeoutException) {
			logger.error("请求URL超时,重试,请求uri为->" + uri);
			return true;
		}
		
		if (exception instanceof SocketException) {
			logger.error("connection reset,重试,请求uri为->" + uri);
			return true;
		}
		
		if (exception instanceof SocketTimeoutException) {
			logger.error("服务器响应超时,重试,请求uri为->" + uri);
			return true;
		}
		
		if (exception instanceof SSLException) {
			//ssl握手异常
			logger.error("ssl握手异常");
			return true;
		}
		
		return false;
	}
}
