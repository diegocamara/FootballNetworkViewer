package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VetorDeDistancia {

	private static final int DISTANCIA_PROPRIA = 0;
	private static final int NUMERO_DE_PROPAGACAO = 5;
	private Field field;
	private List<Player> path;

	public VetorDeDistancia(Field field) {
		this.field = field;
	}

	public void tabelaInicial() {

		for (Player player : field.getPlayers()) {

			player.setVetorDeDistancia(new HashMap<String, Integer>());

			for (Player playerAdjacente : player.getAdjacencias()) {

				int distance = (int) player.getCenter().distance(
						playerAdjacente.getCenter());

				player.getVetorDeDistancia().put(playerAdjacente.getNumber(),
						distance);

			}

		}

	}

	public void propagar() {

		int count = 0;

		while (count != NUMERO_DE_PROPAGACAO) {

			for (Player player : field.getPlayers()) {

				for (Player playerAdjacente : player.getAdjacencias()) {

					for (Map.Entry<String, Integer> entry : playerAdjacente
							.getVetorDeDistancia().entrySet()) {

						if (!player.getVetorDeDistancia().containsKey(
								entry.getKey())) {

							int distancia = entry.getValue()
									+ (int) player.center
											.distance(playerAdjacente.center);

							player.getVetorDeDistancia().put(entry.getKey(),
									distancia);

						}

					}

					player.getVetorDeDistancia().put(player.number,
							DISTANCIA_PROPRIA);

				}

			}

			count++;
		}

	}

	public List<Player> getPath(Player playerInicial, Player playerFinal) {

		List<Player> bestPath = new ArrayList<>();
		bestPath.add(playerInicial);
		while (playerInicial != null) {

			List<Player> path = new ArrayList<>();

			for (Player playerAdjacente : playerInicial.getAdjacencias()) {

				for (Map.Entry<String, Integer> entry : playerAdjacente
						.getVetorDeDistancia().entrySet()) {

					if (entry.getKey().equals(playerFinal.number)) {
						playerAdjacente.setMinVector(entry.getValue());
						path.add(playerAdjacente);
						break;
					}

				}

			}

			Player player = getDistance(path);
			bestPath.add(player);
			resetMinVector(field.getPlayers());
			playerInicial = player;
			
			if(player.equals(playerFinal)){
				playerInicial = null;
			}

		}
		return bestPath;
	}

	
	private Player getDistance(List<Player> players) {

		int menor = players.get(0).getMinVector();
		Player playerMenorDistancia = players.get(0);

		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getMinVector() < menor) {
				menor = players.get(i).getMinVector();
				playerMenorDistancia = players.get(i);
			}
		}

		return playerMenorDistancia;

	}
	
	private void resetMinVector(List<Player> players){
		
		for(Player player : field.getPlayers()){
			player.setMinVector(0);
		}
		
	}

}
