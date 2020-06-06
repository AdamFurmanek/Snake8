import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Rozgrywka extends JPanel {

	Okno okno;
	double skalaWysokosc, skalaSzerokosc;

	public Rozgrywka(Okno okno) throws Exception {
		this.okno = okno;
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaWysokosc = (double) okno.getHeight() / 1080;
				skalaSzerokosc = (double) okno.getWidth() / 1920;
				System.out.println(skalaSzerokosc);
				System.out.println(skalaWysokosc);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokosc, skalaSzerokosc);
		g2d.setTransform(at);

		for (int i = 0; i < okno.szerokoscMapy; i++) {
			for (int j = 0; j < okno.wysokoscMapy; j++) {
				new ImageIcon("images/0.png").paintIcon(this, g2d, i * 200, j * 200);
			}
		}
	}
}
