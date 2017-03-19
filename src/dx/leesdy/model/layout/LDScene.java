/**
 * Leesdy
 * dx.leesdy.model.layout
 * Author: DxWang LDScene.java
 * Created at:2017年3月7日
 */
package dx.leesdy.model.layout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 */
public class LDScene extends Scene {

	/**
	 * @param root
	 */
	public LDScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
		this.initEventListeners();
	}

	private void initEventListeners() {
		this.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				System.out.println(newValue);
			}
			
		});
	}
}
