/**
 * Leesdy
 * dx.leesdy.model
 * Author: DxWang LDMultiLayerCanvas.java
 * Created at:2017年3月20日
 */
package dx.leesdy.model;

import java.util.ArrayList;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.model.layout.LDCanvas;
import dx.leesdy.utils.LDCanvasFactory;
import dx.leesdy.utils.LDDebug;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 *
 */
public class LDMultiLayerCanvas {

	double width;
	double height;

	private MouseState mouseState;

	private String info;

	public MouseState getMouseState() {
		return mouseState;
	}
	public void setMouseState(MouseState mouseState) {
		this.mouseState = mouseState;
	}
	// Load to UI layout.
	private Pane pane;

	private Pane container;

	ArrayList<LDCanvas> layers;

	private void init() {

		this.container = new Pane();
		this.mouseState = new MouseState();
		this.layers = new ArrayList<LDCanvas>();

		// only for test
		container.setOnMouseClicked(new EventHandler<MouseEvent> () {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				LDDebug.print("LDMultiLayerCanvas : Mouse Clicked");
			}

		});

		container.setOnMouseMoved(new EventHandler<MouseEvent> () {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("Mouse Moved");
				mouseState.setMouseInCanvas(true);
				mouseState.setX(event.getX());
				mouseState.setY(event.getY());
			}

		});

		container.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				mouseState.setMouseInCanvas(false);
				mouseState.setX(event.getX());
				mouseState.setY(event.getY());
			}

		});
		
	}
	public LDMultiLayerCanvas() {

		// fix width
		this.width = 400;
		this.height = 200;
		init();
	}

	public LDMultiLayerCanvas(double width2, double height2) {
		this.width = width2;
		this.height = height2;
		init();
	}

	public LDCanvas createNewLayer() {
		LDCanvas canvas = LDCanvasFactory.getFactory().createCanvasWithSize(width, height);
		canvas.init(this.mouseState);
		layers.add(canvas);
		container.getChildren().add(canvas);
		//if (pane != null) pane.getChildren().add(canvas);
		return canvas;
	}

	public void removeLayer(LDCanvas canvas) {
		//if (pane != null) pane.getChildren().remove(canvas);
		container.getChildren().remove(canvas);
		this.layers.remove(canvas);
	}

	public ArrayList<LDCanvas> getLayers() {
		return layers;
	}

	public void setLayoutX(double x) {
		this.container.setLayoutX(x);
	}

	public void setLayoutY(double y) {
		this.container.setLayoutY(y);
	}

	public LDCanvas getLayerAtIndex(int index) {
		return layers.get(index);
	}

	public void setOnMouseClicked(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseClicked(eventHandler);
	}

	public void setOnMouseDragEntered(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseDragEntered(eventHandler);
    }
	public void setOnMouseDragExited(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseDragExited(eventHandler);
    }
	public void setOnMouseDragged(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseDragged(eventHandler);
    }
	public void setOnMouseDragOver(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseDragOver(eventHandler);
    }
	public void setOnMouseDragReleased(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseDragReleased(eventHandler);
    }
	public void setOnMouseEntered(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseEntered(eventHandler);
    }
	public void setOnMouseExited(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseExited(eventHandler);
    }
	public void setOnMouseMoved(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseMoved(eventHandler);
    }
	public void setOnMousePressed(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMousePressed(eventHandler);
    }
	public void setOnMouseReleased(EventHandler<? super MouseEvent> eventHandler) {
		this.container.setOnMouseReleased(eventHandler);
    }
	/**
	 * @return the pane
	 */
	public Pane getPane() {
		return pane;
	}
	/**
	 * @param pane the pane to set
	 */
	public void setPane(Pane pane) {
		this.pane = pane;
//		for (LDCanvas layer : layers) {
//			this.pane.getChildren().add(layer);
//		}
		this.pane.getChildren().add(container);
	}
	public Pane getContainer() {
		return container;
	}
	public void setContainer(Pane container) {
		this.container = container;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
