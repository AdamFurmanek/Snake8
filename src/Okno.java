import java.awt.Dimension;

import javax.swing.JFrame;

public class Okno extends JFrame {

	public Okno() {
		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		Rozgrywka rozgrywka = new Rozgrywka(this, 25, 10, 2);
	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}
}
