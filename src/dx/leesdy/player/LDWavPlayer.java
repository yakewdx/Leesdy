package dx.leesdy.player;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

// Wav player wrapper
public class LDWavPlayer {
	
	// filename
	private String source;
	private BorderPane mRoot;
	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	
	public LDWavPlayer(String filename, BorderPane root) {
		source = filename;
		mRoot = root;
		initPlayer();
	}
	
	
	private void initPlayer() {
		try {
	        media = new Media(source);
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
	    } catch (Exception mediaException) {
	        // Handle exception in Media constructor.
	    	mediaException.printStackTrace();
	    }
		
	}
	private void onPlay() {
		
	}
	
	private void onStop() {
		
	}
}
