package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Nodo;

public class PanelCompletados extends JPanel implements ChangeListener, ActionListener {
	private List<Nodo> completados;
	private JTextPane[] jTextos;
	private JButton[] jBotones;

	public PanelCompletados(List<Nodo> completados) {
		this.completados = completados;
		this.setBorder(BorderFactory.createTitledBorder("Solicitudes completadas"));
		this.setToolTipText("Se muestran las solucitudes que fueron completadas con �xito");
		this.setLayout(new GridLayout(0, 2));

		jTextos = new JTextPane[completados.size()];
		jBotones = new JButton[completados.size()];
		inicializarJTextPane(jTextos);
		inicializarJButton(jBotones);
		agrearComponentes(jTextos, jBotones);
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

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}
}