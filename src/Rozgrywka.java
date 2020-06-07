import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Rozgrywka extends JPanel implements KeyListener{
	
	Okno okno;
	Serwer serwer;
	
	double skalaWysokoscOkno, skalaSzerokoscOkno;
	double skalaWysokoscMapa, skalaSzerokoscMapa;


	public Rozgrywka(Serwer serwer, Okno okno) throws Exception {
		
		addKeyListener(this);
	    setFocusable(true);
		
		this.serwer=serwer;
		this.okno=okno;
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaSzerokoscOkno = (double) okno.getWidth() / 1920;
				skalaWysokoscOkno = (double) okno.getHeight() / 1080;
			}
		});
		skalaSzerokoscMapa = (double) 1720 / (serwer.szerokoscMapy * 200);
		skalaWysokoscMapa = (double) 880 / (serwer.wysokoscMapy * 200);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno * skalaSzerokoscMapa, skalaWysokoscOkno * skalaWysokoscMapa);
		g2d.setTransform(at);

		for (int i = 0; i < serwer.wysokoscMapy; i++) {
			for (int j = 0; j < serwer.szerokoscMapy; j++) {
				if(serwer.mapa[i][j].charAt(0)=='0')
					new ImageIcon("images/0.png").paintIcon(this, g2d, (int) (94 / (skalaSzerokoscMapa) + j * 200), (int) (77 / (skalaWysokoscMapa) + i * 200));
				else
					new ImageIcon("images/1pws.png").paintIcon(this, g2d, (int) (94 / (skalaSzerokoscMapa) + j * 200), (int) (77 / (skalaWysokoscMapa) + i * 200));
			}
		}
	}
	
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    	serwer.steruj(e.getKeyChar());
    }
    public void keyReleased(KeyEvent e) {
    }
}
