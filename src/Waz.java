import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Waz {

	List<int[]> cialoWeza;
	int[] szybkoscWeza;
	boolean czyWazZyje;
	boolean czyWazZostawiaCialo;
	int czyWazPrzenikaInnych;
	boolean czyWazPrzenikaSiebie;
	boolean czyWazMozeCofnac;
	boolean czyZmartwychwstaje;
	int numerWeza;
	int kierunekWeza;
	int kolorWeza;
	int reakcjaNaSciane; // 0-umiera,1-odbija sie,2-przenika
	Rozgrywka rozgrywka;
	int punkty = 0;
	int smierc = 0;
	int hp = 100;

	public Waz(Rozgrywka rozgrywka, int numerWeza, int kolorWeza, int reakcjaNaSciane, boolean czyWazZostawiaCialo,
			int czyWazPrzenikaInnych, boolean czyWazPrzenikaSiebie, boolean czyWazMozeCofnac,
			boolean czyZmartwychwstaje) {

		this.czyZmartwychwstaje = czyZmartwychwstaje;
		this.czyWazMozeCofnac = czyWazMozeCofnac;
		this.czyWazPrzenikaSiebie = czyWazPrzenikaSiebie;
		if (!czyWazPrzenikaSiebie)
			czyWazMozeCofnac = false;
		this.numerWeza = numerWeza;
		this.czyWazPrzenikaInnych = czyWazPrzenikaInnych; // 0 - przenika, 1 - zabiera hp, 2 - umiera
		this.reakcjaNaSciane = reakcjaNaSciane;
		this.kolorWeza = kolorWeza;
		this.rozgrywka = rozgrywka;
		szybkoscWeza = new int[2];
		szybkoscWeza[0] = 5;
		szybkoscWeza[1] = 0;
		czyWazZyje = true;
		this.czyWazZostawiaCialo = czyWazZostawiaCialo;

		cialoWeza = new ArrayList<int[]>();
	}

	public void zrobKrok() {

		if (czyWazZyje && kierunekWeza != 4) {

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
			else {
				if (reakcjaNaSciane == 2) { // mozna przechodzic przez sciany
					if (kierunekWeza == 2 && cialoWeza.get(0)[0] == rozgrywka.wysokoscMapy - 1)
						cialoWeza.get(0)[0] = 0;
					else if (kierunekWeza == 0 && cialoWeza.get(0)[0] == 0)
						cialoWeza.get(0)[0] = rozgrywka.wysokoscMapy - 1;
					else if (kierunekWeza == 3 && cialoWeza.get(0)[1] == rozgrywka.szerokoscMapy - 1)
						cialoWeza.get(0)[1] = 0;
					else if (kierunekWeza == 1 && cialoWeza.get(0)[1] == 0)
						cialoWeza.get(0)[1] = rozgrywka.szerokoscMapy - 1;
				} else if (reakcjaNaSciane == 1) { // odbicie

					kierunekWeza += 2;
					if (kierunekWeza > 3)
						kierunekWeza -= 4;
				} else {
					smiercWeza();
					return;
				}
			}
			if (czyWazPrzenikaInnych > 0) {
				for (int i = 0; i < rozgrywka.ileGraczy; i++) {
					if (numerWeza != i) {
						int y = cialoWeza.get(0)[0];
						int x = cialoWeza.get(0)[1];
						for (int j = 0; j < rozgrywka.waz[i].cialoWeza.size(); j++)
							if (rozgrywka.waz[i].cialoWeza.get(j)[0] == y
									&& rozgrywka.waz[i].cialoWeza.get(j)[1] == x) {
								if (czyWazPrzenikaInnych == 2) {
									smiercWeza();
									return;
								} else if (czyWazPrzenikaInnych == 1) {
									hp -= 10;
									if (hp == 0) {
										smiercWeza();
										return;
									}
								}
							}
					}
				}
			}
			if (!czyWazPrzenikaSiebie) {
				int y = cialoWeza.get(0)[0];
				int x = cialoWeza.get(0)[1];
				if (kierunekWeza == 0)
					y--;
				else if (kierunekWeza == 1)
					x--;
				else if (kierunekWeza == 2)
					y++;
				else if (kierunekWeza == 3)
					x++;
				for (int i = 1; i < cialoWeza.size(); i++)
					if (cialoWeza.get(i)[0] == y && cialoWeza.get(i)[1] == x) {
						smiercWeza();
						return;
					}
			}

			cialoWeza.remove(cialoWeza.size() - 1);
		}
	}

	public void respawn() {
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0;
		int kierunek;
		boolean wolne = false;
		Random random = new Random();
		while (!wolne) {
			x1 = random.nextInt(rozgrywka.wysokoscMapy);
			y1 = random.nextInt(rozgrywka.szerokoscMapy);
			kierunek = random.nextInt(4);
			if (kierunek == 0) {
				y2 = y1 - 1;
				y3 = y2 - 1;
				x2 = x1;
				x3 = x2;
			} else if (kierunek == 2) {
				y2 = y1 + 1;
				y3 = y2 + 1;
				x2 = x1;
				x3 = x2;
			} else if (kierunek == 1) {
				y2 = y1;
				y3 = y2;
				x2 = x1 - 1;
				x3 = x2 - 1;
			} else if (kierunek == 3) {
				y2 = y1;
				y3 = y2;
				x2 = x1 + 1;
				x3 = x2 + 1;
			}

			if (x1 >= 0 && x1 < rozgrywka.szerokoscMapy && x2 >= 0 && x2 < rozgrywka.szerokoscMapy && x3 >= 0
					&& x3 < rozgrywka.szerokoscMapy && y1 >= 0 && y1 < rozgrywka.wysokoscMapy && y2 >= 0
					&& y2 < rozgrywka.wysokoscMapy && y3 >= 0 && y3 < rozgrywka.wysokoscMapy)
				wolne = true;
			for (int i = 0; i < rozgrywka.ileGraczy; i++)
				if (numerWeza != i && rozgrywka.waz[i] != null) {
					System.out.println("Jestem wezem " + numerWeza + ", sprawdzam weza numer " + i);

					for (int j = 0; j < rozgrywka.waz[i].cialoWeza.size(); j++) {
						y4 = rozgrywka.waz[i].cialoWeza.get(j)[0];
						x4 = rozgrywka.waz[i].cialoWeza.get(j)[1];
						System.out.println("Obcy waz ma: " + x4 + " " + y4 + ", a ja mam: " + x1 + " " + y1 + ", " + x2
								+ " " + y2 + ", " + x3 + " " + y3);
						if (x1 == x4 && y1 == y4 || x2 == x4 && y2 == y4 || x3 == x4 && y3 == y4) {
							wolne = false;
						}
					}
				}
		}

		kierunekWeza = 4;

		cialoWeza.add(0, new int[2]);
		cialoWeza.get(0)[0] = y1;
		cialoWeza.get(0)[1] = x1;

		cialoWeza.add(0, new int[2]);
		cialoWeza.get(0)[0] = y2;
		cialoWeza.get(0)[1] = x2;

		cialoWeza.add(0, new int[2]);
		cialoWeza.get(0)[0] = y3;
		cialoWeza.get(0)[1] = x3;

	}

	public void smiercWeza() {
		cialoWeza.remove(cialoWeza.size() - 1);
		smierc++;
		czyWazZyje = false;
		if (!czyWazZostawiaCialo || czyZmartwychwstaje) {
			cialoWeza.clear();
		}
		if (czyZmartwychwstaje) {
			respawn();
			czyWazZyje = true;
		}
	}

	@Override
	public String toString() {

		return new String(
				"Kierunek: " + kierunekWeza + ", szybkosc: " + szybkoscWeza[0] + ", licznik: " + szybkoscWeza[1]);
	}
}
