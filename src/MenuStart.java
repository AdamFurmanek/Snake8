import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuStart extends JPanel implements ActionListener{

	Okno okno;
	Font font;
	double skalaWysokoscOkno, skalaSzerokoscOkno;
	JButton start = new JButton("Start");
	
	public MenuStart(Okno okno) {
		this.okno = okno;
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				skalaSzerokoscOkno = (double) okno.getWidth() / 1920;
				skalaWysokoscOkno = (double) okno.getHeight() / 1080;
				repaint();
			}
		});
		
		start.setBounds(600,0,200,50);
		add(start);
		start.addActionListener(this);
	}

	public void paintComponent(Graphics g) {


		font = new Font("Gill Sans Ultra Bold", Font.BOLD, 72);

		Graphics2D g2d = (Graphics2D) g.create();

		// USTAWIENIE SKALI NA PODSTAWIE WIELKOSCI OKNA
		AffineTransform at = new AffineTransform();
		at.scale(skalaSzerokoscOkno, skalaWysokoscOkno);
		g2d.setTransform(at);

		// RYSOWANIE TLA
		new ImageIcon("images/tlo1.png").paintIcon(this, g2d, 0, 0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if(zrodlo==start)
			okno.ukladZakladek.next(okno.panelGlowny);
	}

}
