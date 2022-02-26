package maven.boleto;

import java.util.Date;

public class Boleto {
	
	private String descricao;
	private double valor;
	private Date vencimento;
	private int indice;

	public Boleto() {
		// TODO Auto-generated constructor stub
	}
	

	


	public Boleto(String descricao, double valor, Date vencimento, int indice) {
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

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	

}
