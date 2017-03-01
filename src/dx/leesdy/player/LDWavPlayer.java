package dx.leesdy.player;

import java.io.File;
import java.util.concurrent.TimeUnit;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import dx.leesdy.view.BasicViewController;
import dx.leesdy.view.Painter;
import dx.leesdy.model.*;
import dx.leesdy.model.paintcomponents.PDrawPlaybackState;
import dx.leesdy.model.paintcomponents.PDrawVerticalLine;
import dx.leesdy.model.paintcomponents.PDrawWav;
import dx.leesdy.model.paintcomponents.PTestOutput;
import dx.leesdy.model.paintcomponents.PTimer;
import dx.leesdy.utils.*;

// Wav player wrapper
public class LDWavPlayer {
	
	// filename
	private String source;
	private BorderPane mRoot;
	private Media media;
	private WavWrapper wav;
	private MediaPlayer mediaPlayer;
	private BasicViewController viewController;
	Painter painter;
	
	public LDWavPlayer(String filename, BorderPane root) {
		source = filename;
		mRoot = root;
		initPlayer();
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
		//painter.addComponent(new PTestOutput(1));
		painter.addComponent(new PDrawVerticalLine(1));
		//painter.addComponent(new PTimer(2));
		painter.addComponent(new PDrawWav(3, wav));
		painter.addComponent(new PDrawPlaybackState(4,wav,this));
		
		LDExecutor.getExecutor().scheduleWithFixedDelay(painter,10,100,TimeUnit.MILLISECONDS);
		
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
	                
	                if (mediaPlayer.getError() == null) {
	                    mediaPlayer.setOnError(new Runnable() {
	                        public void run() {
	                            // Handle asynchronous error in MediaPlayer object.
	                        }
	                    });
	                   // mediaView = new MediaView(mediaPlayer);
//	                    mediaView.setOnError(new EventHandler() {
//	                        public void handle(MediaErrorEvent t) {
//	                            // Handle asynchronous error in MediaView.
//	                        }
//	                    });
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
					viewController.setPlayButtonState(true);
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
	
	private void onPlay() {
		mediaPlayer.play();
	}
	
	private void onStop() {
		
	}
	
	public MediaPlayer getPlayer() {
		return this.mediaPlayer;
	}
}
