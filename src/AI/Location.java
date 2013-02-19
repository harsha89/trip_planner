/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AI;

/**
 *
 * @author Harsha
 */
public class Location {
private String name;
private String city;
private double ApproximateTime;

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
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the ApproximateTime
     */
    public double getApproximateTime() {
        return ApproximateTime;
    }

    /**
     * @param ApproximateTime the ApproximateTime to set
     */
    public void setApproximateTime(double ApproximateTime) {
        this.ApproximateTime = ApproximateTime;
    }
}
