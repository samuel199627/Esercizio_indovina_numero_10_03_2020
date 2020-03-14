package it.polito.tdp.indovinaNumero;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.tdp.indovinaNumero.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
    	//rispetto ai progetti fino ad ora creati qui introduciamo il concetto dei design pattern in cui
    	//andiamo a modificare leggermente il codice iniziale che c'e' quando si crea il progetto perchè qui e'
    	//il posto in cui dobbiamo crearci il modello e pero' dobbiamo anche estrapolare un riferimento al controller
    	//per poterli collegare tramite il setModel() che e' un metodo che abbiamo opportunamente creato nel controller
    	
    	//il codice modificato si ripete sempre cosi' e probabilmente ce lo forniranno gia' cosi'
    	
    	Model model=new Model();
    	FXMLController controller;
    	
    	//ci recuperiamo un riferimento al controller e lo facciamo tramite il loader che va a vedere
    	//qual'è il controller associato al file FXML
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
    	
    	//importante che prima del controller ci sia la definizione della scena
    	
    	 //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Parent root=loader.load(); //modifichiamo usando il loader che ci siamo ricavati da sopra
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
    	
    	controller=loader.getController(); 
    	controller.setModel(model);
    	
       
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
