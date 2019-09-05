package usluge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;

public class Potrosac extends Thread {
	private int minVreme,maxVreme;
	private boolean radi;
	private Skladiste skladiste;
	
	private Panel panel=new Panel(new BorderLayout());
	private Label labela=new Label("Potrosac");
	private List lista = new List();
	
	public Potrosac(Skladiste s,int minVr,int maxVr) {
		skladiste=s;
		
		minVreme=minVr;
		maxVreme=maxVr;
		
		panel.add(labela,BorderLayout.NORTH);
		panel.add(lista,BorderLayout.CENTER);
		
		//radi = true;
		start();
	}
	
	public void setMinVreme(int minVreme) {
		this.minVreme = minVreme;
	}



	public void setMaxVreme(int maxVreme) {
		this.maxVreme = maxVreme;
	}

	public void run() {
		try {
			while(!interrupted()) {
				synchronized (this) {
					while(!radi) { wait();lista.setBackground(Color.red);};
					lista.setBackground(Color.white);
					sleep((long)(minVreme + Math.random()*(maxVreme-minVreme)));
					int vrednost = skladiste.uzmi();
					lista.add(Integer.toString(vrednost));
					notifyAll();
				}
			}
		}catch (InterruptedException e) {
			System.out.println("Interrupted Exception!");
		}
	}
	
	public Panel getPanel() {
		return panel;
	}
	
	public synchronized void pokreni() {
		lista.setBackground(Color.WHITE);
		radi=true;
		notifyAll();
	}
	
	public void zaustavi() {
		lista.setBackground(Color.RED);
		radi = false;
	}
	
	public void prekini() {
		interrupt();
	}
}
