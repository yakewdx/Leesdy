/**
 * Leesdy
 * dx.leesdy.utils
 * Author: DxWang LDInformationCenter.java
 * Created at:2017年3月8日
 */
package dx.leesdy.utils;

import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 
 */
public class LDInformationCenter extends Observable{

	private LDInformationCenter() {
		this.bottomInfo = new SimpleStringProperty();
		this.bottomInfo.addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				setChanged();
				notifyObservers(newValue);
			}
			
		});
	}
	
	private static LDInformationCenter instance;
	
	public static LDInformationCenter getInstance() {
		if (instance == null) {
			synchronized(LDInformationCenter.class) {
				if (instance == null) {
					instance = new LDInformationCenter();
					return instance;
				}
			}
		} 
		return instance;
	}
	
	private StringProperty bottomInfo;
	
	public StringProperty getBottomInfo() {
		return bottomInfo;
	}
	
	public void setBottomInfo(String info) {
		bottomInfo.set(info);
	}

	
}
