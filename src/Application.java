import javax.swing.JFrame;

import presentation.ApplicationWindow;
import presentation.Labels;


public class Application {

	public static void main(String[] args){
				
		ApplicationWindow application = new ApplicationWindow(Labels.TITULO);
		application.setVisible(true);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}// fim do método main.
	
}// fim da classe Application.
