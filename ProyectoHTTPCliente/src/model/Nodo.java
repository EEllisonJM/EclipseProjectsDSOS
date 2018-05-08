package model;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;

public class Nodo {
	private URI uri;

	public Nodo(String schema, String host, int port, String path) throws URISyntaxException {
		uri = new URIBuilder()//
				.setScheme(schema)// "http"
				.setHost(host)// "www.google.com"
				.setPort(port).setPath(path)// "/search"
				.build();
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
}