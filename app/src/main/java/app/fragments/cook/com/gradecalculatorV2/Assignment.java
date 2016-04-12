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
    //private ArrayList<Grade> grades;

    public Assignment(double weight, double average, String name) {
        this.weight = weight;
        this.average = average;
        this.name = name;
        //grades = new ArrayList<Grade>();

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
//    public int getSize(){
//         return grades.size();
//    }

//    public ArrayList<Grade> getGradeList(){
//
//        return grades;
//    }

//    public void addGrade(Grade newGrade){
//        grades.add(newGrade);
//    }

//    public void calculateAverage(){
//        int size = grades.size();
//        double total = 0;
//        double average;
//        for(int i = 0; i < size; i++){
//            total = total + grades.get(i).getGradeValue();
//        }
//        average = total/size;
//        this.average = average;
//    }

}
