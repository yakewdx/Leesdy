package dx.leesdy.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import dx.leesdy.model.paintcomponents.PainterComponent;
import dx.leesdy.utils.LDDebug;
import dx.leesdy.utils.LDExecutor;
import javafx.scene.canvas.Canvas;

public class Painter implements Runnable {

	private ArrayList<PainterComponent> list;
	
	private LDMultiLayerCanvas canvas;
	
	public Painter(LDMultiLayerCanvas canvas){
		this.canvas = canvas;
		list = new ArrayList<PainterComponent>();
	};
	
	
	/**
	 * 
	 * @param pc
	 * @return this
	 * 
	 * Add a painter component to painter's list
	 * and assign a new layer
	 * 
	 */
	public Painter addComponent(PainterComponent pc) {
		list.add(pc);
		pc.setCanvas(canvas.createNewLayer());
		
		return this;
	}
	
	public void removeComponent(PainterComponent pc) {
		this.canvas.removeLayer(pc.getCanvas());
		list.remove(pc);
	}
	
	public synchronized PainterComponent getComponentByName(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName() == name) {
				return list.get(i);
			}
		}
		return null;
	}
	
	public synchronized void removeComponentByName(String name) {
		
		PainterComponent pc = this.getComponentByName(name);
		if (pc != null) {this.removeComponent(pc); }
		else { LDDebug.print("the component for name " + name + " does not exist."); }
	}
	
	
	public void start() {
		
		LDExecutor.getExecutor().scheduleWithFixedDelay(this,10,100,TimeUnit.MILLISECONDS);

	}
	
	@Override
	public void run() {
		//while (!Thread.currentThread().isInterrupted()) {
			paint();
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//}
	}
	
	public void paint() {
		list.sort(new Comparator<PainterComponent> () {

			@Override
			public int compare(PainterComponent arg0, PainterComponent arg1) {
				// TODO Auto-generated method stub
				return arg1.compareTo(arg0);
			}
			
		});
		
		
		
		for (int i = 0; i < list.size(); i++) {
			PainterComponent pc =  list.get(i);
			pc.updateState();
			if (pc.getCanvas() != canvas.getLayerAtIndex(i)) {
				pc.setCanvas(canvas.getLayerAtIndex(i));
			}
			if (pc.needToUpdate()) {
				// System.out.println(pc.getClass().toString() + " is going to update");
				pc.clearCanvas();
				pc.update();
			} else {
				// System.out.println(pc.getClass().toString() + " will not be updated");
			}
		}
		
	}
	
	public PainterComponent getComponentById(int id) {
		for (PainterComponent pc : list) {
			if (pc.getId() == id) {
				return pc;
			}
		}
		return null;
	}
	
	public boolean removeComponentById(int id) {
		PainterComponent pc = this.getComponentById(id);
		if (pc != null) {
			list.remove(pc);
			return true;
		}
		else return false;
	}
}
