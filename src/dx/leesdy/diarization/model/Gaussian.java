/**
 * Leesdy
 * dx.leesdy.diarization.model
 * Author: DxWang Gaussian.java
 * Created at:2017年4月19日
 */
package dx.leesdy.diarization.model;

import java.util.ArrayList;

import JSci.maths.matrices.AbstractDoubleMatrix;
import JSci.maths.matrices.DoubleMatrix;
import JSci.maths.matrices.DoubleSquareMatrix;
import JSci.maths.matrices.Matrix;
import JSci.maths.vectors.AbstractDoubleVector;
import JSci.maths.vectors.DoubleVector;
import dx.leesdy.diarization.datastructure.LDVector;

/**
 * 
 */
public class Gaussian {
	
	private AbstractDoubleVector mean;
	private DoubleSquareMatrix covariance;
	
	public Gaussian(AbstractDoubleVector mean, DoubleSquareMatrix covariance) {
		this.mean = mean;
		this.covariance = covariance;
	}
	
	/**
	 * @return the mean
	 */
	public AbstractDoubleVector getMean() {
		return mean;
	}
	/**
	 * @param mean the mean to set
	 */
	public void setMean(DoubleVector mean) {
		this.mean = mean;
	}
	/**
	 * @return the covariance
	 */
	public DoubleSquareMatrix getCovariance() {
		return covariance;
	}
	/**
	 * @param covariance the covariance to set
	 */
	public void setCovariance(DoubleSquareMatrix covariance) {
		this.covariance = covariance;
	}
	
	public static Gaussian approximate(ArrayList<LDVector> samples) {
		
		int dim = samples.get(0).dimension();
		AbstractDoubleVector _mean = new DoubleVector(dim);
		DoubleSquareMatrix _covariance = new DoubleSquareMatrix(dim);
		
		for (DoubleVector sample : samples) {
			_mean = _mean.add(sample);
		}
		
		_mean = _mean.scalarDivide((double)dim);
		DoubleMatrix mean = new DoubleMatrix(dim,1);
		for (int j = 0; j < dim; j++) {
			mean.setElement(j, 0, _mean.getComponent(j));
		}
		
		for (int i = 0; i < samples.size(); i++) {
			DoubleMatrix col = new DoubleMatrix(dim,1);
			for (int j = 0; j < dim; j++) {
				col.setElement(j, 0, samples.get(i).getComponent(j));
			}
			col = (DoubleMatrix) col.subtract(mean);
			DoubleMatrix row = (DoubleMatrix)col.transpose();
			DoubleSquareMatrix m = (DoubleSquareMatrix)col.multiply(row);
			
			_covariance = _covariance.add(m);
		}
		
		_covariance = (DoubleSquareMatrix) _covariance.scalarDivide((double)dim);
		
		return new Gaussian(_mean, _covariance);
	}
	
	
}
