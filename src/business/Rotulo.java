package business;

public class Rotulo {

	private Integer distanciaMinima;
	private Player playerAnterior;

	
	public Rotulo(Integer distanciaMinima, Player playerAnterior){
		setDistanciaMinima(distanciaMinima);
		setPlayerAnterior(playerAnterior);
	}
	
	public Rotulo(){
		
	}

	public Integer getDistanciaMinima() {
		return distanciaMinima;
	}

	public void setDistanciaMinima(Integer distanciaMinima) {
		this.distanciaMinima = distanciaMinima;
	}

	public Player getPlayerAnterior() {
		return playerAnterior;
	}

	public void setPlayerAnterior(Player playerAnterior) {
		this.playerAnterior = playerAnterior;
	}

		
	
}
