package planner;

import java.util.ArrayList;
import mapitems.City;

/**
 *
 * @author Romesh
 */
public class Map {
    private int mapSize;
    private int[][] AdjacencyMatrix;
    private ArrayList<City> Cities;

    public Map() {
        mapSize = 11;
        setAdjacencyMatrix(new int[mapSize][mapSize]);
        Cities = new ArrayList<City>();
    }

    /**
     * @return the Cities
     */
    public ArrayList<City> getCities() {
        return Cities;
    }

    /**
     * @param Cities the Cities to set
     */
    public void setCities(ArrayList<City> Cities) {
        this.Cities = Cities;
    }

	public int[][] getAdjacencyMatrix() {
		return AdjacencyMatrix;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		AdjacencyMatrix = adjacencyMatrix;
	}
    
    
    
    
}
