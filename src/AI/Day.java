/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.ArrayList;

/**
 *
 * @author Harsha
 */
public class Day {

    public Day() {
        
        visitingPlpaces = new ArrayList<Location>();
        morningMeal = new ArrayList<Location>();
        lunch = new ArrayList<Location>();
        evening = new ArrayList<Location>();
        restaurant = new ArrayList<Location>();
        suggestions = new ArrayList<Location>();
        dinner = new ArrayList<Location>();
    }
    private ArrayList<Location> visitingPlpaces;
    private ArrayList<Location> morningMeal;
    private ArrayList<Location> lunch;
    private ArrayList<Location> evening;
    private ArrayList<Location> restaurant;
    private ArrayList<Location> suggestions;
    private ArrayList<Location> dinner;
    public void addVisitingPlace(Location location) {
        visitingPlpaces.add(location);
    }
     public void addDinner(Location location) {
        dinner.add(location);
    }
    public void addLunch(Location location) {
        lunch.add(location);
    }

    public void addMorningMeal(Location location) {
        morningMeal.add(location);
    }

    public void addeveningMealPlace(Location location) {
        evening.add(location);
    }

    public void addRestaurant(Location location) {
        restaurant.add(location);
    }

    public void addSuggetion(Location location) {
        suggestions.add(location);
    }

    /**
     * @return the visitingPlpaces
     */
    public ArrayList<Location> getVisitingPlpaces() {
        return visitingPlpaces;
    }

    /**
     * @param visitingPlpaces the visitingPlpaces to set
     */
    public void setVisitingPlpaces(ArrayList<Location> visitingPlpaces) {
        this.visitingPlpaces = visitingPlpaces;
    }

    /**
     * @return the morningMeal
     */
    public ArrayList<Location> getMorningMeal() {
        return morningMeal;
    }

    /**
     * @param morningMeal the morningMeal to set
     */
    public void setMorningMeal(ArrayList<Location> morningMeal) {
        this.morningMeal = morningMeal;
    }

    /**
     * @return the lunch
     */
    public ArrayList<Location> getLunch() {
        return lunch;
    }

    /**
     * @param lunch the lunch to set
     */
    public void setLunch(ArrayList<Location> lunch) {
        this.lunch = lunch;
    }

    /**
     * @return the evening
     */
    public ArrayList<Location> getEvening() {
        return evening;
    }

    /**
     * @param evening the evening to set
     */
    public void setEvening(ArrayList<Location> evening) {
        this.evening = evening;
    }

    /**
     * @return the restaurant
     */
    public ArrayList<Location> getRestaurant() {
        return restaurant;
    }

    /**
     * @param restaurant the restaurant to set
     */
    public void setRestaurant(ArrayList<Location> restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * @return the suggestions
     */
    public ArrayList<Location> getSuggestions() {
        return suggestions;
    }

    /**
     * @param suggestions the suggestions to set
     */
    public void setSuggestions(ArrayList<Location> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * @return the dinner
     */
    public ArrayList<Location> getDinner() {
        return dinner;
    }

    /**
     * @param dinner the dinner to set
     */
    public void setDinner(ArrayList<Location> dinner) {
        this.dinner = dinner;
    }
}
