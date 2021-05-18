package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MeuCanvas extends JPanel implements ActionListener{

	ParticulaCirculo particula[];
	public MeuCanvas(int quantidadeDeParticulas)
	{
		particula = new ParticulaCirculo[quantidadeDeParticulas];
	}
	
	public void EnvioDeParticulas(ParticulaCirculo[] particulas)
	{
		 particula = particulas;
		 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.setBackground(new Color(255,255,255));
		
		if(particula!=null)
		{
			for (int i = 0; i < particula.length; i++) {
				particula[i].paint(g);
				
			}
		}

				
		}
		
		
	}


