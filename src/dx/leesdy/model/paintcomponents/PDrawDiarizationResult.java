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

import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.model.WavWrapper;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDDiarizationResultReader;
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
	
	Consumer<LDSegment> paintResult;
	/**
	 * @param priority
	 */
	public PDrawDiarizationResult(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		this.list = this.statusCenter.getReader().getList();
		init();
		
	}
	
	
	private void init() {
		colors = new HashMap<String, Color>();
		this.name = "PDrawDiarizationResult";
	}

	
	/* (non-Javadoc)
	 * @see dx.leesdy.model.paintcomponents.PainterComponent#paint(javafx.scene.canvas.Canvas)
	 */
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		MediaPlayer pl = player;
		Duration mediaLength = pl.getTotalDuration();
		Double totalTime = mediaLength.toSeconds();
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		double k = canvasWidth / totalTime;
		
		for (LDSegment arg0: list) {
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
			gc.strokeLine(startpos, 40, startpos, canvasHeight - 40);
			gc.strokeLine(endpos, 40, endpos, canvasHeight - 40);
			gc.setGlobalAlpha(0.5);
			gc.fillRect(startpos, 40, (endpos - startpos), canvasHeight - 80);
			gc.setGlobalAlpha(1.0);
			
			//gc.setFill(Color.WHITE);
			//gc.fillText(speakerLabel, startpos + 2, 13);
		}

	}
	
	@Override
	public void updateState() {
		this.setNeedToUpdate(false);
	}

}
