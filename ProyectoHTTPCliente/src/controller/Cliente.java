package controller;

import java.util.concurrent.CountDownLatch;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class Cliente {
	
	public void procesarsolicitudes(String[] nodos) throws Exception {
		
		//long inicioApp = System.currentTimeMillis();
    	
    	RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)//
                .build();
            CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
            try {
                httpclient.start();
                final HttpGet[] requests = new HttpGet[nodos.length];
                for (int i=0;i<nodos.length;i++) {
                	requests[i] = new HttpGet(nodos[i]);
                }
                final CountDownLatch latch = new CountDownLatch(requests.length);
                for (final HttpGet request: requests) {
                	System.out.println("Inicainado petición...");
                	long inicioSol = System.currentTimeMillis();
                	Callback futureCallback = new Callback(latch, request.getURI().toString(), inicioSol);
                    httpclient.execute(request, futureCallback);
                    
                }
                latch.await();
                System.out.println("Shutting down");
            } finally {
                httpclient.close();
            }
            System.out.println("Done");
    	
    	//long tiempoApp = System.currentTimeMillis() - inicioApp;
        //System.out.println("\nDuración de la aplicación: "+tiempoApp+" milisegundos");
		
	}

}