package usluge;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jdk.internal.org.objectweb.asm.Label;

public class Prozor extends Frame {
	
	Skladiste s=new Skladiste(100);
	
	private Proizvodjac proizvodjac=new Proizvodjac(s,100,200);
	private Potrosac potrosac=new Potrosac(s,1000,2000);
	
	private Button start,stop,prekini,setuj;
	private TextField minVremeProizv,minVremePotr,maxVremeProiz,maxVremePotr;
	
	public Prozor() {
		super("Proizvodjac/Potrosac");
		setBounds(100,100,500,500);
		Panel centar = new Panel(new GridLayout(1,3));
		setLayout(new BorderLayout());
		
		centar.add(proizvodjac.getPanel());
		centar.add(s.getPanel());
		centar.add(potrosac.getPanel());
		
		add(centar,BorderLayout.CENTER);
		dodajDugmad();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				proizvodjac.prekini();
				potrosac.prekini();
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Prozor();

	}
	
	public void dodajDugmad() {
		Panel jug = new Panel(new GridLayout(2,3));
		
		start = new Button ("Start");
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				proizvodjac.pokreni();
				potrosac.pokreni();
			}
		});
		
		stop = new Button ("Stop");
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				proizvodjac.zaustavi();
				potrosac.zaustavi();
			}
		});
		
		prekini = new Button ("Prekini");
		prekini.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				proizvodjac.prekini();
				potrosac.prekini();
				dispose();
			}
		});
		
		jug.add(start);
		jug.add(stop);
		jug.add(prekini);
		
		minVremeProizv= new TextField();
		minVremePotr = new TextField();
		maxVremePotr= new TextField();
		maxVremeProiz = new TextField();
		
		Panel levo = new Panel(new GridLayout(2,2));
		levo.add(new java.awt.Label("Min vreme: "));
		levo.add(minVremeProizv);
		levo.add(new java.awt.Label("Max vreme: "));
		levo.add(maxVremeProiz);
		
		Panel desno = new Panel(new GridLayout(2,2));
		desno.add(new java.awt.Label("Min vreme: "));
		desno.add(minVremePotr);
		desno.add(new java.awt.Label("Max vreme: "));
		desno.add(maxVremePotr);
		
		jug.add(levo);
		
		setuj = new Button("Setuj");
		setuj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int minVremeProi=Integer.parseInt(minVremeProizv.getText());
				int maxVremeProi=Integer.parseInt(maxVremeProiz.getText());
				
				int minVremePot=Integer.parseInt(minVremePotr.getText());
				int maxVremePot=Integer.parseInt(maxVremePotr.getText());
				
				proizvodjac.setMinVreme(minVremeProi);
				proizvodjac.setMaxVreme(maxVremeProi);
				
				potrosac.setMinVreme(minVremePot);
				potrosac.setMaxVreme(maxVremePot);
			}
		});
		jug.add(setuj);
		
		jug.add(desno);
		
		add(jug,BorderLayout.SOUTH);
	
	}

}
