/**
 * Dongxiao Wang
 * Feb 17, 2017
 *
 * PTestOutput.java
 * 
 */
package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;

/**
 * @author dongxiao
 *
 */
public class PTestOutput extends PainterComponent {

	public PTestOutput(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dx.leesdy.model.PainterComponent#paint(javafx.scene.canvas.Canvas)
	 * Only for test
	 */
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		System.out.println("Test : updating...");
	}

	@Override
	public void updateState() {
		this.setNeedToUpdate(false);
	}
}
