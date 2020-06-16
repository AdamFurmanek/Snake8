
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.*; 


public class CardLayoutDemo extends JFrame { 

	private int currentCard = 1; 
	private CardLayout cl; 

	public CardLayoutDemo() 
	{ 

		setTitle("Card Layout Example"); 
		setSize(300, 150); 
		
		JPanel cardPanel = new JPanel(); 
		cl = new CardLayout(); 
		cardPanel.setLayout(cl); 

		JPanel jp1 = new JPanel(); 
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel(); 
		JPanel jp4 = new JPanel(); 


		JLabel jl1 = new JLabel("Card1"); 
		JLabel jl2 = new JLabel("Card2"); 
		JLabel jl3 = new JLabel("Card3"); 
		JLabel jl4 = new JLabel("Card4"); 

		jp1.add(jl1); 
		jp2.add(jl2); 
		jp3.add(jl3); 
		jp4.add(jl4); 

		cardPanel.add(jp1, "1"); 
		cardPanel.add(jp2, "2"); 
		cardPanel.add(jp3, "3"); 
		cardPanel.add(jp4, "4"); 

		JPanel buttonPanel = new JPanel(); 

		JButton firstBtn = new JButton("First"); 
		JButton nextBtn = new JButton("Next"); 
		JButton previousBtn = new JButton("Previous"); 
		JButton lastBtn = new JButton("Last"); 

		buttonPanel.add(firstBtn); 
		buttonPanel.add(nextBtn); 
		buttonPanel.add(previousBtn); 
		buttonPanel.add(lastBtn); 

		firstBtn.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent arg0) 
			{ 
				
				// used first c1 CardLayout 
				cl.first(cardPanel); 

				// value of currentcard is 1 
				currentCard = 1; 
			} 
		}); 

		// add lastbtn in ActionListener 
		lastBtn.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent arg0) 
			{ 

				// used last c1 CardLayout 
				cl.last(cardPanel); 

				// value of currentcard is 4 
				currentCard = 4; 
			} 
		}); 

		// add nextbtn in ActionListener 
		nextBtn.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent arg0) 
			{ 

				// if condition apply 
				if (currentCard < 4) 
				{ 
					
					// increment the value of currentcard by 1 
					currentCard += 1; 

					// show the value of currentcard 
					cl.show(cardPanel, "" + (currentCard)); 
				} 
			} 
		}); 

		// add previousbtn in ActionListener 
		previousBtn.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent arg0) 
			{ 
				// if condition apply 
				if (currentCard > 1) { 

					// decrement the value 
					// of currentcard by 1 
					currentCard -= 1; 

					// show the value of currentcard 
					cl.show(cardPanel, "" + (currentCard)); 
				} 
			} 
		}); 

		// used to get content pane 
		getContentPane().add(cardPanel, BorderLayout.NORTH); 

		// used to get content pane 
		getContentPane().add(buttonPanel, BorderLayout.SOUTH); 
	} 

	// Main Method 
	public static void main(String[] args) 
	{ 

		// Creating Object of CardLayoutDemo class. 
		CardLayoutDemo cl = new CardLayoutDemo(); 

		// Function to set default operation of JFrame. 
		cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		// Function to set vivibility of JFrame. 
		cl.setVisible(true); 
	} 
} 
