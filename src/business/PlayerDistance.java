package business;

public class PlayerDistance {

	private int distancia;
	private String nome;
	
	public PlayerDistance(int distancia, String nome){
		setDistancia(distancia);
		setNome(nome);
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
