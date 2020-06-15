import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Waz {

	Rozgrywka rozgrywka;

	List<int[]> cialo;

	int flagaSynchronizacjiSmierci = 0; // Flaga potrzebna do wykonania smierci weza, dopiero po przejsciu licznika dla
										// wszystkich wezow. Zapobiega to sytuacji w ktorej gdy dwa weze uderza czolowo
										// (czyli oba powinny umrzec), jeden zostaje usuniety od razu, dzieki czemu
										// drugi nie widzi ze stal w miejscu z innym wezem i nie wykonuje smierci.
	int flagaZmianyKierunku = 4;
	int[] szybkosc;
	int numer, kolor;
	int hp = 100, punkty = 0, zgony = 0;
	// 0 - gora; 1 - lewo; 2 - dol; 3 - prawo; 4 - stop
	int kierunek;
	int przenikanie = -1, odbijanie = -1; // -2 ODBIERA 10hp; -1 NIE ROBI NIC; 0 ZABIJA; >0 NIC NIE ROBI PRZEZ PODANY
											// CZAS
	String pseudonim;

	public Waz(Rozgrywka rozgrywka, int numer, int kolor) {

		pseudonim = new String("Gracz " + numer);
		this.kolor = kolor;
		this.rozgrywka = rozgrywka;
		this.numer = numer;
		cialo = new ArrayList<int[]>();
		szybkosc = new int[3];
		szybkosc[0] = 4;
		szybkosc[1] = 0;
		szybkosc[2] = -1;

	}

	void krok() {

		// BLOK OBECNEJ KOLIZJI
		for (int i = 0; i < rozgrywka.waz.size(); i++) {
			if (i != numer && rozgrywka.waz.get(i).kolizja(cialo.get(0)[1], cialo.get(0)[0]))
				// BLOK REAKCJI NA UDERZENIE PRZECIWNIKA
				if (przenikanie == 0) {
					// SPRAWDZENIE CZY TO BYLA GLOWA, CZY CIALO OBCEGO WEZA
					if (kolizja(rozgrywka.waz.get(i).cialo.get(0)[1], rozgrywka.waz.get(i).cialo.get(0)[0])) {
						if (rozgrywka.waz.get(i).przenikanie == 0)
							rozgrywka.waz.get(i).flagaSynchronizacjiSmierci = 1;
						else if (rozgrywka.waz.get(i).przenikanie == -2)
							rozgrywka.waz.get(i).hp -= 10;
					}
					flagaSynchronizacjiSmierci = 1;
					return;
				} else if (przenikanie == -2)
					hp -= 10;
		}

		// BLOK PRZYSZLEJ KOLIZJI
		int x = cialo.get(0)[1], y = cialo.get(0)[0];

		cialo.get(0)[3] = kierunek;
		switch (kierunek) {
		case 0:
			y--;
			break;
		case 1:
			x--;
			break;
		case 2:
			y++;
			break;
		case 3:
			x++;
			break;
		}
		if (x < 0 || y < 0 || x >= rozgrywka.szerokoscMapy || y >= rozgrywka.wysokoscMapy
				|| rozgrywka.mapa[y][x] == 1) {
			if (odbijanie == 0) {
				flagaSynchronizacjiSmierci = 1;
				return;
			} else {
				if (kierunek == 0) {
					flagaZmianyKierunku = 2;
					kierunek = 2;
				} else if (kierunek == 2) {
					flagaZmianyKierunku = 0;
					kierunek = 0;
				} else if (kierunek == 1) {
					flagaZmianyKierunku = 3;
					kierunek = 3;
				} else if (kierunek == 3) {
					flagaZmianyKierunku = 1;
					kierunek = 1;
				}
				if (odbijanie == -2)
					hp -= 10;
			}

		}

		// WYKONANIE RUCHU
		else if (kierunek != 4) {

			cialo.add(0, new int[4]);
			cialo.get(0)[0] = y;
			cialo.get(0)[1] = x;
			cialo.get(0)[2] = kierunek;
			cialo.get(0)[3] = 4;

			cialo.remove(cialo.size() - 1);
			cialo.get(cialo.size() - 1)[2] = 4;
		}
	}

	void smierc() {
		zgony++;
		cialo.clear();
		respawn();
	}

	void respawn() {
		Random random = new Random();
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
		boolean zajete = true;
		int ulozenieCiala = 0;

		while (zajete) {
			// WYLOSOWANIE WSPOLRZEDNYCH GLOWKI WEZA
			x1 = random.nextInt(rozgrywka.szerokoscMapy);
			y1 = random.nextInt(rozgrywka.wysokoscMapy);
			x2 = x1;
			y2 = y1;
			x3 = x1;
			y3 = y1;

			// WYLOSOWANIE KIERUNKU CIALA WEZA
			ulozenieCiala = random.nextInt(4);
			switch (ulozenieCiala) {
			case 0:
				y2 = y1 + 1;
				y3 = y2 + 1;
				break;
			case 1:
				x2 = x1 + 1;
				x3 = x2 + 1;
				break;
			case 2:
				y2 = y1 - 1;
				y3 = y2 - 1;
				break;
			case 3:
				x2 = x1 - 1;
				x3 = x2 - 1;
				break;
			}

			zajete = false;

			// SPRAWDZENIE GRANIC
			if (x3 < 0 || y3 < 0 || x3 >= rozgrywka.szerokoscMapy || y3 >= rozgrywka.wysokoscMapy)
				zajete = true;

			// SPRAWDZENIE SCIAN NA MAPIE
			else if (rozgrywka.mapa[y1][x1] == 1 || rozgrywka.mapa[y2][x2] == 1 || rozgrywka.mapa[y3][x3] == 1)
				zajete = true;

			// SPRAWDZENIE KOLIZJI Z INNYMI WEZAMI
			for (int i = 0; i < rozgrywka.waz.size(); i++)
				if (rozgrywka.waz.get(i).kolizja(x1, y1) || rozgrywka.waz.get(i).kolizja(x2, y2)
						|| rozgrywka.waz.get(i).kolizja(x3, y3))
					zajete = true;

			// SPRAWDZENIE KOLIZJI Z PRZEDMIOTAMI
//			for(int i=0;i<rozgrywka.przedmiot.size();i++) {
//			
//			}
		}

		kierunek = 4;
		flagaZmianyKierunku = 4;

		cialo.add(0, new int[4]);
		cialo.get(0)[0] = y3;
		cialo.get(0)[1] = x3;
		cialo.get(0)[2] = 4;
		cialo.get(0)[3] = ulozenieCiala;

		cialo.add(0, new int[4]);
		cialo.get(0)[0] = y2;
		cialo.get(0)[1] = x2;
		cialo.get(0)[2] = ulozenieCiala;
		cialo.get(0)[3] = ulozenieCiala;

		cialo.add(0, new int[4]);
		cialo.get(0)[0] = y1;
		cialo.get(0)[1] = x1;
		cialo.get(0)[2] = ulozenieCiala;
		cialo.get(0)[3] = 4;
	}

	void licznik() {

		// WYKONANIE ZAMOWIONEJ SMIERCI PRZEZ FLAGE
		if (flagaSynchronizacjiSmierci == 1) {
			flagaSynchronizacjiSmierci = 0;
			smierc();
			return;
		}

		// WYKONANIE RUCHU CO OKRESLONY SKOK CZASU (SZYBKOSC)
		szybkosc[1]++;
		if (szybkosc[0] <= szybkosc[1]) {
			// WYKONANIE ZAMOWIONEJ ZMIANY KIERUNKU
			if (flagaZmianyKierunku != 4 && (kierunek == 4 || przenikanie != 0
					|| (kierunek != flagaZmianyKierunku + 2 && kierunek + 2 != flagaZmianyKierunku)))
				kierunek = flagaZmianyKierunku;
			szybkosc[1] = 0;
			krok();
		}

		// ODLICZANIE POZOSTALEGO CZASU DLA SPOWONIENIA/PRZYSPIESZENIA
		if (szybkosc[2] == 0)
			szybkosc[0] = rozgrywka.domyslnaSzybkosc;
		else if (szybkosc[2] > 0)
			szybkosc[2]--;

		// ODLICZANIE POZOSTALEGO CZASU DLA PRZENIKANIA
		if (przenikanie > 0)
			przenikanie--;

		// ODLICZANIE POZOSTALEGO CZASU DLA ODBIJANIA
		if (odbijanie > 0)
			odbijanie--;

	}

	boolean kolizja(int x, int y) {
		for (int i = 0; i < cialo.size(); i++)
			if (cialo.get(i)[0] == y && cialo.get(i)[1] == x)
				return true;
		return false;
	}

	boolean kolizjaGlowy(int x, int y) {
		if (cialo.get(0)[0] == y && cialo.get(0)[1] == x)
			return true;
		return false;
	}
}
