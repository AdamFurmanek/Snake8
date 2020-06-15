import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rozgrywka {

	Ekran ekran;
	Okno okno;

	List<Waz> waz;
	List<Przedmiot> przedmiot;

	int domyslnaSzybkosc=10;
	int szerokoscMapy, wysokoscMapy;
	int[] runda;
	int[][] mapa;

	public Rozgrywka(Okno okno, int szerokoscMapy, int wysokoscMapy, int ileGraczy, int ktoraRunda, int ileRund, int czas) {

		this.szerokoscMapy = szerokoscMapy;
		this.wysokoscMapy = wysokoscMapy;

		reset(ileGraczy);
		runda = new int[3];
		runda[0]=ktoraRunda;
		runda[1]=ileRund;
		runda[2]=czas;
		
		ekran = new Ekran(this);
		this.okno = okno;
		okno.add(ekran);

		petlaGlowna();

		System.out.println("koniec");
	}

	void reset(int ileGraczy) {

		// wygenerowanie losowej mapy
		Random random = new Random();
		mapa = new int[wysokoscMapy][szerokoscMapy];
		for (int i = 0; i < wysokoscMapy; i++)
			for (int j = 0; j < szerokoscMapy; j++)
				mapa[i][j] = random.nextInt(10);

		waz = new ArrayList<Waz>();
		for (int i = 0; i < ileGraczy; i++) {
			waz.add(new Waz(this, i, i));
			waz.get(i).respawn();
		}
	}

	void petlaGlowna() {

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			runda[2]--;
			for (int i = 0; i < waz.size(); i++) {
				waz.get(i).licznik();
			}
			ekran.repaint();
			
			if(runda[2]<=0) {
				break;
			}
		}
	}

}
