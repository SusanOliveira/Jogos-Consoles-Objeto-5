package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MeuCanvas extends JPanel implements ActionListener{

	ParticulaCirculo particula[];
	QuadTree quadtree;
	public MeuCanvas(int quantidadeDeParticulas)
	{
		particula = new ParticulaCirculo[quantidadeDeParticulas];
	}
	
	public void EnvioDeParticulas(ParticulaCirculo[] particulas)
	{
		 particula = particulas;
		 
	}
	public void EnvioDaQuadTree(QuadTree valor)
	{
		quadtree = valor;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.setBackground(new Color(255,255,255));
		if(MainClass.quadTreeBoolean==false)
		g.drawRect(0, 0, MainClass.tamanhoX, MainClass.tamanhoY);
		if(quadtree!=null)
		quadtree.paint(g);
		
		if(particula[0]!=null)
		{
			for (int i = 0; i < particula.length; i++) {
				particula[i].paint(g);
				
			}
		}

				
		}
		
		
	}


