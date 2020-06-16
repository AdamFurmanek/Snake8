import java.util.ArrayList;
import java.util.List;

public class Przedmiot {
	
	List<int[]> instancja;
	int etykieta;
	
	void kolizja(Waz waz) {
		
	}

}

class Serek extends Przedmiot{
	
	public Serek() {
		etykieta=0;
		instancja = new ArrayList<int[]>();
	}
	
	@Override
	void kolizja(Waz waz) {
		for(int i=0;i<instancja.size();i++)
			if(waz.cialo.get(0)[0]==instancja.get(i)[0]&&waz.cialo.get(0)[1]==instancja.get(i)[1]) {
				waz.punkty++;
			
			}
	}
	
}
