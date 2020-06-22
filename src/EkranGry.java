import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class EkranGry extends Ekran {

	Okno okno;
	Font font1 = new Font("Calibri Light", Font.PLAIN, 40);
	Font font2 = new Font("Calibri Light", Font.PLAIN, 80);
	FontMetrics metryka1 = getFontMetrics(font1);

	public EkranGry(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI OKNA
		AffineTransform at = new AffineTransform();
		at.scale(okno.skalaSzerokoscOkno, okno.skalaWysokoscOkno);
		g2d.setTransform(at);

		// RYSOWANIE TLA I PLYTY PANELU Z WYNIKAMI
		new ImageIcon("images/g/5.png").paintIcon(this, g2d, 0, 0);
		new ImageIcon("images/g/c.png").paintIcon(this, g2d, 0, 0);
		new ImageIcon("images/t/wyniki4.png").paintIcon(this, g2d, 60, 820);

		// RYSOWANIE KTORA RUNDA
		g2d.setFont(font2);
		g2d.setColor(new Color(236, 240, 241));
		if (okno.aktualnaRunda < 10)
			g2d.drawString(okno.aktualnaRunda + "", 122, 900);
		else
			g2d.drawString(okno.aktualnaRunda + "", 103, 900);
		if (okno.iloscRund < 10)
			g2d.drawString(okno.iloscRund + "", 122, 970);
		else
			g2d.drawString(okno.iloscRund + "", 103, 970);

		// RYSOWANIE WARUNKU ZWYCIESTWA
		if(okno.dlugoscRundy>0) {
			if(okno.pozostalyCzas/50<10)
				g2d.drawString(okno.pozostalyCzas/50 + "", 1756, 935);
			else if(okno.pozostalyCzas/50>99)
				g2d.drawString(okno.pozostalyCzas/50 + "", 1718, 935);
			else
				g2d.drawString(okno.pozostalyCzas/50 + "", 1737, 935);
		}
			
		else if(okno.limitPunktow>0) {
			g2d.setColor(new Color(39, 174, 96));
			if(okno.limitPunktow<10)
				g2d.drawString(okno.limitPunktow + "", 1707, 935);
			else if(okno.limitPunktow>99)
				g2d.drawString(okno.limitPunktow + "", 1677, 935);
			else
				g2d.drawString(okno.limitPunktow + "", 1691, 935);
		}
		else if(okno.limitZgonow>0) {
			g2d.setColor(new Color(231, 76, 60));
			if(okno.limitZgonow<10)
				g2d.drawString(okno.limitZgonow + "", 1707, 935);
			else if(okno.limitZgonow>99)
				g2d.drawString(okno.limitZgonow + "", 1677, 935);
			else
				g2d.drawString(okno.limitZgonow + "", 1691, 935);
		}

		// RYSOWANIE PANELU Z WYNIKAMI
		for (int i = 0; i < okno.waz.size(); i++) {

			// RYSOWANIE BONUSOW
			at.scale(0.2, 0.2);
			g2d.setTransform(at);
			if (okno.waz.get(i).szybkosc[0] > okno.domyslnaSzybkosc)
				new ImageIcon("images/p/3.png").paintIcon(this, g2d, 1720 + i * 910, 4660);
			else if (okno.waz.get(i).szybkosc[0] < okno.domyslnaSzybkosc)
				new ImageIcon("images/p/2.png").paintIcon(this, g2d, 1720 + i * 910, 4660);
			if (okno.waz.get(i).przenikanie > 0 || okno.stalePrzenikanie)
				new ImageIcon("images/p/1.png").paintIcon(this, g2d, 1510 + i * 910, 4660);
			if (okno.waz.get(i).odbijanie > 0 || okno.staleOdbijanie)
				new ImageIcon("images/p/4.png").paintIcon(this, g2d, 1300 + i * 910, 4660);
			else if (okno.waz.get(i).przechodzenie > 0 || okno.stalePrzechodzenie)
				new ImageIcon("images/p/8.png").paintIcon(this, g2d, 1300 + i * 910, 4660);

			// RYSOWANIE GLOW WEZOW
			at.scale(2, 2);
			g2d.setTransform(at);
			new ImageIcon("images/" + (okno.waz.get(i).kolor) + "/04.png").paintIcon(this, g2d, 710 + i * 455, 2070);

			at.scale(2.5, 2.5);
			g2d.setTransform(at);

			g2d.setFont(font1);
			// RYSOWANIE HP
			g2d.setColor(new Color(236, 240, 241));
			if (okno.waz.get(i).hp == 100)
				g2d.drawString(okno.waz.get(i).hp + "", (293 + i * 182), 927);
			else if (okno.waz.get(i).hp < 100)
				g2d.drawString(okno.waz.get(i).hp + "", (303 + i * 182), 927);
			else
				g2d.drawString(okno.waz.get(i).hp + "", (313 + i * 182), 927);

			// RYSOWANIE PUNKTOW
			g2d.setColor(new Color(39, 174, 96));
			if (okno.waz.get(i).punkty == 100)
				g2d.drawString(okno.waz.get(i).punkty + "", (253 + i * 182), 850);
			else if (okno.waz.get(i).punkty == 0)
				g2d.drawString(okno.waz.get(i).punkty + "", (271 + i * 182), 850);
			else
				g2d.drawString(okno.waz.get(i).punkty + "", (262 + i * 182), 850);

			// RYSOWANIE ZGONOW
			g2d.setColor(new Color(231, 76, 60));
			if (okno.waz.get(i).zgony == 100)
				g2d.drawString(okno.waz.get(i).zgony + "", (333 + i * 182), 850);
			else if (okno.waz.get(i).zgony == 0)
				g2d.drawString(okno.waz.get(i).zgony + "", (351 + i * 182), 850);
			else
				g2d.drawString(okno.waz.get(i).zgony + "", (342 + i * 171), 850);

			// RYSOWANIE PSEUDONIMU
			g2d.setColor(new Color(236, 240, 241));
			g2d.drawString(okno.waz.get(i).pseudonim,
					(320 + i * 182) - metryka1.stringWidth(okno.waz.get(i).pseudonim) / 2, 1000);
		}

		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI MAPY
		double skalaSzerokoscMapa = (double) 1800 / (okno.szerokoscMapy * 200);
		double skalaWysokoscMapa = (double) 730 / (okno.wysokoscMapy * 200);
		at.scale(skalaSzerokoscMapa, skalaWysokoscMapa);
		g2d.setTransform(at);

		// RYSOWANIE MAPY
		for (int i = 0; i < okno.wysokoscMapy; i++)
			for (int j = 0; j < okno.szerokoscMapy; j++)
				if (okno.mapa[i][j] != 1)
					new ImageIcon("images/t/mapa.png").paintIcon(this, g2d, (int) (60 / (skalaSzerokoscMapa) + j * 200),
							(int) (60 / (skalaWysokoscMapa) + i * 200));

		// RYSOWANIE PRZEDMIOTOW
		for (int i = 0; i < okno.przedmiot.size(); i++) {
			for (int j = 0; j < okno.przedmiot.get(i).instancja.size(); j++) {
				new ImageIcon("images/p/" + okno.przedmiot.get(i).etykieta + ".png").paintIcon(this, g2d,
						(int) (60 / (skalaSzerokoscMapa) + okno.przedmiot.get(i).instancja.get(j)[1] * 200),
						(int) (60 / (skalaWysokoscMapa) + okno.przedmiot.get(i).instancja.get(j)[0] * 200));
			}
		}
		// RYSOWANIE WEZOW
		for (int i = 0; i < okno.waz.size(); i++)
			for (int j = 0; j < okno.waz.get(i).cialo.size(); j++)
				new ImageIcon("images/" + okno.waz.get(i).kolor + "/" + okno.waz.get(i).cialo.get(j)[2]
						+ okno.waz.get(i).cialo.get(j)[3] + ".png").paintIcon(this, g2d,
								(int) (60 / (skalaSzerokoscMapa) + okno.waz.get(i).cialo.get(j)[1] * 200),
								(int) (60 / (skalaWysokoscMapa) + okno.waz.get(i).cialo.get(j)[0] * 200));
	}

	public void mouseReleased(MouseEvent e) {
		okno.koniecGry=true;
	}

	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 4; j++)
				if (e.getKeyCode() == okno.sterowanie[i][j]) {
					if (okno.stalePrzenikanie || okno.waz.get(i).przenikanie > 0
							|| j != okno.waz.get(i).cialo.get(0)[2] + 2 && j != okno.waz.get(i).cialo.get(0)[2] - 2)
						okno.waz.get(i).kierunek = j;
				}

	}
}
