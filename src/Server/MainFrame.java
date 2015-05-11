package Server;

import java.rmi.Naming;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFrame extends Application
{
	public MainFrame()
	{
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{   
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Bienvenue sur le serveur de distribution");
        scenetitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label lIp = new Label("Adresse ip du serveur:");
        final TextField tfIp = new TextField();
        Label lPort = new Label("Port du serveur:");
        final TextField tfPort = new TextField();
        Label lNbCl = new Label("Nombre de client:");
        final TextField tfNbCl = new TextField();
        Label lNbObs = new Label("Nombre d'obstacles:");
        final TextField tfNbObs = new TextField();
        Label lX = new Label("Taille de la fenêtre en X:");
        final TextField tfX = new TextField();
        Label lY = new Label("Taille de la fenêtre en Y:");
        final TextField tfY = new TextField();
        Label lPas = new Label("Taille du pas d'un individu:");
        final TextField tfPas = new TextField();
        Label lNbIndiv = new Label("Nombre d'individu par village:");
        final TextField tfNbIndiv = new TextField();
        
        grid.add(lIp, 0, 1);
        grid.add(tfIp, 1, 1);
        grid.add(lPort, 0, 2);
        grid.add(tfPort, 1, 2);
        grid.add(lNbCl, 0, 3);
        grid.add(tfNbCl, 1, 3);
        grid.add(lNbObs, 0, 4);
        grid.add(tfNbObs, 1, 4);
        grid.add(lX, 0, 5);
        grid.add(tfX, 1, 5);
        grid.add(lY, 0, 6);
        grid.add(tfY, 1, 6);
        grid.add(lPas, 0, 7);
        grid.add(tfPas, 1, 7);
        grid.add(lNbIndiv, 0, 8);
        grid.add(tfNbIndiv, 1, 8);

        final Text actiontarget = new Text();
        

        
        Button btn = new Button("Lancer le serveur");
        btn.setOnAction(new EventHandler<ActionEvent>() 
		{
	        public void handle(ActionEvent event) 
	        {
	        	if(tfIp.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfPort.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfNbCl.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfNbObs.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfX.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfY.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfPas.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else if(tfNbIndiv.getText().compareTo("")==0)
	        	{
	        		actiontarget.setFill(Color.FIREBRICK);
		            actiontarget.setText("Champ(s) vide(s)");
	        	}
	        	else
	        	{
	        		//System.out.println("Usage : java ServerImpl <port> <nb Obstacle> <Size x>"
	    			//		+ "<Size y> <taille pas> <nb individu> <nb Client>");
	        		try
	        		{
	        			ServeurImpl serv = new ServeurImpl(Integer.parseInt(tfNbObs.getText()),
		        				Integer.parseInt(tfX.getText()),
		        				Integer.parseInt(tfY.getText()),
		        				Integer.parseInt(tfPas.getText()),
		        				Integer.parseInt(tfNbIndiv.getText()),
		        				Integer.parseInt(tfNbCl.getText()));
	        			serv.startRegistry(Integer.parseInt(tfPort.getText()));
	        			Naming.rebind("rmi://"+tfIp.getText()+":"+ tfPort.getText() + "/Serveur", serv);
	        			System.out.println("Serveur en service");
	        			actiontarget.setFill(Color.GREEN);
			            actiontarget.setText("Le serveur est lancé");
	        		}
	        		catch (Exception e)
	        		{
	        			actiontarget.setFill(Color.FIREBRICK);
			            actiontarget.setText("Un problème RMI est survenue");
	        			System.out.println("ServeurImpl : " + e.getMessage());
	        			e.printStackTrace();
	        		}
	        		
	        		
	        	}
	        }
		});
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 10);
        
        grid.add(actiontarget, 0, 11);
        
        Scene scene = new Scene(grid, 400, 500);

        primaryStage.setTitle("Serveur de distributions de données");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public static void main(String[] args) 
	{
        launch(args);
    }
}
