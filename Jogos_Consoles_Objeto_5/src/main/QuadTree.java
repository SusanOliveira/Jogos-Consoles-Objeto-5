package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class QuadTree extends Canvas{
	
	Retangulo quadrante; //acho que é o quadrante
	int capacidade;
	//int [] quantidade;
	ArrayList<ParticulaCirculo> quantidade;
	boolean dividido;
	
	
	
	QuadTree CimaDireita, CimaEsquerda, BaixoDIreita, BaixoEsquerda;
	public QuadTree(Retangulo quadrante, int capacidade)
	{
		this.quadrante = quadrante;
		this.capacidade = capacidade;
		// quantidade = new int[capacidade];
		this.quantidade = new ArrayList<ParticulaCirculo>();
		dividido = false;
		
		//System.out.println("uma nova quadTree");
	}
	public void InserirNoArray(ArrayList<ParticulaCirculo> ArraysPontos)
	{
		//System.out.println("teste 2");
		//checar se havia dentro dos pontos ja salvos
	//	System.out.println("teste" );
		
		if(ArraysPontos.size()<= this.capacidade)
		{
			quantidade = ArraysPontos;
			//System.out.println(quantidade.size() + " " + capacidade );
		}
		else
		{
			if(dividido == false)
			{			
				Subdividir(ArraysPontos);
				//dividido = true;
			}
			
			/*this.CimaDireita.InserirNoArray(ponto);
			this.CimaEsquerda.InserirNoArray(ponto);
			this.BaixoDIreita.InserirNoArray(ponto);
			this.BaixoEsquerda.InserirNoArray(ponto);*/
		}
		
	}
	
	private void Subdividir(ArrayList<ParticulaCirculo> particula)
	{
		//System.out.println("Pos X " + quadrante.PosX + " Pos Y " + quadrante.PosY);
		//System.out.println("width  " + quadrante.width + " height " + quadrante.height);
	//	System.out.println("----------------");
		
		ArrayList<ParticulaCirculo> ArrayCimaDireita = new ArrayList<ParticulaCirculo>();
		ArrayList<ParticulaCirculo> ArrayCimaEsquerda = new ArrayList<ParticulaCirculo>();
		ArrayList<ParticulaCirculo> ArrayBaixoDireita = new ArrayList<ParticulaCirculo>();
		ArrayList<ParticulaCirculo> ArrayBaixoEsquerda = new ArrayList<ParticulaCirculo>();
		
		
		
		Retangulo cimaDireita = new Retangulo(quadrante.PosX +quadrante.width/2,quadrante.PosY ,quadrante.width/2,quadrante.height/2);
		 CimaDireita = new QuadTree(cimaDireita, capacidade);
		
		 Retangulo cimaEsquerda = new Retangulo(quadrante.PosX ,quadrante.PosY ,quadrante.width/2,quadrante.height/2);
		 CimaEsquerda = new QuadTree(cimaEsquerda, capacidade);
		
		 Retangulo baixoDireita = new Retangulo(quadrante.PosX + quadrante.width/2,quadrante.PosY + quadrante.height/2,quadrante.width/2,quadrante.height/2);
		 BaixoDIreita = new QuadTree(baixoDireita, capacidade);
		
		 Retangulo baixoEsquerda = new Retangulo(quadrante.PosX,quadrante.PosY +quadrante.height/2,quadrante.width/2,quadrante.height/2);
		 BaixoEsquerda = new QuadTree(baixoEsquerda, capacidade);
		 
		 
		for (int i = 0; i < particula.size(); i++)
		{
			
			if (CimaDireita.quadrante.contemDentro(particula.get(i)))
			{
				ArrayCimaDireita.add(particula.get(i));
			}
			if (CimaEsquerda.quadrante.contemDentro(particula.get(i)))
			{
				ArrayCimaEsquerda.add(particula.get(i));
			} 
			if (BaixoDIreita.quadrante.contemDentro(particula.get(i)))
			{
				ArrayBaixoDireita.add(particula.get(i));
			}
			if (BaixoEsquerda.quadrante.contemDentro(particula.get(i)))
			{
				ArrayBaixoEsquerda.add(particula.get(i));
			} 	
		}
		
		CimaDireita.InserirNoArray(ArrayCimaDireita);
		CimaEsquerda.InserirNoArray(ArrayCimaEsquerda);
		BaixoDIreita.InserirNoArray(ArrayBaixoDireita);
		BaixoEsquerda.InserirNoArray(ArrayBaixoEsquerda);
		 dividido = true;
		 
		 
		// System.out.println("teste 1");
	}
	
public void ColisaoQuadTree()
{

	if (quantidade.size() > 0)
	{
		long start = System.nanoTime();
	//	System.out.println("chamada");
		for (int i = 0; i < quantidade.size(); i++)
		{
			for (int j = i+1; j < quantidade.size(); j++)
			{
			double xDif = quantidade.get(j).x - quantidade.get(i).x;
			double yDif = quantidade.get(j).y - quantidade.get(i).y;
			double distanceSquared = xDif * xDif + yDif * yDif;
			boolean collision = distanceSquared <= (quantidade.get(i).radio ) * ( quantidade.get(j).radio);
			if(collision == true)
			{

				quantidade.get(i).colidiu = true;
				quantidade.get(j).colidiu = true;
				
				if(quantidade.get(i).velocidadeX != quantidade.get(j).velocidadeX)
				{
					quantidade.get(i).velocidadeX *= -1;
					quantidade.get(j).velocidadeX *= -1;
				} 
				if(quantidade.get(i).velocidadeY != quantidade.get(j).velocidadeY)
				{
					quantidade.get(i).velocidadeY *= -1;
					quantidade.get(j).velocidadeY *= -1;
				}
			}
			
			}

		}
		long end = System.nanoTime();
		MainClass.tempoGastoNaColisaoAtual += end - start;
		if(!MainClass.checagemUmCicloColisao)
		{
			MainClass.checagemUmCicloColisao = true;
			MainClass.tempoGastoNaColisaoAtual = System.nanoTime();//MainClass.start2Inicio
			System.out.println("tempo gasto pra fazer a colsao -> " + ((MainClass.tempoGastoNaColisaoAtual - MainClass.start2Inicio)/1000000000));
		}
	}
	else
	{
		if (CimaDireita != null)
		{
			CimaDireita.ColisaoQuadTree();
			CimaEsquerda.ColisaoQuadTree();
			BaixoDIreita.ColisaoQuadTree();
			BaixoEsquerda.ColisaoQuadTree();
		}
	}
}


	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//System.out.println("particula na tela");
	//	g.drawRect(quadrante.PosX ,quadrante.PosY ,200,200);
		
		quadrante.paint(g);
		
		if(dividido)
		{
			
		//	System.out.println("teste");
			CimaDireita.paint(g);
			CimaEsquerda.paint(g);
			BaixoDIreita.paint(g);
			BaixoEsquerda.paint(g);
		}

		
		//g.fillRect(quadrante.PosX+100,quadrante.PosY +100, 100, 100);
	}
}
