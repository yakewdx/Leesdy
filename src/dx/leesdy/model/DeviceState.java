package dx.leesdy.model;


import dx.leesdy.model.MouseState;


// Singleton
public class DeviceState {
	static DeviceState ds;
	private MouseState mouseState;
	
	private DeviceState(){
		
		mouseState = new MouseState();
		
	}
	public static DeviceState getInstance() {
		if (ds == null) {
			synchronized(DeviceState.class) {
				ds = new DeviceState();
				return ds;
			}
		} else {
			return ds;
		}
	}
	
	public MouseState getMouseState() {
		return ds.mouseState;
	}
}
