/**
 * Leesdy
 * dx.leesdy.diarization.datastructure
 * Author: DxWang LDClusterSet.java
 * Created at:2017年4月26日
 */
package dx.leesdy.diarization.datastructure;

import java.util.ArrayList;

/**
 * 
 */
public class LDClusterSet {
	
	private ArrayList<LDCluster> clusters;
	
	public LDClusterSet(){
		this.clusters = new ArrayList<LDCluster>();
	}
	
	public void add(LDCluster c) {
		clusters.add(c);
	}
	
	
}
