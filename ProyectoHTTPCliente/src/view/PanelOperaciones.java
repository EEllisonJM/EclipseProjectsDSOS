package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelOperaciones extends JPanel implements ChangeListener {

	private String[] operaciones = new String[] { // Operaciones a realizar
			// Operaciones con parametro
			"Sumar", "Restar", "Dividir", "Multiplicar", // 0,1,2,3
			"Truncar parte decimal", "Redondear a n decimas", // 4,5
			// Operaciones que no requieren par�metro
			"Obtener parte entera", "Obtner parte no entera" };// 6,7

	private List<Object[]> listaOperaciones = new ArrayList<Object[]>();// [String][Integer] y [String]

	private JCheckBox[] jcheckRealizar;
	private JTextField[] jtfValor;

	public PanelOperaciones() {
		this.setBorder(BorderFactory.createTitledBorder("Realizar operaciones"));
		this.setToolTipText("Realizar operaciones Aritmeticas y redondeo");
		this.setLayout(new GridLayout(0, 2));
		jcheckRealizar = new JCheckBox[operaciones.length];
		jtfValor = new JTextField[operaciones.length];
		inicializarJCheckBoxs(jcheckRealizar, operaciones);
		inicializarCamposDeTexto(jtfValor);
		agrearComponente(jcheckRealizar, jtfValor);
	}

	private void inicializarJCheckBoxs(JCheckBox jCheckBoxs[], String textos[]) {
		for (int i = 0; i < jCheckBoxs.length; i++) {
			jCheckBoxs[i] = new JCheckBox(textos[i]);
			jCheckBoxs[i].addChangeListener(this);

		}
	}

	private void inicializarCamposDeTexto(JTextField jTextFields[]) {
		for (int i = 0; i < jTextFields.length; i++) {
			jTextFields[i] = new JTextField();
		}
	}

	private void agrearComponente(JCheckBox[] jCheckBoxs, JTextField[] jTextField) {
		// Operaciones sin par�metros
		for (int i = 6; i <= 7; i++) {
			jTextField[i].setVisible(false);
			this.add(jCheckBoxs[i]);
			this.add(jTextField[i]);
		}
		// Operaciones que requieren de un par�metro
		for (int i = 0; i <= 5; i++) {
			this.add(jCheckBoxs[i]);
			this.add(jTextField[i]);
		}
	}

	public List<Object[]> getListaOperaciones() {
		listaOperaciones.removeAll(listaOperaciones);
		// Operaciones que requieren de un par�metro
		for (int i = 0; i <= 5; i++) {
			if (jcheckRealizar[i].isSelected()) {
				listaOperaciones.add(new Object[] { operaciones[i], jtfValor[i].getText() });
				System.out.println(operaciones[i] + ", " + jtfValor[i].getText());
			}
		}
		// Operaciones que no requieren par�metro
		for (int i = 6; i <= 7; i++) {
			if (jcheckRealizar[i].isSelected()) {
				listaOperaciones.add(new Object[] { operaciones[i] });
				System.out.println(operaciones[i]);
			}
		}
		return listaOperaciones;
	}

	public String[] getOperaciones() {
		return operaciones;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}
}