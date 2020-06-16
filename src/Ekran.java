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

	Okno okno;
	Rozgrywka rozgrywka;

	double skalaWysokoscOkno, skalaSzerokoscOkno;
	double skalaWysokoscMapa, skalaSzerokoscMapa;

	Font font;

	public Ekran(Okno okno) {

		addKeyListener(this);
		setFocusable(true);

		this.okno = okno;

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaSzerokoscOkno = (double) okno.getWidth() / 1920;
				skalaWysokoscOkno = (double) okno.getHeight() / 1080;
			}
		});

	}

	public void paintComponent(Graphics g) {

		if (rozgrywka == null)
			return;

		skalaSzerokoscMapa = (double) 1720 / (rozgrywka.szerokoscMapy * 200);
		skalaWysokoscMapa = (double) 680 / (rozgrywka.wysokoscMapy * 200);

		font = new Font("Gill Sans Ultra Bold", Font.BOLD, 72);

		Graphics2D g2d = (Graphics2D) g.create();

		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI OKNA
		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno, skalaWysokoscOkno);
		g2d.setTransform(at);

		// RYSOWANIE TLA I PLYTY PANELU Z WYNIKAMI
		new ImageIcon("images/tlo2.png").paintIcon(this, g2d, 0, 0);
		new ImageIcon("images/00.png").paintIcon(this, g2d, 99, 820);

		// KTÓRA RUNDA, ILE CZASU
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);
		at.scale(0.8, 0.8);
		g2d.setTransform(at);
		g2d.drawString(rozgrywka.runda[0] + "", 150, 1100);
		g2d.drawString(rozgrywka.runda[1] + "", 150, 1180);
		g2d.drawString((int) rozgrywka.runda[2] / 50 + "", 2060, 1140);
		at.scale(1.25, 1.25);
		g2d.setTransform(at);

		// RYSOWANIE PANELU Z WYNIKAMI
		for (int i = 0; i < rozgrywka.waz.size(); i++) {

			at.scale(0.4, 0.4);
			g2d.setTransform(at);

			at.scale(0.5, 0.5);
			g2d.setTransform(at);
			if (rozgrywka.waz.get(i).przenikanie != 0)
				new ImageIcon("images/p/1.png").paintIcon(this, g2d, 1770 + i * 900, 4200);
			if (rozgrywka.waz.get(i).odbijanie != 0)
				new ImageIcon("images/p/4.png").paintIcon(this, g2d, 1770 + i * 900, 4300);
			if (rozgrywka.waz.get(i).szybkosc[0] > rozgrywka.domyslnaSzybkosc)
				new ImageIcon("images/p/3.png").paintIcon(this, g2d, 1770 + i * 900, 4400);
			else if (rozgrywka.waz.get(i).szybkosc[0] < rozgrywka.domyslnaSzybkosc)
				new ImageIcon("images/p/2.png").paintIcon(this, g2d, 1770 + i * 900, 4400);

			at.scale(2, 2);
			g2d.setTransform(at);

			at.scale(0.8, 0.8);
			g2d.setTransform(at);
			g2d.setColor(Color.BLACK);
			g2d.drawString(rozgrywka.waz.get(i).pseudonim, (845 + i * 563), 2980);

			at.scale(1.25, 1.25);
			g2d.setTransform(at);

			new ImageIcon("images/" + (rozgrywka.waz.get(i).kolor) + "/04.png").paintIcon(this, g2d, 670 + i * 450,
					2070);

			g2d.setTransform(at);
			g2d.setColor(Color.BLACK);
			if (rozgrywka.waz.get(i).hp < 0)
				g2d.drawString("0", (670 + i * 450), 2310);
			else if (rozgrywka.waz.get(i).hp < 101)
				g2d.drawString(rozgrywka.waz.get(i).hp + "", (670 + i * 450), 2310);
			g2d.setColor(Color.GREEN);
			g2d.drawString(rozgrywka.waz.get(i).punkty + "", 650 + i * 450, 2140);
			g2d.setColor(Color.RED);
			g2d.drawString(rozgrywka.waz.get(i).zgony + "", 820 + i * 450, 2140);
			at.scale(2.5, 2.5);
		}

		at.scale(skalaSzerokoscMapa, skalaWysokoscMapa);
		g2d.setTransform(at);

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
