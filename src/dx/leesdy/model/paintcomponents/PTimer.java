package dx.leesdy.model.paintcomponents;

import java.util.*;
import java.text.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PTimer extends PainterComponent {

	
	public PTimer(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Canvas canvas) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//�������ڸ�ʽ
		String s = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.fillText(s, 10, 10);                    
	}
}
