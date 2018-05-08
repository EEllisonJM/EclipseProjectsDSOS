package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelOperaciones extends JPanel implements ChangeListener {

	private String listaOperacionesValidas[] = new String[] { // Operaciones a realizar
			"Sumar", "Restar", "Dividir", "Multiplicar", "Duplicar" };

	private JCheckBox jcheckRealizar;
	private JComboBox<String> jcbListaOperaciones;
	private JTextField jtfValor;

	public PanelOperaciones() {
		this.setBorder(BorderFactory.createTitledBorder("Realizar operaciones"));
		this.setToolTipText("Realizar operaciones de +, -, *, /");
		/* Inicializar */
		jcheckRealizar = new JCheckBox();
		jcbListaOperaciones = new JComboBox<>();
		setListaOperaciones(listaOperacionesValidas);
		jtfValor = new JTextField(5);
		/* Agregar a panel */
		jcheckRealizar.addChangeListener(this);
		this.add(jcheckRealizar);

	}

	public void setListaOperaciones(String[] values) {
		for (int i = 0; i < values.length; i++) {
			jcbListaOperaciones.addItem(values[i]);
		}
	}

	public JComboBox<String> getListaOperaciones() {
		return jcbListaOperaciones;
	}

	public boolean isSelected() {
		return jcheckRealizar.isSelected();
	}

	public JTextField getJtfValor() {
		return jtfValor;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (jcheckRealizar.isSelected() == true) {
			this.add(jcbListaOperaciones);
			this.add(jtfValor);
			this.validate();
			this.repaint();
		}
		if (jcheckRealizar.isSelected() == false) {
			this.remove(jcbListaOperaciones);
			this.remove(jtfValor);
			this.validate();
			this.repaint();
		}

	}
}
