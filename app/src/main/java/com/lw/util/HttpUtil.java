package com.lw.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpUtil {

	
	public static String readString(String link) throws IOException{
//		HttpGet request = new HttpGet(url);
//		HttpClient client = new DefaultHttpClient();
//		HttpResponse resp = client.execute(request);
//		InputStream in = resp.getEntity().getContent();
//		int len = 0;
//		byte buffer[] = new byte[8*1024];
//		ByteArrayOutputStream bo = new ByteArrayOutputStream();
//		while((len=in.read(buffer)) != -1) {
//			bo.write(buffer, 0, len);
//		}
//		String result = bo.toString("utf-8");
//		in.close();

        URL url = new URL(link);
		StringBuilder sb = new StringBuilder();
		try {
			InputStream in = url.openConnection().getInputStream();
			int len = 0;
			byte buffer[] = new byte[8*1024];
			while((len = in.read(buffer)) != -1) {
				sb.append(new String(buffer,0,len));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
