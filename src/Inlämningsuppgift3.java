import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Inlämningsuppgift3 extends JFrame implements ActionListener 
{
	
	
	JFrame frame = new JFrame("15-spel");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JButton nyttSpel = new JButton("Nytt Spel");
	JButton [] arrayOfButtons1dimension = new JButton[16];
	JButton [][] arrayOfButtons = new JButton[4][4];
	JButton [][] compareArrayOfButtons = new JButton[4][4];

	
	
	Inlämningsuppgift3()
	{
		frame.add(panel, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setLocation(900, 300);
		frame.setSize(500, 500);
		panel2.setPreferredSize(new Dimension(200, 100));
		panel.setLayout(new GridLayout(4,4));
		panel2.add(nyttSpel);
		nyttSpel.addActionListener(this);
		
		
		//1D array för att skapa varje JButton med tilldelat strängvärde 1-16
		for (int i = 0; i < arrayOfButtons1dimension.length; i++) 
		{
			arrayOfButtons1dimension[i] = new JButton(String.valueOf(i+1));
		}
		
		
		
		//Blanda 1d array:en
		Collections.shuffle(Arrays.asList(arrayOfButtons1dimension));
		
		
		//lägger in blandad 1D-array in i ny 2D-array samt sätter lyssnare på varje knapp, och ruta nr 16 som 
		//den vita rutan
		int p = 0;
		for (int r = 0; r < arrayOfButtons.length; r++) 
		{
			for (int c = 0; c < arrayOfButtons.length; c++) 
			{
				arrayOfButtons[r][c] = arrayOfButtons1dimension[p];
				panel.add(arrayOfButtons[r][c]);
				String buttonText =	arrayOfButtons[r][c].getText();
				p++;
				arrayOfButtons[r][c].addActionListener(this);
				
				if (buttonText.equals("16")) 
				{
					arrayOfButtons[r][c].setBackground(Color.white);
					arrayOfButtons[r][c].setText("");
				}
			} 
		}
		
		
		//array för att jämföra med när man avklarat spelet, som är i rätt nummerordning
		int x = 0;
		for (int r = 0; r < compareArrayOfButtons.length; r++) 
		{
			for (int c = 0; c < compareArrayOfButtons.length; c++) 
			{
				compareArrayOfButtons[r][c] = new JButton(String.valueOf(x+1));
				String textOfButton = compareArrayOfButtons[r][c].getText();
				x++;

				if (textOfButton.equals("16")) 
				{
					compareArrayOfButtons[r][c].setBackground(Color.white);
					compareArrayOfButtons[r][c].setText("");
				}
			}
		}
		
		
			
	}
		
	
	//metod som kollar om den vita rutan är ovanför, under, bredvid (höger el.vänster) om den tryckta nuvarande knappen
	 public void flyttaRuta (int x, int y) 
	 {
	
		 	//om rutan UNDER nuvarande knappen är vit, byter de färg och siffra/tom med varann
			if (y < arrayOfButtons.length-1 && arrayOfButtons [x][y+1].getBackground( ) == Color.white)
			{
				arrayOfButtons [x][y+1].setText (arrayOfButtons [x][y].getText());
				arrayOfButtons [x][y+1].setBackground(null);
				arrayOfButtons [x][y].setText("");
				arrayOfButtons [x][y].setBackground (Color.white);
			}
			
			//om rutan OVANFÖR nuvarande knappen är vit, byter de färg och siffra/tom med varann
			else if (y > 0 && arrayOfButtons [x][y -1].getBackground( ) == Color.white)
			{
				arrayOfButtons [x][y-1].setText (arrayOfButtons [x][y].getText());
				arrayOfButtons [x][y-1].setBackground(null);
				arrayOfButtons [x][y].setText("");
				arrayOfButtons [x][y].setBackground (Color.white);
			}
		 
			//om rutan HÖGER om den nuvarande knappen är vit, byter de färg och siffra/tom med varann
			else if (x < arrayOfButtons.length-1 && arrayOfButtons [x+1][y].getBackground( ) == Color.white)
			{
				arrayOfButtons [x+1][y].setText (arrayOfButtons [x][y].getText());
				arrayOfButtons [x+1][y].setBackground(null);
				arrayOfButtons [x][y].setText("");
				arrayOfButtons [x][y].setBackground (Color.white);
			}
			
			//om rutan VÄNSTER om den nuvarande knappen är vit, byter de färg och siffra/tom med varann
			else if (x > 0 && arrayOfButtons [x -1][y].getBackground( ) == Color.white)
			{
				arrayOfButtons [x-1][y].setText (arrayOfButtons [x][y].getText());
				arrayOfButtons [x-1][y].setBackground(null);
				arrayOfButtons [x][y].setText("");
				arrayOfButtons [x][y].setBackground (Color.white);
			}
				
				
	 }
				
			
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		Object source = e.getSource();
		
		if(nyttSpel == source)
		{

			//blanda 1D-arrayen, sätt in i 2D-array, samt ge lyssnare och lägga ruta16 som vita rutan
			Collections.shuffle(Arrays.asList(arrayOfButtons1dimension));
			
			int p = 0;
			for (int r = 0; r < arrayOfButtons.length; r++) 
			{
				for (int c = 0; c < arrayOfButtons.length; c++) 
				{
					arrayOfButtons[r][c] = arrayOfButtons1dimension[p];
					panel.add(arrayOfButtons[r][c]);
					String buttonText =	arrayOfButtons[r][c].getText();
					p++;
					arrayOfButtons[r][c].addActionListener(this);
					
					if (buttonText.equals("16")) 
					{
						arrayOfButtons[r][c].setBackground(Color.white);
						arrayOfButtons[r][c].setText("");
			
					}
				
				} 
			}
			
			frame.revalidate();
			frame.repaint();
		}
	
		//nästlad loop som kollar vilken knapp som blev tryckt och därmed kallar på metoden slideTile
		for (int r = 0; r < arrayOfButtons.length; r++) 
		{
			for (int c = 0; c < arrayOfButtons.length; c++) 
			{
				if(arrayOfButtons[r][c] == source)
				{
					flyttaRuta (r, c);
				
				}					
			}
		}
		
		
		
		//nästlad loop som jämför den nuvarande arrayen med en array med korrekt nummerordning 
		//för att kolla om uppställningen i arrayen är korrekt (dvs klarat spelet)
		int räknare = 0;
		for (int i = 0; i < arrayOfButtons.length; i++) 
		{
			for (int j = 0; j < arrayOfButtons.length; j++) 
			{
				String textOfCurrent =  arrayOfButtons[i][j].getText();
				String textOfCorrect = compareArrayOfButtons[i][j].getText();
				
				if(textOfCurrent.equals(textOfCorrect))
				{
					räknare++;
				}
				
			}
		}
		
		
		if(räknare == 16)
		{
			JOptionPane.showMessageDialog(null, "Grattis, du vann!");
		}
		
	
	}
		
	
	
	public static void main(String[] args) 
	{
		
		Inlämningsuppgift3 ny = new Inlämningsuppgift3();
		
	}



}

