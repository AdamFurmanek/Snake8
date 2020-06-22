import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class MenuGracze extends Ekran {

	Okno okno;
	int myszX, myszY, klawisz;

	public MenuGracze(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(klawisz==1) {
			okno.startGry = true;
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("MT " + e.getKeyChar());
	}
	
	public void mouseMoved(MouseEvent arg0) {

		myszX = (int)(arg0.getX()/okno.skalaSzerokoscOkno);
		myszY = (int)(arg0.getY()/okno.skalaWysokoscOkno);
		//dalej
		if (myszY >= 0 && myszY <= 100 && myszX >= 0 && myszX <= 200)
			klawisz=1;
		//pusto
		else
			klawisz=0;
		repaint();
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		
		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI OKNA
		AffineTransform at = new AffineTransform();
		at.scale(okno.skalaSzerokoscOkno, okno.skalaWysokoscOkno);
		g2d.setTransform(at);
		
		// RYSOWANIE TLA
		new ImageIcon("images/g/3.png").paintIcon(this, g2d, 0, 0);
		
		// RYSOWANIE PRZYCISKU GRAJ
		if(klawisz==1) {
			new ImageIcon("images/i/dalej2.png").paintIcon(this, g2d, 0, 0);
		}
		else
			new ImageIcon("images/i/dalej.png").paintIcon(this, g2d, 0, 0);
	}
}
