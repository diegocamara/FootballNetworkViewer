package business;

public enum Algorithm {

	DIJKSTRA(0),
	VETOR_DE_DISTANCIA(1);
	
	private int code;
	
	Algorithm(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
