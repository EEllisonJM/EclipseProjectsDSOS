package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Nodo;

public class PanelCompletados extends JPanel implements ChangeListener, ActionListener {
	private List<Nodo> completados;
	private PanelRestricciones restricciones;
	private PanelOperaciones operaciones;

	private JTextPane[] jTextos;
	private JButton[] jBotones;
	JTabbedPane tablaPaneles;

	public PanelCompletados(//
			List<Nodo> completados, // Solicitudes completadas
			JTabbedPane tablaPaneles, // Paneles de Paneles[Tabulado]
			PanelRestricciones restricciones, // Lista de Restriicones
			PanelOperaciones operaciones) {// Lista de operaciones

		this.completados = completados;
		this.restricciones = restricciones;
		this.operaciones = operaciones;

		this.setBorder(BorderFactory.createTitledBorder("Solicitudes completadas"));
		this.setToolTipText("Se muestran las solucitudes que fueron completadas con exito");
		this.setLayout(new GridLayout(0, 2));

		jTextos = new JTextPane[completados.size()];
		jBotones = new JButton[completados.size()];
		inicializarJTextPane(jTextos);
		inicializarJButton(jBotones);
		agrearComponentes(jTextos, jBotones);

		this.tablaPaneles = tablaPaneles;
	}

	public void inicializarJTextPane(JTextPane jTextPane[]) {
		for (int i = 0; i < jTextPane.length; i++) {
			jTextPane[i] = new JTextPane();
			jTextPane[i].setText(completados.get(i).getUri().toString());
		}
	}

	public void inicializarJButton(JButton jButton[]) {
		for (int i = 0; i < jButton.length; i++) {
			jButton[i] = new JButton("Mostrar Datos");
			jButton[i].addActionListener(this);
		}
	}

	public void agrearComponentes(JTextPane[] jTextPane, JButton[] jButton) {
		for (int i = 0; i < completados.size(); i++) {
			this.add(jTextPane[i]);
			this.add(jButton[i]);
		}
	}

	/* JButton [MostrarDatos] */
	@Override
	public void actionPerformed(ActionEvent e) { 
		for (int i = 0; i < jBotones.length; i++) {
			if (e.getSource() == jBotones[i]) {
				try {
					JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));
					/* Panel con tabla oroginal */
					JPanel panelTablaOriginal = new PanelTabla(completados.get(i).getUri().getHost());
					panelTablaOriginal.setBorder(BorderFactory.createTitledBorder("Datos originales"));
					panelTablaOriginal.setToolTipText("Se muestran los datos que fueron completadas con exito");

					/* Panel tabla datos procesados */
					JPanel panelTablaParseada = new PanelTabla(//
							completados.get(i).getUri().getHost(), // Nombre archivo a cargar [Host]
							restricciones.getListaRestricciones(), // Lista restricciones
							operaciones.getListaOperaciones());// Lista operaciones

					panelTablaParseada.setBorder(BorderFactory.createTitledBorder("Datos procesados"));
					panelTablaParseada
							.setToolTipText("Se muestran los datos procesaos que fueron completadas con exito");

					panelPrincipal.add(panelTablaOriginal);
					panelPrincipal.add(panelTablaParseada);
					tablaPaneles.addTab("Datos", panelPrincipal);
				} catch (IOException e1) {
					System.out.println("Ups!, ");
					e1.printStackTrace();
				}
				System.out.println("Paso por aqui");
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}
}