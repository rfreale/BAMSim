package DSTE;

public enum LspStatus {
	criada(0), estabelecida(1), bloqueada(2), preemptada(3), devolvida(4), finalizada(5);
	private final int status; 
	
	LspStatus(int valorStatus)
	{ 
		status = valorStatus; 
	} 
	public int getValor()
	{ 
		return status; 
	}


}
