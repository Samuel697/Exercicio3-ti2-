package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Carro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String DESCRICAO_PADRAO = "Novo Carro";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String descricao;
	private float preco;
	//private int quantidade;
	private Date dataFabricacao;	
	//private LocalDate dataValidade;
	
	public Carro() {
		id = -1;
		descricao = DESCRICAO_PADRAO;
		preco = 0.01F;
		//quantidade = 0;
		dataFabricacao =null;
		//dataValidade = LocalDate.now().plusMonths(6); // o default é uma validade de 6 meses.
	}

	public Carro(int id, String descricao, float preco, Date date) {
		setId(id);
		setDescricao(descricao);
		setPreco(preco);
		//setQuant(quantidade);
		setDataFabricacao(date);
		//setDataValidade(v);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao.length() >= 3)
			this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		if (preco > 0)
			this.preco = preco;
	}

	//public int getQuant() {
		//return quantidade;
	//}
	
	//public void setQuant(int quantidade) {
		//if (quantidade >= 0 && quantidade <= MAX_ESTOQUE)
			//this.quantidade = quantidade;
	//}
	
	//public LocalDate getDataValidade() {
		//return dataValidade;
	//}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date date) {
		// Pega a Data Atual
		//LocalDateTime agora = LocalDateTime.now();
		// Garante que a data de fabricação não pode ser futura
		//if (agora.compareTo(date) >= 0)
			this.dataFabricacao = date;
	}

	//public void setDataValidade(LocalDate dataValidade) {
		// a data de fabricação deve ser anterior é data de validade.
		//if (getDataFabricacao().isBefore(dataValidade.atStartOfDay()))
			//this.dataValidade = dataValidade;
	//

	//public boolean emValidade() {
	//	return LocalDateTime.now().isBefore(this.getDataValidade().atTime(23, 59));
	//}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Carro: " + descricao + "   Preço: R$" + preco +  "   Fabricação: "
				+ dataFabricacao ;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Carro) obj).getId());
	}	
}