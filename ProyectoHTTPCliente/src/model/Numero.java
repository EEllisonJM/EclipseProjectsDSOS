package model;

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

}
