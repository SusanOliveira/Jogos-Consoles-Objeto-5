package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Retangulo extends Canvas{

	float PosX,PosY,width,height;
	public Retangulo(float PosX, float PosY, float width, float height)
	{
		this.PosX = PosX;
		this.PosY = PosY;
		this.width = width;
		this.height = height;
	}
	
	public boolean contem(ParticulaCirculo particula)
	{
		boolean temp = false;
		if(particula.x>this.PosX &&
				particula.x<this.PosX+this.width &&
				particula.y>this.PosY &&
				particula.y<this.PosY+this.height)
		{
			temp = true;
		}
		return temp;
	}
	public boolean contemDentro(ParticulaCirculo particula)
	{
		boolean contem = (particula.x + MainClass.tamanhoCirculo > PosX && particula.x < PosX + width && particula.y + MainClass.tamanhoCirculo > PosY && particula.y < PosY + height);
		return contem;
	}
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//System.out.println("particula na tela");
		g.drawRect((int)PosX, (int)PosY, (int)width, (int)height);

	}
}
