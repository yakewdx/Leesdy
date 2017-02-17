package dx.leesdy.player;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import dx.leesdy.view.Painter;
import dx.leesdy.model.*;
import dx.leesdy.model.paintcomponents.PTestOutput;
import dx.leesdy.model.paintcomponents.PTimer;

// Wav player wrapper
public class LDWavPlayer {
	
	// filename
	private String source;
	private BorderPane mRoot;
	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	Painter painter;
	
	public LDWavPlayer(String filename, BorderPane root) {
		source = filename;
		mRoot = root;
		initPlayer();
	}
	
	public void initGraphics(Canvas canvas) {
		// TODO Auto-generated method stub
		
		painter = new Painter(canvas);
		painter.addComponent(new PTestOutput(1));
		painter.addComponent(new PTimer(2));
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
	                if (mediaPlayer.getError() == null) {
	                    mediaPlayer.setOnError(new Runnable() {
	                        public void run() {
	                            // Handle asynchronous error in MediaPlayer object.
	                        }
	                    });
	                    mediaView = new MediaView(mediaPlayer);
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
	        mRoot.getChildren().add(mediaView);
	        
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
					
					if (painter != null) painter.paint();
					
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
	}
	
	private void onPlay() {
		mediaPlayer.play();
	}
	
	private void onStop() {
		
	}
}
