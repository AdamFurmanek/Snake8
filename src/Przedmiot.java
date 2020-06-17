import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Przedmiot {

	Rozgrywka rozgrywka;
	List<int[]> instancja = new ArrayList<int[]>();
	int czestotliwosc, licznik = 0;
	int limit;
	int etykieta;

	void respawn() {
		licznik++;
		if (licznik == czestotliwosc) {
			licznik = 0;
			if (instancja.size() < limit) {
				Random random = new Random();
				int x = 0, y = 0;
				boolean zajete = true;
				while (zajete) {
					x = random.nextInt(rozgrywka.szerokoscMapy);
					y = random.nextInt(rozgrywka.wysokoscMapy);

					zajete = false;

					// SPRAWDZENIE GRANIC
					if (x < 0 || y < 0 || x >= rozgrywka.szerokoscMapy || y >= rozgrywka.wysokoscMapy)
						zajete = true;

					// SPRAWDZENIE SCIAN NA MAPIE
					else if (rozgrywka.mapa[y][x] == 1)
						zajete = true;

					// SPRAWDZENIE KOLIZJI Z WEZAMI
					for (int i = 0; i < rozgrywka.waz.size(); i++)
						if (rozgrywka.waz.get(i).kolizja(x, y))
							zajete = true;

					// SPRAWDZENIE KOLIZJI Z INNYMI PRZEDMIOTAMI
					for (int i = 0; i < rozgrywka.przedmiot.size(); i++) {
						for (int j = 0; j < rozgrywka.przedmiot.get(i).instancja.size(); j++) {
							if (rozgrywka.przedmiot.get(i).instancja.get(j)[1] == x
									&& rozgrywka.przedmiot.get(i).instancja.get(j)[0] == y)
								zajete = true;
						}
					}
				}
				instancja.add(0, new int[2]);
				instancja.get(0)[0] = y;
				instancja.get(0)[1] = x;
			}
		}
	}

	void wykonanie(Waz waz, int j) {

	}

}

class Serek extends Przedmiot {

	public Serek(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 0;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.punkty++;
		waz.flagaWydluzeniaCiala = 1;
		instancja.remove(j);
	}

}

class Duszek extends Przedmiot {

	public Duszek(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 1;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.przenikanie = 500;
		instancja.remove(j);
	}

}

class Adrenalinka extends Przedmiot {

	public Adrenalinka(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 2;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		if (waz.szybkosc[0] > 3)
			waz.szybkosc[0] = rozgrywka.domyslnaSzybkosc - 3;
		waz.szybkosc[2] = 500;
		instancja.remove(j);
	}

}

class Slimaczek extends Przedmiot {

	public Slimaczek(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 3;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.szybkosc[0] = rozgrywka.domyslnaSzybkosc + 3;
		waz.szybkosc[2] = 500;
		instancja.remove(j);
	}

}

class Odbijaczek extends Przedmiot {

	public Odbijaczek(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 4;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.odbijanie = 500;
		instancja.remove(j);
	}

}

class Nozyczki extends Przedmiot {

	public Nozyczki(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 5;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		while (waz.cialo.size() > 3)
			waz.cialo.remove(waz.cialo.size() - 1);
		waz.cialo.get(waz.cialo.size() - 1)[2] = 4;

		instancja.remove(j);
	}

}

class Apteczka extends Przedmiot {

	public Apteczka(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 6;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.hp = 100;
		instancja.remove(j);
	}

}

class Kolce extends Przedmiot {

	public Kolce(Rozgrywka rozgrywka, int czestotliwosc, int limit) {
		this.rozgrywka = rozgrywka;
		this.czestotliwosc = czestotliwosc;
		this.limit = limit;
		etykieta = 7;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.hp -= 30;
		instancja.remove(j);
	}

}
