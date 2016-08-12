package app.fragments.cook.com.gradecalculatorV2;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


/**
 * Created by cbcoo_000 on 6/23/2015.
 */


public class ClassView extends Activity implements OnClickListener, OnItemSelectedListener{

    private Subject classToView;
    private TextView grade;
    private TextView className;
    private int numberOfAssigns;
    private ArrayList<Assignment> assignmentList;
    private int id;
    private String IdString, listId, table, pref, prefFile;
    private String[] assignmentNames;
    private List<Grade>  gradesList;
    private boolean projected;
    private ProgressDialog progress;
    public static final int prg_bar = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page);
        Intent receive = getIntent();
        IdString = receive.getStringExtra("idString");
        listId = receive.getStringExtra("listString");
        if(IdString.equals("Class One")) {
            classToView = (Subject) receive.getSerializableExtra("C1");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_One";
            id = 1;
            pref = "One";
            prefFile = "class_one_name";
        }
        else if(IdString.equals("Class Two")){
            classToView = (Subject) receive.getSerializableExtra("C2");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_Two";
            id = 2;
            pref = "Two";
            prefFile = "class_two_name";
        }
        else if(IdString.equals("Class Three")){
            classToView = (Subject) receive.getSerializableExtra("C3");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_Three";
            id = 3;
            pref = "Three";
            prefFile = "class_three_name";
        }
        else if(IdString.equals("Class Four")){
            classToView = (Subject) receive.getSerializableExtra("C4");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_Four";
            id = 4;
            pref = "Four";
            prefFile = "class_four_name";
        }
        else if(IdString.equals("Class Five")){
            classToView = (Subject) receive.getSerializableExtra("C5");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_Five";
            id = 5;
            pref = "Five";
            prefFile = "class_five_name";
        }
        else if(IdString.equals("Class Six")){
            classToView = (Subject) receive.getSerializableExtra("C6");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_Six";
            id = 6;
            pref = "Six";
            prefFile = "class_six_name";
        }
        else{
            classToView = (Subject) receive.getSerializableExtra("C7");
            assignmentList = (ArrayList<Assignment>) receive.getSerializableExtra("list");
            table = "Class_Seven";
            id = 7;
            pref = "Seven";
            prefFile = "class_seven_name";
        }
        className = (TextView) findViewById(R.id.class_name);
        SharedPreferences retrieve = getSharedPreferences(pref, MODE_PRIVATE);
        String name = retrieve.getString(prefFile, "Class");
        className.setText(name);
        //Button Listener Setup-->
        grade = (TextView) findViewById(R.id.number_calculation);                       //Grade Field
        grade.setText(Double.toString(classToView.getGrade()));
        Button deleteGradeButton = (Button) findViewById(R.id.delete_grade);            //Delete Grade
        deleteGradeButton.setOnClickListener(this);
        Button clearButton = (Button) findViewById(R.id.clear_button);                  //Clear Button
        clearButton.setOnClickListener(this);
        Button addButton = (Button) findViewById(R.id.add_grade_button);                //Add Grade
        addButton.setOnClickListener(this);
        Button doneButton = (Button) findViewById(R.id.back_button);                    //Cancel Button
        doneButton.setOnClickListener(this);
        Button addAssign = (Button) findViewById(R.id.add_assignment_button);           //Add Assignment Button
        addAssign.setOnClickListener(this);
        Button viewAssign = (Button) findViewById(R.id.view_assignment_button);         //View Assignment
        viewAssign.setOnClickListener(this);
        Button deleteAssignments = (Button) findViewById(R.id.delete_assignment);          //Deletion Button
        deleteAssignments.setOnClickListener(this);

        //Spinner Setup
        Spinner spin = (Spinner) findViewById(R.id.grade_type);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade_pref, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        numberOfAssigns = assignmentList.size();
        projected = false;                                                              //To Display the current grade on startup
        DatabaseHandler db = new DatabaseHandler(ClassView.this);
        gradesList = db.getAllGrades(id /*, deleteFrom.getName()*/);                  //Initially retrieve all grades
        calculateGrade(assignmentList);
        DecimalFormat df = new DecimalFormat("0.00");
        if (projected == true) {
            grade.setText(df.format(classToView.getProjectedGrade()));
        } else {
            grade.setText(df.format(classToView.getGrade()));
        }


    }

    @Override
    public void onBackPressed() {                                                    //When the back button is pressed
        new goBack().execute(0);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){                 //For Spinner
        String pref = parent.getItemAtPosition(pos).toString();
        DecimalFormat df = new DecimalFormat("0.00");
        if(pref.equals("Current Grade")){
            projected = false;
            grade.setText(df.format(classToView.getGrade()));
        }else{
            projected = true;
            grade.setText(df.format(classToView.getProjectedGrade()));
        }

    }

    public void onNothingSelected(AdapterView<?> parent){

    }
    //Calculates projected and current grade for spinner
    public void calculateGrade(ArrayList<Assignment> list){
        int listSize = list.size();
        double currentGrade = 0.00;
        double projectedGrade = 100.00;
        double decimalWeight;

            for(int i = 0; i < listSize; i++) {
                if(list.isEmpty()){

                }else {
                    decimalWeight = list.get(i).getWeight() / 100.00;
                    if(list.get(i).getAverage() != 0) {
                        projectedGrade = projectedGrade - (list.get(i).getWeight() - (decimalWeight * list.get(i).getAverage()));
                    }
                    currentGrade = currentGrade + (list.get(i).getAverage() * decimalWeight);
                }
            }

            classToView.setGrade(currentGrade);
            classToView.setProjectedGrade(projectedGrade);


    }
    //Calculate Class Average
    public void calculateAverage(List<Grade> list, Assignment current) {
        int size = list.size();
        int assignmentSize = 0;
        double total = 0.00;
        double average;
        String search = current.getName();
        for(int i = 0; i < size; i++){
            if(list.get(i).getAssignment().contains(search)){
                total = total + list.get(i).getGradeValue();
                assignmentSize = assignmentSize + 1;
            }
            //total = total + list.get(i).getGradeValue();
        }
        if(total == 0){
            average = 0.00;
        }else {

            average = total / assignmentSize;

        }
        current.setAverage(average);
    }                   //Calculate Assignment average

    public void showDialog(final Context context, Assignment assignmentToView){                     //Show grades Dialog
        //DatabaseHandler db = new DatabaseHandler(this);                                             //Database Handler
        //gradesList = db.getAllContacts(id, assignmentToView.getName());                             //Get all grades
        calculateAverage(gradesList, assignmentToView);                                             //Set new average
        AlertDialog.Builder gradeView = new AlertDialog.Builder(context);
        LayoutInflater inflater = ClassView.this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_view_assignment, null);
        gradeView.setView(layout);
        final TextView weight = (TextView) layout.findViewById(R.id.dialog_average);
        final TextView grade = (TextView) layout.findViewById(R.id.dialog_Weight);
        TableLayout table = (TableLayout) layout.findViewById(R.id.grades_table);
        gradeView.setTitle(assignmentToView.getName());
        DecimalFormat df = new DecimalFormat("0.00");
        weight.setText("   Wt: " + assignmentToView.getWeight() + "%");
        grade.setText("Avg: " + df.format(assignmentToView.getAverage()));


        for(int i = 0; i < gradesList.size() + 1; i++){
            TextView filler = new TextView(this);
            TextView filler2 = new TextView(this);
            TextView name = new TextView(this);
            TextView value = new TextView(this);
            filler.setText("            ");                                         //Spacing in between columns
            filler2.setText("                  ");
            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutRow = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(layoutRow);
            if(i == 0){
                name.setText("Title");
                value.setText("Grade");
                name.setPaintFlags(name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                value.setPaintFlags(value.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);                   //Set Text to 30 sp
                value.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                row.addView(filler);
                row.addView(name);
                row.addView(filler2);
                row.addView(value);
                table.addView(row);
            }else {
                if(gradesList.get(i - 1).getAssignment().equals(assignmentToView.getName())) {
                    name.setText(gradesList.get(i - 1).getGradeName());
                    value.setText(Float.toString(gradesList.get(i - 1).getGradeValue()));
                    name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);                   //Set Text to 30 sp
                    value.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);                  //Set Text to 30 sp
                    row.addView(filler);
                    row.addView(name);
                    row.addView(filler2);
                    row.addView(value);
                    table.addView(row);
                }
            }
        }
        gradeView.setNegativeButton("Done", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        gradeView.create().show();
    }          //Displaying all assignments for adding

    public void deleteGrade(final Context context, Assignment assignment){
        final Assignment deleteFrom = assignment;
        AlertDialog.Builder deleteView = new AlertDialog.Builder(context);
        deleteView.setTitle("Pick a Grade to Delete");
        List<String> GNames = new ArrayList();                                        //Grade Names Storage
        for(int i = 0; i < gradesList.size(); i++){
            if(gradesList.get(i).getAssignment().equals(assignment.getName())) {
                GNames.add(gradesList.get(i).getGradeName());
            }
        }
        final String[] names = GNames.toArray(new String[GNames.size()]);           //setItems requires an Array not an ArrayList
        deleteView.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(context);

                db.deleteGrade(table, names[which]);                                //Deletes selected grade from the database
                gradesList.remove(which);
                calculateAverage(gradesList, deleteFrom);                           //Update Average Grade considering deleted assignment grade
                calculateGrade(assignmentList);                                     // Update Overall Course Grade
                DecimalFormat df = new DecimalFormat("0.00");
                if (projected == true) {
                    grade.setText(df.format(classToView.getProjectedGrade()));
                } else {
                    grade.setText(df.format(classToView.getGrade()));
                }
            }
        });
        deleteView.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        deleteView.create().show();

    }
    public void addGrade(final Context context, Assignment assignment){                     //Add grade to assignment
        final Assignment addTo = assignment;
        AlertDialog.Builder addView = new AlertDialog.Builder(context);
        LayoutInflater inflater = ClassView.this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_add_grade, null);
        addView.setView(layout);
        final EditText addValue = (EditText) layout.findViewById(R.id.add_grade_edittext);
        addValue.setHint("Grade");
        addValue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addValue.setText("");
                return false;
            }
        });
        final EditText addName = (EditText) layout.findViewById(R.id.add_grade_name_edittext);
        addName.setHint("Name");
        addName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addName.setText("");
                return false;
            }
        });
        addView.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(addName.getText().toString().equals("") && addValue.getText().toString().equals("")) {
                    Toast notice = Toast.makeText(ClassView.this,"No values inputted for the name or grade value", Toast.LENGTH_SHORT);
                    notice.show();
                } else if(addValue.getText().toString().equals("")){
                    Toast notice = Toast.makeText(ClassView.this,"Invalid Input: please input a grade value", Toast.LENGTH_SHORT);
                    notice.show();
                } else if(addName.getText().toString().equals("")){
                    Toast notice = Toast.makeText(ClassView.this,"Invalid Input: please input a name associated with the grade", Toast.LENGTH_SHORT);
                    notice.show();
                }else {
                    DatabaseHandler db = new DatabaseHandler(context);
                    Grade fill = new Grade(addTo.getName(), addName.getText().toString(), Float.parseFloat(addValue.getText().toString()), id);
                    gradesList.add(fill);
//                db.addGrade(fill, id);
//                gradesList = db.getAllContacts(id, addTo.getName());                       //Get all grades
                    calculateAverage(gradesList, addTo);
                    calculateGrade(assignmentList);
                    DecimalFormat df = new DecimalFormat("0.00");
                    if (projected == true) {
                        grade.setText(df.format(classToView.getProjectedGrade()));
                    } else {
                        grade.setText(df.format(classToView.getGrade()));
                    }
                }
            }
        });
        addView.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        addView.create().show();

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.delete_assignment:
                assignmentNames = new String[numberOfAssigns];                            //add in assignmentNames
                for(int i = 0; i < numberOfAssigns; i++){
                    assignmentNames[i] = assignmentList.get(i).getName();
                }
                AlertDialog.Builder deleteAssignment = new AlertDialog.Builder(ClassView.this);
                deleteAssignment.setTitle("Pick a Category to delete");
                deleteAssignment.setItems(assignmentNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler db = new DatabaseHandler(ClassView.this);
                        db.deleteAssignment(table, assignmentList.get(which).getName());            //Delete grades relating to this name
                        assignmentList.remove(which);
                        calculateGrade(assignmentList);
                        DecimalFormat df = new DecimalFormat("0.00");
                        if (projected == true) {
                            grade.setText(df.format(classToView.getProjectedGrade()));
                        } else {
                            grade.setText(df.format(classToView.getGrade()));
                        }
                        numberOfAssigns = assignmentList.size();
                    }
                });
                deleteAssignment.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                deleteAssignment.create().show();
                break;
            case R.id.delete_grade:
                assignmentNames = new String[numberOfAssigns];                            //add in assignmentNames
                for(int i = 0; i < numberOfAssigns; i++){
                    assignmentNames[i] = assignmentList.get(i).getName();
                }
                AlertDialog.Builder deleteGrade = new AlertDialog.Builder(ClassView.this);
                deleteGrade.setTitle("Pick a Category to delete a grade from");
                deleteGrade.setItems(assignmentNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteGrade(ClassView.this, assignmentList.get(which));     //pull grade information
                    }
                });
                deleteGrade.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                deleteGrade.create().show();
                break;
            case R.id.add_grade_button:
                numberOfAssigns = assignmentList.size();
                assignmentNames = new String[numberOfAssigns];
                for(int i = 0; i < numberOfAssigns; i++){
                    assignmentNames[i] = assignmentList.get(i).getName();                 //add in assignmentNames
                }
                AlertDialog.Builder gradesPick = new AlertDialog.Builder(ClassView.this);
                gradesPick.setTitle("Pick a Category to add a grade to");
                gradesPick.setItems(assignmentNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addGrade(ClassView.this, assignmentList.get(which));    //add in grades
                    }
                });
                gradesPick.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                gradesPick.create().show();
                break;
            case R.id.view_assignment_button:
                numberOfAssigns = assignmentList.size();
                assignmentNames = new String[numberOfAssigns];                            //add in assignmentNames
                for(int i = 0; i < numberOfAssigns; i++){
                    assignmentNames[i] = assignmentList.get(i).getName();
                }
                AlertDialog.Builder viewPick = new AlertDialog.Builder(ClassView.this);
                viewPick.setTitle("Pick an Category to View");
                viewPick.setItems(assignmentNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDialog(ClassView.this, assignmentList.get(which));     //display grades for the chosen assignment
                    }
                });
                viewPick.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                viewPick.create().show();

                break;
            case R.id.add_assignment_button:

                AlertDialog.Builder builder = new AlertDialog.Builder(ClassView.this);
                LayoutInflater inflater = ClassView.this.getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_add_assignment, null);
                builder.setView(layout);
                final EditText assignName = (EditText) layout.findViewById(R.id.dialog_enter_assign_name);
                assignName.setHint("Name");
                assignName.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        assignName.setText("");
                        return false;
                    }
                });
                final EditText weightField = (EditText) layout.findViewById(R.id.dialog_enter_assign_weight);
                weightField.setHint("Weight (in Percent)");
                weightField.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        weightField.setText("");
                        return false;
                    }
                });
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(assignName.getText().toString().equals("") && weightField.getText().toString().equals("")) {
                            Toast notice = Toast.makeText(ClassView.this,"No values inputted for the name and weight", Toast.LENGTH_SHORT);
                            notice.show();
                        } else if(weightField.getText().toString().equals("") || (Integer.parseInt(weightField.getText().toString()) < 0) || (Integer.parseInt(weightField.getText().toString()) > 100)){
                            Toast notice = Toast.makeText(ClassView.this,"Invalid Input: please input a weight from 0 to 100", Toast.LENGTH_SHORT);
                            notice.show();
                        } else if(assignName.getText().toString().equals("")){
                            Toast notice = Toast.makeText(ClassView.this,"Invalid Input: please input a name", Toast.LENGTH_SHORT);
                            notice.show();
                        }else{
                            Assignment filler = new Assignment(Integer.parseInt(weightField.getText().toString()), 0.0, assignName.getText().toString());
                            assignmentList.add(filler);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;

            case R.id.clear_button:                                                                  //Loads the assignments to the view
                AlertDialog.Builder delete = new AlertDialog.Builder(ClassView.this);
                delete.setTitle("This will clear all the data related to this class. Continue anyway?");
                delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler db = new DatabaseHandler(ClassView.this);
                        db.deleteTable(table);
                        SharedPreferences remove = ClassView.this.getSharedPreferences(pref, Context.MODE_PRIVATE);
                        remove.edit().remove(prefFile).commit();

                        try {
                            FileOutputStream find = openFileOutput(IdString, Context.MODE_PRIVATE);
                            File fileLocation = new File(System.getProperty("user.dir"),IdString);
                            find.close();
                            fileLocation.delete();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                        }
                        try {
                            FileOutputStream find = openFileOutput(listId, Context.MODE_PRIVATE);
                            File fileLocationList = new File(System.getProperty("user.dir"),listId);
                            find.close();
                            fileLocationList.delete();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {

                        }

                        Intent goBack = new Intent(ClassView.this, MainActivity.class);
                        finish();
                        startActivity(goBack);

                    }
                });
                delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                delete.show();
                break;

            case R.id.back_button:

                new goBack().execute(0);

                break;
        }
    }
    protected Dialog onCreateDialog(int id){
        switch(id){
            case prg_bar:
                progress = new ProgressDialog(this);
                progress.setMessage("Returning to Home Screen");
                progress.setIndeterminate(false);
                progress.setMax(100);
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();
                return progress;
            default:
                return null;
        }
    }
    class goBack extends AsyncTask<Integer, String, Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                FileOutputStream classOne = openFileOutput(listId, Context.MODE_PRIVATE);
                ObjectOutputStream subjOne = new ObjectOutputStream(classOne);
                subjOne.writeObject(assignmentList);
                subjOne.close();
                classOne.close();
            } catch (IOException ioe) {
            }
            try {
                FileOutputStream classOne = openFileOutput(IdString, Context.MODE_PRIVATE);
                ObjectOutputStream subjOne = new ObjectOutputStream(classOne);
                subjOne.writeObject(classToView);
                subjOne.close();
                classOne.close();
            } catch (IOException ioe) {
            }
            DatabaseHandler db = new DatabaseHandler(ClassView.this);
            for(int i = 0; i < gradesList.size(); i++){
                db.addGrade(gradesList.get(i), id);
            }
            Intent goBack = new Intent(ClassView.this, MainActivity.class);
            finish();
            startActivity(goBack);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(prg_bar);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //super.onProgressUpdate(values);
            progress.setProgress(Integer.parseInt(values[0]));
        }
    }

}
