import java.awt.Color;
import java.awt.Font;
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

	char klawiszKlawiatury[][] = { { 'w', 'a', 's', 'd' }, { 't', 'f', 'g', 'h' }, { 'i', 'j', 'k', 'l' },
			{ '5', '1', '2', '3' }, { 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x' } };

	Rozgrywka rozgrywka;

	double skalaWysokoscOkno, skalaSzerokoscOkno;
	double skalaWysokoscMapa, skalaSzerokoscMapa;

	Font font;

	public Ekran(Rozgrywka rozgrywka) {

		this.rozgrywka = rozgrywka;

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaSzerokoscOkno = (double) rozgrywka.okno.getWidth() / 1920;
				skalaWysokoscOkno = (double) rozgrywka.okno.getHeight() / 1080;
			}
		});

		skalaSzerokoscMapa = (double) 1720 / (rozgrywka.szerokoscMapy * 200);
		skalaWysokoscMapa = (double) 680 / (rozgrywka.wysokoscMapy * 200);

		font = new Font("Gill Sans Ultra Bold", Font.BOLD, 72);

		addKeyListener(this);
		setFocusable(true);

	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI OKNA
		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno, skalaWysokoscOkno);
		g2d.setTransform(at);

		// RYSOWANIE TLA I PLYTY PANELU Z WYNIKAMI
		new ImageIcon("images/tlo2.png").paintIcon(this, g2d, 0, 0);
		new ImageIcon("images/00.png").paintIcon(this, g2d, 99, 820);

		at.scale(skalaSzerokoscMapa, skalaWysokoscMapa);
		g2d.setTransform(at);

		// RYSOWANIE PANELU Z WYNIKAMI
		g2d.setFont(font);
		for (int i = 0; i < rozgrywka.waz.size(); i++) {
			new ImageIcon("images/" + (rozgrywka.waz.get(i).kolor) + "gw.png").paintIcon(this, g2d, 1100 + i * 450,
					2440);
			g2d.setColor(Color.BLACK);
			g2d.drawString(rozgrywka.waz.get(i).hp + "", 1100 + i * 450, 2680);
			g2d.setColor(Color.GREEN);
			g2d.drawString(rozgrywka.waz.get(i).punkty + "", 1080 + i * 450, 2510);
			g2d.setColor(Color.RED);
			g2d.drawString(rozgrywka.waz.get(i).zgony + "", 1250 + i * 450, 2510);
		}

		// RYSOWANIE MAPY
		for (int i = 0; i < rozgrywka.wysokoscMapy; i++)
			for (int j = 0; j < rozgrywka.szerokoscMapy; j++)
				if (rozgrywka.mapa[i][j] != 1)
					new ImageIcon("images/0.png").paintIcon(this, g2d, (int) (94 / (skalaSzerokoscMapa) + j * 200),
							(int) (77 / (skalaWysokoscMapa) + i * 200));

		// RYSOWANIE WEZOW
		for (int i = 0; i < rozgrywka.waz.size(); i++)
			for (int j = 0; j < rozgrywka.waz.get(i).cialo.size(); j++)
				new ImageIcon("images/" + rozgrywka.waz.get(i).kolor + "/" + rozgrywka.waz.get(i).cialo.get(j)[2]
						+ rozgrywka.waz.get(i).cialo.get(j)[3] + ".png").paintIcon(this, g2d,
								(int) (94 / (skalaSzerokoscMapa) + rozgrywka.waz.get(i).cialo.get(j)[1] * 200),
								(int) (77 / (skalaWysokoscMapa) + rozgrywka.waz.get(i).cialo.get(j)[0] * 200));

		// RYSOWANIE PRZEDMIOTOW
//		for(int i=0;i<rozgrywka.przedmiot.size();i++) {
//			
//		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 4; j++)
				if (e.getKeyChar() == klawiszKlawiatury[i][j]) {
					rozgrywka.waz.get(i).flagaZmianyKierunku = j;
				}
	}

	public void keyReleased(KeyEvent e) {
	}

}
