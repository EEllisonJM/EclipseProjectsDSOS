
package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelRestricciones extends JPanel implements ChangeListener {
	private String[] contenidos = new String[] { //
			"Mayor que", "Menor que", // 0[>], 1[<]
			"Igual a", "Diferente de", // 2[==], 3[!=]
			"Mayor o igual a", "Menor o Igual a" };// 4[>=], 5[<=]

	private JCheckBox[] jChecksDesIgualdades;
	private JTextField[] jTextFields;

	private List<Object[]> listaRestricciones;// [String][Integer]

	public PanelRestricciones() {
		this.setSize(new Dimension(30, 30));
		this.setBorder(BorderFactory.createTitledBorder("Realizar restricciones"));
		this.setToolTipText("Realizar restricciones (>, <, =, !=, >=, <=");
		this.setLayout(new GridLayout(0, 2));

		jChecksDesIgualdades = new JCheckBox[contenidos.length];
		jTextFields = new JTextField[contenidos.length];
		inicializarJCheckBoxs(jChecksDesIgualdades);
		inicializarCamposDeTexto(jTextFields);
		agrearComponente(jChecksDesIgualdades, jTextFields);
	}

	public void inicializarJCheckBoxs(JCheckBox jCheckBoxs[]) {
		for (int i = 0; i < jCheckBoxs.length; i++) {
			jCheckBoxs[i] = new JCheckBox(contenidos[i]);
			jCheckBoxs[i].addChangeListener(this);

		}
	}

	public void inicializarCamposDeTexto(JTextField jTextFields[]) {
		for (int i = 0; i < jTextFields.length; i++) {
			jTextFields[i] = new JTextField(2);
		}
	}

	public void agrearComponente(JCheckBox[] jCheckBoxs, JTextField[] jTextFields) {
		for (int i = 0; i < jTextFields.length; i++) {
			this.add(jCheckBoxs[i]);
			this.add(jTextFields[i]);
		}
	}

	public List<Object[]> getListaRestricciones() {
		for (int i = 0; i < jChecksDesIgualdades.length; i++) {
			if (jChecksDesIgualdades[i].isSelected()) {
				listaRestricciones.add(new Object[] { contenidos[i], jTextFields[i] });
				System.out.println(contenidos[i]);
				System.out.println(jTextFields[i]);
			}
		}
		return listaRestricciones;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		/*
		 * if (jChecksDesIgualdades[0].isSelected() == true) {
		 * System.out.println(jChecksDesIgualdades[0].getText()); }
		 */
	}
}