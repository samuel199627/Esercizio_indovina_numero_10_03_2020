package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtRimasti;

    @FXML
    private Button newBtn;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtProva;

    @FXML
    private Button tryBtn;

    @FXML
    private TextArea txtRis;

    @FXML
    void doNuova(ActionEvent event) {

    	//comunico al modello che voglio iniziare una nuova partita per risettare le variabili correttamente
    	model.nuovaPartita();
    	
    	
    	//gestione dell'interfaccia
    	//abilitiamo la possibilità di giocare una volta che premo sulla nuova partita e dunque sulla possibilità di inserire i numeri da tastiera
    	layoutTentativo.setDisable(false);
    	//puliamo l'area di testo in fondo da eventuali residui di partite precedenti
    	txtRis.clear();
    	//Inseriamo nell'apposita casella di testo il numero di tentativi che abbiamo che all'inzio sono tutti quelli disponibili
    	txtRimasti.setText(Integer.toString(model.getTMAX()));
    	//mi ero dimenticato di farlo dalla parte dello scene builder quindi lo faccio qui
    	txtRimasti.setDisable(true);
    	
    
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	//leggo l'input dell'utente ed essendo questa parte di controllo del numero inserito esclusivamente
    	//legata alla vista la lasciamo qui e non la passiamo al modello perche' qui non stiamo controllando
    	//se il numero inserito e' nell'intervallo accettabile (in quanto quella e' una cosa che spetta al modello)
    	//ma qui si sta controllando se abbiamo effettivamente inserito un numero e non qualche altra cosa
    	int tentativo;
    	try {
    		tentativo=Integer.parseInt(txtProva.getText());
    	}
    	catch(NumberFormatException e){
    		//con appendText() si concatena la stringa a quello che c'è già in precedenza, mentre con setText() si sostituisce il testo precedente con quello che inseriamo
    		txtRis.appendText("\nDEVI INSERIRE UN NUMERO!!");
    		return;
    	}
    	
    	//teniamo traccia di ciò che inseriamo
    	
    	
    	int risultato=-1; //valore di default
    	
    	
    	try {
    		
    		risultato=this.model.tentativo(tentativo);
    	}
    	catch(IllegalStateException ie) {
    		//appendiamo al video il messaggio creato dall'eccezione che abbiamo definito in modello
    		txtRis.appendText(ie.getMessage());
    		return; //se trovo un'eccezione non vado logicamente avanti con il programma e dunque ritorno
    	}
    	catch(InvalidParameterException ipe) {
    		//appendiamo al video il messaggio creato dall'eccezione che abbiamo definito in modello
    		txtRis.appendText(ipe.getMessage());
    		return;//se trovo un'eccezione non vado logicamente avanti con il programma e dunque ritorno
    	}
    	
    	if(risultato==0) {
    		txtRis.appendText("\n COMPLIMENTIIIII!! \n Hai vinto!! Hai utilizzato "+model.getNumeroTentativi()+" tentativi.");
    		//una volta che ho indovinato non vado più a fare nulla logicamente e dunque disattivo il box con il pulsante di prova e la casella di testo associata
    		layoutTentativo.setDisable(true);
    		
    		return;
    	}
    	else {
    		if(model.getNumeroTentativi()==model.getTMAX()) {
    			txtRis.appendText("\nHAI PERSO! il numero segreto era...\n "+model.getNumeroSegreto());
        		txtRimasti.setText(Integer.toString(model.getTMAX()-model.getNumeroTentativi()));
        		
        		layoutTentativo.setDisable(true);
        		return;
    		}
    		txtRis.appendText("\nNon hai vinto!! Hai utilizzato per ora "+model.getNumeroTentativi()+" tentativi.");
    		txtRimasti.setText(Integer.toString(model.getTMAX()-model.getNumeroTentativi()));
    		if(risultato==-1) {
    			txtRis.appendText(" Tentativo troppo bassoooo...\n");
        		
    		}
    		else {
    			txtRis.appendText(" Tentativo troppo alto...\n");
        		
    		}
    	}
    	

    }

    @FXML
    void initialize() {
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert newBtn != null : "fx:id=\"newBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtProva != null : "fx:id=\"txtProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tryBtn != null : "fx:id=\"tryBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRis != null : "fx:id=\"txtRis\" was not injected: check your FXML file 'Scene.fxml'.";

        //ricordiamo che il modello contiene la o le classi che sono legate alla gestione dei dati
        //non da errore crearlo qui in questo spazio quando il controller viene inizializzato, pero' 
        //concettualmente non e' corretto perche' in questo modo ci vincoliamo troppo, cioe' andiamo
        //a creare un legame molto stretto tra il controller e il modello, cose che in realta' dobbiamo
        //cercare di tenere il piu' separate possibili.
        
        //this.model=new Model();
        
        //il problema della parte di codice qui sopra e' che io potrei volere un nuovo modello senza necessariamente
        //aver cambiato controller, invece creando il modello quando creo il controller, ci vincoliamo che fino a che
        //abbiamo quel controller non possiamo andare ad usufruire di un nuovo modello.
        
    }
    
    //ha senso quindi per quanto riguarda il modello andare a crearsi un metodo esterno che importa solamente il modello
    //e non lo va a creare.
    //Il modello lo andiamo a creare in Entry Point
    public void setModel(Model model) {
    	this.model=model;
    	
    }
}


