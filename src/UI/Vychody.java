/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import utils.Observer;
/**
 *
 * @author Katarína Medovarská
 */

public class Vychody implements Observer
{

    private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    
    private TextArea centralText;
    private TextField zadejPrikazTextArea;

    public Vychody(HerniPlan plan, TextArea text,TextField field)
      {
        this.plan = plan;
        plan.registerObserver(this);

        centralText = text;
        zadejPrikazTextArea = field;
        
        init();
      }

    private void init()
      {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    String vstupniPrikaz = "jdi "+list.getSelectionModel().getSelectedItem();
                    String odpovedHry = plan.getHra().zpracujPrikaz("jdi "+list.getSelectionModel().getSelectedItem());

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
                    
                    
                    if (plan.getHra().konecHry()) 
                    {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(plan.getHra().vratEpilog());
                    }
                    
                    
                    plan.notifyAllObservers();
                }
            }
        });
        update();
      }

    public ListView<String> getList()
      {
        return list;
      }

    @Override
    public void update()
      {
        String vychody = plan.getAktualniProstor().seznamVychoduProPanely();
        data.clear();
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
      }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }


}
