package it.polito.tdp.indovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	//contiene la logica dell'applicazione
	
	private final int NMAX=100;
	private final int TMAX=8;
	private int numeroSegreto;
	private int numeroTentativi;
	private boolean inGioco;
	private Set<Integer> tentativi; //contenitore che tiene traccia della partita corrente e molto comodo per ricercare elementi in esso
	
	public Model() {
		this.inGioco=false;
		
	}
	
	
	
	
	
	public Set<Integer> getTentativi() {
		return tentativi;
	}





	public void setTentativi(Set<Integer> tentativi) {
		this.tentativi = tentativi;
	}





	public int getNumeroSegreto() {
		return numeroSegreto;
	}



	public void setNumeroSegreto(int numeroSegreto) {
		this.numeroSegreto = numeroSegreto;
	}



	public int getNumeroTentativi() {
		return numeroTentativi;
	}



	public void setNumeroTentativi(int numeroTentativi) {
		this.numeroTentativi = numeroTentativi;
	}



	public boolean isInGioco() {
		return inGioco;
	}



	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}



	public int getNMAX() {
		return NMAX;
	}



	public int getTMAX() {
		return TMAX;
	}



	public void nuovaPartita() {
		//quando l'utente clicca una nuova partita
    	//Math.random() restituisce un numero casuale tra 0 e 0.999
    	//(int)(Math.random()*NMAX) abbiamo la parte intera di (0%99.9)
    	//dove la parte intera tronca
    	//serve quindi il +1 per avere il 100 anche tra i valori casuali possibili
    	this.numeroSegreto=((int)(Math.random()*NMAX))+1;
    	this.numeroTentativi=0;
    	this.inGioco=true;
    	//ogni volta che creiamo una nuova partita azzeriamo lo storico di vecchie partite e creiamo il posto per questa nuova
    	this.tentativi=new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		//tentativo e' cio' che l'utente ha messo nella tastiera, cioe' e' appunto il tentativo che ha fatto
		
		
		//controllo se la partita e' in corso
		if(!inGioco) {
			//l'utente ha fatto un tentativo anche se non e' in gioco e dunque c'e' un errore
			//perche' non dovrebbe giocare
			//scateno un'eccezione
			throw new IllegalStateException("La partita e' gia' terminata");
		}
		
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("\nDevi inserire un numero accettabile (tra 1 e NMAX oppure che non hai ancora utilizzato)!!");
		}
		
		this.numeroTentativi++;
		
		if(tentativo==this.numeroSegreto) {
    		return 0;
    	}
    	else {
    		if(this.numeroTentativi==TMAX) {
    			this.inGioco=false;
        		
    		}
    		
    		if(tentativo<numeroSegreto) {
    			return -1;
        		
    		}
    		else {
    			return 1;
        		
    		}
    	}
		
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1||tentativo>NMAX) {
			return false;	
			}
		else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			tentativi.add(tentativo);
			return true;
		}
	}

}
