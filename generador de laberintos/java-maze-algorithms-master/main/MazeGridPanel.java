package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import generator.*;
import util.Cell;

public class MazeGridPanel extends JPanel {

	private static final long serialVersionUID = 7237062514425122227L;
	private final List<Cell> grid = new ArrayList<Cell>();
	private List<Cell> currentCells = new ArrayList<Cell>();

	public MazeGridPanel(int rows, int cols) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				grid.add(new Cell(x, y));
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		// +1 pixel on width and height so bottom and right borders can be drawn.
		return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
	}

	public void generate() {
		// switch statement for gen method read from combobox in Maze.java
		new WilsonsGen(grid, this);
	}
	
	public void resetSolution() {
		for (Cell c : grid) {
			c.setDeadEnd(false);
			c.setPath(false);
			c.setDistance(-1);
			c.setParent(null);
		}
		repaint();
	}
	
	public void setCurrent(Cell current) {
		if(currentCells.size() == 0) {
			currentCells.add(current);
		} else {
			currentCells.set(0, current);			
		}
	}
	
	public void setCurrentCells(List<Cell> currentCells) {
		this.currentCells = currentCells;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Cell c : grid) {
			c.draw(g);
		}
		for (Cell c : currentCells) {
			if(c != null) c.displayAsColor(g, Color.ORANGE);
		}
		grid.get(0).displayAsColor(g, Color.GREEN); // start cell
		grid.get(grid.size() - 1).displayAsColor(g, Color.YELLOW); // end or goal cell
	}
}
