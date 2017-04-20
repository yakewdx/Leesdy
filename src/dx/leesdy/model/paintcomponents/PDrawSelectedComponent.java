package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import dx.leesdy.utils.LDDebug;

public class PDrawSelectedComponent extends PainterComponent{

	private boolean updated;
	
	public PDrawSelectedComponent(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		this.updated = false;
		statusCenter.getSelectedProperty().addListener((a,ov,nv) -> {
			this.updated = false;
		});
		this.name = "PDrawSelectedComplnent";
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		if (this.updated == false) {
			this.setNeedToUpdate();
			LDDebug.print("PDrawSelectedComponent : Need to update.");
		} else {
			this.setNeedToUpdate(false);
		}
		
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		if (this.getStatusCenter().getSelected()) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setGlobalAlpha(0.1);
			gc.setFill(Color.VIOLET);
			gc.fillRoundRect(5, 5, canvas.getWidth() - 10, canvas.getHeight() - 10, 50, 50);
			//gc.fillRect(5, 5, canvas.getWidth() - 10, canvas.getHeight() - 10);
		} else {
			LDDebug.print("PDrawSelectedComponent : Clear.");
		}
		this.updated = true;
	}

}
