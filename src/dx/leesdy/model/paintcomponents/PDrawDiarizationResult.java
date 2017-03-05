/**
 * Leesdy
 * dx.leesdy.model.paintcomponents
 * @Author: DxWang PDrawDiarizationResult.java
 * Created at:2017年3月1日
 */
package dx.leesdy.model.paintcomponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

import dx.leesdy.model.WavWrapper;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDDiarizationResultReader;
import dx.leesdy.utils.LDInterval;
import dx.leesdy.utils.LDSegment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class is just for testing
 *
 */
public class PDrawDiarizationResult extends PDrawPlaybackState {

	
	ArrayList<LDSegment> list;
	Map<String, Color> colors;
	/**
	 * @param priority
	 */
	public PDrawDiarizationResult(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
		colors = new HashMap<String, Color>();
		this.list = new ArrayList<LDSegment>();
	}
	
	public PDrawDiarizationResult(int priority, WavWrapper wav, LDWavPlayer player, LDDiarizationResultReader reader) {
		super(priority, wav, player);
		colors = new HashMap<String, Color>();
		this.list = reader.getList();
	}

	
	/* (non-Javadoc)
	 * @see dx.leesdy.model.paintcomponents.PainterComponent#paint(javafx.scene.canvas.Canvas)
	 */
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		MediaPlayer pl = player.getPlayer();
		Duration mediaLength = pl.getTotalDuration();
		Double totalTime = mediaLength.toSeconds();
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		double k = canvasWidth / totalTime;
		
		list.forEach(new Consumer<LDSegment> () {
			@Override
			public void accept(LDSegment arg0) {
				// TODO Auto-generated method stub
				String speakerLabel = arg0.getSpeaker();
				Double startpos = arg0.getStartPos() * k;
				Double endpos = arg0.getEndPos() * k;
				
				
				if (colors.get(speakerLabel) == null) {
					Random rand = new Random();
					int red = rand.nextInt(127) + 128;
					int green = rand.nextInt(127) + 128;
					int blue = rand.nextInt(127) + 128;
					
					Color color = Color.rgb(red, green, blue);
					colors.put(speakerLabel, color);
				}
				
				gc.setStroke(colors.get(speakerLabel));
				gc.setFill(colors.get(speakerLabel));
				gc.setLineWidth(1.0);
				gc.strokeLine(startpos, 0, startpos, canvasHeight);
				gc.strokeLine(endpos, 0, endpos, canvasHeight);
				gc.setGlobalAlpha(0.5);
				gc.fillRect(startpos, 0, (endpos - startpos), canvasHeight);
				gc.setGlobalAlpha(1.0);
			}
		});

	}

}