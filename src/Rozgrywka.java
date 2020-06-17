import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rozgrywka {

	Ekran ekran;

	List<Waz> waz;
	List<Przedmiot> przedmiot;

	int szerokoscMapy, wysokoscMapy;

	int[] runda = new int[3];
	int domyslnaSzybkosc;
	boolean czyHp;
	int[] kolory;
	String[] pseudonimy;
	boolean przenikanie, odbijanie;

	int[][] mapa;
	int[][] moce;
	
	public Rozgrywka(int szerokoscMapy, int wysokoscMapy, int ileGraczy, int ktoraRunda, int ileRund, int czas,
			int domyslnaSzybkosc, boolean czyHp, int[] kolory, String[] pseudonimy, boolean przenikanie,
			boolean odbijanie, int[][] mapa, int[][] moce) {

		this.szerokoscMapy = szerokoscMapy;
		this.wysokoscMapy = wysokoscMapy;
		runda[0] = ktoraRunda;
		runda[1] = ileRund;
		runda[2] = czas;
		this.domyslnaSzybkosc = domyslnaSzybkosc;
		this.czyHp = czyHp;
		this.kolory = kolory;
		this.pseudonimy = pseudonimy;
		this.przenikanie = przenikanie;
		this.odbijanie = odbijanie;
		this.mapa = mapa;
		this.moce=moce;
		reset(ileGraczy);
	}

	void reset(int ileGraczy) {

		waz = new ArrayList<Waz>();
		przedmiot = new ArrayList<Przedmiot>();

		for (int i = 0; i < ileGraczy; i++) {
			waz.add(new Waz(this, i, kolory[i], pseudonimy[i]));
			waz.get(i).respawn();
		}

		przedmiot.add(new Serek(this, moce[0][0], moce[0][1]));
		przedmiot.add(new Duszek(this, moce[1][0], moce[1][1])); //nie moga sie pojawiac jesli przenikanie jest stale
		przedmiot.add(new Adrenalinka(this, moce[2][0], moce[2][1]));
		przedmiot.add(new Slimaczek(this, moce[3][0], moce[3][1]));
		przedmiot.add(new Odbijaczek(this, moce[4][0], moce[4][1])); //nie moga sie pojawiac jesli odbijanie jest stale
		przedmiot.add(new Nozyczki(this, moce[5][0], moce[5][1]));
		przedmiot.add(new Apteczka(this, moce[6][0], moce[6][1])); //nie moga sie pojawic jesli gra bez hp
		przedmiot.add(new Kolce(this, moce[7][0], moce[7][1])); //nie moga sie pojawic jesli gra bez hp
	}

	void petlaGlowna(Ekran ekran) {

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			runda[2]--;
			for (int i = 0; i < waz.size(); i++) {
				waz.get(i).licznik();
			}

			for (int i = 0; i < przedmiot.size(); i++) {
				przedmiot.get(i).respawn();
			}

			ekran.repaint();

			if (runda[2] == 0) {
				break;
			}
		}
	}

}
