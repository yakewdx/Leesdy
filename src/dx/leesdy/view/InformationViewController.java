/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang InformationViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 
 */
public class InformationViewController implements Observer {

	@FXML
	private Label label;
	
	public InformationViewController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.label.setText((String)arg1);
	}
	
	
}
