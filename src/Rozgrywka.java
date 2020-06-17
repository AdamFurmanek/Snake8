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

	public Rozgrywka(int szerokoscMapy, int wysokoscMapy, int ileGraczy, int ktoraRunda, int ileRund, int czas,
			int domyslnaSzybkosc, boolean czyHp, int[] kolory, String[] pseudonimy, boolean przenikanie,
			boolean odbijanie, int[][] mapa) {

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

		reset(ileGraczy);
	}

	void reset(int ileGraczy) {

		waz = new ArrayList<Waz>();
		przedmiot = new ArrayList<Przedmiot>();

		for (int i = 0; i < ileGraczy; i++) {
			waz.add(new Waz(this, i, kolory[i], pseudonimy[i]));
			waz.get(i).respawn();
		}

		przedmiot.add(new Serek(this, 100, 5));
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
