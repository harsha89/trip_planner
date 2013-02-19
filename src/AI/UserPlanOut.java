/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AI;

import java.util.ArrayList;
import java.util.List;
import mapitems.City;

/**
 *
 * @author Harsha
 */
public class UserPlanOut {
private  List<Day> dayPlan;
private  ArrayList<City> route;
public void addDay(Day day)

    {
        getDayPlan().add(day);
}

    public UserPlanOut() {
        this.dayPlan = new ArrayList<Day>();
        
    }

    /**
     * @return the dayPlan
     */
    public List<Day> getDayPlan() {
        return dayPlan;
    }

    /**
     * @param dayPlan the dayPlan to set
     */
    public void setDayPlan(List<Day> dayPlan) {
        this.dayPlan = dayPlan;
    }

    /**
     * @return the route
     */
    public ArrayList<City> getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(ArrayList<City> route) {
        this.route = route;
    }
}
