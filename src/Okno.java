import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Okno extends JFrame {

	CardLayout card;
	
	public Okno() {
		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		Rozgrywka rozgrywka = new Rozgrywka(this, 50, 25, 8, 7, 15, 500);

	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}
}
