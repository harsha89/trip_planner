/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.List;
import mapitems.City;

/**
 *
 * @author Harsha
 */
public class PlaceRoute {

    private int noOfDays;
    private double hoursForDay;
    private List<City> city;
    private UserPreference preference;
    private int currentDate;
    private double currentRemainingTime;
    public double getTotalTravelingTime() {
        double TotalTravelingTIme = 0;
        for (int i = 0; i < getPreference().getCity().size(); i++) {
            for (int j = 0; j < getPreference().getCity().get(i).getViews().size(); j++) {
                TotalTravelingTIme = +preference.getCity().get(i).getViews().get(j).getTime();
            }
        }

        return TotalTravelingTIme;
    }

    public double getFullTravelTime() {
        double firstDay = getHoursForDay() - Double.parseDouble(getPreference().getStartTime());
        double remainingDayTime = (getPreference().getNoOfDays() - 1) *getHoursForDay();
        return firstDay+remainingDayTime;
    }
    public double getFirstdayTrvaelTime()
    {
        double time=getHoursForDay()-Double.parseDouble(preference.getStartTime());
        return time;
    }
    public void planRoute() {
        setCurrentRemainingTime(Double.parseDouble(preference.getStartTime()));
    }

    private void remainingTime() 
    {

    }

    private double getSpareTime() {
        return getFullTravelTime()-getTotalTravelingTime();
    }

    /**
     * @return the noOfDays
     */
    public int getNoOfDays() {
        return noOfDays;
    }

    /**
     * @param noOfDays the noOfDays to set
     */
    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     * @return the hoursForDay
     */
    public double getHoursForDay() {
        return hoursForDay;
    }

    /**
     * @param hoursForDay the hoursForDay to set
     */
    public void setHoursForDay(double hoursForDay) {
        this.hoursForDay = hoursForDay;
    }

    /**
     * @return the city
     */
    public List<City> getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(List<City> city) {
        this.city = city;
    }

    /**
     * @return the preference
     */
    public UserPreference getPreference() {
        return preference;
    }

    /**
     * @param preference the preference to set
     */
    public void setPreference(UserPreference preference) {
        this.preference = preference;
    }

    /**
     * @return the currentDate
     */
    public int getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(int currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the currentRemainingTime
     */
    public double getCurrentRemainingTime() {
        return currentRemainingTime;
    }

    /**
     * @param currentRemainingTime the currentRemainingTime to set
     */
    public void setCurrentRemainingTime(double currentRemainingTime) {
        this.currentRemainingTime = currentRemainingTime;
    }
}
