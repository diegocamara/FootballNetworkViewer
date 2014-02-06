package business;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import presentation.ApplicationWindow;


public class Player implements Comparable<Player>{
	
	int x,y,w,h,r;
	Point center;
	int posAnteriorX,posAnteriorY,pointDistanceMouse;
	List<Integer> pointDistancePlayer;	
	List<Point> pointDistancePlayerCenter;
	List<PlayerDistance> playerDistance;
	List<Player> adjacencias;
	int power;
	String number;
	Rotulo rotulo;
	Map<String,Integer> vetorDeDistancia;	
	int minVector;
	
	
	public Player(int x, int y, int w, int h, String number){
				
		pointDistancePlayer = new ArrayList<>();
		
		pointDistancePlayerCenter = new ArrayList<>();
		
		adjacencias = new ArrayList<>();
		
		vetorDeDistancia = new HashMap<>();
		
		//this.power = 200 + new Random().nextInt(100);
		this.power = ApplicationWindow.DISTANCIA_MINIMA_ADJACENTE;
		this.number = number;
		
		this.rotulo = new Rotulo();
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.r = w/2;
		center = new Point(x+(w/2), y+(h/2));
		
	}
	
	public Player(){
		
	}	
	

	public boolean hasCollision(){
		
		boolean result = false;
		
		for(Integer distancia : this.pointDistancePlayer){
			
			if(distancia < 2*this.r){
				//System.out.println(distancia + "  "+2*this.r);
				result = true;
			}
			
		}
		return result;
	}
	
	
	
	// configurando distancia do centro para os outros players.
	public void setPointDistancePlayer(List<Player> players) {
		
		pointDistancePlayer = new ArrayList<>();
		pointDistancePlayerCenter = new ArrayList<>();
		playerDistance = new ArrayList<>();
		adjacencias = new ArrayList<>();
		
		for (Player player : players) {
			
			if(!this.equals(player)){
				int distancia = (int) this.center.distance(player.center);				
				
				if(this.power >= distancia){
					adjacencias.add(player);					
				}
				
				pointDistancePlayer.add(distancia);
				playerDistance.add(new PlayerDistance(distancia, player.number));
				pointDistancePlayerCenter.add(player.center);
				
				//System.out.println(distancia);
			}
						
		}
		
				
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}


	public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
	}


	public Point getCenter() {
		return center;
	}


	public void setCenter(Point center) {
		this.center = center;
	}
		
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}	

	public Rotulo getRotulo() {
		return rotulo;
	}

	public void setRotulo(Rotulo rotulo) {
		this.rotulo = rotulo;
	}	

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}	

	public List<Player> getAdjacencias() {
		return adjacencias;
	}

	public void setAdjacencias(List<Player> adjacencias) {
		this.adjacencias = adjacencias;
	}
				
	public int getPointDistanceMouse() {
		return pointDistanceMouse;
	}

	public void setPointDistanceMouse(int pointDistanceMouse) {
		this.pointDistanceMouse = pointDistanceMouse;
	}

	public List<Point> getPointDistancePlayerCenter() {
		return pointDistancePlayerCenter;
	}

	public void setPointDistancePlayerCenter(List<Point> pointDistancePlayerCenter) {
		this.pointDistancePlayerCenter = pointDistancePlayerCenter;
	}

	public void setPointDistancePlayerP(List<Integer> pointDistancePlayer) {
		this.pointDistancePlayer = pointDistancePlayer;
	}

	public List<Integer> getPointDistancePlayer() {
		return pointDistancePlayer;
	}	
	
	public List<PlayerDistance> getPlayerDistance() {
		return playerDistance;
	}

	public void setPlayerDistance(List<PlayerDistance> playerDistance) {
		this.playerDistance = playerDistance;
	}
		
	public Map<String, Integer> getVetorDeDistancia() {
		return vetorDeDistancia;
	}

	public void setVetorDeDistancia(Map<String, Integer> vetorDeDistancia) {
		this.vetorDeDistancia = vetorDeDistancia;
	}
		
	public int getMinVector() {
		return minVector;
	}

	public void setMinVector(int minVector) {
		this.minVector = minVector;
	}

	@Override
	public String toString() {
		return "Player [number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	

			
	public int getDistance(List<PlayerDistance> distances, String id){				
		
		int result = 0;
		int index = Integer.parseInt(id);
		
		
		for(PlayerDistance pd : distances){
			
			if(pd.getNome().equals(id)){
				result = pd.getDistancia();
			}
			
		}				
		
		return result;			
		
	}	
	
	@Override
	public int compareTo(Player o) {
		return Double.compare(rotulo.getDistanciaMinima(), o.getRotulo().getDistanciaMinima());
		//return Double.compare(minDistance, o.minDistance);
	}
	
}// fim da classe Player.
