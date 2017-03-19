package dx.leesdy.player;

import java.io.File;
import java.util.concurrent.TimeUnit;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import dx.leesdy.view.BasicViewController;
import dx.leesdy.model.*;
import dx.leesdy.model.paintcomponents.PDrawDiarizationResult;
import dx.leesdy.model.paintcomponents.PDrawPlaybackState;
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
	private String source;
	private Media media;
	private WavWrapper wav;
	private MediaPlayer mediaPlayer;
	private BasicViewController viewController;
	private Painter painter;
	private LDDiarizationResultReader reader;
	
	private boolean isInitializationSucceeded;
	// For test
	private String DiarizationOutput = "showName.seg";
	
	public LDWavPlayer(String filename) {
		this.isInitializationSucceeded = false;
		source = filename;
		initPlayer();
	}
	
	public boolean isInitializationSuceeded() {
		return isInitializationSucceeded;
	}
	
	public void setViewController(BasicViewController controller) {
		viewController = controller;
	}
	
	public void initGraphics(Canvas canvas) {
		// TODO Auto-generated method stub
		
		MouseState ms = DeviceState.getInstance().getMouseState();
		canvas.setOnMouseMoved(new EventHandler<MouseEvent> () {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("Mouse Moved");
				ms.setMouseInCanvas(true);
				ms.setX(event.getX());
				ms.setY(event.getY());
			}
			
		});
		
		canvas.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				ms.setMouseInCanvas(false);
				ms.setX(event.getX());
				ms.setY(event.getY());
			}
			
		});
		
		painter = new Painter(canvas);
		painter.addComponent(new PDrawVerticalLine(6,wav,this));
		painter.addComponent(new PDrawWav(3, wav));
		painter.addComponent(new PDrawPlaybackState(5,wav,this));
		
		LDExecutor.getExecutor().scheduleWithFixedDelay(painter,10,100,TimeUnit.MILLISECONDS);
		
	}
	
	public void addPainterComponents() {
		if (painter != null) {
			if (reader != null) {
				painter.addComponent(new PDrawDiarizationResult(4, wav, this, reader));
			} else {
				reader = new LDDiarizationResultReader(DiarizationOutput);
				painter.addComponent(new PDrawDiarizationResult(4, wav, this, reader));
				System.out.println("Reader is NULL.");
			}
		} else {
			System.out.println("Painter is null.");
		}
	}
	
	private void initPlayer() {
		try {
	        media = new Media(new File(source).toURI().toString());
	        if (media.getError() == null) {
	            media.setOnError(new Runnable() {
	                public void run() {
	                    // Handle asynchronous error in Media object.
	                }
	            });
	            try {
	                mediaPlayer = new MediaPlayer(media);
	                
	                // Set media to wavWrapper
	                wav = new WavWrapper(media);
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
	                
	                
	            } catch (Exception mediaPlayerException) {
	                // Handle exception in MediaPlayer constructor.
	            	mediaPlayerException.printStackTrace();
	            }
	        } else {
	            // Handle synchronous error creating Media.
	        }
	        //mRoot.getChildren().add(mediaView);
	        
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
				new LDDiarization(source, output).diarization();
				reader = new LDDiarizationResultReader(output);
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
