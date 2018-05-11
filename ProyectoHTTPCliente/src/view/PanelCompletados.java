package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
	private JTextPane[] jTextos;
	private JButton[] jBotones;
	JTabbedPane tablaPaneles;

	public PanelCompletados(List<Nodo> completados, JTabbedPane tablaPaneles) {
		this.completados = completados;
		this.setBorder(BorderFactory.createTitledBorder("Solicitudes completadas"));
		this.setToolTipText("Se muestran las solucitudes que fueron completadas con �xito");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		for (int i = 0; i < jBotones.length; i++) {
			if (e.getSource() == jBotones[i]) {
				try {
					List<Object[]> restriccionesPlus = new ArrayList<>();
					restriccionesPlus.add(new Object[] { "Positivo" });
					restriccionesPlus.add(new Object[] { "MayorQue", 7 });

					JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));
					
					JPanel panelTablaOriginal = new PanelTabla(completados.get(i).getUri().getHost());
					panelTablaOriginal.setBorder(BorderFactory.createTitledBorder("Datos originales"));
					panelTablaOriginal.setToolTipText("Se muestran los datos que fueron completadas con exito");
 
					JPanel panelTablaParseada = new PanelTabla(completados.get(i).getUri().getHost(),
							restriccionesPlus);
					panelTablaParseada.setBorder(BorderFactory.createTitledBorder("Datos procesados"));
					panelTablaParseada
							.setToolTipText("Se muestran los datos procesaos que fueron completadas con exito");

					panelPrincipal.add(panelTablaOriginal);
					panelPrincipal.add(panelTablaParseada);
					tablaPaneles.addTab("Datos", panelPrincipal);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("Paso por aqui");

			}
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}
}