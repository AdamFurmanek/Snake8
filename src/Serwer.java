import java.awt.event.KeyEvent;

public class Serwer {

	Okno okno;
	Rozgrywka rozgrywka;

	String[][] mapa;
	int szerokoscMapy;
	int wysokoscMapy;
	int ileGraczy;
	char klawisze[][] = { { 'w', 'a', 's', 'd' }, { 't','f','g','h' }, { 'j', 'k', 'k', 'l' },
			{ '5', '1', '2', '3' }, { 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x' } };
	char[] kierunek;

	public Serwer(Okno okno, int szerokoscMapy, int wysokoscMapy, int ileGraczy) throws Exception {

		this.ileGraczy = ileGraczy;
		kierunek = new char[ileGraczy];
		kierunek[0] = 'd';
		kierunek[1] = 'd';
		this.okno = okno;
		this.szerokoscMapy = szerokoscMapy;
		this.wysokoscMapy = wysokoscMapy;
		mapa = new String[wysokoscMapy][szerokoscMapy];
		reset();

		for (int i = 0; i < ileGraczy; i++)
			nowyGracz(i);
	}

	void nowyGracz(int i) {

		Thread thread = new Thread(() -> {
			new Gracz(this, i);
		});
		thread.start();
	}

	public void reset() {
		for (int i = 0; i < wysokoscMapy; i++)
			for (int j = 0; j < szerokoscMapy; j++) {
				mapa[i][j] = new String("000");
			}
		mapa[4][7] = new String("10s");
		mapa[5][7] = new String("1sd");
		mapa[5][8] = new String("1ds");
		mapa[6][8] = new String("1s0");
		
		mapa[10][13] = new String("20s");
		mapa[11][13] = new String("2sd");
		mapa[11][14] = new String("2ds");
		mapa[12][14] = new String("2s0");
	}

	public void steruj(char znak) {
		int flaga = 0;
		for (int i = 0; i < 8 && flaga == 0; i++)
			for (int j = 0; j < 4 && flaga == 0; j++) {
				if (klawisze[i][j] == znak) {

					for (int k = 0; k < wysokoscMapy && flaga == 0; k++)
						for (int l = 0; l < szerokoscMapy && flaga == 0; l++) {
							if (mapa[k][l].charAt(0) == Integer.toString(i + 1).charAt(0)
									&& mapa[k][l].charAt(2) == '0') {
								System.out.println(k + " " + l);

								if (!(j==2&&mapa[k][l].charAt(1)==klawisze[0][0]
										|| j==0&&mapa[k][l].charAt(1)==klawisze[0][2]
										|| j==1&&mapa[k][l].charAt(1)==klawisze[0][3]
										|| j==3&&mapa[k][l].charAt(1)==klawisze[0][1])) {

									kierunek[i] = klawisze[0][j];
									System.out.println(znak + " " + i);
									flaga = 1;
								}
							}
						}
				}
			}
	}
}
