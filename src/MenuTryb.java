import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuTryb extends Ekran {

	Okno okno;

	public MenuTryb(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void mouseReleased(MouseEvent e) {
		okno.zakladki.show(okno.panelGlowny, "menuMapa");
		okno.menuMapa.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("MT " + e.getKeyChar());
	}
}
