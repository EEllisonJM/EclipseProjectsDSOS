package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.Numero;

public class CSVHandler {
	List<Numero[]> numeros;
	private Path ubicacionArchivoCSV;
	private char delimitador;

	public CSVHandler(String rutaArchivo, char delimitador) {
		this.ubicacionArchivoCSV = Paths.get(rutaArchivo);
		this.delimitador = delimitador;
	}

	/* Lista de Arreglo de numeros */
	List<Numero[]> leerArchivo() throws IOException {
		if (Files.exists(ubicacionArchivoCSV)) {
			numeros = new ArrayList<>();

			/* Accessing column values by index */
			Reader lectorArchivo = new FileReader(ubicacionArchivoCSV.toString());
			Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
			for (CSVRecord registro : registros) {
				/* Working with Headers */
				Numero arreNumeros[] = new Numero[registro.size()];
				for (int i = 0; i < arreNumeros.length; i++) {
					try {
						arreNumeros[i] = new Numero(Integer.parseInt(registro.get(i)));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				numeros.add(arreNumeros);
			}
			return numeros;
		}
		System.out.println("El archivo que se intenta procesar no existe, verifique");
		return null;

	}
}