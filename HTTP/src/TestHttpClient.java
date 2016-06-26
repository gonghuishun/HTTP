import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class TestHttpClient {
	
	public void get(){
		HttpClient client = HttpClients.createDefault();
		//HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://fanyi.youdao.com/openapi.do?keyfrom=w-share&key=363532535&type=data&doctype=xml&version=1.1&q=welcome");
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
//			String result = EntityUtils.toString(entity, "UTF-8");
//			System.out.println(result);
			
			InputStream is = entity.getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream( new FileOutputStream(new File("textnew.xml")));
			
			byte b[] = new byte[1000];
			int length;
			while((length = bis.read(b)) != -1){
				bos.write(b, 0, length);
			}
			
			bis.close();
			is.close();
			bos.close();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void post(){
		HttpClient client = HttpClients.createDefault();
		//HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://fanyi.youdao.com/openapi.do");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("keyfrom", "w-share"));
		params.add(new BasicNameValuePair("key", "363532535"));
		params.add(new BasicNameValuePair("type", "data"));
		params.add(new BasicNameValuePair("doctype", "xml"));
		params.add(new BasicNameValuePair("version", "1.1"));
		params.add(new BasicNameValuePair("q", "welcome"));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = client.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String [] args){
		TestHttpClient t = new TestHttpClient();
		//t.get();
		t.post();
	}
}
