package business;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player{
	
	int x,y,w,h,r;
	Point center;
	int posAnteriorX,posAnteriorY,pointDistanceMouse;
	List<Integer> pointDistancePlayer;	
	List<Point> pointDistancePlayerCenter;
	List<Player> adjacencias;
	int power;
	String number;
	
	
	public Player(int x, int y, int w, int h, String number){
		
		pointDistancePlayer = new ArrayList<>();
		
		pointDistancePlayerCenter = new ArrayList<>();
		
		adjacencias = new ArrayList<>();
		
		this.power = 200 + new Random().nextInt(100);
		
		this.number = number;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.r = w/2;
		center = new Point(x+(w/2), y+(h/2));
		
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
		adjacencias = new ArrayList<>();
		
		for (Player player : players) {
			
			if(!this.equals(player)){
				int distancia = (int) this.center.distance(player.center);				
				
				if(this.power >= distancia){
					adjacencias.add(player);					
				}
				
				pointDistancePlayer.add(distancia);
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

	
		
	
}// fim da classe Player.
