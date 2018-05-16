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

import model.Nodo;
import model.Numero;

public class CSVHandler {
	private List<Numero[]> numeros;
	private List<Numero[]> numerosParseados;
	private List<Nodo> nodos;
	private Path ubicacionArchivoCSV;
	private char delimitador;

	public CSVHandler(char delimitador) {
		this.delimitador = delimitador;
	}

	/* Lista de Arreglo de numeros */
	public List<Numero[]> parsearArchivoNumeros(String rutaArchivo) throws IOException {
		ubicacionArchivoCSV = Paths.get(rutaArchivo);
		if (Files.exists(ubicacionArchivoCSV)) {
			numeros = new ArrayList<>();
			/* Accessing column values by index */
			Reader lectorArchivo = new FileReader(ubicacionArchivoCSV.toString());
			Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
			for (CSVRecord registro : registros) {
				/* Working with HeaderparsearArchivoNumerosRestriccioness */
				Numero arreNumeros[] = new Numero[registro.size()];
				for (int i = 0; i < arreNumeros.length; i++) {
					try {
						arreNumeros[i] = new Numero(registro.get(i));
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

	/* Lista de Arreglo de numeros */
	public List<Numero[]> parsearArchivoNumerosRestricciones(//
			String rutaArchivo, // Host name [file]
			List<Object[]> restricciones, // Lista restricciones
			List<Object[]> operaciones) throws IOException {// Lista Operaciones
		ubicacionArchivoCSV = Paths.get(rutaArchivo);
		if (Files.exists(ubicacionArchivoCSV)) {
			numerosParseados = new ArrayList<>();
			/* Accessing column values by index */
			Reader lectorArchivo = new FileReader(ubicacionArchivoCSV.toString());
			Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
			for (CSVRecord registro : registros) {
				/* Working with Headers */
				Numero arreNumeros[] = new Numero[registro.size()];
				for (int i = 0; i < arreNumeros.length; i++) {
					Numero numero = new Numero(registro.get(i));
					try {
						if (numero.aplicarRestricciones(restricciones) == true) {
							numero.aplicarOperaciones(operaciones);
							arreNumeros[i] = numero;
						} else {
							arreNumeros[i] = null;
						}

					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				}
				numerosParseados.add(arreNumeros);
			}
			return numerosParseados;
		}
		System.out.println("El archivo que se intenta procesar no existe, verifique");
		return null;
	}

	/* Lista de Arreglo de numeros */
	public List<Numero[]> leerArchivoNumeros(String rutaArchivo) throws IOException {
		ubicacionArchivoCSV = Paths.get(rutaArchivo);
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

	public List<Nodo> leerArchivoNodos(String rutaArchivo, String nombreArchivo, int puerto) throws IOException {
		ubicacionArchivoCSV = Paths.get(rutaArchivo);
		if (Files.exists(ubicacionArchivoCSV)) {
			nodos = new ArrayList<>();
			/* Accessing column values by index */
			Reader lectorArchivo = new FileReader(ubicacionArchivoCSV.toString());
			Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
			for (CSVRecord registro : registros) {
				/* Working with Headers */
				try {
					nodos.add(new Nodo("http", registro.get(0), puerto, "/" + nombreArchivo));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return nodos;
		}
		System.out.println("El archivo que se intenta procesar no existe, verifique");
		return null;
	}
}