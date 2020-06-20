import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuGracze extends Ekran {

	Okno okno;

	public MenuGracze(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void mouseReleased(MouseEvent e) {
		okno.zakladki.show(okno.panelGlowny, "ekranGry");
		okno.ekranGry.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("MG " + e.getKeyChar());
	}
}
