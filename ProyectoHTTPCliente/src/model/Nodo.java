package model;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;

public class Nodo {
	private URI uri;

	/* [Ejemplo] Constructor Nodo("http","localhost",1234,"datos.csv" */
	public Nodo(String schema, String host, int port, String path) throws URISyntaxException {
		uri = new URIBuilder()//
				.setScheme(schema)// "http"
				.setHost(host)// "www.google.com"
				.setPort(port).setPath(path)// "/search"
				.build();
	}

	public Nodo(URI uri) {
		this.uri = uri;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
}