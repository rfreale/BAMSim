package DSTE;

public class Caminho
	{
		int NumHops; // Número de Saltos do Caminho Escolhido dentre aqueles caminhos possíveis
		int NumParOD; // Número do Par OD para efeitos de identificação na simulação
		int PrioridadeOD; // Prioridade da LSP (Associada a Classe de Serviço)
		int [] CaminhoEscolhido  = new int [ParametrosDSTE.MaxSaltos +1]; // Vetor que armazena o caminho escolhido pela LSP dentre aqueles possíveis
	}