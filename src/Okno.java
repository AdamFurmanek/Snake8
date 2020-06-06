import javax.swing.JFrame;

public class Okno extends JFrame{
	
	char[][] mapa;
	int wysokoscMapy=50;
	int szerokoscMapy=100;
	
	private Okno()  throws Exception{
		
		super("Snake8");
		setSize(1286, 743);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		mapa = new char[wysokoscMapy][szerokoscMapy];
		
		Rozgrywka rozgrywka = new Rozgrywka(this);
		add(rozgrywka);

	}
	
	public static void main(String[] args)  throws Exception{
		new Okno();
	}

}
