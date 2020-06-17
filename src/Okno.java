import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame {

	Rozgrywka rozgrywka = null;
	Ekran ekran = new Ekran(this);
	MenuStart menuStart = new MenuStart(this);
	MenuTryb menuTryb = new MenuTryb(this);
	MenuMapa menuMapa = new MenuMapa(this);
	MenuGracze menuGracze = new MenuGracze(this);

	JPanel panelGlowny = new JPanel();
	CardLayout ukladZakladek = new CardLayout();
	
	public int flagaUruchomienia=0;

	public Okno() {
		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		panelGlowny.setLayout(ukladZakladek);
		add(panelGlowny);
		panelGlowny.add("ekran", ekran);
		panelGlowny.add("menuStart", menuStart);
		panelGlowny.add("menuTryb", menuTryb);
		panelGlowny.add("menuMapa", menuMapa);
		panelGlowny.add("menuGracze", menuGracze);
		
		ukladZakladek.show(panelGlowny, "menuStart");

		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			if(flagaUruchomienia==1) {
				flagaUruchomienia=0;
				uruchom();
			}
		}
		
	}
	
	void uruchom() {
		// kolory,imiona,power-upy,mapa
		int[] kolory = { 5, 7, 6, 2, 4, 1, 3, 0 };
		String[] pseudonimy = { "Marek", "Hanka", "Mateusz", "Barbara", "Lucek", "Ula", "Natalka", "Budzyn" };
		int[][] moce = { { 100, 1 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } };
		int szerokoscMapy = 30;
		int wysokoscMapy = 14;
		Random random = new Random();
		int[][] mapa = new int[wysokoscMapy][szerokoscMapy];
		for (int i = 0; i < wysokoscMapy; i++)
			for (int j = 0; j < szerokoscMapy; j++)
				mapa[i][j] = random.nextInt(10);
		// URUCHOMIENIE ROZGRYWKI
		rozgrywka = new Rozgrywka(szerokoscMapy, wysokoscMapy, 8, 7, 15, 5000, 10, true, kolory, pseudonimy, false,
				false, mapa, moce); // 1 szerokoscMapy; 2 wysokoscMapy; 3 ileGraczy; 4 ktoraRunda; 5 ileRund; 6
		// czas; 7 domyslnaSzybkosc; 8 czyHp; 9 kolory; 10 pseudonimy; 11 przenikanie;
		// 12 odbijanie; 13 mapa; 14 moce;
		
		ekran.rozgrywka = rozgrywka;
		ukladZakladek.show(panelGlowny, "ekran");
		ekran.setFocusable(true);
		ekran.requestFocusInWindow();
		rozgrywka.petlaGlowna(ekran);
		
	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}
}
