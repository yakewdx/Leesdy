package dx.leesdy.player;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import dx.leesdy.model.*;
import dx.leesdy.model.layout.LDCanvas;
import dx.leesdy.model.paintcomponents.PDrawDiarizationResult;
import dx.leesdy.model.paintcomponents.PDrawPlaybackState;
import dx.leesdy.model.paintcomponents.PDrawReferencePlane;
import dx.leesdy.model.paintcomponents.PDrawTimeAxis;
import dx.leesdy.model.paintcomponents.PDrawVerticalLine;
import dx.leesdy.model.paintcomponents.PDrawWav;

import dx.leesdy.utils.*;

/** Wav player wrapper
 *  To initialize the player:
 *  
 * 
 */
public class LDWavPlayer {
	
	// filename
	//	private String source;
	//	private Media media;
	//	private WavWrapper wav;
	private MediaPlayer mediaPlayer;
	private LDStatusCenter statusCenter;
	private Painter painter;
	//	private LDDiarizationResultReader reader;
	
	public Painter getPainter() {
		return painter;
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	private boolean isInitializationSucceeded;
	// For test
	private String DiarizationOutput = "showName.out.seg";
	
	public LDWavPlayer(String filename) {
		this.isInitializationSucceeded = false;
		this.statusCenter = new LDStatusCenter(filename);
		//this.statusCenter.getSource(). source = filename;
		initPlayer();
	}
	
	public boolean isInitializationSuceeded() {
		return isInitializationSucceeded;
	}
	
	public void initGraphics(LDMultiLayerCanvas ldmlCanvas) {
		// TODO Auto-generated method stub
		// 
		painter.addComponent(new PDrawVerticalLine(6, this.statusCenter));
		painter.addComponent(new PDrawWav(3, this.statusCenter));
		painter.addComponent(new PDrawTimeAxis(8,this.statusCenter));
		painter.addComponent(new PDrawPlaybackState(5, this.statusCenter));
		painter.addComponent(new PDrawReferencePlane(10, this.statusCenter));
		
		//new Thread(painter).start();
		//painter.start();
		//painter.run();
		
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				painter.paint();
			}
			
		}.start();
		
	}
	
	public void addPainterComponents() {
		if (painter != null) {
			if (this.statusCenter.getReader() == null) {
				LDDiarizationResultReader reader = new LDDiarizationResultReader(DiarizationOutput);
				this.statusCenter.setReader(reader);
				LDDebug.print("Reader is NULL.");
			}
			painter.addComponent(new PDrawDiarizationResult(4, this.statusCenter));
			
			PDrawVerticalLine pc = (PDrawVerticalLine) painter.getComponentByName("PDrawVerticalLine");
			pc.setShowDiarizationResult(true);
		} else {
			System.out.println("Painter is null.");
		}
	}
	
	public void removePainterComponent(String componentName) {
		this.painter.removeComponentByName(componentName);
		if (componentName == "PDrawDiarizationResult") {
			PDrawVerticalLine pc = (PDrawVerticalLine) painter.getComponentByName("PDrawVerticalLine");
			pc.setShowDiarizationResult(false);
		}
	}
	
	private void initPlayer() {
		try {
			String source = this.statusCenter.getSouceFile();
			Media media = new Media(new File(source).toURI().toString());
	        if (media.getError() == null) {
	            media.setOnError(new Runnable() {
	                public void run() {
	                    // Handle asynchronous error in Media object.
	                }
	            });
	            try {
	            	mediaPlayer = new MediaPlayer(media);
	                
	                // Set media to wavWrapper
	                WavWrapper wav = new WavWrapper(source);
	                if (wav.isSucceeded())
	                this.isInitializationSucceeded = true;
	                

	                if (mediaPlayer.getError() == null) {
	                    mediaPlayer.setOnError(new Runnable() {
	                        public void run() {
	                            // Handle asynchronous error in MediaPlayer object.
	                        }
	                    });
	               
	                } else {
	                    // Handle synchronous error creating MediaPlayer.
	                }
	                
	                this.statusCenter.setMediaPlayer(mediaPlayer);
	                this.statusCenter.setSource(wav);
	                
	            } catch (Exception mediaPlayerException) {
	                // Handle exception in MediaPlayer constructor.
	            	mediaPlayerException.printStackTrace();
	            }
	        } else {
	            // Handle synchronous error creating Media.
	        }
	        //mRoot.getChildren().add(mediaView);
	        
	        MediaPlayer mediaPlayer = this.statusCenter.getMediaPlayer();
	        // Set listener
	        mediaPlayer.setOnMarker(new EventHandler<MediaMarkerEvent>() {

				@Override
				public void handle(MediaMarkerEvent event) {
					// TODO Auto-generated method stub
					
				}
	        	
	        });
	        
	        mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {

				@Override
				public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
					// TODO Auto-generated method stub
					
					//if (painter != null) painter.paint();
					
				}
	        	
	        });
	        
	        mediaPlayer.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stop();
					//viewController.setPlayButtonState(true);
				}
	        	
	        });
	        
	        
	    } catch (Exception mediaException) {
	        // Handle exception in Media constructor.
	    	mediaException.printStackTrace();
	    }
		
	}
	
	public LDStatusCenter getStatusCenter() {
		return statusCenter;
	}

	public void setStatusCenter(LDStatusCenter statusCenter) {
		this.statusCenter = statusCenter;
	}

	public void play() {
		mediaPlayer.play();
	}
	
	public void stop() {
		mediaPlayer.stop();
		mediaPlayer.seek(Duration.ZERO);
	}
	
	public void pause() {
		mediaPlayer.pause();
	}
	
	public void diarization() {
		LDExecutor.getExecutor().submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String output = DiarizationOutput;
				new LDDiarization(statusCenter.getSouceFile(), output).diarization();
				LDDiarizationResultReader reader = new LDDiarizationResultReader(output);
				statusCenter.setReader(reader);
			}
			
		});
	}
	
	private void onPlay() {
		this.play();
	}
	
	private void onStop() {
		this.stop();
	}
	
	public MediaPlayer getPlayer() {
		return this.mediaPlayer;
	}
}
