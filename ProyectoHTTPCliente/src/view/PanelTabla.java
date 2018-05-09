package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CSVHandler;
import model.Numero;

public class PanelTabla extends JPanel {
	private JTable tabla;
	int maxColumnas;
	// List<Numero[]> leerArchivo()

	public PanelTabla(){
		// TODO Auto-generated constructor stub
		//CSVHandler csvHandler=new CSVHandler(',');
		//List<Numero[]> numerosArchivo =csvHandler.leerArchivoNumeros("");
		// CSV handler, get values
		List<Numero[]> numeros = new ArrayList<>();
		Numero[] nums0 = new Numero[] { new Numero(37), new Numero(2), new Numero(2) };
		Numero[] nums1 = new Numero[] { new Numero(42), new Numero(53), new Numero(8), new Numero(2) };
		Numero[] nums2 = new Numero[] { new Numero(52), new Numero(83), new Numero(1), new Numero(9) };
		numeros.add(nums0);
		numeros.add(nums1);
		numeros.add(nums2);

		Object[][] k = parseListArrNumeroToObject(numeros);
		String[] s = getEncabezado(maxColumnas);

		tabla = new JTable(k, s);
		tabla.setTableHeader(null);
		this.add(tabla);
	}

	String[] getEncabezado(int max) {
		String[] encabezados = new String[max];
		for (int i = 0; i < max; i++) {
			encabezados[i] = "[" + (i + 1) + "]";
		}
		return encabezados;
	}

	Object[][] parseListArrNumeroToObject(List<Numero[]> numeros) {
		/* [OK] Obtener el maximo de columnas */
		maxColumnas = numeros.get(0).length;
		for (int i = 1; i < numeros.size(); i++) {
			if (numeros.get(i).length > maxColumnas) {
				maxColumnas = numeros.get(i).length;
			}
		}
		/**/
		Object[][] rows = new Object[numeros.size()][maxColumnas];
		for (int i = 0; i < numeros.size(); i++) {
			for (int j = 0; j < numeros.get(i).length; j++) {
				rows[i][j] = numeros.get(i)[j].getValor();
			}
		}
		return rows;
	}
}
