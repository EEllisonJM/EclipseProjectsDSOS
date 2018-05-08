package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

public class Callback implements FutureCallback<HttpResponse> {
	
	private CountDownLatch latch;
	private URI uri;
	//private long inicioSol;
	
	public Callback(CountDownLatch latch, URI uri) {
		this.latch = latch;
		this.uri = uri;
		//this.inicioSol = inicioSol;
	}

	public void completed(final HttpResponse response) {
        latch.countDown();
        //System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
        String contenido="";
    	HttpEntity entity = response.getEntity();
        try {
			contenido = EntityUtils.toString(entity);
			crearArchivo(contenido);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //long tiempoSol = System.currentTimeMillis() - inicioSol;
        //System.out.println("Terminando petición... ("+uri+")\nDuración de la solicitud: "+tiempoSol+" milisegundos\n");
    }

    @Override
    public void failed(final Exception ex) {
        latch.countDown();
        System.out.println("Solicitud a "+uri + " fallida, " + ex);
        
        //long tiempoSol = System.currentTimeMillis() - inicioSol;
        //System.out.println("Terminando petición...\nDuración de la solicitud: "+tiempoSol+" milisegundos\n");
    }

    @Override
    public void cancelled() {
        latch.countDown();
        System.out.println("Solicitud a "+uri+ " cancelada");
        
        //long tiempoSol = System.currentTimeMillis() - inicioSol;
        //System.out.println("Terminando petición...\nDuración de la solicitud: "+tiempoSol+" milisegundos\n");
    }
    
    private void crearArchivo(String contenido) throws IOException{
    	File temp = new File("archivos/"+uri.getHost()+".csv");
    	BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        bw.write(contenido);
        bw.close();
        System.out.println("El archivo fue ubicado en "+temp.getPath().toString());
        //temp.delete();
    }

}