package dataaccess;

import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import mapitems.Bed;
import mapitems.City;
import mapitems.Meal;
import mapitems.Place;
import mapitems.View;
import planner.Map;

/**
 *
 * @author Romesh
 */
public class MapGenerator {
    private XMLReader newReader;
    private Map Map;
    
    private ArrayList<City> Cities;
    private int[][] AdjacencyMatrix;
    
    public MapGenerator(Map newMap) {
        newReader = new XMLReader();
        Map = newMap;
        Cities = new ArrayList<City>();
        
    }
    public Map Generate(){
        NodeList CityList;
        NodeList AjMatrix;
        Cities = Map.getCities();
        AdjacencyMatrix = Map.getAdjacencyMatrix();
        CityList = newReader.readCityInfo("cityInfo.xml");
        
        for(int s=0; s<CityList.getLength() ; s++){
            Node CityNode = CityList.item(s);
            if(CityNode.getNodeType() == Node.ELEMENT_NODE){
                City newCity = new City();
                newCity.setId(s);
                Element cityElement = (Element)CityNode;

                    //-------
                    NodeList NameList = cityElement.getElementsByTagName("name");
                    Element nameElement = (Element)NameList.item(0);

                    NodeList textFNList = nameElement.getChildNodes();
                    newCity.setName(((Node)textFNList.item(0)).getNodeValue().trim());
                    
                    
                    
                newCity.setViews(setCityViews(cityElement.getElementsByTagName("visit")));
                newCity.setBeds(setCityBeds(cityElement.getElementsByTagName("bed")));
                newCity.setMeals(setCityMeals(cityElement.getElementsByTagName("meals")));
                
                
                Cities.add(newCity);
            }
        }

        AjMatrix = newReader.readAdjacencyMatrix("AdjacencyMatrix.xml");
        
        for(int s=0; s<AjMatrix.getLength() ; s++){
            Node MatrixNode = AjMatrix.item(s);
            if(MatrixNode.getNodeType() == Node.ELEMENT_NODE){
                
                Element MatrixElement = (Element)MatrixNode;

                    //-------
                    NodeList weightList = MatrixElement.getElementsByTagName("col");
                    for (int i = 0; i < weightList.getLength(); i++) {
						Element nameElement = (Element)weightList.item(i);
						NodeList textFNList = nameElement.getChildNodes();
	                    AdjacencyMatrix[s][i] = Integer.parseInt(((Node)textFNList.item(0)).getNodeValue().trim());
					}
                    

                    
            }
        }
        
        Map.setAdjacencyMatrix(AdjacencyMatrix);
        Map.setCities(Cities);
        return Map;
     }
    
    public String setAttribute(Element Element, Place newMeal, String attr) throws DOMException {
        //-------
        NodeList List = Element.getElementsByTagName(attr);
        Element newElement = (Element)List.item(0);

        NodeList textnameList = newElement.getChildNodes();
        return ((Node)textnameList.item(0)).getNodeValue().trim();
    }
    
    
    public ArrayList<Meal> setCityMeals(NodeList mealList){
        ArrayList<Meal> Meals = new ArrayList<Meal>();
        
        for(int s=0; s<mealList.getLength() ; s++){
            Node MealNode = mealList.item(s);
            if(MealNode.getNodeType() == Node.ELEMENT_NODE){
                Meal newMeal = new Meal();
                
                Element mealElement = (Element)MealNode;
                    newMeal.setName(setAttribute(mealElement, newMeal, "name"));
                    newMeal.setCost(Integer.parseInt( setAttribute(mealElement, newMeal, "cost")));
                    newMeal.setTime(Integer.parseInt( setAttribute(mealElement, newMeal, "time")));
                 
                    Meals.add(newMeal);
            }
        }
        
        return Meals;
    }

    public ArrayList<Bed> setCityBeds(NodeList bedList){
        ArrayList<Bed> Beds = new ArrayList<Bed>();
        for(int s=0; s<bedList.getLength() ; s++){
            Node BedNode = bedList.item(s);
            if(BedNode.getNodeType() == Node.ELEMENT_NODE){
                Bed newBed = new Bed();
                
                Element BedElement = (Element)BedNode;
                    newBed.setName(setAttribute(BedElement, newBed, "name"));
                    newBed.setCost(Integer.parseInt( setAttribute(BedElement, newBed, "cost")));
                 
                    Beds.add(newBed);
                
            }
        }
        return Beds;
    }
    
    public ArrayList<View> setCityViews(NodeList viewList){
        ArrayList<View> Views = new ArrayList<View>();
        for(int s=0; s<viewList.getLength() ; s++){
            Node ViewNode = viewList.item(s);
            if(ViewNode.getNodeType() == Node.ELEMENT_NODE){
                View newView = new View();
                
                Element ViewElement = (Element)ViewNode;
                    newView.setName(setAttribute(ViewElement, newView, "name"));
                    newView.setCost(Integer.parseInt( setAttribute(ViewElement, newView, "cost")));
                    newView.setTime(Integer.parseInt( setAttribute(ViewElement, newView, "time")));
                 
                    Views.add(newView);
            }
        }
        return Views;
    }
}

