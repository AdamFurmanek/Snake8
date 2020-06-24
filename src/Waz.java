import java.util.ArrayList;
import java.util.List;

public class Waz {

	Okno okno;
	List<int[]> cialo = new ArrayList<int[]>();
	String pseudonim;
	int numer, kolor;
	int kierunek, wydluzenieCiala;
	int hp = 100, punkty = 0, zgony = 0;
	int[] szybkosc = { 0, 0, 0 }; // 0 szybkosc; 1 licznik 2; czas mocy
	int przenikanie = 0, odbijanie = 0, przechodzenie = 0;

	public Waz(Okno okno, int numer, int kolor, String pseudonim) {
		this.okno = okno;
		this.numer = numer;
		this.kolor = kolor;
		this.pseudonim = pseudonim;
		szybkosc[0] = okno.domyslnaSzybkosc;
	}

	void takt() {

		// WYKONANIE RUCHU CO OKRESLONY SKOK CZASU (SZYBKOSC)
		szybkosc[1]++;
		if (szybkosc[0] <= szybkosc[1]) {
			szybkosc[1] = 0;
			krok();
		}

		// ODLICZANIE POZOSTALEGO CZASU DLA SPOWONIENIA/PRZYSPIESZENIA
		if (szybkosc[2] == 0)
			szybkosc[0] = okno.domyslnaSzybkosc;
		else if (szybkosc[2] > 0)
			szybkosc[2]--;

		// ODLICZANIE POZOSTALEGO CZASU DLA PRZENIKANIA
		if (przenikanie > 0)
			przenikanie--;

		// ODLICZANIE POZOSTALEGO CZASU DLA ODBIJANIA
		if (odbijanie > 0)
			odbijanie--;
		
		// ODLICZANIE POZOSTALEGO CZASU DLA PRZECHODZENIA
		if (przechodzenie > 0)
			przechodzenie--;
	}

	void krok() {

		// KOLIZJA Z WEZAMI
		for (int i = 0; i < okno.waz.size(); i++) {
			if (i == numer && kolizja() || i != numer && okno.waz.get(i).kolizja(this)) {
				if (!okno.stalePrzenikanie && przenikanie <= 0) {
					if (okno.wlaczHP) {
						hp -= 20;
						if (hp <= 0) {
							smierc();
							return;
						}
					} else {
						smierc();
						return;
					}
				}
			}
		}

		// KONTAKT Z PRZEDMIOTAMI
		for (int i = 0; i < okno.przedmiot.size(); i++) {
			for (int j = 0; j < okno.przedmiot.get(i).instancja.size(); j++) {
				if (kolizja(okno.przedmiot.get(i).instancja.get(j)[0], okno.przedmiot.get(i).instancja.get(j)[1])) {
					okno.przedmiot.get(i).wykonanie(this, j);
				}
			}
		}

		// OBLICZENIE KOLEJNEGO POLA
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

		// SPRAWDZENIE CZY WCHODZI NA SCIANE
		if (x < 0 || y < 0 || x >= okno.szerokoscMapy || y >= okno.wysokoscMapy || okno.mapa[y][x] == 1) {

			// WAZ PRZEJDZIE JESLI MA WLACZONE PRZECHODZENIE
			if (okno.stalePrzechodzenie || przechodzenie > 0) {
				if (x < 0)
					x = okno.szerokoscMapy - 1;
				else if (x >= okno.szerokoscMapy)
					x = 0;
				else if (y < 0)
					y = okno.wysokoscMapy - 1;
				else if (y >= okno.wysokoscMapy)
					y = 0;
			} 
			// WAZ SIE ODBIJE JESLI MA WLACZONE ODBICIE LUB HP JEST WLACZONE
			else if (okno.staleOdbijanie || odbijanie > 0 || okno.wlaczHP) {
				if (kierunek == 0)
					kierunek = 2;
				else if (kierunek == 2)
					kierunek = 0;
				else if (kierunek == 1)
					kierunek = 3;
				else if (kierunek == 3)
					kierunek = 1;
				//ZABIERA HP JESLI WLACZONE
				if (okno.wlaczHP && !okno.staleOdbijanie && odbijanie == 0) {
					hp -= 20;
					if (hp <= 0) {
						smierc();
						return;
					}
				}
				return;
			}

			// WAZ UMRZE JESLI SIE NIE ODBIJA ANI NIE PRZECHODZI
			else {
				smierc();
				return;
			}

		}
		//WYKONANIE RUCHU
		if (kierunek != 4) {

			cialo.add(0, new int[4]);
			cialo.get(0)[0] = y;
			cialo.get(0)[1] = x;
			cialo.get(0)[2] = kierunek;
			cialo.get(0)[3] = 4;

			if (wydluzenieCiala > 0)
				wydluzenieCiala--;
			else {
				cialo.remove(cialo.size() - 1);
				cialo.get(cialo.size() - 1)[2] = 4;
			}
		}
	}

	void respawn() {
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
		int ulozenieCiala = 0;
		boolean zajete = true;
		int proba=0;
		
		// SZUKANIE WOLNEGO MIEJSCA NA MAPIE
		while (zajete&&proba<50) {
			proba++;
			// WYLOSOWANIE WSPOLRZEDNYCH GLOWKI WEZA
			x1 = okno.random.nextInt(okno.szerokoscMapy);
			y1 = okno.random.nextInt(okno.wysokoscMapy);
			x2 = x1;
			y2 = y1;
			x3 = x1;
			y3 = y1;

			// WYLOSOWANIE KIERUNKU CIALA WEZA
			ulozenieCiala = okno.random.nextInt(4);
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
			if (x3 < 0 || y3 < 0 || x3 >= okno.szerokoscMapy || y3 >= okno.wysokoscMapy)
				zajete = true;

			// SPRAWDZENIE SCIAN NA MAPIE
			else if (okno.mapa[y1][x1] == 1 || okno.mapa[y2][x2] == 1 || okno.mapa[y3][x3] == 1)
				zajete = true;

			// SPRAWDZENIE KOLIZJI Z INNYMI WEZAMI
			for (int i = 0; i < okno.waz.size(); i++)
				if (okno.waz.get(i).kolizja(y1, x1) || okno.waz.get(i).kolizja(y2, x2)
						|| okno.waz.get(i).kolizja(y3, x3))
					zajete = true;

			// SPRAWDZENIE KOLIZJI Z PRZEDMIOTAMI
			for (int i = 0; i < okno.przedmiot.size(); i++) {
				for (int j = 0; j < okno.przedmiot.get(i).instancja.size(); j++) {
					if (kolizja(okno.przedmiot.get(i).instancja.get(j)[0], okno.przedmiot.get(i).instancja.get(j)[1]))
						zajete = true;
				}
			}
		}

		kierunek = 4;

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

		hp = 100;

	}

	void smierc() {
		System.out.println(okno.limitZgonow);
		hp = 0;
		zgony++;
		if(okno.limitZgonow>zgony||okno.limitZgonow==0) {
			cialo.clear();
			respawn();
		}
		else if(!okno.zostawianieCiala)
			cialo.clear();
	}

	// SPRAWDZENIE KOLIZJI WYWOLANE PRZEZ OBCEGO WEZA
	boolean kolizja(Waz waz) {
		
		//JESLI WAZ NIE MA CIALA
		if(cialo.size()==0)
			return false;

		// JESLI ZDERZYL SIE Z GLOWA
		
		if (cialo.get(0)[0] == waz.cialo.get(0)[0] && cialo.get(0)[1] == waz.cialo.get(0)[1]) {
			if (!okno.stalePrzenikanie && przenikanie <= 0) {
				if (okno.wlaczHP) {
					hp -= 20;
					if (hp <= 0) {
						smierc();
					}
				} else {
					smierc();
				}
			}
			return true;
		}

		// JESLI ZDERZYL SIE Z CIALEM
		for (int i = 1; i < cialo.size(); i++)
			if (cialo.get(i)[0] == waz.cialo.get(0)[0] && cialo.get(i)[1] == waz.cialo.get(0)[1])
				return true;

		// JESLI SIE NIE ZDERZYL
		return false;
	}

	// SPRAWDZENIE KOLIZJI Z WLASNYM CIALEM
	boolean kolizja() {

		// JESLI DOSZLO DO ZDERZENIA
		for (int i = 1; i < cialo.size(); i++)
			if (cialo.get(i)[0] == cialo.get(0)[0] && cialo.get(i)[1] == cialo.get(0)[1])
				return true;

		// JESLI NIE DOSZLO DO ZDERZENIA
		return false;
	}

	// SPRAWDZENIE CZY ZAJMUJE PODANE WSPOLRZEDNE
	boolean kolizja(int y, int x) {

		// JESLI ZAJMUJE
		for (int i = 0; i < cialo.size(); i++)
			if (cialo.get(i)[0] == y && cialo.get(i)[1] == x)
				return true;

		// JESLI NIE ZAJMUJE
		return false;
	}

}
