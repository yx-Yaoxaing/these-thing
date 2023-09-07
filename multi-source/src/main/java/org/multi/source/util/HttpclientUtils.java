package org.multi.source.util;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpclientUtils {
	  public static String defaultEncoding = "UTF-8";
	    public static String doGet(String url, Map<String, String> param) {
	        // 创建Httpclient对象
	        CloseableHttpClient httpclient = HttpClients.createDefault();

	        String resultString = "";
	        CloseableHttpResponse response = null;
	        try {
	            // 创建uri
	            URIBuilder builder = new URIBuilder(url);
	            if (param != null) {
	                for (String key : param.keySet()) {
	                    builder.addParameter(key, param.get(key));
	                }
	            }
	            URI uri = builder.build();

	            // 创建http GET请求
	            HttpGet httpGet = new HttpGet(uri);

	            // 执行请求
	            response = httpclient.execute(httpGet);
	            // 判断返回状态是否为200
	            if (response.getStatusLine().getStatusCode() == 200) {
	                resultString = EntityUtils.toString(response.getEntity(), Charset.forName(defaultEncoding));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (response != null) {
	                    response.close();
	                }
	                httpclient.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return resultString;
	    }

	    public static String doGet(String url) {
	        return doGet(url, null);
	    }

	    public static String doPost(String url, Map<String, String> header, Map<String, String> param) {
	        // 创建Httpclient对象
	        CloseableHttpClient httpclient = HttpClients.createDefault();

	        String resultString = "";
	        CloseableHttpResponse response = null;
	        // 创建http POST请求
	        HttpPost httpPost = new HttpPost(url);
	        if (header != null) {
	            for (String key : header.keySet()) {
	                httpPost.addHeader(new BasicHeader(key, header.get(key)));
	            }
	        }
	        if (param != null) {
	            List<NameValuePair> pairList = new ArrayList<>();
	            for (String key : param.keySet()) {
	                NameValuePair pair = new BasicNameValuePair(key, param.get(key));
	                pairList.add(pair);
	            }
	            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(defaultEncoding)));
	        }
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(
	                8000
	                ).setConnectTimeout(
					8000
	                ).build();
	                //设置请求和传输超时时间
	                httpPost.setConfig(requestConfig);
	        try {
	            // 执行请求
	            response = httpclient.execute(httpPost);
	            // 判断返回状态是否为200
	            if (response.getStatusLine().getStatusCode() == 200) {
	                resultString = EntityUtils.toString(response.getEntity(), Charset.forName(defaultEncoding));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (response != null) {
	                    response.close();
	                }
	                httpclient.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return resultString;
	    }






	/**
	 * 返回的结果转换成String
	 * @param is
	 * @return
	 */
	public static String is2String(InputStream is){
		String response = "";
		try {
			//连接后，创建一个输入流来读取response
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder stringBuilder = new StringBuilder();
			String line = "";
			//每次读取一行，若非空则添加至 stringBuilder
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			//读取所有的数据后，赋值给 response
			response = stringBuilder.toString().trim();
			bufferedReader.close();
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	    public static String doPost(String url, HashMap<Object, Object> codeparamMap) {
	        return doPost(url, null, null);
	    }
	   
	    public static String doPost(String url, Map<String, String> param) {
	        return doPost(url, null, param);
	    }

	    /**
	     * 创建httpclient
	     *
	     * @return
	     */
	    public static CloseableHttpClient buildHttpClient() {
	        try {
	            RegistryBuilder<ConnectionSocketFactory> builder = RegistryBuilder.create();
	            ConnectionSocketFactory factory = new PlainConnectionSocketFactory();
	            builder.register("http", factory);
	            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	            SSLContext context = SSLContexts.custom().useTLS()
	                    .loadTrustMaterial(trustStore, new TrustStrategy() {
	                        public boolean isTrusted(X509Certificate[] chain,
	                                                 String authType) throws CertificateException {
	                            return true;
	                        }
	                    }).build();
	            LayeredConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
	                    context,
	                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	            builder.register("https", sslFactory);
	            Registry<ConnectionSocketFactory> registry = builder.build();
	            PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(
	                    registry);
	            ConnectionConfig connConfig = ConnectionConfig.custom()
	                    .setCharset(Charset.forName(defaultEncoding)).build();
	            SocketConfig socketConfig = SocketConfig.custom()
	                    .setSoTimeout(10000).build();
	            manager.setDefaultConnectionConfig(connConfig);
	            manager.setDefaultSocketConfig(socketConfig);
	            return HttpClientBuilder.create().setConnectionManager(manager)
	                    .build();
	        } catch (KeyStoreException e) {
	            e.printStackTrace();
	        } catch (KeyManagementException e) {
	            e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }



	/**
	 * 向指定 URL 发送json格式参数的POST方法的请求
	 *
	 * @param url  发送请求的 URL
	 * @param json json格式请求参数
	 * @return 所代表远程资源的响应结果
	 */
	public static String postJson(String url, String json, Map<String, String> headMap) {
		String returnStr;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost post = new HttpPost(url);
			post.setConfig(RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build());
			httpClient = HttpClientBuilder.create().build();
			StringEntity s = new StringEntity(json, Consts.UTF_8);
			s.setContentType("application/json");
			if (headMap != null && headMap.size() > 0) {
				Set<String> keySet = headMap.keySet();
				for (String key : keySet) {
					post.addHeader(key, headMap.get(key));
				}
			}
			post.setEntity(s);
			httpResponse = httpClient.execute(post);
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),
					StandardCharsets.UTF_8.name()));
			StringBuilder stringBuffer = new StringBuilder(100);
			String str;
			while ((str = reader.readLine()) != null) {
				stringBuffer.append(str);
			}
			returnStr = stringBuffer.toString();
			reader.close();
			return returnStr;
		} catch (SocketTimeoutException e) {
			return "";
		} catch (ConnectTimeoutException e) {
			return "";
		} catch (Exception e) {
			return "";
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
			HttpClientUtils.closeQuietly(httpClient);
		}
	}


	public static String postParams(String url, Map<String, String> params) {

		//创建自定义的httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse res = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			res = httpclient.execute(post);
			HttpEntity entity = res.getEntity();
			return EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	// body
	public static String httpPost(String url,Map<String, String> map,Map<String, String> header){

		String body = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse=null;
		HttpPost post = new HttpPost(url);


		if (header != null) {
			for (String key : header.keySet()) {
				post.addHeader(new BasicHeader(key, header.get(key)));
			}
		}

		// 设置参数
		if (map != null) {
			try {
				List<NameValuePair> pairList = new ArrayList<>();
				for (String key : map.keySet()) {
					NameValuePair pair = new BasicNameValuePair(key, map.get(key));
					pairList.add(pair);
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairList, Charset.forName(defaultEncoding));
				urlEncodedFormEntity.setContentType("application/json");
				post.setEntity(urlEncodedFormEntity);

				httpResponse = httpClient.execute(post);
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					body = EntityUtils.toString(entity, "UTF-8");
				}
				try {
					httpResponse.close();
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return body;
	}

	public static String doPostFormData(String url, Map<String, String> header, String body) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// 设置 url
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			// 设置 header

			// 设置请求 body
			connection.setDoOutput(true);
			connection.setDoInput(true);
			out = new PrintWriter(connection.getOutputStream());
			// 保存body
			out.print(body);
			// 发送body
			out.flush();
			// 获取响应body
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			return null;
		}
		return result;
	}



	public static String sendPostByForm(String url, Map<String,String> map,int reSend) {
		//声明返回结果
		String result = "";
		//开始请求API接口时间
		long startTime=System.currentTimeMillis();
		//请求API接口的响应时间
		long endTime= 0L;
		HttpEntity httpEntity = null;
		UrlEncodedFormEntity entity = null;
		HttpResponse httpResponse = null;
		HttpClient httpClient = null;
		try {
			// 创建连接
			 httpClient = HttpClients.createDefault();
			// 设置请求头和报文
			HttpPost httpPost =new HttpPost(url);
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			entity = new UrlEncodedFormEntity(list,Charset.forName(defaultEncoding));
			httpPost.setEntity(entity);
			//执行发送，获取相应结果
			httpResponse = httpClient.execute(httpPost);
			httpEntity= httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity);
		} catch (Exception e) {
			if (reSend > 0) {
				result = sendPostByForm(url, map, reSend - 1);
				if (result != null && !"".equals(result)) {
					return result;
				}
			}
		}finally {
			try {
				EntityUtils.consume(httpEntity);
			} catch (IOException e) {
			}
		}
		//请求接口的响应时间
		endTime=System.currentTimeMillis();
		return result;

	}


	    
}
