package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	public LoginModel loginModel = new LoginModel();
	private Connection connUsers;
	
	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;
	
	@Override
	public void initialize(URL location, ResourceBundle recources) {
	
		if(loginModel.isDBConnected()) {
			isConnected.setText("Connected");
		} else {
			isConnected.setText("Not Connected");
		}
	}
	
	public void Login (ActionEvent event) {
		try {
		if(loginModel.isLogin(txtUserName.getText(), txtPassword.getText())) {
			isConnected.setText("Имя и пароль верные");
			((Node)event.getSource()).getScene().getWindow().hide();//Hiding the login page
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/MainPage.fxml").openStream());
			MainPageController mainpageController = (MainPageController)loader.getController();
			mainpageController.GetUser(txtUserName.getText());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}else {
			isConnected.setText("Имя и пароль неверные");
		}
	}catch (SQLException e) {
		isConnected.setText("Имя и пароль неверные");
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	public Connection getConnUsers() {
		return connUsers;
	}

	public void setConnUsers(Connection connUsers) {
		this.connUsers = connUsers;
	}
}