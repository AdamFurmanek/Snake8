import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame {

	Rozgrywka rozgrywka;
	Serwer serwer;

	private Okno() throws Exception {

		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		serwer = new Serwer(this, 50, 25, 2);
		Thread thread = new Thread(() -> {

		});
		thread.start();
		rozgrywka = new Rozgrywka(serwer, this);
		this.add(rozgrywka);
	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}

}
