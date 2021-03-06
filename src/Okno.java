import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame {

	// GLOWNY PANEL, ORGANIZER PANELI
	JPanel panelGlowny = new JPanel();
	CardLayout zakladki = new CardLayout();

	// PANELE NAKLADANE NA PANEL GLOWNY
	MenuStart menuStart = new MenuStart(this);
	MenuTryb menuTryb = new MenuTryb(this);
	MenuMapa menuMapa = new MenuMapa(this);
	MenuGracze menuGracze = new MenuGracze(this);
	EkranGry ekranGry = new EkranGry(this);
	EkranWynik ekranWynik = new EkranWynik(this);

	// ZMIENNE UMOZLIWIAJACE SKALOWANIE OKNA
	double skalaSzerokoscOkno, skalaWysokoscOkno;

	// ZMIENNE ROZGRYWKI
	List<Waz> waz = new ArrayList<Waz>();
	List<Przedmiot> przedmiot = new ArrayList<Przedmiot>();
	int szerokoscMapy, wysokoscMapy;
	int[][] parametryPrzedmiotow = new int[9][2];
	int[][] mapa;
	int[] kolory = new int[8];
	String[] pseudonimy = new String[8];
	int warunekKonca;
	int aktualnaRunda, iloscRund, dlugoscRundy, pozostalyCzas;
	int limitZgonow, limitPunktow;
	int domyslnaSzybkosc;
	boolean wlaczHP, stalePrzenikanie, staleOdbijanie, stalePrzechodzenie, zostawianieCiala;

	// TECHNICZNE
	int ileGraczy;
	Random random = new Random();
	int sterowanie[][] = { { 38, 37, 40, 39 }, { 87, 65, 83, 68 }, { 84, 70, 71, 72 }, { 73, 74, 75, 76 },
			{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
	boolean startGry = false, koniecGry = false, rundaTrwa;

	public Okno() {
		super("Snake8");
		setSize(1297, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaSzerokoscOkno = (double) (getWidth() - 16) / 1920;
				skalaWysokoscOkno = (double) (getHeight() - 39) / 1080;
			}
		});

		panelGlowny.setLayout(zakladki);
		add(panelGlowny);

		panelGlowny.add(menuStart, "menuStart");
		panelGlowny.add(menuTryb, "menuTryb");
		panelGlowny.add(menuMapa, "menuMapa");
		panelGlowny.add(menuGracze, "menuGracze");
		panelGlowny.add(ekranGry, "ekranGry");
		panelGlowny.add(ekranWynik, "ekranWynik");

		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
			if (startGry) {
				startGry = false;
				koniecGry = false;
				for (aktualnaRunda = 1; aktualnaRunda < iloscRund + 1; aktualnaRunda++) {
					zakladki.show(panelGlowny, "ekranGry");
					ekranGry.requestFocusInWindow();
					pozostalyCzas=dlugoscRundy;
					rozgrywka();
					zakladki.show(panelGlowny, "ekranWynik");
					ekranWynik.requestFocusInWindow();
					try {
						Thread.sleep(2000);
					} catch (Exception ex) {
					}
					if (koniecGry)
						break;
				}
				zakladki.show(panelGlowny, "menuStart");
				menuStart.requestFocusInWindow();
			}
		}
	}

	void rozgrywka() {
		
		for (int i = 0; i < 8; i++) {
			kolory[i] = i + 2;
			pseudonimy[i] = new String("Gracz " + i);
		}
		szerokoscMapy = 16;
		wysokoscMapy = 8;

		mapa = new int[wysokoscMapy][szerokoscMapy];
		for (int i = 0; i < wysokoscMapy; i++)
			for (int j = 0; j < szerokoscMapy; j++)
				mapa[i][j] = random.nextInt(10)+2;
		ileGraczy = 4;

		// ^^^ LOSOWE ^^^

		waz.clear();
		for (int i = 0; i < ileGraczy; i++) {
			waz.add(new Waz(this, i, kolory[i], pseudonimy[i]));
			waz.get(i).respawn();
		}

		przedmiot.clear();
		przedmiot.add(new Serek(this, parametryPrzedmiotow[0][0], parametryPrzedmiotow[0][1]));
		przedmiot.add(new Duszek(this, parametryPrzedmiotow[1][0], parametryPrzedmiotow[1][1]));
		przedmiot.add(new Adrenalinka(this, parametryPrzedmiotow[2][0], parametryPrzedmiotow[2][1]));
		przedmiot.add(new Slimaczek(this, parametryPrzedmiotow[3][0], parametryPrzedmiotow[3][1]));
		przedmiot.add(new Odbijaczek(this, parametryPrzedmiotow[4][0], parametryPrzedmiotow[4][1]));
		przedmiot.add(new Nozyczki(this, parametryPrzedmiotow[5][0], parametryPrzedmiotow[5][1]));
		przedmiot.add(new Apteczka(this, parametryPrzedmiotow[6][0], parametryPrzedmiotow[6][1]));
		przedmiot.add(new Kolce(this, parametryPrzedmiotow[7][0], parametryPrzedmiotow[7][1]));
		przedmiot.add(new Duszek2(this, parametryPrzedmiotow[8][0], parametryPrzedmiotow[8][1]));

		rundaTrwa = true;
		while (rundaTrwa && !koniecGry) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			for (int i = 0; i < waz.size(); i++) {
				if (waz.get(i).zgony < limitZgonow || limitZgonow == 0)
					waz.get(i).takt();
			}

			for (int i = 0; i < przedmiot.size(); i++) {
				przedmiot.get(i).respawn();
			}

			ekranGry.repaint();

			for (int i = 0; i < waz.size(); i++) {
				if (limitPunktow > 0 && waz.get(i).punkty >= limitPunktow)
					rundaTrwa = false;
			}
			if (limitZgonow > 0)
				rundaTrwa = false;
			for (int i = 0; i < waz.size(); i++) {
				if (waz.get(i).zgony < limitZgonow)
					rundaTrwa = true;
			}

			if (dlugoscRundy > 0) {
				pozostalyCzas--;
				if (pozostalyCzas <= 0)
					rundaTrwa = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Okno();
	}
}
