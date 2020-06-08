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
	
	char klawiszKlawiatury[][] = { { 'w', 'a', 's', 'd' }, { 't', 'f', 'g', 'h' }, { 'i', 'j', 'k', 'l' },
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
			
			if(rozgrywka.waz[i].cialoWeza.size()>0) {
			
			String sciezka = new String("images/"+rozgrywka.waz[i].kolorWeza);
			
			int y=rozgrywka.waz[i].cialoWeza.get(0)[0];
			int x=rozgrywka.waz[i].cialoWeza.get(0)[1];
			int y2=rozgrywka.waz[i].cialoWeza.get(1)[0];
			int x2=rozgrywka.waz[i].cialoWeza.get(1)[1];
			int y3=rozgrywka.waz[i].cialoWeza.get(2)[0];
			int x3=rozgrywka.waz[i].cialoWeza.get(2)[1];
			
			if(y==y2+1||y==y2-rozgrywka.wysokoscMapy+1)
				sciezka+="gs.png";
			else if(y==y2-1||y==y2+rozgrywka.wysokoscMapy-1)
				sciezka+="gw.png";
			else if(x==x2+1||x==x2-rozgrywka.szerokoscMapy+1)
				sciezka+="gd.png";
			else if(x==x2-1||x==x2+rozgrywka.szerokoscMapy-1)
				sciezka+="ga.png";
			else if(y==y2&&x==x2) {
				if(y==y3+1||y==y3-rozgrywka.wysokoscMapy+1)
					sciezka+="cws.png";
				else if(y==y3-1||y==y3+rozgrywka.wysokoscMapy-1)
					sciezka+="cws.png";
				else if(x==x3+1||x==x3-rozgrywka.szerokoscMapy+1)
					sciezka+="cad.png";
				else if(x==x3-1||x==x3+rozgrywka.szerokoscMapy-1)
					sciezka+="cad.png";
			}
				
				
			new ImageIcon(sciezka).paintIcon(this, g2d,
					(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz[i].cialoWeza.get(0)[1] * 200),
					(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz[i].cialoWeza.get(0)[0] * 200));
			
			
			
			for (int j = 1; j < rozgrywka.waz[i].cialoWeza.size()-1; j++) {
				sciezka = new String("images/"+rozgrywka.waz[i].kolorWeza);
				
				y=rozgrywka.waz[i].cialoWeza.get(j)[0];
				x=rozgrywka.waz[i].cialoWeza.get(j)[1];
				y2=rozgrywka.waz[i].cialoWeza.get(j+1)[0];
				x2=rozgrywka.waz[i].cialoWeza.get(j+1)[1];
				y3=rozgrywka.waz[i].cialoWeza.get(j-1)[0];
				x3=rozgrywka.waz[i].cialoWeza.get(j-1)[1];
				
				if(y==y3-1||y==y3+rozgrywka.wysokoscMapy-1) { //idzie w dol
					if(x==x2+1||x==x2-rozgrywka.szerokoscMapy+1)
						sciezka+="cas.png";
					else if(x==x2-1||x==x2+rozgrywka.szerokoscMapy-1)
						sciezka+="csd.png";
					else
						sciezka+="cws.png";
				}
				else if(y==y3+1||y==y3-rozgrywka.wysokoscMapy+1) { //idzie w gore
					if(x==x2+1||x==x2-rozgrywka.szerokoscMapy+1)
						sciezka+="cwa.png";
					else if(x==x2-1||x==x2+rozgrywka.szerokoscMapy-1)
						sciezka+="cwd.png";
					else
						sciezka+="cws.png";
				}
				else if(x==x3-1||x==x3+rozgrywka.szerokoscMapy-1) { //idzie w prawo
					if(y==y2+1||y==y2-rozgrywka.wysokoscMapy+1)
						sciezka+="cwd.png";
					else if(y==y2-1||y==y2+rozgrywka.wysokoscMapy-1)
						sciezka+="csd.png";
					else
						sciezka+="cad.png";
				}
				else if(x==x3+1||x==x3-rozgrywka.szerokoscMapy+1) { //idzie w lewo
					if(y==y2+1||y==y2-rozgrywka.wysokoscMapy+1)
						sciezka+="cwa.png";
					else if(y==y2-1||y==y2+rozgrywka.wysokoscMapy-1)
						sciezka+="cas.png";
					else
						sciezka+="cad.png";
				}
				
				new ImageIcon(sciezka).paintIcon(this, g2d,
						(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz[i].cialoWeza.get(j)[1] * 200),
						(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz[i].cialoWeza.get(j)[0] * 200));
			}
			
			sciezka = new String("images/"+rozgrywka.waz[i].kolorWeza);
			
			y=rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[0];
			x=rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[1];
			y3=rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-2)[0];
			x3=rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-2)[1];
			
			if(y==y3+1||y==y3-rozgrywka.wysokoscMapy+1)
				sciezka+="ow.png";
			else if(y==y3-1||y==y3+rozgrywka.wysokoscMapy-1)
				sciezka+="os.png";
			else if(x==x3+1||x==x3-rozgrywka.szerokoscMapy+1)
				sciezka+="oa.png";
			else if(x==x3-1||x==x3+rozgrywka.szerokoscMapy-1)
				sciezka+="od.png";
				
			new ImageIcon(sciezka).paintIcon(this, g2d,
					(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[1] * 200),
					(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz[i].cialoWeza.get(rozgrywka.waz[i].cialoWeza.size()-1)[0] * 200));
		}
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		for(int i=0; i<8;i++) {
			for(int j=0;j<4;j++)
				if(e.getKeyChar()==klawiszKlawiatury[i][j]) {
					if(!rozgrywka.waz[i].czyWazMozeCofnac){
						if(j==0&&rozgrywka.waz[i].kierunekWeza==2||j==2&&rozgrywka.waz[i].kierunekWeza==0||j==1&&rozgrywka.waz[i].kierunekWeza==3||j==3&&rozgrywka.waz[i].kierunekWeza==1)
							return;
						else
							rozgrywka.waz[i].kierunekWeza=j;
					}
					else
						rozgrywka.waz[i].kierunekWeza=j;
				}
		}
		
	}

	public void keyReleased(KeyEvent e) {
	}
}
