/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mapitems.View;
import mapitems.City;
import planner.TripPlanner;

/**
 *
 * @author Harsha
 */
public class PlaceRoute {

    private int noOfDays;
    private double hoursForDay;
    private double defaultStartTime;
    private List<City> city;
    private UserPreference preference;
    private double currentRemainingTime;
    private Map map;
    private ArrayList<Integer> visited;
    private TripPlanner planner;
    private int[][] AdjacencyMatrix;
    private ArrayList<Integer> route;
    private double CurrentTime;
    private double justBeforeCurrentTime;
    private List<City> routePlaces;
    private List<City> userPreference;
    private UserPlanOut planOut;
    private Day day;

    public PlaceRoute(UserPreference preference) {
        this.preference = preference;
        planOut=new UserPlanOut();
        setDefaultStartTime(7);
        noOfDays=preference.getNoOfDays();
        setHoursForDay(18);
    }



   
    private ArrayList<Integer> getRouteCities(ArrayList<Integer> cityId, int startId) {
        ArrayList<Integer> temp;
        setPlanner(new TripPlanner());
        temp = getPlanner().getRoute(cityId, startId);
        setCity(getPlanner().getMap().getCities());
      //  System.out.println("Ahh"+route.size());
        setAdjacencyMatrix(getPlanner().getMap().getAdjacencyMatrix());
        return temp;

    }

    public UserPlanOut planRoute(ArrayList<Integer> cityId, int startId) {
        getRouteCities(cityId, startId);
        route=new ArrayList<Integer>();
        route.add(1);
        route.add(2);
        route.add(3);
        route.add(4);
        route.add(1);
        routePlaces = makeRouteCities();
        userPreference = userPreference();
        for(int i=0;i<routePlaces.size();i++)
        {
            System.out.println(routePlaces.get(i).getName());
        }
         for(int i=0;i<userPreference.size();i++)
        {
            System.out.println(userPreference.get(i).getName());

        }
        planOut.setRoute((ArrayList<City>) routePlaces);
        while (noOfDays > 0) {
            day = new Day();
            if (noOfDays == preference.getNoOfDays()) {
                CurrentTime = preference.getStartTime();
                System.out.println("Day 1");
            } else {
                CurrentTime = getDefaultStartTime();
            }
            planRouteForDay();
            planOut.addDay(day);
            noOfDays--;
        }
        return planOut;
    }

    public void planRouteForDay() {
       
        double cityTravelTime;
        List<City> user_Preference = userPreference;
        for (int i = 0; i < routePlaces.size() - 1; i++) {
            addSuggestions(routePlaces.get(i));
            for (int j = 0; j < user_Preference.size(); j++) {
                if (user_Preference.get(j).getId() == routePlaces.get(i).getId()) {
                    System.out.println("CallerFunction");
                    String s = planVisitingPlaces(j);
                    
                    if (s.equalsIgnoreCase("end")) {
                        System.out.println("end");
                        return;
                    } else {
                        userPreference.remove(user_Preference.get(j));
                        System.out.println("Yeppi");
                    }
                }
            }
            cityTravelTime = AdjacencyMatrix[routePlaces.get(i).getId()][routePlaces.get(i + 1).getId()];
            if (isDayEnd(CurrentTime, cityTravelTime)) {
                if(noOfDays>1)
                {
                    for(int a=0;a<routePlaces.get(i).getBeds().size();a++)
                    {
                        Location loc = new Location();
                        loc.setCity(routePlaces.get(i).getName());
                        loc.setName(routePlaces.get(i).getBeds().get(a).getName());
                        day.addRestaurant(loc);
                    }
                }
                else if(noOfDays==1 && preference.isDinner())
                {
                    for(int b=0;b<routePlaces.get(i).getBeds().size();b++)
                    {
                        Location loc = new Location();
                        loc.setCity(routePlaces.get(i).getName());
                        loc.setName(routePlaces.get(i).getMeals().get(b).getName());
                        day.addDinner(loc);
                    }
                }
                return;
            } else if(!isDayEnd(CurrentTime, cityTravelTime)){
                justBeforeCurrentTime = CurrentTime;
                CurrentTime += cityTravelTime;
                if (chekForBreakFirst(CurrentTime, justBeforeCurrentTime)) {
                    if (preference.isBreakFast()) {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime = +1;
                        Location loc = new Location();
                        loc.setCity(routePlaces.get(i).getName());
                        loc.setApproximateTime(justBeforeCurrentTime);
                        loc.setName("Cannot get meal at a visiting place.Changing between cities");
                        day.addMorningMeal(loc);
                    } else {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1;
                        for (int j = 0; j < routePlaces.get(i).getMeals().size(); j++) {
                            Location loc = new Location();
                            loc.setCity(routePlaces.get(i).getName());
                            loc.setApproximateTime(justBeforeCurrentTime);
                            loc.setName(routePlaces.get(i).getMeals().get(j).getName());
                            day.addMorningMeal(loc);
                        }
                    }
                } else if (checkForLunch(CurrentTime, justBeforeCurrentTime)) {
                    if (preference.isLunch()) {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1.5;
                        Location loc = new Location();
                        loc.setCity(routePlaces.get(i).getName());
                        loc.setApproximateTime(justBeforeCurrentTime);
                        loc.setName("Cannot get meal at a visiting place.Changing between cities");
                        day.addLunch(loc);
                    } else {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1.5;
                        for (int j = 0; j < routePlaces.get(i).getMeals().size(); j++) {
                            Location loc = new Location();
                            loc.setCity(routePlaces.get(i).getName());
                            loc.setApproximateTime(justBeforeCurrentTime);
                            loc.setName(routePlaces.get(i).getMeals().get(j).getName());
                            day.addLunch(loc);
                        }
                    }
                } else if (checkForEveningTea(CurrentTime, justBeforeCurrentTime)) {
                    if (preference.isEveningMeal()) {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1;
                        Location loc = new Location();
                        loc.setCity(routePlaces.get(i).getName());
                        loc.setApproximateTime(justBeforeCurrentTime);
                        loc.setName("Cannot get meal at a visiting place.Changing between cities");
                        day.addeveningMealPlace(loc);
                    } else {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1;
                        for (int j = 0; j < routePlaces.get(i).getMeals().size(); j++) {
                            Location loc = new Location();
                            loc.setCity(routePlaces.get(i).getName());
                            loc.setApproximateTime(justBeforeCurrentTime);
                            loc.setName(routePlaces.get(i).getMeals().get(j).getName());
                            day.addeveningMealPlace(loc);
                        }
                    }
                }
                routePlaces.remove(routePlaces.get(i));
                i = 0;
            }
            System.out.println(CurrentTime+"After a City");
        }
    }

    public void addSuggestions(City city) {
        City temp = null;
        System.out.println("Awwwwwwwww");
        if(!isSuggestionExist(city.getName())){
        for (int j = 0; j < userPreference.size(); j++) {
            if (userPreference.get(j).getId() == city.getId()) {
                System.out.println("found");
                temp = userPreference.get(j);
            }

        }
        if (temp != null) {
            for (int a = 0; a < city.getViews().size(); a++) {
                boolean isTrue=false;
                for (int b = 0; b < temp.getViews().size(); b++) {
                    
                    if (city.getViews().get(a).getName().equalsIgnoreCase(temp.getViews().get(b).getName())) {
                        isTrue=true;
                        break;
                    }
                }
                if(!isTrue)
                {
                        System.out.println("AddingSuggestion");
                        Location location = new Location();
                        location.setCity(city.getName());
                        location.setName(city.getViews().get(a).getName());
                        day.addSuggetion(location);
                }
            }
        } else {
            for (int a = 0; a < city.getViews().size(); a++) {
                System.out.println("AddingSuggestionFinish");
                Location location = new Location();
                location.setCity(city.getName());
                location.setName(city.getViews().get(a).getName());
                day.addSuggetion(location);
            }
        }
        }
    }
     public boolean isSuggestionExist(String name)
    {
         for(int i=0;i<day.getSuggestions().size();i++)
         {
             if(day.getSuggestions().get(i).getCity().equalsIgnoreCase(name))
                 return true;
         }
        
             return false;
     }
    public boolean chekForBreakFirst(double currentTime, double timebefore) {
        if (preference.getBreakFirstTime() <= currentTime && preference.getBreakFirstTime() >= timebefore) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkForLunch(double currentTime, double timebefore) {
        if (preference.getLunchTime() <= currentTime && preference.getLunchTime() >= timebefore) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkForEveningTea(double currentTime, double timebefore) {
        if (preference.getEveningMealTime() <= currentTime && preference.getEveningMealTime() >= timebefore) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Planning trip within visiting city
     * @param x
     * @return
     */
    public String planVisitingPlaces(int x) {
        System.out.println("Callededdd");
        System.out.println(CurrentTime+"See");
        double visitingTime = 0;
        System.out.println("Oooopz"+userPreference.get(x).getViews().size());
        while (!userPreference.get(x).getViews().isEmpty()) {
            visitingTime = userPreference.get(x).getViews().get(0).getTime()/60;
            System.out.println("VistingTIme"+visitingTime);
            if (isDayEnd(CurrentTime, visitingTime)) {
                System.out.println("DayEndwwwww");
                if(noOfDays>1)
                {
                    for(int a=0;a<userPreference.get(x).getBeds().size();a++)
                    {
                        Location loc = new Location();
                        loc.setCity(userPreference.get(x).getName());
                        loc.setName(userPreference.get(x).getBeds().get(a).getName());
                        day.addRestaurant(loc);
                    }
                }
                else if(noOfDays==1 && preference.isDinner())
                {
                    for(int b=0;b<userPreference.get(x).getBeds().size();b++)
                    {
                        Location loc = new Location();
                        loc.setCity(userPreference.get(x).getName());
                        loc.setName(userPreference.get(x).getMeals().get(b).getName());
                        day.addDinner(loc);
                    }
                 }
                return "end";
            } else {
                    justBeforeCurrentTime=CurrentTime;
                    CurrentTime+=visitingTime;
                if (chekForBreakFirst(CurrentTime, justBeforeCurrentTime)) {
                    if (preference.isBreakFast()) {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 0.25;
                        Location loc = new Location();
                        loc.setCity(userPreference.get(x).getName());
                        loc.setApproximateTime(justBeforeCurrentTime);
                        loc.setName(userPreference.get(x).getViews().get(0).getName());
                        day.addMorningMeal(loc);
                    } else {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1;
                        for (int j = 0; j < userPreference.get(x).getMeals().size(); j++) {
                            Location loc = new Location();
                            loc.setCity(userPreference.get(x).getName());
                            loc.setApproximateTime(justBeforeCurrentTime);
                            loc.setName(userPreference.get(x).getMeals().get(j).getName());
                            day.addMorningMeal(loc);
                        }
                    }
                } else if (checkForLunch(CurrentTime, justBeforeCurrentTime)) {
                    if (preference.isLunch()) {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1;
                        Location loc = new Location();
                        loc.setCity(userPreference.get(x).getName());
                        loc.setApproximateTime(justBeforeCurrentTime);
                        loc.setName(userPreference.get(x).getViews().get(0).getName());
                        day.addLunch(loc);
                    } else {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime +=1.5;
                        for (int j = 0; j < userPreference.get(x).getMeals().size(); j++) {
                            Location loc = new Location();
                            loc.setCity(userPreference.get(x).getName());
                            loc.setApproximateTime(justBeforeCurrentTime);
                            loc.setName(userPreference.get(x).getMeals().get(j).getName());
                            day.addLunch(loc);
                        }
                    }
                } else if (checkForEveningTea(CurrentTime, justBeforeCurrentTime)) {
                    if (preference.isEveningMeal()) {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 0.25;
                        Location loc = new Location();
                        loc.setCity(userPreference.get(x).getName());
                        loc.setApproximateTime(justBeforeCurrentTime);
                        loc.setName(userPreference.get(x).getViews().get(0).getName());
                        day.addeveningMealPlace(loc);
                    } else {
                        justBeforeCurrentTime = CurrentTime;
                        CurrentTime += 1;
                        for (int j = 0; j < userPreference.get(x).getMeals().size(); j++) {
                            Location loc = new Location();
                            loc.setCity(userPreference.get(x).getName());
                            loc.setApproximateTime(justBeforeCurrentTime);
                            loc.setName(userPreference.get(x).getMeals().get(j).getName());
                            day.addeveningMealPlace(loc);
                        }
                    }
                }
            }
            Location location = new Location();
            location.setCity(userPreference.get(x).getName());
            location.setApproximateTime(justBeforeCurrentTime);
            location.setName(userPreference.get(x).getViews().get(0).getName());
            day.addVisitingPlace(location);
            userPreference.get(0).removeView(userPreference.get(x).getViews().get(0).getName());

        }
            
        return "Ok";
    }

    public boolean isDayEnd(double currenTime, double otherTime) {
        if (getHoursForDay() > currenTime + otherTime) {
            return false;
        } else {
            return true;
        }
    }

    public List<City> makeRouteCities() {
        List<City> city = new ArrayList<City>();

        for (int i = 0; i < route.size(); i++) {
            city.add(getCityById(route.get(i)));
        }
        return city;
    }

    public List<City> userPreference() {
        List<UserData> data = preference.getData();
        List<City> city = new ArrayList<City>();
        for (int i = 0; i < data.size(); i++) {
            ArrayList<View> view = new ArrayList<View>();
            City temp = getCityById(data.get(i).getCityId());
            for (int j = 0; j < data.get(i).getLocations().size(); j++) {
                View temp1 = temp.getLocationByViewName(data.get(i).getLocations().get(j));
                view.add(temp1);
            }
            temp.setViews(view);
            city.add(temp);
        }
        return city;
    }

    public boolean isVisited(int id) {
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i) == id) {
                return true;
            }
        }
        return false;
    }

    public City getCityById(int id) {
        City temp = null;
        for (int i = 0; i < city.size(); i++) {
            if (city.get(i).getId() == id) {
                temp = city.get(i);
            }
        }
        return temp;
    }

    private void remainingTime() {
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
     * @param city the city to set
     */
    public void setCity(List<City> city) {
        this.city=city;
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

    /**
     * @return the defaultStartTime
     */
    public double getDefaultStartTime() {
        return defaultStartTime;
    }

    /**
     * @param defaultStartTime the defaultStartTime to set
     */
    public void setDefaultStartTime(double defaultStartTime) {
        this.defaultStartTime = defaultStartTime;
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * @return the planner
     */
    public TripPlanner getPlanner() {
        return planner;
    }

    /**
     * @param planner the planner to set
     */
    public void setPlanner(TripPlanner planner) {
        this.planner = planner;
    }

    /**
     * @return the AdjacencyMatrix
     */
    public int[][] getAdjacencyMatrix() {
        return AdjacencyMatrix;
    }

    /**
     * @param AdjacencyMatrix the AdjacencyMatrix to set
     */
    public void setAdjacencyMatrix(int[][] AdjacencyMatrix) {
        this.AdjacencyMatrix = AdjacencyMatrix;
    }

    /**
     * @return the route
     */
    public ArrayList<Integer> getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(ArrayList<Integer> route) {
        this.route = route;
    }

    /**
     * @return the routePlaces
     */
    public List<City> getRoutePlaces() {
        return routePlaces;
    }

    /**
     * @param routePlaces the routePlaces to set
     */
    public void setRoutePlaces(List<City> routePlaces) {
        this.routePlaces = routePlaces;
    }

    /**
     * @return the userPererence
     */
    public List<City> getUserPererence() {
        return userPreference;
    }

    /**
     * @param userPererence the userPererence to set
     */
    public void setUserPererence(List<City> userPererence) {
        this.userPreference = userPererence;
    }
}
