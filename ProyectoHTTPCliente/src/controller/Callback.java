package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

import model.Nodo;

public class Callback implements FutureCallback<HttpResponse> {
	private List<Nodo> completados;
	private List<Nodo> fallidos;
	private List<Nodo> cancelados;
	private CountDownLatch latch;
	private URI uri;
	private long inicioSol;

	public Callback(CountDownLatch latch, URI uri, List<Nodo> completados, List<Nodo> fallidos, List<Nodo> cancelados) {
		this.latch = latch;
		this.uri = uri;
		this.completados = completados;
		this.fallidos = fallidos;
		this.cancelados = cancelados;
		this.inicioSol = System.currentTimeMillis();
	}

	public void completed(final HttpResponse response) {
		latch.countDown();
		String contenido = "";
		HttpEntity entity = response.getEntity();
		try {
			contenido = EntityUtils.toString(entity);
			crearArchivo(contenido);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		completados.add(new Nodo(uri));
		long tiempoSol = System.currentTimeMillis() - inicioSol;
		System.out.println(
				"Terminando peticion... (" + uri + ")\nDuracion de la solicitud: " + tiempoSol + " milisegundos\n");
	}

	@Override
	public void failed(final Exception ex) {
		latch.countDown();
		System.out.println("Solicitud a " + uri + " fallida, " + ex);
		fallidos.add(new Nodo(uri));
		long tiempoSol = System.currentTimeMillis() - inicioSol;
		System.out.println("Terminando peticion...\nDuracion de la solicitud:" + tiempoSol + " milisegundos\n");
	}

	@Override
	public void cancelled() {
		latch.countDown();
		System.out.println("Solicitud a " + uri + " cancelada");
		cancelados.add(new Nodo(uri));
		long tiempoSol = System.currentTimeMillis() - inicioSol;
		System.out.println("Terminando peticion...\nDuracion de la solicitud: " + tiempoSol + " milisegundos\n");
	}

	/* Guardar archivo Host */
	private void crearArchivo(String contenido) throws IOException {
		File temp = new File("archivos/" + uri.getHost() + ".csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		bw.write(contenido);
		bw.close();
		System.out.println("El archivo fue ubicado en " + temp.getPath().toString());
	}

}