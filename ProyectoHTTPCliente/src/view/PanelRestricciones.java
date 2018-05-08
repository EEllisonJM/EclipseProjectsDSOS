
package view;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelRestricciones extends JPanel implements ChangeListener {

	private JCheckBox jcheckMayor,jcheckMenor,jcheckIgual,jcheckMulti;
	private JTextField jtextMayor,jtextMenor,jtextIgual,jtextMulti;
	private String[][] restricciones = {{"Mayor que",""},{"Menor que",""},{"Exactamente",""},{"Multiplo de",""}};
	
	public PanelRestricciones() {
		
		setLayout(null);
		jcheckMayor=new JCheckBox("Mayor que");
		jcheckMenor=new JCheckBox("Menor que");
		jcheckIgual=new JCheckBox("Exactamente");
		jcheckMulti=new JCheckBox("Multiplo de ");
		jtextMayor=new JTextField("");
		jtextMenor=new JTextField("");
		jtextIgual=new JTextField("");
		jtextMulti=new JTextField("");
		
        jcheckMayor.setBounds(10,10,120,30);
        jcheckMenor.setBounds(10,40,120,30);
        jcheckIgual.setBounds(10,70,120,30);
        jcheckMulti.setBounds(10,100,120,30);
        jtextMayor.setBounds(130,10,50,30);
		jtextMenor.setBounds(130,40,50,30);
		jtextIgual.setBounds(130,70,50,30);
		jtextMulti.setBounds(130,100,50,30);
        
        jcheckMayor.addChangeListener(this);
        jcheckMenor.addChangeListener(this);
        jcheckIgual.addChangeListener(this);
        jcheckMulti.addChangeListener(this);

        add(jcheckMayor);
        add(jcheckMenor);
        add(jcheckIgual);
        add(jcheckMulti);
        add(jtextMayor);
        add(jtextMenor);
        add(jtextIgual);
        add(jtextMulti);
	}
	
	public String[][] getRestricciones() {
		return restricciones;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
        if (jcheckMayor.isSelected()==true) {
            restricciones[0][1]=jtextMayor.getText();
        } else {
        	restricciones[0][1]="";
        }
        if (jcheckMenor.isSelected()==true) {
        	restricciones[1][1]=jtextMenor.getText();
        } else {
        	restricciones[1][1]="";
        }
        if (jcheckIgual.isSelected()==true) {
        	restricciones[2][1]=jtextIgual.getText();
        } else {
        	restricciones[2][1]="";
        }
        if (jcheckMulti.isSelected()==true) {
        	restricciones[3][1]=jtextMulti.getText();
        } else {
        	restricciones[3][1]="";
        }
	} 
	
}