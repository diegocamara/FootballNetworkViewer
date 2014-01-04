package presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import business.Field;
import business.PowerPanel;

public class ApplicationWindow extends JFrame{

	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	
	private Field field;
	private JPanel headPanel;
	private JPanel centerPanel;
	private PowerPanel powersPanel;
	private JButton executeButton;
	private JComboBox<String> algorithmList;
	
	public ApplicationWindow(String titulo){		
		
		setLayout(new MigLayout("","","[][]"));
		
		String[] algorithms = {"algorithm 01", "algorithm 02", "algorithm 03", "algorithm 04"};
		
		algorithmList = new JComboBox<>(algorithms);
		executeButton = new JButton(Labels.EXECUTAR);
				
		field = new Field();
		field.setLayout(new MigLayout());		
		field.setBackground(Color.LIGHT_GRAY);
		
		headPanel = new JPanel(new MigLayout("fill"));		
		headPanel.add(algorithmList, "width :200:,split 2,right");
		headPanel.add(executeButton);
		headPanel.setBackground(Color.LIGHT_GRAY);
		
		centerPanel = new JPanel(new MigLayout("","[][]",""));
		centerPanel.setBackground(Color.WHITE);
		
		powersPanel = new PowerPanel(field.getPlayers(), field);
		powersPanel.setBackground(Color.LIGHT_GRAY);
		
		centerPanel.add(powersPanel, "width :150:, height :"+field.getHeight()+":");		
		centerPanel.add(field,"width :"+field.getWidth()+":, height :"+field.getHeight()+":");
		
		
		add(headPanel, "wrap, growx");		
		add(centerPanel, "grow");
		
		setTitle(titulo);
		setSize(new Dimension(WIDTH,HEIGHT));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		
	}// fim do construtor Window.
	
}
