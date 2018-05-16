package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import controller.CSVHandler;
import controller.Cliente;

public class PanelResultados extends JPanel {
	private PanelCompletados panelCompletados;
	private PanelFallidos panelFallidos;
	private PanelOperaciones panelOperaciones;
	private PanelRestricciones panelRestricciones;

	private CSVHandler csvHandler;
	Cliente cliente;

	public PanelResultados(JTabbedPane tablaPaneles, String ruta, String nombreArchivo, int puerto) {
		this.setLayout(new BorderLayout());
		inicializarComponenetes(ruta);

		try {
			cliente.solicitud(csvHandler.leerArchivoNodos(ruta, nombreArchivo, puerto));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		panelCompletados = new PanelCompletados(//
				cliente.getCompletados(), // Lista solicitud completada
				tablaPaneles, // Panel de Paneles[Tabulado]
				panelRestricciones, // Lista Restricciones
				panelOperaciones);// Lista Operaciones
		panelFallidos = new PanelFallidos(cliente.getFallidos());

		agregarCompoenetes();

		tablaPaneles.addTab("Resultados", this);
	}

	public void inicializarComponenetes(String ruta) {
		panelOperaciones = new PanelOperaciones();
		panelRestricciones = new PanelRestricciones();

		csvHandler = new CSVHandler(',');
		cliente = new Cliente();

	}

	public void agregarCompoenetes() {
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new BorderLayout());
		panelLeft.add(panelRestricciones, BorderLayout.NORTH);
		panelLeft.add(panelOperaciones, BorderLayout.CENTER);

		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelRestricciones, panelOperaciones);
		panelLeft.add(splitPane1, BorderLayout.CENTER);
		this.add(panelLeft, BorderLayout.WEST);

		JPanel panelRight = new JPanel();
		panelRight.setLayout(new BorderLayout());
		panelRight.add(panelCompletados, BorderLayout.NORTH);
		panelRight.add(panelFallidos, BorderLayout.CENTER);
		this.add(panelRight, BorderLayout.CENTER);
	}
}