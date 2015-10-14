package com.ikl.lab.alchemyapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class AlcheConcepts {
	public static String getConcepts(String content) {
		StringBuffer sb = new StringBuffer();

		try {
			// String baseUrl =
			// "http://access.alchemyapi.com/calls/url/URLGetRankedConcepts?apikey=cfb03d0dfeaf1bcc8ce5a5d9311285b7cf38b27a&url=";

			String baseUrl = "http://access.alchemyapi.com/calls/text/TextGetRankedConcepts";
			// ;?outputMode=json&apikey=cfb03d0dfeaf1bcc8ce5a5d9311285b7cf38b27a&text=";
			String newUrl = baseUrl;

			// JSONObject params = new JSONObject();
			// params.put("outputMode", "json");
			// params.put("apikey", "43b4a41c9969bd6b29fa497efd22c8675ca07a16");
			// params.put("text", content);

			HttpClient httpClient = HttpClientBuilder.create().build(); // Use

			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("outputMode", "json"));
			params.add(new BasicNameValuePair("apikey",
					"43b4a41c9969bd6b29fa497efd22c8675ca07a16"));
			params.add(new BasicNameValuePair("text", content));

			try {
				HttpPost request = new HttpPost(baseUrl);
				request.addHeader("content-type", "application/json");
				// request.setHeader("Content-Length", new UrlEncodedFormEntity(
				// params, "UTF-8"));
				// request.addHeader("Accept", "application/json");
				// request.setEntity(new StringEntity(params.toString()));
				request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

				HttpResponse response = httpClient.execute(request);

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent()));
				String tmp = null;

				while ((tmp = reader.readLine()) != null)
					sb.append(tmp);
				// handle response here...
			} catch (Exception ex) {
				// handle exception here
			} finally {
				httpClient.getConnectionManager().shutdown();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void main(String[] args) {

		AlcheConcepts al = new AlcheConcepts();
		List<File> files = al
				.listFile("C:\\IBM\\Workspace_keplar\\infant\\data\\textfiles\\");
		// String url = "http://www.ibm.com/press/us/en/pressrelease/46740.wss";
		int i = 0;
		for (File file : files) {

			if (i > 10) {
				break;
			}
			i++;
			try {
				System.out.println(" file: " + file.getAbsolutePath());
				String content = FileUtils.readFileToString(file);
				// content = URLEncoder.encode(content, "UTF-8");
				System.out.println(" Content : " + content.length());
				if (content.length() > 100) {
					System.out
							.println("  ------------------------------------------------ \n");
					String conceptResponse = getConcepts(content);
					FileUtils.write(
							new File("data/extracted/" + file.getName()),
							conceptResponse);
					// System.out.println();
					System.out
							.println(" ********************************************* \n");
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public List<File> listFile(String pathname) {
		List<File> files = new LinkedList<File>();
		File f = new File(pathname);
		File[] listfiles = f.listFiles();
		for (int i = 0; i < listfiles.length; i++) {
			if (listfiles[i].isDirectory()) {
				File[] internalFile = listfiles[i].listFiles();
				for (int j = 0; j < internalFile.length; j++) {
					System.out.println(internalFile[j]);
					if (internalFile[j].isDirectory()) {
						String name = internalFile[j].getAbsolutePath();
						listFile(name);
					}

				}
			} else {
				// System.out.println(listfiles[i]);
				files.add(listfiles[i]);
				// String filePath = listfiles[i]
				// .getAbsolutePath();

				// }
			}

		}
		return files;
	}
}
