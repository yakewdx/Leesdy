/**
 * Leesdy
 * dx.leesdy.diarization.datastructure
 * Author: DxWang LDVector.java
 * Created at:2017年4月19日
 */
package dx.leesdy.diarization.datastructure;

import dx.leesdy.utils.LDDebug;

/**
 * 
 */
public class LDVector{
	
	private double [] data;
	
	public LDVector() {
		
	}
	
	int size() {
		if (data != null)
			return data.length;
		else return 0;
	}
	
	public LDVector(int size) {
		data = new double[size];
	}
	
	public  LDVector add(LDVector v) {
		if (data.length != v.data.length) return null;
		LDVector r = new LDVector(v.size());
		for (int i = 0 ; i < r.size(); i++) {
			r.set(i, data[i] + v.get(i));
		}
		return r;
	}
	
	public  LDVector sub(LDVector v) {
		if (data.length != v.data.length) return null;
		LDVector r = new LDVector(v.size());
		for (int i = 0 ; i < size(); i++) {
			r.set(i, data[i] - v.data[i]);
		}
		return r;
	}
	public double dot(LDVector v){
		if (data.length != v.data.length) {
			LDDebug.print("LDVector : the length does not match.");
			return -1;
		}
		double ans = 0;
		for (int i = 0 ; i < size(); i++) {
			ans += data[i] * v.data[i];
		}
		return ans;
	}
	public  LDVector scalarProduct(double s) {
		LDVector r = new LDVector(size());
		for (int i = 0 ; i < size(); i++) {
			r.set(i, data[i] * s);
		}
		return r;
	}
	
	void set(int index, double value) {
		if (index < data.length) {
			data[index] = value;
		} else {
			LDDebug.print("LDVector : index out of bound.");
		}
	}
	
	double get(int index) {
		if (index < data.length)
			return data[index];
		else {
			LDDebug.print("LDVector : index out of bound.");
			return -1;
		}
	}
	

}
