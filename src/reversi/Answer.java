package reversi;

import it.unical.mat.embasp.languages.*;

@Id("answer")
public class Answer {
	
	@Param(0)
	private int x;
	
	@Param(1)
	private int y;
	
	public Answer(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public Answer() {
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
