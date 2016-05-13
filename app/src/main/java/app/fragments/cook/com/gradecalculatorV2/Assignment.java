package app.fragments.cook.com.gradecalculatorV2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cbcoo_000 on 6/20/2015.
 */
public class Assignment implements Serializable{
    private String name;
    private double weight;
    private double average;


    public Assignment(double weight, double average, String name) {
        this.weight = weight;
        this.average = average;
        this.name = name;
    }
    public void setWeight(double value){
        weight = value;
    }
    public void setName(String holder){
        name = holder;
    }
    public double getWeight(){
        return weight;
    }
    public double getAverage(){
        return average;
    }
    public String getName(){
        return name;
    }
    public void setAverage(double value){
        average = value;
    }


}
