/**
 * Leesdy
 * dx.leesdy.utils
 * Author: DxWang LDCanvasFactory.java
 * Created at:2017年3月20日
 */
package dx.leesdy.utils;

import dx.leesdy.model.layout.LDCanvas;
/**
 * 
 */
public class LDCanvasFactory {

	private static LDCanvasFactory factory;
	
	private LDCanvasFactory(){}
	
	public static LDCanvasFactory getFactory() {
		if (factory == null) {
			synchronized (LDCanvasFactory.class) {
				if (factory == null) {
					return new LDCanvasFactory();
				}
			}
		}
		return factory;
		 
	}
	
	public LDCanvas createCanvasWithSize(double width, double height) {
		return new LDCanvas(width,height);
	}
	
}
