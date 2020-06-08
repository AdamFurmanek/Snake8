import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno2 extends JFrame {

	Wyswietlacz2 wyswietlacz;
	Rozgrywka2 rozgrywka;

	private Okno2() throws Exception {

		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		rozgrywka = new Rozgrywka2(this, 50, 25, 2);
		wyswietlacz = new Wyswietlacz2(rozgrywka, this);
		this.add(wyswietlacz);
	}

	public static void main(String[] args) throws Exception {
		//new Okno2();
	}
}
