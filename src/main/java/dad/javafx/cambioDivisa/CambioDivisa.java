package dad.javafx.cambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application{
	private TextField origenText,destinoText;
	private ComboBox<String> authOrigen,authDestino;
	private Button cambioButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		origenText = new TextField();
		origenText.setPromptText("0");
		origenText.setMaxWidth(80);
		
		destinoText = new TextField();
		destinoText.setPromptText("0");
		destinoText.setMaxWidth(80);
		
		authOrigen = new ComboBox<String>();
		authOrigen.getItems().addAll("Euro","Libra","Dolar","Yen");
		authOrigen.setPromptText("Moneda");
		
		authDestino = new ComboBox<String>();
		authDestino.getItems().addAll("Euro","Libra","Dolar","Yen");
		authDestino.setPromptText("Moneda");
		
		cambioButton = new Button("Cambio");
		cambioButton.setDefaultButton(true);
		cambioButton.setOnAction(e -> onLoginButtonAction(e));
		
		HBox origenBox= new HBox(5,origenText,authOrigen);
		origenBox.setAlignment(Pos.CENTER);
		origenBox.setStyle("-fx-background-color: white");
		
		HBox destinoBox= new HBox(5,destinoText,authDestino);
		destinoBox.setAlignment(Pos.CENTER);
		destinoBox.setStyle("-fx-background-color: white");
		
		HBox cambioBox= new HBox(5,cambioButton);
		cambioBox.setAlignment(Pos.CENTER);
		cambioBox.setStyle("-fx-background-color: white");
		
		VBox root = new VBox(5,origenBox, destinoBox, cambioBox);
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: white");
		
		Scene scene= new Scene(root,320,200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	private void onLoginButtonAction(ActionEvent e) {
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		Divisa origen,destino;
		Double cantidad=0.0;
		
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("AdivinApp");
		alertError.setHeaderText("Error");
		alertError.setContentText("El numero introducido no es valido");
		
		String numero =origenText.getText();
		try {
			cantidad=(double)Integer.parseInt(numero);
		} catch (NumberFormatException excp) {
			alertError.showAndWait();
		}
		if(authOrigen.getValue() =="Dolar") {
			origen = dolar;
		}else if(authOrigen.getValue() =="Libra") {
			origen = libra;
		}else if(authOrigen.getValue() =="Yen") {
			origen = yen;
		}else{
			origen = euro;
		}
		
		if(authDestino.getValue() =="Dolar") {
			destino = dolar;
		}else if(authDestino.getValue() =="Libra") {
			destino = libra;
		}else if(authDestino.getValue() =="Yen") {
			destino = yen;
		}else {
			destino = euro;
		}
		
		destinoText.setText(Divisa.fromTo(origen, destino, cantidad).toString());
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
