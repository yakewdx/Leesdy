/**
 * Leesdy
 * dx.leesdy.diarization.datastructure
 * Author: DxWang LDCluster.java
 * Created at:2017年4月26日
 */
package dx.leesdy.diarization.datastructure;

import java.util.ArrayList;

/**
 * 
 */
public class LDCluster {
	
	private ArrayList<LDVector> frames;
	
	public LDCluster() {
		this.frames = new ArrayList<LDVector>();
	}
	
	public void add(LDVector frame) {
		frames.add(frame);
	}
	
}
