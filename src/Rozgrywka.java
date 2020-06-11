import java.util.ArrayList;
import java.util.List;

public class Rozgrywka {

	Okno okno;
	Ekran ekran;

	int ileGraczy;
	int szerokoscMapy, wysokoscMapy;
	char[][][] mapa;
	Waz[] waz;

	int[][] szybkoscWeza;
	int[][] pozycjaWeza;

	public Rozgrywka(Okno okno, int szerokoscMapy, int wysokoscMapy, int ileGraczy) {

		this.okno = okno;
		this.szerokoscMapy = szerokoscMapy;
		this.wysokoscMapy = wysokoscMapy;
		this.ileGraczy = ileGraczy;

		ekran = new Ekran(this);
		okno.add(ekran);

		reset();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		petlaGlowna();

	}

	void petlaGlowna() {

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			for (int i = 0; i < ileGraczy; i++) {
				waz[i].szybkoscWeza[1]++;
				if (waz[i].szybkoscWeza[0] <= waz[i].szybkoscWeza[1]) {
					waz[i].szybkoscWeza[1] = 0;
					waz[i].zrobKrok();
				}
			}
			ekran.repaint();
		}
	}

	void reset() {

		mapa = new char[wysokoscMapy][szerokoscMapy][3];

		for (int i = 0; i < wysokoscMapy; i++) {
			for (int j = 0; j < szerokoscMapy; j++) {
				mapa[i][j][0] = '0';
				mapa[i][j][1] = '0';
				mapa[i][j][2] = '0';
			}
		}

		waz = new Waz[ileGraczy];
		for (int i = 0; i < ileGraczy; i++) {
			waz[i] = new Waz(this, i, i + 1, 1, true, 2, true, true, true);
			waz[i].respawn();
		}

	}
}
