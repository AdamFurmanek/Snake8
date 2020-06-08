import java.util.ArrayList;
import java.util.List;

public class Waz {

	List<int[]> cialoWeza;
	int[] szybkoscWeza;
	int kierunekWeza;
	int kolor;
	Rozgrywka rozgrywka;
	

	public Waz(Rozgrywka rozgrywka) {
		kolor=1;
		this.rozgrywka = rozgrywka;
		szybkoscWeza = new int[2];
		szybkoscWeza[0] = 5;
		szybkoscWeza[1] = 0;

		kierunekWeza = 2;

		cialoWeza = new ArrayList<int[]>();
	}

	public void zrobKrok() {

		cialoWeza.add(0, new int[2]);
		cialoWeza.get(0)[0] = cialoWeza.get(1)[0];
		cialoWeza.get(0)[1] = cialoWeza.get(1)[1];

		if (kierunekWeza == 2 && cialoWeza.get(0)[0] < rozgrywka.wysokoscMapy - 1)
			cialoWeza.get(0)[0]++;
		else if (kierunekWeza == 0 && cialoWeza.get(0)[0] > 0)
			cialoWeza.get(0)[0]--;
		else if (kierunekWeza == 3 && cialoWeza.get(0)[1] < rozgrywka.szerokoscMapy - 1)
			cialoWeza.get(0)[1]++;
		else if (kierunekWeza == 1 && cialoWeza.get(0)[1] > 0)
			cialoWeza.get(0)[1]--;
		else
			szybkoscWeza[0] = 100000000;

		cialoWeza.remove(cialoWeza.size() - 1);

	}

	@Override
	public String toString() {

		return new String(
				"Kierunek: " + kierunekWeza + ", szybkosc: " + szybkoscWeza[0] + ", licznik: " + szybkoscWeza[1]);
	}
}
