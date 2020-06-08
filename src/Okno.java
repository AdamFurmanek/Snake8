import javax.swing.JFrame;

public class Okno extends JFrame {

	public Okno() {
		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		Rozgrywka rozgrywka = new Rozgrywka(this, 30, 15, 3);
	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}
}
