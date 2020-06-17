import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame {

	Rozgrywka rozgrywka;
	Ekran ekran;

	public Okno() {
		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		// URUCHOMIENIE EKRANU
		ekran = new Ekran(this);
		add(ekran);

		// kolory,imiona,mapa
		int[] kolory = { 5, 7, 6, 2, 4, 1, 3, 0 };
		String[] pseudonimy = { "Marek", "Hanka", "Mateusz", "Barbara", "Lucek", "Ula", "Natalka", "Budzyn" };

		int szerokoscMapy = 20;
		int wysokoscMapy = 8;
		Random random = new Random();
		int[][] mapa = new int[wysokoscMapy][szerokoscMapy];
		for (int i = 0; i < wysokoscMapy; i++)
			for (int j = 0; j < szerokoscMapy; j++)
				mapa[i][j] = random.nextInt(10);

		// URUCHOMIENIE ROZGRYWKI
		rozgrywka = new Rozgrywka(szerokoscMapy, wysokoscMapy, 8, 7, 15, 5000, 10, true, kolory, pseudonimy, false,
				false, mapa); // 1 szerokoscMapy; 2 wysokoscMapy; 3 ileGraczy; 4 ktoraRunda; 5 ileRund; 6
								// czas; 7 domyslnaSzybkosc; 8 czyHp; 9 kolory; 10 pseudonimy; 11 przenikanie;
								// 12 odbijanie;

		ekran.rozgrywka = rozgrywka;
		rozgrywka.petlaGlowna(ekran);

		// DRUGIE URUCHOMIENIE ROZGRYWKI
		rozgrywka = new Rozgrywka(szerokoscMapy, wysokoscMapy, 8, 7, 15, 500, 10, false, kolory, pseudonimy, false,
				false, mapa);
		ekran.rozgrywka = rozgrywka;
		rozgrywka.petlaGlowna(ekran);

	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}
}
