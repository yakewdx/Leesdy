/**
 * Leesdy
 * dx.leesdy.model.layout
 * Author: DxWang LDButton.java
 * Created at:2017年3月8日
 */
package dx.leesdy.model.layout;

import dx.leesdy.utils.LDInformationCenter;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * 
 */
public class LDButton extends Button {

	private String info;

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	public LDButton() {
		super();
		info = "";
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				LDInformationCenter informationCenter = LDInformationCenter.getInstance();
				informationCenter.setBottomInfo(info);
			}
			
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				LDInformationCenter informationCenter = LDInformationCenter.getInstance();
				informationCenter.setBottomInfo("");

			}
			
		});
	}
	
}
