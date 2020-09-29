package util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Maze;

public class Cell {

	private int x, y, distancia, id;
	
	private Cell padre;
	
	private boolean visited = false;
	private boolean camino = false;
	private boolean deadEnd = false;
	
	private boolean[] muros = {true, true, true, true};
	
	public boolean[] getMuros() {
		return muros;
	}

	public void setMuros(boolean[] muros) {
		this.muros = muros;
	}

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.distancia = -1;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean isDeadEnd() {
		return deadEnd;
	}
	
	public void setDeadEnd(boolean deadEnd) {
		this.deadEnd = deadEnd;
	}

	public boolean iscamino() {
		return camino;
	}

	public void setcamino(boolean camino) {
		this.camino = camino;
	}
	
	public int getdistancia() {
		return distancia;
	}

	public void setdistancia(int distancia) {
		this.distancia = distancia;
	}

	public Cell getpadre() {
		return padre;
	}
	
	public void setpadre(Cell padre) {
		this.padre = padre;
	}
	
	public void draw(Graphics g) {
		int x2 = x * Maze.W;
	    int y2 = y * Maze.W;
	    
	    if (visited) {
	    	g.setColor(Color.MAGENTA);
	    	g.fillRect(x2, y2, Maze.W, Maze.W);
	    }
	   
	    if (camino) {
	    	g.setColor(Color.BLUE);
	    	g.fillRect(x2, y2, Maze.W, Maze.W);
	    } else if (deadEnd) {
	    	g.setColor(Color.RED);
	    	g.fillRect(x2, y2, Maze.W, Maze.W);
	    }
	    
	    g.setColor(Color.WHITE);
	    if (muros[0]) {
	    	g.drawLine(x2, y2, x2+Maze.W, y2);
	    }
	    if (muros[1]) {
	    	g.drawLine(x2+Maze.W, y2, x2+Maze.W, y2+Maze.W);
	    }
	    if (muros[2]) {
	    	g.drawLine(x2+Maze.W, y2+Maze.W, x2, y2+Maze.W);
	    }
	    if (muros[3]) {
	    	g.drawLine(x2, y2+Maze.W, x2, y2);
	    } 
	}
	
	public void displayAsColor(Graphics g, Color color) {
		int x2 = x * Maze.W;
	    int y2 = y * Maze.W;
		g.setColor(color);
    	g.fillRect(x2, y2, Maze.W, Maze.W);
	}
	
	public void removemuros(Cell next) {
		int x = this.x - next.x;
		 // norte 0, este 1, sur 2, oeste 3
		
		if(x == 1) {
			muros[3] = false;
			next.muros[1] = false;
		} else if (x == -1) {
			muros[1] = false;
			next.muros[3] = false;
		}
		
		int y = this.y - next.y;
		
		if(y == 1) {
			muros[0] = false;
			next.muros[2] = false;
		} else if (y == -1) {
			muros[2] = false;
			next.muros[0] = false;
		}
	}

	private Cell VecinoAleatorio(List<Cell> vecinos) {
		if (vecinos.size() > 0) {
			return vecinos.get(new Random().nextInt(vecinos.size()));
		} else {
			return null;
		}
	}
	
	private Cell checkvecinoInGridBounds(List<Cell> grid, Cell vecino) {
		if (grid.contains(vecino)) {
			return grid.get(grid.indexOf(vecino));
		} else {
			return null;
		}
	}
	
	// Used for Wilson's algorithm
	public Cell getCamino(List<Cell> grid) {

		List<Cell> vecinos = new ArrayList<Cell>(4);
		
		Cell norte = checkvecinoInGridBounds(grid, new Cell(x, y - 1));
		Cell este = checkvecinoInGridBounds(grid, new Cell(x + 1, y));
		Cell sur = checkvecinoInGridBounds(grid, new Cell(x, y + 1));
		Cell oeste = checkvecinoInGridBounds(grid, new Cell(x - 1, y));
		
		if (norte != null) if(!norte.camino) vecinos.add(norte);
		if (este != null) if(!este.camino) vecinos.add(este);
		if (sur != null) if(!sur.camino) vecinos.add(sur);
		if (oeste != null) if(!oeste.camino) vecinos.add(oeste);
		
		if (vecinos.size() ==  1) {
			return vecinos.get(0);
		}
		return randomvecino(vecinos);
	}

	public Cell getUnvisitedvecino(List<Cell> grid) {
		
		List<Cell> vecinos = getUnvisitedvecinosList(grid);
		
		if (vecinos.size() ==  1) {
			return vecinos.get(0);
		}
		return randomvecino(vecinos);
	}
	
	public List<Cell> getUnvisitedvecinosList(List<Cell> grid) {
		
		List<Cell> vecinos = new ArrayList<Cell>(4);
		
		Cell norte = checkvecinoInGridBounds(grid, new Cell(x, y - 1));
		Cell este = checkvecinoInGridBounds(grid, new Cell(x + 1, y));
		Cell sur = checkvecinoInGridBounds(grid, new Cell(x, y + 1));
		Cell oeste = checkvecinoInGridBounds(grid, new Cell(x - 1, y));
		
		if (norte != null) if(!norte.visited) vecinos.add(norte);
		if (este != null) if(!este.visited) vecinos.add(este);
		if (sur != null)if(!sur.visited) vecinos.add(sur);
		if (oeste != null) if(!oeste.visited)vecinos.add(oeste);
		
		return vecinos;
	}
	
	// no muros between
	public List<Cell> getValidMovevecinos(List<Cell> grid) {
		List<Cell> vecinos = new ArrayList<Cell>(4);
		
		Cell norte = checkvecinoInGridBounds(grid, new Cell(x, y - 1));
		Cell este = checkvecinoInGridBounds(grid, new Cell(x + 1, y));
		Cell sur = checkvecinoInGridBounds(grid, new Cell(x, y + 1));
		Cell oeste = checkvecinoInGridBounds(grid, new Cell(x - 1, y));
		
		if (norte != null) {
			if (!muros[0]) vecinos.add(norte);
		}	
		
		if (este != null) {
			if (!muros[1]) vecinos.add(este);
		}
		
		if (sur != null) {
			if (!muros[2]) vecinos.add(sur);
		}
		
		if (oeste != null) {
			if (!muros[3]) vecinos.add(oeste);
		}
		
		return vecinos;
	}
	
	// used for DFS solving, gets a vecino that could potentially be part of the solution camino.
	public Cell getCamino_Vecino(List<Cell> grid) {
		List<Cell> vecinos = new ArrayList<Cell>();
		
		Cell norte = checkvecinoInGridBounds(grid, new Cell(x, y - 1));
		Cell este = checkvecinoInGridBounds(grid, new Cell(x + 1, y));
		Cell sur = checkvecinoInGridBounds(grid, new Cell(x, y + 1));
		Cell oeste = checkvecinoInGridBounds(grid, new Cell(x - 1, y));
		
		if (norte != null) {
			if (!norte.deadEnd) {
				if (!muros[0]) vecinos.add(norte);
			}
		}
		
		if (este != null) {
			if (!este.deadEnd) {
				if (!muros[1]) vecinos.add(este);
			}
		}
		
		if (sur != null) {
			if (!sur.deadEnd) {
				if (!muros[2]) vecinos.add(sur);
			}
		}
		
		if (oeste != null) {
			if (!oeste.deadEnd) {
				if (!muros[3]) vecinos.add(oeste);
			}
		}
		
		if (vecinos.size() ==  1) {
			return vecinos.get(0);
		}
		
		return randomvecino(vecinos);
	}
	
	public List<Cell> getAllVecinos(List<Cell> grid) {
		List<Cell> vecinos = new ArrayList<Cell>();
		
		Cell norte = checkvecinoInGridBounds(grid, new Cell(x, y - 1));
		Cell este = checkvecinoInGridBounds(grid, new Cell(x + 1, y));
		Cell sur = checkvecinoInGridBounds(grid, new Cell(x, y + 1));
		Cell oeste = checkvecinoInGridBounds(grid, new Cell(x - 1, y));
		
		if (norte != null) vecinos.add(norte);
		if (este != null) vecinos.add(este);
		if (sur != null) vecinos.add(sur);
		if (oeste != null) vecinos.add(oeste);
		
		return vecinos;
	}
	
	public Cell getVecinoNorte(List<Cell> grid) {
		return checkvecinoInGridBounds(grid, new Cell(x, y - 1));
	}
	
	public Cell getVecinoEste(List<Cell> grid) {
		return checkvecinoInGridBounds(grid, new Cell(x + 1, y));
	}
	
	public Cell getVecinoSur(List<Cell> grid) {
		return checkvecinoInGridBounds(grid, new Cell(x, y + 1));
	}
	
	public Cell getVecinoOeste(List<Cell> grid) {
		return checkvecinoInGridBounds(grid, new Cell(x - 1, y));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}