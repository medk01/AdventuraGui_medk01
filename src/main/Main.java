/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import UI.PanelBatohu;
import UI.PanelVeci;
import UI.Vychody;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author Katarína Medovarská
 */
public class Main extends Application {
    
    private Mapa mapa;
    private MenuPole menu;
    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextField;
    private Stage primaryStage;
    private UI.Vychody sezVych;
    private PanelBatohu pBatoh;
    private PanelVeci pVeci;
    
    @Override
    public void start(Stage primaryStage) {
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);
        this.primaryStage = primaryStage;
        
        BorderPane borderPane = new BorderPane();
        
        /* Centrální text s průběhem hry je ve formátu text area a není editovatelný - nejde mazat. */
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);

        /* Label s textom Zadaj príkaz */
        Label zadejPrikazLabel = new Label("Zadaj príkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        // Zadání příkazu
        zadejPrikazTextField = new TextField("Sem zadej prikaz");
        
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>(){
           
            @Override
            public void handle(ActionEvent event){
        
            String vstupniPrikaz = zadejPrikazTextField.getText();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
            
            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");
            
            zadejPrikazTextField.setText("");
            
            
            if (hra.konecHry()){
                zadejPrikazTextField.setEditable(false);
                centralText.appendText(hra.vratEpilog());
            }
            }
        });
      
        
        sezVych = new Vychody(hra.getHerniPlan(),centralText,zadejPrikazTextField);
        pBatoh = new PanelBatohu(hra.getHerniPlan(),centralText);
        pVeci = new PanelVeci(hra.getHerniPlan(),centralText);
        
        
        /* Co všechno se nachází v dolní liště. */
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        //čo sa nachádza na paneli vpravo
        BorderPane pravy = new BorderPane();
        pravy.setLeft(pBatoh.getList());
        pravy.setCenter(sezVych.getList());
        pravy.setRight(pVeci.getList());
        pravy.setTop(centralText);

        borderPane.setBottom(dolniLista);
        borderPane.setLeft(mapa);
        borderPane.setRight(pravy);
        borderPane.setTop(menu);
        
        Scene scene = new Scene(borderPane, 900, 650);

        primaryStage.setTitle("Adventúra");

        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
        
    }

    /**
     * ako spúšťať hru (text alebo ui)
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-.txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }
    public void novaHra() 
    {
        start(primaryStage);
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

}