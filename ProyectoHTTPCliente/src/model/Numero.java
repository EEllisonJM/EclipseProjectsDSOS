package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Numero {
	private int valor;

	public Numero(int valor) {
		this.valor = valor;

	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	//Restricciones
	
	public boolean esPositivo() {
		if (valor>0) {
			return true;
		}
		return false;
	}
	
	public boolean esNegativo() {
		if (valor<0) {
			return true;
		}
		return false;
	}
	
	public boolean esPar() {
		if ((valor%2)==0) {
			return true;
		}
		return false;
	}
	
	public boolean esImpar() {
		if (!esPar()) {
			return true;
		}
		return false;
	}
	
	public boolean esEntero(Object v) {
		try {
			Integer.parseInt(""+v);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public boolean esFlotante(Object v) {
		if (!esEntero(v)) {
			try {
				Float.parseFloat(""+v);
				return true;
			} catch(NumberFormatException e) {
				return false;
			}
		}
		return false;
	}
	
	public boolean mayorQue(int n) {
		return (valor > n) ? true : false;
	}

	public boolean menorQue(int n) {
		return (valor < n) ? true : false;
	}

	public boolean igualA(int n) {
		return (valor == n) ? true : false;
	}

	public boolean mayorIgualQue(int n) {
		return (valor >= n) ? true : false;
	}

	public boolean menorIgualQue(int n) {
		return (valor <= n) ? true : false;
	}

	public boolean diferenteA(int n) {
		return (valor != n) ? true : false;
	}
	
	public boolean estaEnRango(int x, int y) {
		if (valor>=x&&valor<=y) {
			return true;
		} if (valor<=x&&valor>=y) {
			return true;
		}
		return false;
	}
	
	public boolean noEstaEnRango(int x, int y) {
		if (valor>x&&valor>y) {
			return true;
		} if (valor<x&&valor<y) {
			return true;
		}
		return false;
	}
	
	//Operaciones
	
	public int sumar(int n) {
		return valor+n;
	}
	
	public int restar(int n) {
		return valor-n;
	}
	
	public int multiplicar(int n) {
		return valor*n;
	}
	
	public int dividir(int n) {
		return valor+n;
	}
	
	public String truncar(String v, int numDecimales) {
		String cad = "#.";
	 	for (int i=0;i<numDecimales;i++){
	 		cad+="0";
	 	}
		DecimalFormat formato = new DecimalFormat(cad);
		formato.setRoundingMode(RoundingMode.DOWN);
		v = formato.format(Double.parseDouble(v));
		return v;
	}
	
	public String redondear(String v, int numDecimales) {
		String cad = "#.";
	 	for (int i=0;i<numDecimales;i++){
	 		cad+="0";
	 	}
		DecimalFormat formato = new DecimalFormat(cad);
		formato.setRoundingMode(RoundingMode.HALF_UP);
		v = formato.format(Double.parseDouble(v));
		return v;
	}
	
	public String obtenerParteEntera(String v) {
		DecimalFormat formato = new DecimalFormat("#");
		formato.setRoundingMode(RoundingMode.DOWN);
		v = formato.format(Double.parseDouble(v));
		return v;
	}
	
	public String obtenerParteNoEntera(String v) {
		return v.substring(v.indexOf('.') + 1);
	}

}