package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controller.CSVHandler;
import model.Numero;

public class PanelTabla extends JPanel {
	private JTable tabla;
	private int maxColumnas;
	private String[] encabezados;

	/* Panel tabla procesado */ 
	public PanelTabla(String host, List<Object[]> restricciones, List<Object[]> operaciones) throws IOException {
		/* Obtener Lista de Numeros */
		CSVHandler csvHandler = new CSVHandler(',');
		List<Numero[]> numeros = csvHandler.parsearArchivoNumerosRestricciones("archivos/" + host + ".csv",
				restricciones, operaciones);
		/* Crear Tabla con valores y encabezado */
		tabla = new JTable(parsearListaNumeros(numeros), getEncabezado());
		JScrollPane jScrollBar = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(jScrollBar);

		this.validate();
		this.repaint();
	}

	/* Panel tabla original */
	public PanelTabla(String host) throws IOException {
		/* Obtener Lista de Numeros */
		CSVHandler csvHandler = new CSVHandler(',');
		List<Numero[]> numeros = csvHandler.parsearArchivoNumeros("archivos/" + host + ".csv");
		/* Crear Tabla con valores y encabezado */
		tabla = new JTable(parsearListaNumeros(numeros), getEncabezado());
		JScrollPane jScrollBar = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(jScrollBar);

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
		Object[][] datos;
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
				if (numeros.get(i)[j] != null) {
					datos[i][j] = numeros.get(i)[j].getValor();
					System.out.println(numeros.get(i)[j].getValor());
				} else {
					datos[i][j] = 0;
				}
			}
		}
		return datos;
	}
}