package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainClass extends JFrame implements ActionListener{

	static boolean quadTreeBoolean = true;
	int quantidade = 5000;
	Timer tempo = new Timer(10,this);
	static int tamanhoX = 500,tamanhoY = 500;
	static int tamanhoCirculo = 3;
	static int quantidadeParticulasSubdividir = 10;
	
	static double tempoGastoNaColisaoAtual;

	static double start2fim, start2Inicio;
	 
	static boolean checagemUmCicloColisao = false;
	static double tempoGastoUmCicloColisao;
	static Random rand;
	ParticulaCirculo particula[], irmaos[];
	double segundos = 0;
	MeuCanvas canvas = new MeuCanvas(quantidade);
	boolean podeIniciar = false;
	ArrayList<ParticulaCirculo> particulas;
	QuadTree quadtree;
	Retangulo retangulo;
	
	
	public static void main(String[] args) {
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
		start2Inicio = System.nanoTime();
	    
	    for (int i = 0; i < particula.length; i++) {
			particula[i] = new ParticulaCirculo(rand.nextInt(tamanhoX), rand.nextInt(tamanhoY), tamanhoCirculo, tamanhoX,tamanhoY);
			irmaos[i] = particula[i];
			particulas.add(particula[i]);
			//quadtree.InserirNoArray(particula[i]);
	    }
	    
	    if(quadTreeBoolean==false)
	    {
	    	for (int i = 0; i < particula.length; i++) {
				for (int j = i+1; j < particula.length; j++) {
					if(i==j) {
						
					} else {
						irmaos[i].AdicionarIrmaos(particula[j]); 
					}
				}
		
		    }
	    } else {
	    	quadtree = new QuadTree(retangulo, quantidadeParticulasSubdividir);
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
		if(quadTreeBoolean && quadtree!=null) {
			quadtree = new QuadTree(retangulo, quantidadeParticulasSubdividir);
			quadtree.InserirNoArray(particulas);
			canvas.EnvioDaQuadTree(quadtree);
			
			quadtree.ColisaoQuadTree();
			
		}
		//System.out.println(tempoGastoNaColisaoAtual/10000000 + " colisao ");
		// start2 += System.currentTimeMillis()/1000000000;
		start2fim = System.nanoTime();
		// System.out.println((start2fim-start2Inicio)/1000000000 + " tempo da simulaçao ");
		
		  if(podeIniciar){
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
