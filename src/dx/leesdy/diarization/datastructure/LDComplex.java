package dx.leesdy.diarization.datastructure;


public class LDComplex {
	double real;
	double imag;
	
	public LDComplex() {
		real = 0;
		imag = 0;
	}
	
	public LDComplex(double _r, double _i) {
		real = _r;
		imag = _i;
	}
	
	public LDComplex(LDComplex c) {
		real = c.real;
		imag = c.imag;
	}
	
	public void set(double _r, double _i) {
		real = _r;
		imag = _i;
	}
	
	public void set(LDComplex c) {
		real = c.real;
		imag = c.imag;
	}
	
	public LDComplex add (LDComplex rhs) {
		return new LDComplex(this.real + rhs.real, this.imag + rhs.imag);
	}
	
	public LDComplex sub (LDComplex rhs) {
		return new LDComplex(this.real - rhs.real, this.imag - rhs.imag);
	}

	public LDComplex mul(double c) {
		return new LDComplex(this.real * c, this.imag * c);
	}
	
	public double length() {
		return Math.sqrt(this.real * this.real + this.imag * this.imag);
	}
	
	public double lengthSquared() {
		return real * real + imag * imag;
	}
	
	public LDComplex mul(LDComplex c) {
		double real = this.real * c.real - this.imag * c.imag;
		double imag = this.imag * c.real + this.real * c.imag;
		return new LDComplex(real, imag);
	}
	
	public static double[] powerSpectrum(LDComplex[] seg) {
		// seg is the result of fft
		int length = seg.length;
		double [] res = new double[length];
		for (int i = 0; i < length; i++) {
			res[i] = seg[i].lengthSquared();
		}
		return res;
	}
	
	static public LDComplex exp(LDComplex c) {
		LDComplex complex = new LDComplex();
		double rho = Math.exp(c.real);
		complex.real = rho * Math.cos(c.imag);
		complex.imag = rho * Math.sin(c.imag);
		return complex;
	}
}
