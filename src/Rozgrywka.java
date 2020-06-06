import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Rozgrywka extends JPanel {

	Okno okno;
	double skalaWysokoscOkno, skalaSzerokoscOkno;
	double skalaWysokoscMapa, skalaSzerokoscMapa;

	public Rozgrywka(Okno okno) throws Exception {
		this.okno = okno;
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaWysokoscOkno = (double) okno.getHeight() / 1080;
				skalaSzerokoscOkno = (double) okno.getWidth() / 1920;
			}
		});
		skalaWysokoscMapa=(double)880/(okno.wysokoscMapy*200);
		skalaSzerokoscMapa=(double)1720/(okno.szerokoscMapy*200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();

		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno*skalaSzerokoscMapa, skalaWysokoscOkno*skalaWysokoscMapa);
		g2d.setTransform(at);
		
		for (int i = 0; i < okno.szerokoscMapy; i++) {
			for (int j = 0; j < okno.wysokoscMapy; j++) {
				new ImageIcon("images/0.png").paintIcon(this, g2d, (int)(94/(skalaSzerokoscMapa) + i * 200), (int)(77/(skalaWysokoscMapa) + j * 200));
			}
		}
	}
}
