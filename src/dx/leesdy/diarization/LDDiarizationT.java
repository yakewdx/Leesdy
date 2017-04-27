package dx.leesdy.diarization;

import dx.leesdy.diarization.audiofeature.LDMFCCFeature;
import dx.leesdy.diarization.parameter.*;
import dx.leesdy.diarization.segmentation.LDBICSegmentation;
import dx.leesdy.diarization.segmentation.LDSegmentation;
import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.utils.LDDebug;

// 手工实现 diarization 过程
public class LDDiarizationT {

	
	public static void diarization(LDStatusCenter statusCenter) {
		// initialize 
		LDParameter parameter = new LDParameter();
		LDDiarizationResultReaderT reader = new LDDiarizationResultReaderT();
		reader.setNeedUpdate(true);
		
		statusCenter.setReader_T(reader);
		
		// Segmentation: initialize
		LDSegmentation segmentation = new LDSegmentation(statusCenter, parameter, reader);
		segmentation.initialize();
		
		// calculate MFCC
		LDMFCCFeature mfccFeature = new LDMFCCFeature(segmentation.getSegments(), statusCenter, reader);
		
		
		
		// Segmentation: BIC based segmentation
		LDBICSegmentation bicSeg = new LDBICSegmentation(statusCenter);
		bicSeg.segmentation(mfccFeature.getFeature());
		
		LDDebug.print("Debugging");
		
		// calculate i-vector
		
		// PLDA scoring
		
		// clustering
		
		// write output
		
		reader.setNeedUpdate(false);
	}
}
