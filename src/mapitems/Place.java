/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapitems;

/**
 *
 * @author Romesh
 */
public class Place {
    private String name;
    private int time;
    private int cost;

    public Place() {
        name = "";
        time = 0;
        cost = 0;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
}
