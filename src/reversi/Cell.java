package reversi;
import it.unical.mat.embasp.languages.*;

@Id("cell")
public class Cell {
	
	@Param(0)
	private int x;
	
	@Param(1)
	private int y;
	
	public Cell(int _x, int _y) {
		
		x = _x;
		y = _y;
		
	}
	
	public Cell() {
		
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
