import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class MenuTryb extends Ekran {

	Okno okno;
	int myszX, myszY, klawisz;
	int aktualnyTryb = 0;
	String nazwaTrybu;
	
	Font font1 = new Font("Calibri Light", Font.PLAIN, 40);
	Font font2 = new Font("Calibri Light", Font.PLAIN, 80);
	FontMetrics metryka1 = getFontMetrics(font1);
	FontMetrics metryka2 = getFontMetrics(font2);
	File plik = new File("txt/tryby.txt");

	public MenuTryb(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (klawisz == 1) {
			okno.zakladki.show(okno.panelGlowny, "menuMapa");
			okno.menuMapa.requestFocusInWindow();
		}
		else if (klawisz == 2) {
			okno.zakladki.show(okno.panelGlowny, "menuStart");
			okno.menuStart.requestFocusInWindow();
		}
		else if (klawisz == 3) {
			aktualnyTryb++;
			if(aktualnyTryb==12)
				aktualnyTryb=0;
			pobierzTryb();
		}
		else if (klawisz == 4) {
			aktualnyTryb--;
			if(aktualnyTryb==-1)
				aktualnyTryb=11;
			pobierzTryb();
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("MT " + e.getKeyChar());
	}

	public void mouseMoved(MouseEvent arg0) {

		myszX = (int) (arg0.getX() / okno.skalaSzerokoscOkno);
		myszY = (int) (arg0.getY() / okno.skalaWysokoscOkno);
		// dalej
		if (myszY >= 920 && myszY <= 1020 && myszX >= 1660 && myszX <= 1860)
			klawisz = 1;
		// wstecz
		else if (myszY >= 920 && myszY <= 1020 && myszX >= 60 && myszX <= 260)
			klawisz = 2;
		// prawo
		else if (myszY >= 250 && myszY <= 350 && myszX >= 1760 && myszX <= 1860)
			klawisz = 3;
		// lewo
		else if (myszY >= 250 && myszY <= 350 && myszX >= 60 && myszX <= 160)
			klawisz = 4;
		// pusto
		else
			klawisz = 0;
		repaint();
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI OKNA
		AffineTransform at = new AffineTransform();
		at.scale(okno.skalaSzerokoscOkno, okno.skalaWysokoscOkno);
		g2d.setTransform(at);

		// RYSOWANIE TLA
		new ImageIcon("images/g/1.png").paintIcon(this, g2d, 0, 0);
		new ImageIcon("images/g/c.png").paintIcon(this, g2d, 0, 0);

		// RYSOWANIE PRZYCISKU DALEJ
		if (klawisz == 1) {
			new ImageIcon("images/i/dalej2.png").paintIcon(this, g2d, 1660, 920);
		} else
			new ImageIcon("images/i/dalej.png").paintIcon(this, g2d, 1660, 920);
		// RYSOWANIE PRZYCISKU WSTECZ
		if (klawisz == 2) {
			new ImageIcon("images/i/wstecz2.png").paintIcon(this, g2d, 60, 920);
		} else
			new ImageIcon("images/i/wstecz.png").paintIcon(this, g2d, 60, 920);
		// RYSOWANIE PRZYCISKU PRAWO
		if (klawisz == 3) {
			new ImageIcon("images/i/prawo2.png").paintIcon(this, g2d, 1760, 250);
		} else
			new ImageIcon("images/i/prawo.png").paintIcon(this, g2d, 1760, 250);
		// RYSOWANIE PRZYCISKU LEWO
		if (klawisz == 4) {
			new ImageIcon("images/i/lewo2.png").paintIcon(this, g2d, 60, 250);
		} else
			new ImageIcon("images/i/lewo.png").paintIcon(this, g2d, 60, 250);
		
		new ImageIcon("images/modes/"+aktualnyTryb+".png").paintIcon(this, g2d, 560, 100);
		
		at.scale(0.4, 0.4);
		g2d.setTransform(at);
		if(aktualnyTryb==0)
			new ImageIcon("images/modes/11.png").paintIcon(this, g2d, 500, 550);
		else
			new ImageIcon("images/modes/"+(aktualnyTryb-1)+".png").paintIcon(this, g2d, 500, 550);
		if(aktualnyTryb==11)
			new ImageIcon("images/modes/0.png").paintIcon(this, g2d, 3500, 550);
		else
			new ImageIcon("images/modes/"+(aktualnyTryb+1)+".png").paintIcon(this, g2d, 3500, 550);
		
		at.scale(2.5, 2.5);
		g2d.setTransform(at);
		
		g2d.setColor(new Color(236, 240, 241));
		g2d.setFont(font1);
		g2d.drawString("Choose mode",
				(960 - metryka1.stringWidth("Choose mode") / 2), 80);
		
		g2d.setFont(font2);
		g2d.drawString(nazwaTrybu,
				(960 - metryka2.stringWidth(nazwaTrybu) / 2), 570);

	}

	void pobierzTryb() {

		Scanner skaner = null;

		try {
			skaner = new Scanner(plik);
		} catch (Exception e) {
		}

		for (int i = 0; i < aktualnyTryb; i++)
			for (int j = 0; j < 28; j++)
				skaner.nextLine();
		// 0. nazwa
		nazwaTrybu=skaner.nextLine();
		// 1. hp
		if (skaner.nextLine().charAt(0) == 't')
			okno.wlaczHP = true;
		else
			okno.wlaczHP = false;
		// 2. przenikanie
		if (skaner.nextLine().charAt(0) == 't')
			okno.stalePrzenikanie = true;
		else
			okno.stalePrzenikanie = false;
		// 3. odbijanie
		if (skaner.nextLine().charAt(0) == 't')
			okno.staleOdbijanie = true;
		else
			okno.staleOdbijanie = false;
		// 4. przechodzenie
		if (skaner.nextLine().charAt(0) == 't')
			okno.stalePrzechodzenie = true;
		else
			okno.stalePrzechodzenie = false;
		// 5. zostawianie ciala
		if (skaner.nextLine().charAt(0) == 't')
			okno.zostawianieCiala = true;
		else
			okno.zostawianieCiala = false;
		// 6. warunek
		okno.warunekKonca = Integer.parseInt(skaner.nextLine());

		// 7. wartosc warunku
		okno.limitZgonow = 0;
		okno.limitPunktow = 0;
		okno.dlugoscRundy = 0;
		if (okno.warunekKonca == 0)
			okno.dlugoscRundy = Integer.parseInt(skaner.nextLine());
		else if (okno.warunekKonca == 1)
			okno.limitPunktow = Integer.parseInt(skaner.nextLine());
		else if (okno.warunekKonca == 2)
			okno.limitZgonow = Integer.parseInt(skaner.nextLine());
		// 8. liczba rund
		okno.iloscRund = Integer.parseInt(skaner.nextLine());
		// 9. domyslna szybkosc
		okno.domyslnaSzybkosc = Integer.parseInt(skaner.nextLine());
		// 10. przedmiot

		for (int i = 0; i < 9; i++) {
			okno.parametryPrzedmiotow[i][0] = Integer.parseInt(skaner.nextLine());
			okno.parametryPrzedmiotow[i][1] = Integer.parseInt(skaner.nextLine());
		}
	}
}
