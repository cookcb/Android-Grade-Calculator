package app.fragments.cook.com.gradecalculatorV2;

/**
 * Created by cbcoo_000 on 7/23/2015.
 */
public class Grade {
    private int id;
    private String assignment;
    private String gradeName;
    private float gradeValue;

    public Grade(){

    }
    public Grade(String assignment, String gradeName, float gradeValue, int id){
        this.assignment = assignment;
        this.gradeName = gradeName;
        this.gradeValue = gradeValue;
        this.id = id;
    }
    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public float getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(float gradeValue) {
        this.gradeValue = gradeValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
