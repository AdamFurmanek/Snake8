import java.util.ArrayList;
import java.util.List;

public class Waz {

	List<int[]> cialoWeza;
	int[] szybkoscWeza;
	boolean czyWazZyje;
	boolean czyWazZostawiaCialo;
	boolean czyWazPrzenikaInnych;
	boolean czyWazPrzenikaSiebie;
	boolean czyWazMozeCofnac;
	int numerWeza;
	int kierunekWeza;
	int kolorWeza;
	int reakcjaNaSciane; //0-umiera,1-odbija sie,2-przenika
	Rozgrywka rozgrywka;
	

	public Waz(Rozgrywka rozgrywka,int numerWeza, int kolorWeza, int reakcjaNaSciane, boolean czyWazZostawiaCialo, boolean czyWazPrzenikaInnych, boolean czyWazPrzenikaSiebie, boolean czyWazMozeCofnac) {
		
		this.czyWazMozeCofnac=czyWazMozeCofnac;
		this.czyWazPrzenikaSiebie=czyWazPrzenikaSiebie;
		if(!czyWazPrzenikaSiebie)
			czyWazMozeCofnac=false;
		this.numerWeza=numerWeza;
		this.czyWazPrzenikaInnych=czyWazPrzenikaInnych;
		this.reakcjaNaSciane=reakcjaNaSciane;
		this.kolorWeza=kolorWeza;
		this.rozgrywka = rozgrywka;
		szybkoscWeza = new int[2];
		szybkoscWeza[0] = 4;
		szybkoscWeza[1] = 0;
		kierunekWeza = 2;
		czyWazZyje=true;
		this.czyWazZostawiaCialo=czyWazZostawiaCialo;
		
		cialoWeza = new ArrayList<int[]>();
	}

	public void zrobKrok() {
		
//		if(!czyWazMozeCofnac) {
//			if(staryKierunekWeza==0&&kierunekWeza==2||staryKierunekWeza==2&&kierunekWeza==0||staryKierunekWeza==1&&kierunekWeza==3||staryKierunekWeza==3&&kierunekWeza==1) {
//				kierunekWeza=staryKierunekWeza;
//				return;
//			}
//		}
		
		if(czyWazZyje) {
		
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
			if(reakcjaNaSciane==2) { //mozna przechodzic przez sciany
				if (kierunekWeza == 2 && cialoWeza.get(0)[0] == rozgrywka.wysokoscMapy - 1)
					cialoWeza.get(0)[0]=0;
				else if (kierunekWeza == 0 && cialoWeza.get(0)[0] == 0)
					cialoWeza.get(0)[0]=rozgrywka.wysokoscMapy - 1;
				else if (kierunekWeza == 3 && cialoWeza.get(0)[1] == rozgrywka.szerokoscMapy - 1)
					cialoWeza.get(0)[1]=0;
				else if (kierunekWeza == 1 && cialoWeza.get(0)[1] == 0)
					cialoWeza.get(0)[1]=rozgrywka.szerokoscMapy - 1;
			}
			else if(reakcjaNaSciane==1){ //odbicie
				
				kierunekWeza+=2;
				if(kierunekWeza>3)
					kierunekWeza-=4;
			}
			else {
				smiercWeza();
			}
		}
		if(!czyWazPrzenikaInnych) {
		for(int i=0;i<rozgrywka.ileGraczy;i++) {
			if(numerWeza!=i) {
				int y=cialoWeza.get(0)[0];
				int x=cialoWeza.get(0)[1];
				if(kierunekWeza==0)
					y--;
				else if(kierunekWeza==1)
					x--;
				else if(kierunekWeza==2)
					y++;
				else if(kierunekWeza==3)
					x++;
				for(int j = 0; j<rozgrywka.waz[i].cialoWeza.size();j++)
					if(rozgrywka.waz[i].cialoWeza.get(j)[0]==y&&rozgrywka.waz[i].cialoWeza.get(j)[1]==x)
						smiercWeza();
			}
		}
		}
		if(!czyWazPrzenikaSiebie) {
			int y=cialoWeza.get(0)[0];
			int x=cialoWeza.get(0)[1];
			if(kierunekWeza==0)
				y--;
			else if(kierunekWeza==1)
				x--;
			else if(kierunekWeza==2)
				y++;
			else if(kierunekWeza==3)
				x++;
			for(int i = 1; i<cialoWeza.size();i++)
				if(cialoWeza.get(i)[0]==y&&cialoWeza.get(i)[1]==x)
					smiercWeza();
		}
		

		
		cialoWeza.remove(cialoWeza.size() - 1);
		}
	}

	public void smiercWeza() {
		czyWazZyje=false;
		if(!czyWazZostawiaCialo) {
			for(int i = cialoWeza.size()-1; i>0;i--)
				cialoWeza.remove(i);
		}
	}
	
	@Override
	public String toString() {

		return new String(
				"Kierunek: " + kierunekWeza + ", szybkosc: " + szybkoscWeza[0] + ", licznik: " + szybkoscWeza[1]);
	}
}
