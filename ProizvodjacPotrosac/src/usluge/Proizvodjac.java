package usluge;

import java.awt.*;

public class Proizvodjac extends Thread {
	private int br=0;
	private int minVreme,maxVreme;
	private boolean radi;
	private Skladiste skladiste;
	
	private Panel panel=new Panel(new BorderLayout());
	private Label labela=new Label("Proizvodjac");
	private List lista = new List();
	
	public Proizvodjac(Skladiste s,int minVr,int maxVr) {
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
					while(!radi) { wait();lista.setBackground(Color.red);}
					lista.setBackground(Color.white);
					lista.add(Integer.toString(br++));
					sleep((long)(minVreme + Math.random()*(maxVreme-minVreme)));
					int vrednost = Integer.parseInt(lista.getItem(0));
					lista.remove(0);
					skladiste.stavi(vrednost);
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
