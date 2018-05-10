
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

public class PanelRestricciones extends JPanel implements ChangeListener {
	
	private String[] restricciones = new String[] {
			//Restricciones que no requieren parámetros
			"Positivo", "Negativo", //0,1
			"Par", "impar", //2,3
			"Entero", "Punto flotante",//4,5
			//Requieren un parámetro
			"Mayor que", "Menor que",//6,7
			"Igual a", "Diferente de",//8,9
			"Mayor o igual a", "Menor o Igual a",//10,11
			//Requieren dos parámetros
			"Rango", "No esta en el rango de" };//12,13
	
	private List<Object[]> listaRestricciones = new ArrayList<Object[]>();// [String], [String][Integer] o [String][Integer][Integer]
	
	private JCheckBox[] jChecks;
	private JTextField[] jTextField1, jTextField2;

	public PanelRestricciones() {
		this.setBorder(BorderFactory.createTitledBorder("Realizar restricciones"));
		this.setToolTipText("Realizar restricciones >, <, =, !=, >=, <=, etc.");
		this.setLayout(new GridLayout(0, 3));
		jChecks = new JCheckBox[restricciones.length];
		jTextField1 = new JTextField[restricciones.length];
		jTextField2 = new JTextField[restricciones.length];
		inicializarJCheckBoxs(jChecks, restricciones);
		inicializarCamposDeTexto(jTextField1);
		inicializarCamposDeTexto(jTextField2);
		agrearComponente(jChecks, jTextField1, jTextField2);
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

	private void agrearComponente(JCheckBox[] jCheckBoxs, JTextField[] jTF1, JTextField[] jTF2) {
		//No requieren parámetros
		for (int i = 0; i <= 5; i++) {
			jTF1[i].setVisible(false);
			jTF2[i].setVisible(false);
			this.add(jCheckBoxs[i]);
			this.add(jTF1[i]);
			this.add(jTF2[i]);
		}
		//Requieren un parámetro
		for (int i = 6; i <= 11; i++) {
			jTF2[i].setVisible(false);
			this.add(jCheckBoxs[i]);
			this.add(jTF1[i]);
			this.add(jTF2[i]);
		}
		//Requieren dos parámetros
		for (int i = 12; i <= 13; i++) {
			this.add(jCheckBoxs[i]);
			this.add(jTF1[i]);
			this.add(jTF2[i]);
		}
	}
	
	public List<Object[]> getListaRestricciones() {
		listaRestricciones.removeAll(listaRestricciones);
		//No requieren parámetros
		for (int i = 0; i <= 5; i++) {
			if (jChecks[i].isSelected()) {
				listaRestricciones.add(new Object[] { restricciones[i] });
				System.out.println(restricciones[i]);
			}
		}
		//Requieren un parámetro
		for (int i = 6; i <= 11; i++) {
			if (jChecks[i].isSelected()) {
				listaRestricciones.add(new Object[] { restricciones[i], jTextField1[i].getText() });
				System.out.println(restricciones[i]+", "+jTextField1[i].getText());
			}
		}
		//Requieren 2 parámetros
		for (int i = 12; i <= 13; i++) {
			if (jChecks[i].isSelected()) {
				listaRestricciones.add(new Object[] { restricciones[i], jTextField1[i].getText(), jTextField2[i].getText() });
				System.out.println(restricciones[i]+", "+jTextField1[i].getText()+", "+jTextField2[i].getText());
			}
		}
		return listaRestricciones;
	}

	public String[] getRestricciones() {
		return restricciones;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		/*System.out.println("--> Agregando:");
		getListaRestricciones();
		System.out.println("--> Aqui inicia la lista:");
		for(int i=0;i<listaRestricciones.size();i++) {
			System.out.println(listaRestricciones.get(i).toString());
		}*/
	}
}