
public class Serwer {

	Okno okno;
	Rozgrywka rozgrywka;
	
	String[][] mapa;
	int szerokoscMapy;
	int wysokoscMapy;
	int ileGraczy;
	char klawisze[][]= {{'w','a','s','d'},{'4','3','e','r'},{'6','5','t','y'},{'8','7','u','i'},{'0','9','o','p'},{'g','f','v','b'},{'j','h','n','m'},{'l','k',',','.'}};
	char[] kierunek;
	
	public Serwer(Okno okno, int szerokoscMapy, int wysokoscMapy, int ileGraczy) throws Exception {
		
		this.ileGraczy=ileGraczy;
		kierunek = new char[ileGraczy];
		kierunek[0]='d';
		this.okno=okno;
		this.szerokoscMapy=szerokoscMapy;
		this.wysokoscMapy=wysokoscMapy;
		mapa=new String[wysokoscMapy][szerokoscMapy];
		reset();
		
		for(int i=0;i<ileGraczy;i++)
			nowyGracz(i);
	}
	
	void nowyGracz(int i) {
		
		Thread thread = new Thread(() -> {
			new Gracz(this, i);
		});
		thread.start();
	}
	
	public void reset() {
		for(int i=0;i<wysokoscMapy;i++)
			for(int j=0;j<szerokoscMapy;j++) {
				mapa[i][j]=new String("0");
			}
		mapa[4][7]=new String("10s");
		mapa[5][7]=new String("1sd");
		mapa[5][8]=new String("1ds");
		mapa[6][8]=new String("1s0");
	}
	
	public void steruj(char znak){
		for(int i=0;i<8;i++)
			for(int j=0;j<4;j++) {
				if(klawisze[i][j]==znak) {
					kierunek[i]=znak;
					System.out.println(znak+" "+i);
					break;
				}
			}
	}
}
