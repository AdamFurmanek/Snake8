import java.util.ArrayList;
import java.util.List;

public class Waz {

	List<int[]> cialoWeza;
	int[] szybkoscWeza;
	int kierunekWeza;
	int kolor;
	int sciana; //0-umiera,1-odbija sie,2-przenika
	Rozgrywka rozgrywka;
	

	public Waz(Rozgrywka rozgrywka,int kolor, int sciana) {
		
		this.sciana=sciana;
		this.kolor=kolor;
		this.rozgrywka = rozgrywka;
		szybkoscWeza = new int[2];
		szybkoscWeza[0] = 4;
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
		else{
			if(sciana==2) { //mozna przechodzic przez sciany
				if (kierunekWeza == 2 && cialoWeza.get(0)[0] == rozgrywka.wysokoscMapy - 1)
					cialoWeza.get(0)[0]=0;
				else if (kierunekWeza == 0 && cialoWeza.get(0)[0] == 0)
					cialoWeza.get(0)[0]=rozgrywka.wysokoscMapy - 1;
				else if (kierunekWeza == 3 && cialoWeza.get(0)[1] == rozgrywka.szerokoscMapy - 1)
					cialoWeza.get(0)[1]=0;
				else if (kierunekWeza == 1 && cialoWeza.get(0)[1] == 0)
					cialoWeza.get(0)[1]=rozgrywka.szerokoscMapy - 1;
			}
			else if(sciana==1){ //odbicie
				
				kierunekWeza+=2;
				if(kierunekWeza>3)
					kierunekWeza-=4;
			}
			else { //smierc
				//blok usuwajacy weza
				//blok zostawiajacy cialo
			}
		}

		cialoWeza.remove(cialoWeza.size() - 1);

	}

	@Override
	public String toString() {

		return new String(
				"Kierunek: " + kierunekWeza + ", szybkosc: " + szybkoscWeza[0] + ", licznik: " + szybkoscWeza[1]);
	}
}
