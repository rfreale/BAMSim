package DSTE;

public class Caminho
	{
		int NumHops; // N�mero de Saltos do Caminho Escolhido dentre aqueles caminhos poss�veis
		int NumParOD; // N�mero do Par OD para efeitos de identifica��o na simula��o
		int PrioridadeOD; // Prioridade da LSP (Associada a Classe de Servi�o)
		int [] CaminhoEscolhido  = new int [ParametrosDSTE.MaxSaltos +1]; // Vetor que armazena o caminho escolhido pela LSP dentre aqueles poss�veis
	}