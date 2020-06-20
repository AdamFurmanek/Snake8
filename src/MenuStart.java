import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuStart extends Ekran {

	Okno okno;

	public MenuStart(Okno okno) {
		this.okno = okno;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void mouseReleased(MouseEvent e) {
		okno.zakladki.show(okno.panelGlowny, "menuTryb");
		okno.menuTryb.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("MS " + e.getKeyChar());
	}
}
