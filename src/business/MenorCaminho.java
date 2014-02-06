package business;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MenorCaminho {

	public static List<Player> menorCaminho(List<Player> players, Player playerInicial) {
		
		int alt = 0;
		
		for(Player playerField : players){
			playerField.getRotulo().setDistanciaMinima(Integer.MAX_VALUE);					
		}		
		
		playerInicial.getRotulo().setDistanciaMinima(0);		
		
		PriorityQueue<Player> q = new PriorityQueue<Player>(players);
		
		while(!q.isEmpty()){
			
			Player u = q.poll();
			
			if(u.getRotulo().getDistanciaMinima() >= Integer.MAX_VALUE){
				break;
			}
			
			for(Player adjacencia : u.getAdjacencias()){
				alt = u.getRotulo().getDistanciaMinima() + distanciaEntre(u.center, adjacencia.center);
				
				if(alt < adjacencia.getRotulo().getDistanciaMinima()){
					adjacencia.getRotulo().setDistanciaMinima(alt);
					adjacencia.getRotulo().setPlayerAnterior(u);
					q.add(adjacencia);
					
				}
				
			}
			
		}
		
		return null;
	}
	
	public static List<Player> menorCaminhoList(Player target){
		List<Player> caminho = new ArrayList<>();
		
		for(Player player = target; player != null; player = player.getRotulo().getPlayerAnterior()){
			caminho.add(player);
		}
		Collections.reverse(caminho);
		
		return caminho;
	}
	
	private static int distanciaEntre(Point u, Point v){				
		return (int)u.distance(v);
	}
}
