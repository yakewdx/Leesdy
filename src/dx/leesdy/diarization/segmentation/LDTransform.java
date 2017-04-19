package dx.leesdy.diarization.segmentation;

import dx.leesdy.diarization.datastructure.LDComplex;

public class LDTransform {
	static public LDComplex [] fft(LDComplex[] orig) {
		int n = orig.length;
		
		// base case
		if (n == 1) return new LDComplex[] {orig[0]};
		
		LDComplex[] x = orig;
		
		// assume n is even
		
		// fft of even terms
		LDComplex [] even = new LDComplex[n/2];
		for (int k = 0; k < n/2; k++) {
			even[k] = x[2*k];
		}
		LDComplex[] q = fft(even);
		
		// fft of odd terms
		LDComplex[] odd = even; // reuse the array
		for (int k = 0; k < n/2; k++) {
			odd[k] = x[2*k + 1];
		}
		LDComplex[] r = fft(odd);
		
		// combine
		LDComplex[] y = new LDComplex[n];
		for (int k = 0; k < n/2; k++) {
			double kth = -2 * k * Math.PI / n;
			LDComplex wk = new LDComplex(Math.cos(kth), Math.sin(kth));
			y[k] = q[k].add(wk.mul(r[k]));
			y[k + n/2] = q[k].sub(wk.mul(r[k]));
		}
		return y;
	}
	
	static public LDComplex[] half_fft(LDComplex[] orig) {
		int n = orig.length;
		
		LDComplex[] x = orig;
		
		if ((n & (n-1)) != 0) { 
			// apply zero-padding
			int m = 2;
			while (m < n) { m <<= 1; }
			x = new LDComplex[m];
			for (int i = 0; i < n; i++) x[i] = orig[i];
			for (int i = n; i < m; i++) x[i] = new LDComplex();
			n = m;
		}
		LDComplex[] fft_res = fft(x);
		int length = fft_res.length / 2 + 1;
		LDComplex[] res = new LDComplex[length];
		for (int i = 0; i < length; i++) {
			res[i] = fft_res[i];
		}
		return res;
	}
	
	static public double[] dct(double [] p, int L) {
		
		// result
		double[] c = new double[L];
		
		for (int i = 1; i <= L; i++) {
			for (int j = 0; j < p.length; j++) {
				c[i-1] += p[j] * Math.cos(Math.PI * i * (j - 0.5) / p.length);
			}
		}
		return c;
	}
}
