package dx.leesdy.model.layout;

import dx.leesdy.utils.LDInformationCenter;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class LDSlider extends Slider {

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
	
	public LDSlider() {
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
