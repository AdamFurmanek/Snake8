import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Wyswietlacz2 extends JPanel implements KeyListener {

	Okno2 okno;
	Rozgrywka2 serwer;

	double skalaWysokoscOkno, skalaSzerokoscOkno;
	double skalaWysokoscMapa, skalaSzerokoscMapa;

	public Wyswietlacz2(Rozgrywka2 serwer, Okno2 okno) throws Exception {

		addKeyListener(this);
		setFocusable(true);

		this.serwer = serwer;
		this.okno = okno;

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
		new ImageIcon("images/tlo.png").paintIcon(this, g2d, 0, 0);
		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno * skalaSzerokoscMapa, skalaWysokoscOkno * skalaWysokoscMapa);
		g2d.setTransform(at);

		for (int i = 0; i < serwer.wysokoscMapy; i++) {
			for (int j = 0; j < serwer.szerokoscMapy; j++) {
				new ImageIcon("images/0.png").paintIcon(this, g2d, (int) (94 / (skalaSzerokoscMapa) + j * 200),
						(int) (77 / (skalaWysokoscMapa) + i * 200));
			}
		}

		for (int i = 0; i < serwer.wysokoscMapy; i++) {
			for (int j = 0; j < serwer.szerokoscMapy; j++) {
				String sciezka = new String("images/");
				sciezka += serwer.mapa[i][j].charAt(0);
				if (serwer.mapa[i][j].charAt(0) != '0') {
					sciezka = new String("images/1"); // TYMCZASOWO ZEBY WSZYSCY BYLI ZIELONI
					if (serwer.mapa[i][j].charAt(1) == 'w' && serwer.mapa[i][j].charAt(2) == 'w'
							|| serwer.mapa[i][j].charAt(1) == 's' && serwer.mapa[i][j].charAt(2) == 's')
						sciezka += "pws";
					else if (serwer.mapa[i][j].charAt(1) == 'a' && serwer.mapa[i][j].charAt(2) == 'a'
							|| serwer.mapa[i][j].charAt(1) == 'd' && serwer.mapa[i][j].charAt(2) == 'd')
						sciezka += "pad";
					else if (serwer.mapa[i][j].charAt(1) == 's' && serwer.mapa[i][j].charAt(2) == 'a'
							|| serwer.mapa[i][j].charAt(1) == 'd' && serwer.mapa[i][j].charAt(2) == 'w')
						sciezka += "swa";
					else if (serwer.mapa[i][j].charAt(1) == 's' && serwer.mapa[i][j].charAt(2) == 'd'
							|| serwer.mapa[i][j].charAt(1) == 'a' && serwer.mapa[i][j].charAt(2) == 'w')
						sciezka += "swd";
					else if (serwer.mapa[i][j].charAt(1) == 'd' && serwer.mapa[i][j].charAt(2) == 's'
							|| serwer.mapa[i][j].charAt(1) == 'w' && serwer.mapa[i][j].charAt(2) == 'a')
						sciezka += "ssa";
					else if (serwer.mapa[i][j].charAt(1) == 'w' && serwer.mapa[i][j].charAt(2) == 'd'
							|| serwer.mapa[i][j].charAt(1) == 'a' && serwer.mapa[i][j].charAt(2) == 's')
						sciezka += "ssd";
					else if (serwer.mapa[i][j].charAt(2) == '0') {
						if (serwer.mapa[i][j].charAt(1) == 'w')
							sciezka += "gw";
						else if (serwer.mapa[i][j].charAt(1) == 'a')
							sciezka += "ga";
						else if (serwer.mapa[i][j].charAt(1) == 's')
							sciezka += "gs";
						else if (serwer.mapa[i][j].charAt(1) == 'd')
							sciezka += "gd";
					} else if (serwer.mapa[i][j].charAt(1) == '0') {
						if (serwer.mapa[i][j].charAt(2) == 'w')
							sciezka += "ow";
						else if (serwer.mapa[i][j].charAt(2) == 'a')
							sciezka += "oa";
						else if (serwer.mapa[i][j].charAt(2) == 's')
							sciezka += "os";
						else if (serwer.mapa[i][j].charAt(2) == 'd')
							sciezka += "od";
					} else
						sciezka += "ga";
				}
				sciezka += ".png";
				new ImageIcon(sciezka).paintIcon(this, g2d, (int) (94 / (skalaSzerokoscMapa) + j * 200),
						(int) (77 / (skalaWysokoscMapa) + i * 200));
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
