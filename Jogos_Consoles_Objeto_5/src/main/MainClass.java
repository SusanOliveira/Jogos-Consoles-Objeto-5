package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainClass extends JFrame implements ActionListener{
	
	static boolean quadTreeBoolean = false;
	int quantidade = 800;
	Timer tempo = new Timer(10,this);
	static int tamanhoX = 600,tamanhoY = 600;
	static int tamanhoCirculo = 5;
	
	static Random rand;
	ParticulaCirculo particula[], irmaos[];
	double segundos = 0;
	MeuCanvas canvas = new MeuCanvas(quantidade);
	boolean podeIniciar = false;
	ArrayList<ParticulaCirculo> particulas;
	QuadTree quadtree;
	Retangulo retangulo;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainClass mainClass = new MainClass();
	}
	
	public MainClass()
	{
		tempo.start();
		rand = new Random();
	//	tamanhoX = 400;
	//	tamanhoY = 400;
		setLayout (new BorderLayout ());
		setSize(tamanhoX, tamanhoY);
		setTitle("Meu canvas teste");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	
		particulas = new ArrayList<ParticulaCirculo>();
	    particula = new ParticulaCirculo[quantidade];
	    irmaos = new ParticulaCirculo[particula.length];
		retangulo = new Retangulo(0, 0, tamanhoX, tamanhoY);
		
	    
	    for (int i = 0; i < particula.length; i++) {
			particula[i] = new ParticulaCirculo(rand.nextInt(tamanhoX), rand.nextInt(tamanhoY), tamanhoCirculo, tamanhoX,tamanhoY);
			irmaos[i] = particula[i];
			particulas.add(particula[i]);
			//quadtree.InserirNoArray(particula[i]);
	    }
	    
	    if(quadTreeBoolean==false)
	    {
	    	for (int i = 0; i < particula.length; i++) {
				for (int j = 0; j < particula.length; j++) {
					if(i==j)
					{
						
					}
					else
					{
						irmaos[i].AdicionarIrmaos(particula[j]); 
					}
				}
		
	    	}
	    }
	    else
	    {
	    
	    	quadtree = new QuadTree(retangulo, 4);
	    	quadtree.InserirNoArray(particulas);
	    	canvas.EnvioDaQuadTree(quadtree);
	    	//System.out.println(particulas.size());
	    }
	
	    canvas.EnvioDeParticulas(particula);
	  
	    
	
	    podeIniciar = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		segundos+=0.1;
		//System.out.println((int)segundos);
		if(quadTreeBoolean && quadtree!=null)
		{
		quadtree = new QuadTree(retangulo, 4);
		quadtree.InserirNoArray(particulas);
		canvas.EnvioDaQuadTree(quadtree);
		
		quadtree.ColisaoQuadTree();
			
		}
		
		
		
		
		
		
		if(podeIniciar)
		{
		    for (int i = 0; i < particula.length; i++) {
				particula[i].andar(segundos);
				
				//quadtree.InserirNoArray(particula[i]);
				canvas.EnvioDeParticulas(particula);
		    	canvas.repaint();
			}
		}
		// TODO Auto-generated method stub
		
	}
	

}
