/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapitems;

import java.util.ArrayList;

/**
 *
 * @author Romesh Malinga Perera
 */
public class City {
	private int id;
    private String name;
    private ArrayList<View> views;
    private ArrayList<Meal> Meals;
    private ArrayList<Bed> Beds;

    public City() {
    	setId(-1);
       name = "";
       views = new ArrayList<View>();
       Meals = new ArrayList<Meal>();
       Beds = new ArrayList<Bed>(); 
        
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
     * @return the views
     */
    public ArrayList<View> getViews() {
        return views;
    }

    /**
     * @param views the views to set
     */
    public void setViews(ArrayList<View> views) {
        this.views = views;
    }

    /**
     * @return the Meals
     */
    public ArrayList<Meal> getMeals() {
        return Meals;
    }

    /**
     * @param Meals the Meals to set
     */
    public void setMeals(ArrayList<Meal> Meals) {
        this.Meals = Meals;
    }

    /**
     * @return the Bed
     */
    public ArrayList<Bed> getBeds() {
        return Beds;
    }

    /**
     * @param Bed the Bed to set
     */
    public void setBeds(ArrayList<Bed> Beds) {
        this.Beds = Beds;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public View getLocationByViewName(String viewName)
    {
        for(int i=0;i<views.size();i++)
        {
            if(views.get(i).getName().equalsIgnoreCase(viewName))
            {
                return views.get(i);
            }
        }
        return null;
    }
    public void removeView(String name)
    {
          for(int i=0;i<views.size();i++)
        {
            if(views.get(i).getName().equalsIgnoreCase(name))
            {
               views.remove(i);
            }
        }
    }
}
