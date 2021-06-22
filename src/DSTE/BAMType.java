package DSTE;

public enum BAMType {
	NoPreemptionMAM(0), NoPreemptionRDM(1), NoPreemptionAllocCTSharing(2),PreemptionMAM(3), PreemptionRDM(4), PreemptionAllocCTSharing(5),PreemptionGBAM(6),NoPreemptionBestEffort(7);
	private final int status; 
	
	BAMType(int valorStatus)
	{ 
		status = valorStatus; 
	} 
	public int getValor()
	{ 
		return status; 
	}


}
