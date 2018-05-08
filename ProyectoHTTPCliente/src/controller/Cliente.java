package controller;

import java.util.concurrent.CountDownLatch;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class Cliente {
	
	public void procesarsolicitudes() throws Exception {
		
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
                final HttpGet[] requests = new HttpGet[] {
                		new HttpGet("http://altamiranonolascoadalididier.dsos.net:1234/nombres.csv"),
                		new HttpGet("http://antoniomoralesalfonsofabian.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://bartoloosoriogabino.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://chavezhernandezsergioivan.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://cuevasortizemmanuelalejandro.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://diazperezmarcos.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://enriqueztoraljuancarlos.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://garciagarciaguillermoricardo.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://gonzalezcruzcarlosfrancisco.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://hernandezalcantarabrenda.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://hernandezgarciadavid.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://jimenezmartinezerikellison.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://lopezhernandezrobertobenjamin.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://martinezgarciajorgealejandro.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://martinezmartinezjosemanuel.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://matiasjacintogibran.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://mendezramirezricardo.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://mirandamirandaedgaryoset.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://perezsantiagoeduardo.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://ramirezruizdaniel.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://reyesoliverajocelyncitlali.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://reyesvasquezdanielalejandro.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://riossilvaguillermo.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://sanchezlopezeduardogeovanni.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://zaratehernandezjairomiguel.dsos.net:1234/nombres.csv"),
        		    	new HttpGet("http://zigalopezantonio.dsos.net:1234/nombres.csv")
                        //new HttpGet("http://localhost:1234/nombres.csv")
                };
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