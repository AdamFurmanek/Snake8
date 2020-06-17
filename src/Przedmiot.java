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
					for(int i=0;i<rozgrywka.przedmiot.size();i++) {
						for(int j=0;j<rozgrywka.przedmiot.get(i).instancja.size();j++) {
							if(rozgrywka.przedmiot.get(i).instancja.get(j)[1]==x && rozgrywka.przedmiot.get(i).instancja.get(j)[0]==y)
								zajete = true;	
						}
					}
				}
				System.out.println("Dodaje serek");
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
		etykieta=0;
	}

	@Override
	void wykonanie(Waz waz, int j) {
		waz.punkty++;
		waz.flagaWydluzeniaCiala=1;
		instancja.remove(j);
	}

}


