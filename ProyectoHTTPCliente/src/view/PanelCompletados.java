package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
	private JTabbedPane tablaPaneles;

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
					JPanel panelPrincipal = new JPanel(new BorderLayout());
					JPanel panelSecundario = new JPanel(new GridLayout(1, 2));
					/* Panel con tabla original */
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
							.setToolTipText("Se muestran los datos procesados que fueron completadas con exito");

					panelSecundario.add(panelTablaOriginal);
					panelSecundario.add(panelTablaParseada);

					JPanel panel = new JPanel(new FlowLayout());
					panel.add(new JLabel("Host :[" + jTextos[i].getText().toString() + "]"));

					for (int j = 0; j < restricciones.getListaRestricciones().size(); j++) {
						if (restricciones.getListaRestricciones().get(j).length == 1) {
							panel.add(
									new JLabel("[" + restricciones.getListaRestricciones().get(j)[0].toString() + "]"));
						}
						if (restricciones.getListaRestricciones().get(j).length == 2) {
							panel.add(new JLabel("[" + restricciones.getListaRestricciones().get(j)[0].toString()
									+ " : " + restricciones.getListaRestricciones().get(j)[1].toString() + "]"));
						}
						if (restricciones.getListaRestricciones().get(j).length == 3) {
							panel.add(new JLabel("[" + restricciones.getListaRestricciones().get(j)[0].toString()
									+ " : (" + restricciones.getListaRestricciones().get(j)[1].toString() + " - "
									+ restricciones.getListaRestricciones().get(j)[2].toString() + ")]"));
						}

					}
					panelPrincipal.add(panel, BorderLayout.NORTH);
					panelPrincipal.add(panelSecundario, BorderLayout.CENTER);
					tablaPaneles.addTab("Datos", panelPrincipal);
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}

			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}
}