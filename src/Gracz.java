
public class Gracz {

	Serwer serwer;
	int gracz;
	int x, y;
	int x2, y2;
	int x3, y3;
	public char kierunek;

	Gracz(Serwer serwer, int gracz) {
		gracz++;
		this.gracz = gracz;
		this.serwer = serwer;

		// szuka swojej glowki
		for (int i = 0; i < serwer.wysokoscMapy; i++)
			for (int j = 0; j < serwer.szerokoscMapy; j++) {
				if (serwer.mapa[i][j].charAt(0) == '1' && serwer.mapa[i][j].charAt(2) == '0') {
					y = i;
					x = j;
				}
			}

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			if (x == serwer.szerokoscMapy - 1 && serwer.kierunek[gracz - 1] == 'd'
					|| x == 0 && serwer.kierunek[gracz - 1] == 'a'
					|| y == serwer.wysokoscMapy - 1 && serwer.kierunek[gracz - 1] == 's'
					|| y == 0 && serwer.kierunek[gracz - 1] == 'w'
					|| y>0&&serwer.mapa[y - 1][x].charAt(0) != '0' && serwer.kierunek[gracz - 1] == 'w'
					|| y<serwer.wysokoscMapy-1&&serwer.mapa[y + 1][x].charAt(0) != '0' && serwer.kierunek[gracz - 1] == 's'
					|| x>0&&serwer.mapa[y][x - 1].charAt(0) != '0' && serwer.kierunek[gracz - 1] == 'a'
					|| x<serwer.szerokoscMapy-1&&serwer.mapa[y][x + 1].charAt(0) != '0' && serwer.kierunek[gracz - 1] == 'd') {

				if (false) { // jesli cialo ma zniknac
					for (int i = 0; i < serwer.wysokoscMapy; i++)
						for (int j = 0; j < serwer.szerokoscMapy; j++) {
							if (serwer.mapa[i][j].charAt(0) == '1') {
								serwer.mapa[i][j] = new String("0");
							}
						}
				}

				serwer.okno.rozgrywka.repaint();
				break;
			}
			System.out.println("Mam glowke na " + y + " " + x + ", a moj kierunek to " + serwer.kierunek[gracz - 1]);
			System.out.println("komorka mapy: " + serwer.mapa[y][x]);

			x2 = x;
			y2 = y;
			x3 = x2;
			y3 = y2;

			if (serwer.kierunek[gracz - 1] == 's')
				y++;
			else if (serwer.kierunek[gracz - 1] == 'w')
				y--;
			else if (serwer.kierunek[gracz - 1] == 'a')
				x--;
			else if (serwer.kierunek[gracz - 1] == 'd')
				x++;
			serwer.mapa[y][x] = new String(gracz + "" + serwer.kierunek[gracz - 1] + "0");
			serwer.mapa[y2][x2] = new String(
					"" + serwer.mapa[y2][x2].charAt(0) + serwer.mapa[y2][x2].charAt(1) + serwer.kierunek[gracz - 1]);

			while (true) {
				x2 = x3;
				y2 = y3;
				if (serwer.mapa[y3][x3].charAt(1) == 'w')
					y3++;
				else if (serwer.mapa[y3][x3].charAt(1) == 's')
					y3--;
				else if (serwer.mapa[y3][x3].charAt(1) == 'a')
					x3++;
				else if (serwer.mapa[y3][x3].charAt(1) == 'd')
					x3--;

				if (serwer.mapa[y3][x3].charAt(1) == '0') {
					break;
				}
				// System.out.println("wspolrzedne " + y3 + " " + x3);
			}

			serwer.mapa[y3][x3] = new String("0");
			serwer.mapa[y2][x2] = new String("" + serwer.mapa[y2][x2].charAt(0) + '0' + serwer.mapa[y2][x2].charAt(2));

			serwer.okno.rozgrywka.repaint();

		}
	}
}
