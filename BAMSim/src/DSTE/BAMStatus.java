package DSTE;

public enum BAMStatus {
	bloqueada(0), aceita(1), preempcao(2), devolucao(3), devolucaoEpreempcao(4);
	private final int status; 
	
	BAMStatus(int valorStatus)
	{ 
		status = valorStatus; 
	} 
	public int getValor()
	{ 
		return status; 
	}


}
