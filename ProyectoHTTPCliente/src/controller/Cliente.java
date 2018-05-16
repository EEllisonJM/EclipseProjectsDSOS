package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import model.Nodo;

public class Cliente {
	private List<Nodo> completados;
	private List<Nodo> fallidos;
	private List<Nodo> cancelados;

	public void solicitud(List<Nodo> nodos) throws Exception {
		completados = new ArrayList<Nodo>();
		fallidos = new ArrayList<Nodo>();
		cancelados = new ArrayList<Nodo>();

		RequestConfig requestConfig = RequestConfig.custom().//
				setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).build();
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
		try {
			httpclient.start();
			final HttpGet[] requests = new HttpGet[nodos.size()];
			for (int i = 0; i < nodos.size(); i++) {
				requests[i] = new HttpGet(nodos.get(i).getUri());
			}
			final CountDownLatch latch = new CountDownLatch(requests.length);
			for (final HttpGet request : requests) {
				Callback futureCallback = new Callback(latch, request.getURI(), completados, fallidos, cancelados);
				httpclient.execute(request, futureCallback);
			}
			latch.await();
		} finally {
			httpclient.close();
		}
	}

	public List<Nodo> getCompletados() {
		return completados;
	}

	public List<Nodo> getFallidos() {
		return fallidos;
	}

	public List<Nodo> getCancelados() {
		return cancelados;
	}

}