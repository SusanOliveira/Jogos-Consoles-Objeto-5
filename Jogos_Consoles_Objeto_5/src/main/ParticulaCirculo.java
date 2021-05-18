package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ParticulaCirculo extends Canvas{

	int x,y,radio;
	//ThreadFisica thread;
	double tempo,tempoPassado;
	int tamanhoX,TamanhoY;
	 int velocidadeX,velocidadeY;
	boolean colidiu = false;
	//ParticulaCirculo[] irmaos;
	ArrayList<ParticulaCirculo> irmaos;
	Random rand;
	//int tempo;
	public ParticulaCirculo(int x, int y, int radio,int tamanhoX,int tamanhoY)
	{
		rand = new Random();
		irmaos = new ArrayList<ParticulaCirculo>();
		this.x = x;
		this.y = y;
		this.radio = radio;

		this.tamanhoX = tamanhoX;
		this.TamanhoY = tamanhoY;
		
		if(rand.nextBoolean())
		velocidadeX = 1;
		else
		velocidadeX = -1;
		if(rand.nextBoolean())
		velocidadeY = 1;
		else
			velocidadeY=-1;
		tempo = 0;
	}


	public void Colisao()
	{
		for (int i = 0; i < irmaos.size(); i++) {
			double xDif = x - irmaos.get(i).x;
			double yDif = y - irmaos.get(i).y;
			double distanceSquared = xDif * xDif + yDif * yDif;
			boolean collision = distanceSquared < (radio ) * ( irmaos.get(i).radio);
			if(collision == true)
			{

				colidiu = true;
				if(irmaos.get(i).velocidadeX != this.velocidadeX)
				{
				velocidadeX *= -1;
				irmaos.get(i).velocidadeX *= -1;
				}
				if(irmaos.get(i).velocidadeY != this.velocidadeY)
				{
				velocidadeY *= -1;
				irmaos.get(i).velocidadeY *= -1;
				}

			}
		}

	//	System.out.print("colidiu");
	}
	
	public void AdicionarIrmaos(ParticulaCirculo irmaos)
	{
		//this.irmaos = irmaos;
		this.irmaos.add(irmaos);
	}

	public void andar(double segundos)
	{
		
		if(this.x>=tamanhoX-radio )
		{
			
			colidiu = true;
			velocidadeX = -1;
		}
		else if( this.x<=0)
		{
			velocidadeX = 1;
			colidiu = true;
		}
		
		if(this.y>=TamanhoY-radio)
		{
			//velocidadeY *= -1;
			colidiu = true;
			velocidadeY = -1;
		}
		else if( this.y<=0)
		{
			velocidadeY = 1;
			colidiu = true;
		}
		if(colidiu)
		{
			tempoPassado = tempo - segundos;
			//this.tempo-= segundos;
		}
		if(tempoPassado <=0 )
		{
			colidiu = false;
			this.tempo = segundos+1;
		}
		this.y += velocidadeY;
		this.x += velocidadeX;
	//	System.out.println(this.tempo);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//System.out.println("particula na tela");
		
		
		if(colidiu)
		{
			g.setColor(Color.red);
		}
		else
		{
			g.setColor(new Color(0,0,0));
		}
		g.fillOval(x, y, radio, radio);
	}

}

