package usluge;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;

public class Skladiste {
	private int niz[],br=0,ulaz=0,izlaz=0;

	
	private Panel panel=new Panel(new BorderLayout());
	private Label labela=new Label("Skladiste");
	private List lista = new List();
	
	public Skladiste(int kap) {
		niz = new int[kap];
		
		panel.add(labela,BorderLayout.NORTH);
		panel.add(lista,BorderLayout.CENTER);
	}
	
	public Panel getPanel() {
		return panel;
	}
	
	public synchronized void stavi(int vrednost) {
			try {
				if(br==niz.length) wait();
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception!");
				e.printStackTrace();
			}
			niz[ulaz++] = vrednost;
			if(ulaz==niz.length) ulaz=0;
			br++;
			lista.add(Integer.toString(vrednost));
			notifyAll();
	}
	
	public synchronized int uzmi() {
		try {
			if(br==0) wait();
			
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception!");
			e.printStackTrace();
		}
		int vrednost=niz[izlaz++];
		if(izlaz==niz.length) izlaz=0;
		br--;
		lista.remove(0);
		notifyAll();
		return vrednost;
}
}
