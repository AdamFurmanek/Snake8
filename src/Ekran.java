import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ekran extends JPanel implements KeyListener {

	
	
	Rozgrywka rozgrywka;

	double skalaWysokoscOkno, skalaSzerokoscOkno;
	double skalaWysokoscMapa, skalaSzerokoscMapa;
	
	char klawiszKlawiatury[][] = { { 'w', 'a', 's', 'd' }, { 't', 'f', 'g', 'h' }, { 'j', 'k', 'k', 'l' },
			{ '5', '1', '2', '3' }, { 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x' } };

	public Ekran(Rozgrywka rozgrywka) {
		
		this.rozgrywka = rozgrywka;

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaSzerokoscOkno = (double) rozgrywka.okno.getWidth() / 1920;
				skalaWysokoscOkno = (double) rozgrywka.okno.getHeight() / 1080;
			}
		});

		skalaSzerokoscMapa = (double) 1720 / (rozgrywka.szerokoscMapy * 200);
		skalaWysokoscMapa = (double) 880 / (rozgrywka.wysokoscMapy * 200);
		
		addKeyListener(this);
		setFocusable(true);
		
		repaint();
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		new ImageIcon("images/tlo.png").paintIcon(this, g2d, 0, 0);
		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno * skalaSzerokoscMapa, skalaWysokoscOkno * skalaWysokoscMapa);
		g2d.setTransform(at);

		for (int i = 0; i < rozgrywka.wysokoscMapy; i++) {
			for (int j = 0; j < rozgrywka.szerokoscMapy; j++) {
				new ImageIcon("images/0.png").paintIcon(this, g2d, (int) (94 / (skalaSzerokoscMapa) + j * 200),
						(int) (77 / (skalaWysokoscMapa) + i * 200));
			}
		}

		
		
		for (int i = 0; i < rozgrywka.ileGraczy; i++) {
			String sciezka = new String("images/"+rozgrywka.waz[i].kolor);
			
			if(rozgrywka.waz[i].cialoWeza.get(0)[0]==rozgrywka.waz[i].cialoWeza.get(1)[0]+1)
				sciezka+="gs.png";
			else if(rozgrywka.waz[i].cialoWeza.get(0)[0]==rozgrywka.waz[i].cialoWeza.get(1)[0]-1)
				sciezka+="gw.png";
			else if(rozgrywka.waz[i].cialoWeza.get(0)[1]==rozgrywka.waz[i].cialoWeza.get(1)[1]+1)
				sciezka+="gd.png";
			else if(rozgrywka.waz[i].cialoWeza.get(0)[1]==rozgrywka.waz[i].cialoWeza.get(1)[1]-1)
				sciezka+="ga.png";
				
			new ImageIcon(sciezka).paintIcon(this, g2d,
					(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz[i].cialoWeza.get(0)[1] * 200),
					(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz[i].cialoWeza.get(0)[0] * 200));
			
			for (int j = 1; j < rozgrywka.waz[i].cialoWeza.size()-1; j++) {
				sciezka = new String("images/"+rozgrywka.waz[i].kolor);
				
				if(rozgrywka.waz[i].cialoWeza.get(j)[0]==rozgrywka.waz[i].cialoWeza.get(j-1)[0]-1) { //idzie w dol
					if(rozgrywka.waz[i].cialoWeza.get(j)[1]==rozgrywka.waz[i].cialoWeza.get(j+1)[1]+1)
						sciezka+="cas.png";
					else if(rozgrywka.waz[i].cialoWeza.get(j)[1]==rozgrywka.waz[i].cialoWeza.get(j+1)[1]-1)
						sciezka+="csd.png";
					else
						sciezka+="cws.png";
				}
				else if(rozgrywka.waz[i].cialoWeza.get(j)[0]==rozgrywka.waz[i].cialoWeza.get(j-1)[0]+1) { //idzie w gore
					if(rozgrywka.waz[i].cialoWeza.get(j)[1]==rozgrywka.waz[i].cialoWeza.get(j+1)[1]+1)
						sciezka+="cwa.png";
					else if(rozgrywka.waz[i].cialoWeza.get(j)[1]==rozgrywka.waz[i].cialoWeza.get(j+1)[1]-1)
						sciezka+="cwd.png";
					else
						sciezka+="cws.png";
				}
				else if(rozgrywka.waz[i].cialoWeza.get(j)[1]==rozgrywka.waz[i].cialoWeza.get(j-1)[1]-1) { //idzie w prawo
					if(rozgrywka.waz[i].cialoWeza.get(j)[0]==rozgrywka.waz[i].cialoWeza.get(j+1)[0]+1)
						sciezka+="cwd.png";
					else if(rozgrywka.waz[i].cialoWeza.get(j)[0]==rozgrywka.waz[i].cialoWeza.get(j+1)[0]-1)
						sciezka+="csd.png";
					else
						sciezka+="cad.png";
				}
				else if(rozgrywka.waz[i].cialoWeza.get(j)[1]==rozgrywka.waz[i].cialoWeza.get(j-1)[1]+1) { //idzie w prawo
					if(rozgrywka.waz[i].cialoWeza.get(j)[0]==rozgrywka.waz[i].cialoWeza.get(j+1)[0]+1)
						sciezka+="cwa.png";
					else if(rozgrywka.waz[i].cialoWeza.get(j)[0]==rozgrywka.waz[i].cialoWeza.get(j+1)[0]-1)
						sciezka+="cas.png";
					else
						sciezka+="cad.png";
				}
				
				new ImageIcon(sciezka).paintIcon(this, g2d,
						(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz[i].cialoWeza.get(j)[1] * 200),
						(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz[i].cialoWeza.get(j)[0] * 200));
			}
			
			sciezka = new String("images/"+rozgrywka.waz[i].kolor);
			if(rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[0]==rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-2)[0]+1)
				sciezka+="ow.png";
			else if(rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[0]==rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-2)[0]-1)
				sciezka+="os.png";
			else if(rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[1]==rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-2)[1]+1)
				sciezka+="oa.png";
			else if(rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[1]==rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-2)[1]-1)
				sciezka+="od.png";
				
			new ImageIcon(sciezka).paintIcon(this, g2d,
					(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[1] * 200),
					(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[0] * 200));
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		for(int i=0; i<8;i++) {
			for(int j=0;j<4;j++)
				if(e.getKeyChar()==klawiszKlawiatury[i][j]) {
					rozgrywka.waz[i].kierunekWeza=j;
				}
		}
		
	}

	public void keyReleased(KeyEvent e) {
	}
}
