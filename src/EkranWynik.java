import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EkranWynik extends Ekran {

	Okno okno;

	public EkranWynik(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		System.out.println("EW " + e.getKeyChar());
	}
}
