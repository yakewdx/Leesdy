package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PTestDrawWhiteBackground extends PainterComponent {

	public PTestDrawWhiteBackground(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		this.setNeedToUpdate();
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.setGlobalAlpha(0.7);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

}
