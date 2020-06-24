import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Przedmiot {

	Okno okno;
	List<int[]> instancja = new ArrayList<int[]>();
	int czestotliwosc, licznik = 0, limit;
	int etykieta;

	void respawn() {
		licznik++;
		if (licznik == czestotliwosc) {
			licznik = 0;
			if (instancja.size() < limit) {
				int x = 0, y = 0;
				boolean zajete = true;
				int proba = 0;
				while (zajete && proba < 50) {
					proba++;
					x = okno.random.nextInt(okno.szerokoscMapy);
					y = okno.random.nextInt(okno.wysokoscMapy);

					zajete = false;

					// SPRAWDZENIE GRANIC
					if (x < 0 || y < 0 || x >= okno.szerokoscMapy || y >= okno.wysokoscMapy)
						zajete = true;

					// SPRAWDZENIE SCIAN NA MAPIE
					else if (okno.mapa[y][x] == 1)
						zajete = true;

					// SPRAWDZENIE KOLIZJI Z WEZAMI
					for (int i = 0; i < okno.waz.size(); i++)
						if (okno.waz.get(i).kolizja(y, x))
							zajete = true;

					// SPRAWDZENIE KOLIZJI Z INNYMI PRZEDMIOTAMI
					for (int i = 0; i < okno.przedmiot.size(); i++) {
						for (int j = 0; j < okno.przedmiot.get(i).instancja.size(); j++) {
							if (okno.przedmiot.get(i).instancja.get(j)[0] == y
									&& okno.przedmiot.get(i).instancja.get(j)[1] == x)
								zajete = true;
						}
					}
				}
				if (proba < 50) {
					instancja.add(0, new int[2]);
					instancja.get(0)[0] = y;
					instancja.get(0)[1] = x;
				}
			}
		}
	}

	void wykonanie(Waz waz, int j) {
	}
}

class Serek extends Przedmiot {

	public Serek(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 0;
	}

	void wykonanie(Waz waz, int j) {
		waz.punkty++;
		waz.wydluzenieCiala++;
		instancja.remove(j);
	}
}

class Duszek extends Przedmiot {

	public Duszek(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 1;
	}

	void wykonanie(Waz waz, int j) {
		waz.przenikanie = 500;
		instancja.remove(j);
	}
}

class Adrenalinka extends Przedmiot {

	public Adrenalinka(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 2;
	}

	void wykonanie(Waz waz, int j) {
		if (waz.szybkosc[0] > 3)
			waz.szybkosc[0] = okno.domyslnaSzybkosc - 3;
		waz.szybkosc[2] = 500;
		instancja.remove(j);
	}
}

class Slimaczek extends Przedmiot {

	public Slimaczek(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 3;
	}

	void wykonanie(Waz waz, int j) {
		waz.szybkosc[0] = okno.domyslnaSzybkosc + 3;
		waz.szybkosc[2] = 500;
		instancja.remove(j);
	}
}

class Odbijaczek extends Przedmiot {

	public Odbijaczek(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 4;
	}

	void wykonanie(Waz waz, int j) {
		waz.odbijanie = 500;
		instancja.remove(j);
	}
}

class Nozyczki extends Przedmiot {

	public Nozyczki(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 5;
	}

	void wykonanie(Waz waz, int j) {
		while (waz.cialo.size() > 3)
			waz.cialo.remove(waz.cialo.size() - 1);
		waz.cialo.get(waz.cialo.size() - 1)[2] = 4;

		instancja.remove(j);
	}
}

class Apteczka extends Przedmiot {

	public Apteczka(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 6;
	}

	void wykonanie(Waz waz, int j) {
		waz.hp += 50;
		if (waz.hp > 100)
			waz.hp = 100;
		instancja.remove(j);
	}
}

class Kolce extends Przedmiot {

	public Kolce(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 7;
	}

	void wykonanie(Waz waz, int j) {
		waz.hp -= 30;
		if (waz.hp <= 0)
			waz.smierc();
		instancja.remove(j);
	}
}

class Duszek2 extends Przedmiot {

	public Duszek2(Okno okno, int czestotliwosc, int limit) {
		this.okno = okno;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 8;
	}

	void wykonanie(Waz waz, int j) {
		waz.przechodzenie = 500;
		instancja.remove(j);
	}
}