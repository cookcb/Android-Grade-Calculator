package app.fragments.cook.com.gradecalculatorV2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cbcoo_000 on 6/20/2015.
 */
public class Subject implements Serializable{

    private double grade, projectedGrade;
    private String name;

    public Subject(double grade, String name){
        this.name = name;
        this.grade = grade;
        this.projectedGrade = 100;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getGrade(){
        return grade;
    }

    public void setGrade(double value){
        grade = value;
    }

    public void setProjectedGrade(double value){ projectedGrade = value;}

    public double getProjectedGrade(){
        return projectedGrade;
    }
}
