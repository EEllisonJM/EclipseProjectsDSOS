package view;

import java.io.IOException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import controller.CSVHandler;
import model.Numero;

public class PanelTabla extends JPanel {
	private JTable tabla;
	private int maxColumnas;
	private Object[][] datos;
	private String[] encabezados;

	public PanelTabla(String host) throws IOException {
		/* Obtener Lista de Numeros */
		CSVHandler csvHandler = new CSVHandler(',');
		List<Numero[]> numeros = csvHandler.leerArchivoNumeros("archivos/" + host + ".csv");
		/* Crear Tabla con valores y encabezado */
		tabla = new JTable(parsearListaNumeros(numeros), getEncabezado());
		this.add(tabla);
		
		this.validate();
		this.repaint();
	}

	String[] getEncabezado() {
		encabezados = new String[maxColumnas];
		for (int i = 0; i < maxColumnas; i++) {
			encabezados[i] = "[" + (i + 1) + "]";
		}
		return encabezados;
	}

	Object[][] parsearListaNumeros(List<Numero[]> numeros) {
		/* Obtener el maximo de columnas */
		maxColumnas = numeros.get(0).length;
		for (int i = 1; i < numeros.size(); i++) {
			if (numeros.get(i).length > maxColumnas) {
				maxColumnas = numeros.get(i).length;
			}
		}
		/* Cargar datos de la lista */
		datos = new Object[numeros.size()][maxColumnas];
		for (int i = 0; i < numeros.size(); i++) {
			for (int j = 0; j < numeros.get(i).length; j++) {
				datos[i][j] = numeros.get(i)[j].getValor();
			}
		}
		return datos;
	}
}