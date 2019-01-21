package com.liyunet.web.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * 添加对 DELETE PUT PATCH 方法传参的的支持
 * 
 * @author wuchengf	
 * @date 2016年5月30日
 * @since 1.8
 */
public class HttpDeleteFormContentFilter extends OncePerRequestFilter {
	private final FormHttpMessageConverter formConverter = new AllEncompassingFormHttpMessageConverter();
	
	@Override
	protected void doFilterInternal(final HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		if (("PUT".equals(request.getMethod()) || "PATCH".equals(request.getMethod()) || "DELETE".equals(request.getMethod())) && isFormContentType(request)) {
			HttpInputMessage inputMessage = new ServletServerHttpRequest(request) {
				@Override
				public InputStream getBody() throws IOException {
					return request.getInputStream();
				}
			};
			MultiValueMap<String, String> formParameters = formConverter.read(null, inputMessage);
			HttpServletRequest wrapper = new HttpPutFormContentRequestWrapper(request, formParameters);
			filterChain.doFilter(wrapper, response);
		}
		else {
			filterChain.doFilter(request, response);
		}
	}

	private boolean isFormContentType(HttpServletRequest request) {
		String contentType = request.getContentType();
		if (contentType != null) {
			try {
				MediaType mediaType = MediaType.parseMediaType(contentType);
				return (MediaType.APPLICATION_FORM_URLENCODED.includes(mediaType));
			}
			catch (IllegalArgumentException ex) {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	private static class HttpPutFormContentRequestWrapper extends HttpServletRequestWrapper {

		private MultiValueMap<String, String> formParameters;

		public HttpPutFormContentRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> parameters) {
			super(request);
			this.formParameters = (parameters != null) ? parameters : new LinkedMultiValueMap<String, String>();
		}

		@Override
		public String getParameter(String name) {
			String queryStringValue = super.getParameter(name);
			String formValue = this.formParameters.getFirst(name);
			return (queryStringValue != null) ?  queryStringValue : formValue;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> result = new LinkedHashMap<String, String[]>();
			Enumeration<String> names = this.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				result.put(name, this.getParameterValues(name));
			}
			return result;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			Set<String> names = new LinkedHashSet<String>();
			names.addAll(Collections.list(super.getParameterNames()));
			names.addAll(this.formParameters.keySet());
			return Collections.enumeration(names);
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] queryStringValues = super.getParameterValues(name);
			List<String> formValues = this.formParameters.get(name);
			if (formValues == null) {
				return queryStringValues;
			}
			else if (queryStringValues == null) {
				return formValues.toArray(new String[formValues.size()]);
			}
			else {
				List<String> result = new ArrayList<String>();
				result.addAll(Arrays.asList(queryStringValues));
				result.addAll(formValues);
				return result.toArray(new String[result.size()]);
			}
		}
	}
}
