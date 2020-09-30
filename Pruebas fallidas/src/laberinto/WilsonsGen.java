package laberinto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.Timer;

public class WilsonsGen {

	private final List<Celda> grid; //celdas
	private final Stack<Celda> stack = new Stack<Celda>(); //laberinto
	private Celda current;
	private final Random r = new Random();

	public WilsonsGen(List<Celda> grid) {
		this.grid = grid;
		current = grid.get(r.nextInt(grid.size()));
		current.setVisitado(true);
		current = grid.get(r.nextInt(grid.size()));
		final Timer timer = new Timer(Maze.speed, null);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!grid.parallelStream().allMatch(c -> c.isVisitado())) {
					carve();
				} else {
					current = null;
					timer.stop();
				}
			}
		});
		timer.start();
	}
	
	private void carve() {
		if (current.isVisitado()) {
			addPathToMaze();
			// TODO: Minor future refinement:
			/* Do not need to run through whole maze with stream filter.
			 * Could maintain a list of all cells not in the maze from beginning and remove them 
			 * from the list as we pop them off the stack in addPathToMaze(). Algorithm should still work as 
			 * current will never be in maze. When this list is empty we have carved the maze.
			 */
			List<Celda> notInMaze = grid.parallelStream().filter(c -> !c.isVisitado()).collect(Collectors.toList());
			if (!notInMaze.isEmpty()) {
				current = notInMaze.get(r.nextInt(notInMaze.size()));							
			} else {
				return;
			}
		}
		current.setCamino(true);
		Celda next = current.getNonPathNeighbour(grid);
		if (next != null) {
			stack.push(current);
			current.eliminarMuro(next);
			current = next;
		} else if (!stack.isEmpty()) {
			try {
				current = stack.pop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addPathToMaze() {
		grid.parallelStream().filter(c -> c.isCamino()).forEach(c -> {
			c.setVisitado(true); 
			c.setCamino(false);
		});
		stack.clear();
	}
}