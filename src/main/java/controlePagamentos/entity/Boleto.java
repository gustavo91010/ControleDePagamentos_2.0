package controlePagamentos.entity;

import java.time.LocalDate;

public class Boleto {
	
	private String descricao;
	private double valor;
	private LocalDate vencimento;
	private int indice;

	public Boleto() {
		// TODO Auto-generated constructor stub
	}
	
	public Boleto(String descricao, double valor, LocalDate vencimento) {
		this.descricao = descricao;
		this.valor = valor;
		this.vencimento = vencimento;
	}


	public Boleto(String descricao, double valor, LocalDate vencimento, int indice) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.vencimento = vencimento;
		this.indice = indice;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	

}