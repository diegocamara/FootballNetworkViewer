package presentation;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;

import business.Player;

public class TableWindow extends JFrame {

	private final int WIDTH = 200;
	private final int HEIGHT = 220;

	private JTable tabela;

	public TableWindow(String title, Player player) {

		String colunas[] = new String[] { "Test", "Test2" };
		

		tabela = new JTable(getLinhas(player), colunas);
		add(tabela);
		setTitle(title);
		setSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		setVisible(true);

		setLocationRelativeTo(null);

	}

	private String[][] getLinhas(Player player) {

		String rows[][] = new String[player.getVetorDeDistancia().size()][2];
		
		int count = 0;
		
		for (Map.Entry<String, Integer> entry : player.getVetorDeDistancia()
				.entrySet()) {

			rows[count][0] = entry.getKey();
			rows[count][1] = entry.getValue().toString();
			count++;
		}
		
		return rows;
		
	}

}
