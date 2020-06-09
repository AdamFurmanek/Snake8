import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener{

	Okno okno;
	
	public Menu(Okno okno) {
		this.okno=okno;
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		new ImageIcon("images/tlo2.png").paintIcon(this, g2d, 0, 0);
//		AffineTransform at = new AffineTransform();
//		at.scale(skalaSzerokoscOkno * skalaSzerokoscMapa, skalaWysokoscOkno * skalaWysokoscMapa);
//		g2d.setTransform(at);
	}
	
	@Override
 	public void mouseClicked(MouseEvent e) {
		//okno.remove(this);
		//Rozgrywka rozgrywka = new Rozgrywka(okno, 20, 10, 2);
 		System.out.println("mouseClicked");
 	}
 
 	@Override
 	public void mouseEntered(MouseEvent e) {
 		System.out.println("mouseEntered");
 	}
 
 	@Override
 	public void mouseExited(MouseEvent e) {
 		System.out.println("mouseExited");
 	}
 
 	@Override
 	public void mousePressed(MouseEvent e) {
 		System.out.println("mousePressed");
 	}
 
 	@Override
 	public void mouseReleased(MouseEvent e) {
 		System.out.println("mouseReleased");
 	}
}
