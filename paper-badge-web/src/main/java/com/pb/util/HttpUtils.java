package com.pb.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;

import org.orcid.jaxb.model.message.OrcidMessage;

public class HttpUtils {

	public static OrcidMessage runHttpGet(String searchAPI) throws Exception {
		URL url = new URL(searchAPI);
		HttpURLConnection connection =
		    (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");

		JAXBContext jc = JAXBContext.newInstance(OrcidMessage.class);
		InputStream xml = connection.getInputStream();
		OrcidMessage orcProf =
		    (OrcidMessage) jc.createUnmarshaller().unmarshal(xml);

		connection.disconnect();
		
		return orcProf;
	}
}
