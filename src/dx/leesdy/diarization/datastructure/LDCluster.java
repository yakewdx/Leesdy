/**
 * Leesdy
 * dx.leesdy.diarization.datastructure
 * Author: DxWang LDCluster.java
 * Created at:2017年4月26日
 */
package dx.leesdy.diarization.datastructure;

import java.util.ArrayList;

import JSci.maths.vectors.DoubleVector;

/**
 * 
 */
public class LDCluster {
	
	private ArrayList<DoubleVector> frames;
	
	public LDCluster() {
		this.frames = new ArrayList<DoubleVector>();
	}
	
	public void add(LDVector frame) {
		frames.add(frame);
	}
	
}
