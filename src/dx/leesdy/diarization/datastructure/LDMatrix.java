/**
 * Leesdy
 * dx.leesdy.diarization.datastructure
 * Author: DxWang LDMatrix.java
 * Created at:2017年4月19日
 */
package dx.leesdy.diarization.datastructure;
import JSci.maths.matrices.AbstractDoubleMatrix;
import JSci.maths.matrices.DoubleSquareMatrix;
/**
 * 
 */
public class LDMatrix extends DoubleSquareMatrix {

	/**
	 * @param arg0
	 * @param arg1
	 */
	public LDMatrix(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public static double logDet(DoubleSquareMatrix m) {
		AbstractDoubleMatrix [] comp = m.luDecompose();
		double res = 0;
		double tmp;
		for (int i = 0 ; i < m.rows(); i++) {
			tmp = comp[1].getElement(i, i);
			if (tmp < 0) tmp = -tmp;
			res += Math.log(tmp);
		}
		return res;
	}
}
