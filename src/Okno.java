import javax.swing.JFrame;

public class Okno extends JFrame{
	
	char[][] mapa;
	int wysokoscMapy=4;
	int szerokoscMapy=4;
	
	private Okno()  throws Exception{
		
		super("Snake8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		
		mapa = new char[wysokoscMapy][szerokoscMapy];
		
		Rozgrywka rozgrywka = new Rozgrywka(this);
		add(rozgrywka);
		
	}
	
	public static void main(String[] args)  throws Exception{
		new Okno();
	}

}
