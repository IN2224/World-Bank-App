package statsVisualiser.gui;

import java.awt.event.ActionEvent;

public abstract class Observer {
	
	protected Subject subject;
	public abstract void actionPerformed(ActionEvent e);

}
