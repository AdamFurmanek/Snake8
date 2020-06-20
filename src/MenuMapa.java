import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuMapa extends Ekran {

	Okno okno;

	public MenuMapa(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void mouseReleased(MouseEvent e) {
		okno.zakladki.show(okno.panelGlowny, "menuGracze");
		okno.menuGracze.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("MM " + e.getKeyChar());
	}
}
