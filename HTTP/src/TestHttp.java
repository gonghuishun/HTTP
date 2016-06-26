import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class TestHttp {
	
	public void readByGet(){
		try {
			URL url = new URL("http://fanyi.youdao.com/openapi.do?keyfrom=w-share&key=363532535&type=data&doctype=xml&version=1.1&q=welcome");
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is); 
			BufferedReader br = new BufferedReader(isr);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("test.xml"));
			BufferedWriter bw = new BufferedWriter(osw); 
			String line;
			while((line = br.readLine()) != null){
				bw.write(line);
			}
			
			br.close();
			isr.close();
			is.close();
			bw.close();
			osw.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readByPost(){
		try {
			URL url = new URL("http://fanyi.youdao.com/openapi.do");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.addRequestProperty("encoding", "UTF-8");
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			
			OutputStream os = con.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("keyfrom=w-share&key=363532535&type=data&doctype=xml&version=1.1&q=welcome");
			bw.flush();
			
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder str = new StringBuilder();
			String line;
			while((line = br.readLine()) !=null){
				str.append(line);
			}
			
			System.out.println(str.toString());
			
			bw.close();
			osw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		TestHttp t = new TestHttp();
			//t.readByGet();
		t.readByPost();
	}
}
