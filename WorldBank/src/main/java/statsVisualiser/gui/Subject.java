package statsVisualiser.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	   private ActionEvent state;

	   public ActionEvent getState() {
	      return state;
	   }

	   public void setState(ActionEvent e) {
	      this.state = e;
	      notifyAllObservers();
	   }

	   public void attach(Observer observer){
	      observers.add(observer);		
	   }

	   public void notifyAllObservers(){
	      for (Observer observer : observers) {
	         observer.actionPerformed(state);
	      }
	   } 	

}
