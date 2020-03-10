package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX=100;
	private final int TMAX=8;
	private int numeroSegreto;
	private int numeroTentativi;
	private boolean inGioco=false;

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

    	//quando l'utente clicca una nuova partita
    	//Math.random() restituisce un numero casuale tra 0 e 0.999
    	//(int)(Math.random()*NMAX) abbiamo la parte intera di (0%99.9)
    	//dove la parte intera tronca
    	//serve quindi il +1 per avere il 100 anche tra i valori casuali possibili
    	this.numeroSegreto=((int)(Math.random()*NMAX))+1;
    	this.numeroTentativi=0;
    	this.inGioco=true;
    	
    	//gestione dell'interfaccia
    	//abilitiamo la possibilità di giocare una volta che premo sulla nuova partita e dunque sulla possibilità di inserire i numeri da tastiera
    	layoutTentativo.setDisable(false);
    	//puliamo l'area di testo in fondo da eventuali residui di partite precedenti
    	txtRis.clear();
    	//Inseriamo nell'apposita casella di testo il numero di tentativi che abbiamo che all'inzio sono tutti quelli disponibili
    	txtRimasti.setText(Integer.toString(TMAX));
    	//mi ero dimenticato di farlo dalla parte dello scene builder quindi lo faccio qui
    	txtRimasti.setDisable(true);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	//leggo l'input dell'utente
    	int tentativo;
    	try {
    		tentativo=Integer.parseInt(txtProva.getText());
    	}
    	catch(NumberFormatException e){
    		//con appendText() si concatena la stringa a quello che c'è già in precedenza, mentre con setText() si sostituisce il testo precedente con quello che inseriamo
    		txtRis.appendText("\nDEVI INSERIRE UN NUMERO!!");
    		return;
    	}
    	
    	numeroTentativi++;
    	
    	if(tentativo==this.numeroSegreto) {
    		txtRis.appendText("\n COMPLIMENTIIIII!! \n Hai vinto!! Hai utilizzato "+this.numeroTentativi+" tentativi.");
    		//una volta che ho indovinato non vado più a fare nulla logicamente e dunque disattivo il box con il pulsante di prova e la casella di testo associata
    		layoutTentativo.setDisable(true);
    		
    		return;
    	}
    	else {
    		if(this.numeroTentativi==TMAX) {
    			txtRis.appendText("\nHAI PERSO! il numero segreto era...\n "+this.numeroSegreto);
        		txtRimasti.setText(Integer.toString(TMAX-this.numeroTentativi));
        		this.inGioco=false;
        		layoutTentativo.setDisable(true);
        		return;
    		}
    		txtRis.appendText("\nNon hai vinto!! Hai utilizzato per ora "+this.numeroTentativi+" tentativi.");
    		txtRimasti.setText(Integer.toString(TMAX-this.numeroTentativi));
    		if(tentativo<numeroSegreto) {
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

    }
}


