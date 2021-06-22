package DSTE;

public class Roteador {
	int ID;
	String Descricao;
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	Link [][] caminhos = new Link[ParametrosDSTE.MaxCaminhos][ParametrosDSTE.MaxSaltos];



}
