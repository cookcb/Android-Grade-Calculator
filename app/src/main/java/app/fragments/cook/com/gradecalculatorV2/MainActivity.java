package app.fragments.cook.com.gradecalculatorV2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends Activity implements OnClickListener {

    private Subject class_one, class_two, class_three, class_four, class_five, class_six, class_seven;
    private ArrayList<Assignment> list1, list2, list3, list4, list5, list6, list7;

    public static final int prg_bar = 0;
    public static final int prg_bar_clear = 1;
    private ProgressDialog progressBar;

    TextView nameOne, nameTwo, nameThree, nameFour, nameFive, nameSix, nameSeven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameOne = (TextView) this.findViewById(R.id.edit_text_one);
        SharedPreferences retrieve = getSharedPreferences("One", MODE_PRIVATE);
        String name = retrieve.getString("class_one_name", "Enter class name here");
        nameOne.setText(name);
        nameOne.setOnClickListener(this);

        nameTwo = (TextView) this.findViewById(R.id.edit_text_two);
        SharedPreferences retrieve2 = getSharedPreferences("Two", MODE_PRIVATE);
        String name2 = retrieve2.getString("class_two_name", "Enter class name here");
        nameTwo.setText(name2);
        nameTwo.setOnClickListener(this);

        nameThree = (TextView) this.findViewById(R.id.edit_text_three);
        SharedPreferences retrieve3 = getSharedPreferences("Three", MODE_PRIVATE);
        String name3 = retrieve3.getString("class_three_name", "Enter class name here");
        nameThree.setText(name3);
        nameThree.setOnClickListener(this);

        nameFour = (TextView) this.findViewById(R.id.edit_text_four);
        SharedPreferences retrieve4 = getSharedPreferences("Four", MODE_PRIVATE);
        String name4 = retrieve4.getString("class_four_name", "Enter class name here");
        nameFour.setText(name4);
        nameFour.setOnClickListener(this);

        nameFive = (TextView) this.findViewById(R.id.edit_text_five);
        SharedPreferences retrieve5 = getSharedPreferences("Five", MODE_PRIVATE);
        String name5 = retrieve5.getString("class_five_name", "Enter class name here");
        nameFive.setText(name5);
        nameFive.setOnClickListener(this);

        nameSix = (TextView) this.findViewById(R.id.edit_text_six);
        SharedPreferences retrieve6 = getSharedPreferences("Six", MODE_PRIVATE);
        String name6 = retrieve6.getString("class_six_name", "Enter class name here");
        nameSix.setText(name6);
        nameSix.setOnClickListener(this);

        nameSeven = (TextView) this.findViewById(R.id.edit_text_seven);
        SharedPreferences retrieve7 = getSharedPreferences("Seven", MODE_PRIVATE);
        String name7 = retrieve7.getString("class_seven_name", "Enter class name here");
        nameSeven.setText(name7);
        nameSeven.setOnClickListener(this);


        Button viewButton1 = (Button)this.findViewById(R.id.view_button);                   //View Buttons for each Class
        viewButton1.setOnClickListener(this);
        Button viewButton2 = (Button)this.findViewById(R.id.view_button_two);
        viewButton2.setOnClickListener(this);
        Button viewButton3 = (Button)this.findViewById(R.id.view_button_three);
        viewButton3.setOnClickListener(this);
        Button viewButton4 = (Button)this.findViewById(R.id.view_button_four);
        viewButton4.setOnClickListener(this);
        Button viewButton5 = (Button)this.findViewById(R.id.view_button_five);
        viewButton5.setOnClickListener(this);
        Button viewButton6 = (Button)this.findViewById(R.id.view_button_six);
        viewButton6.setOnClickListener(this);
        Button viewButton7 = (Button)this.findViewById(R.id.view_button_seven);
        viewButton7.setOnClickListener(this);



    }
    @Override
    public void onBackPressed() {
        Intent mainActivity = new Intent(Intent.ACTION_MAIN);
        mainActivity.addCategory(Intent.CATEGORY_HOME);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainActivity);
        finish();
    }

    public void onClick(View v){

        AlertDialog.Builder  setName = new AlertDialog.Builder(MainActivity.this);
        final EditText nameField = new EditText(MainActivity.this);
        switch(v.getId()) {
            case R.id.edit_text_one:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameOne.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("One", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_one_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;
            case R.id.edit_text_two:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameTwo.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("Two", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_two_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;
            case R.id.edit_text_three:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameThree.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("Three", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_three_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;
            case R.id.edit_text_four:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameFour.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("Four", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_four_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;
            case R.id.edit_text_five:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameFive.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("Five", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_five_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;
            case R.id.edit_text_six:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameSix.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("Six", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_six_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;
            case R.id.edit_text_seven:
                setName.setTitle("Enter Class name here");
                setName.setMessage("Enter Name");
                setName.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameSeven.setText(nameField.getText().toString());
                        SharedPreferences n1 = getSharedPreferences("Seven", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = n1.edit();
                        editor.putString("class_seven_name", nameField.getText().toString());
                        editor.commit();
                    }
                });
                setName.setView(nameField);
                setName.show();
                break;

            case R.id.view_button:
                String[] name  = new String[5];
                name[0] = "Class One";
                new LoadSubject().execute(name);
                break;
            case R.id.view_button_two:
                String[] name2  = new String[5];
                name2[0] = "Class Two";
                new LoadSubject().execute(name2);
                break;
            case R.id.view_button_three:
                String[] name3  = new String[5];
                name3[0] = "Class Three";
                new LoadSubject().execute(name3);
                break;
            case R.id.view_button_four:
                String[] name4  = new String[5];
                name4[0] = "Class Four";
                new LoadSubject().execute(name4);
                break;
            case R.id.view_button_five:
                String[] name5  = new String[5];
                name5[0] = "Class Five";
                new LoadSubject().execute(name5);
                break;
            case R.id.view_button_six:
                String[] name6  = new String[5];
                name6[0] = "Class Six";
                new LoadSubject().execute(name6);
                break;
            case R.id.view_button_seven:
                String[] name7  = new String[5];
                name7[0] = "Class Seven";
                new LoadSubject().execute(name7);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected Dialog onCreateDialog(int id){
        switch(id){
            case prg_bar:
                progressBar = new ProgressDialog(this);
                progressBar.setMessage("Loading Subject");
                progressBar.setIndeterminate(false);
                progressBar.setMax(100);
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.show();
                return progressBar;
            case prg_bar_clear:
                progressBar = new ProgressDialog(this);
                progressBar.setMessage("Clearing Courses");
                progressBar.setIndeterminate(false);
                progressBar.setMax(100);
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.show();
                return progressBar;
            default:
                return null;
        }
    }
    class LoadSubject extends AsyncTask<String, String, Subject>{
        @Override

        protected void onPreExecute(){
            super.onPreExecute();
            showDialog(prg_bar);

        }
        protected Subject doInBackground(String...subjName) {
            String FILENAME, LISTNAME;
            Subject placeHolder = null;
            Intent goToClassView = new Intent(MainActivity.this, ClassView.class);

            if (subjName[0].equals("Class One")) {                                          //Class 1 subheading
                FILENAME = "Class One";
                LISTNAME = "List One";

                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_one = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list1 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if(class_one == null) {
                    class_one = new Subject(0, "Class1");
                    list1 = new ArrayList<Assignment>();

                    try {
                        FileOutputStream classOne = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjOne = new ObjectOutputStream(classOne);
                        subjOne.writeObject(class_one);
                        subjOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list1);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_one;
            }
            else if (subjName[0].equals("Class Two")) {                 //Class 2 subheading
                FILENAME = "Class Two";
                LISTNAME = "List Two";
                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_two = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list2 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if (class_two == null) {

                    class_two = new Subject(0, "Class2");
                    list2 = new ArrayList<Assignment>();
                    try {
                        FileOutputStream classTwo = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjTwo = new ObjectOutputStream(classTwo);
                        subjTwo.writeObject(class_two);
                        subjTwo.close();
                        classTwo.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list2);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_two;
            }
            else if (subjName[0].equals("Class Three")) {                 //Class 3 subheading
                FILENAME = "Class Three";
                LISTNAME = "List Three";

                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_three = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list3 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if (class_three == null) {

                    class_three = new Subject(0, "Class3");
                    list3 = new ArrayList<Assignment>();
                    try {
                        FileOutputStream classTwo = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjTwo = new ObjectOutputStream(classTwo);
                        subjTwo.writeObject(class_three);
                        subjTwo.close();
                        classTwo.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list3);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_three;
            }else if (subjName[0].equals("Class Four")) {                 //Class 4 subheading
                FILENAME = "Class Four";
                LISTNAME = "List Four";

                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_four = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list4 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if (class_four == null) {

                    class_four = new Subject(0, "Class4");
                    list4 = new ArrayList<Assignment>();
                    try {
                        FileOutputStream classTwo = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjTwo = new ObjectOutputStream(classTwo);
                        subjTwo.writeObject(class_four);
                        subjTwo.close();
                        classTwo.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list4);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_four;
            }else if (subjName[0].equals("Class Five")) {                 //Class 5 subheading
                FILENAME = "Class Five";
                LISTNAME = "List Five";

                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_five = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list5 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if (class_five == null) {

                    class_five = new Subject(0, "Class5");
                    list5 = new ArrayList<Assignment>();
                    try {
                        FileOutputStream classTwo = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjTwo = new ObjectOutputStream(classTwo);
                        subjTwo.writeObject(class_five);
                        subjTwo.close();
                        classTwo.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list5);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_five;
            }else if (subjName[0].equals("Class Six")) {                 //Class 6 subheading
                FILENAME = "Class Six";
                LISTNAME = "List Six";

                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_six = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list6 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if (class_six == null) {

                    class_six = new Subject(0, "Class6");
                    list6 = new ArrayList<Assignment>();
                    try {
                        FileOutputStream classTwo = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjTwo = new ObjectOutputStream(classTwo);
                        subjTwo.writeObject(class_six);
                        subjTwo.close();
                        classTwo.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list6);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_six;
            }else {                                                                     //Class 7 subheading
                FILENAME = "Class Seven";
                LISTNAME = "List Seven";

                try {
                    FileInputStream recieve = openFileInput(FILENAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    class_seven = (Subject) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                try {
                    FileInputStream recieve = openFileInput(LISTNAME);
                    ObjectInputStream recieve1 = new ObjectInputStream(recieve);
                    list7 = (ArrayList<Assignment>) recieve1.readObject();
                    recieve.close();
                    recieve1.close();
                } catch (FileNotFoundException e) {
                } catch (IOException x) {
                } catch (ClassNotFoundException e) {
                }
                if (class_seven == null) {

                    class_seven = new Subject(0, "Class7");
                    list7 = new ArrayList<Assignment>();
                    try {
                        FileOutputStream classTwo = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        ObjectOutputStream subjTwo = new ObjectOutputStream(classTwo);
                        subjTwo.writeObject(class_seven);
                        subjTwo.close();
                        classTwo.close();
                    } catch (IOException ioe) {
                    }
                    try {
                        FileOutputStream classOne = openFileOutput(LISTNAME, Context.MODE_PRIVATE);
                        ObjectOutputStream listOne = new ObjectOutputStream(classOne);
                        listOne.writeObject(list7);
                        listOne.close();
                        classOne.close();
                    } catch (IOException ioe) {
                    }
                }
                placeHolder = class_seven;
            }
            return placeHolder;

        }

        @Override
        protected void onPostExecute(Subject s) {

            Intent goToClassView = new Intent(MainActivity.this, ClassView.class);
            if(s == class_one) {
                goToClassView.putExtra("idString", "Class One");
                goToClassView.putExtra("listString", "List One");
                goToClassView.putExtra("list", list1);
                goToClassView.putExtra("C1", class_one);
                finish();
                startActivity(goToClassView);
            }
            else if(s == class_two){
                goToClassView.putExtra("idString", "Class Two");
                goToClassView.putExtra("listString", "List Two");
                goToClassView.putExtra("list", list2);
                goToClassView.putExtra("C2", class_two);
                finish();
                startActivity(goToClassView);
            }
            else if(s == class_three){
                goToClassView.putExtra("idString", "Class Three");
                goToClassView.putExtra("listString", "List Three");
                goToClassView.putExtra("list", list3);
                goToClassView.putExtra("C3", class_three);
                finish();
                startActivity(goToClassView);
            }
            else if(s == class_four){
                goToClassView.putExtra("idString", "Class Four");
                goToClassView.putExtra("listString", "List Four");
                goToClassView.putExtra("list", list4);
                goToClassView.putExtra("C4", class_four);
                finish();
                startActivity(goToClassView);
            }
            else if(s == class_five){
                goToClassView.putExtra("idString", "Class Five");
                goToClassView.putExtra("listString", "List Five");
                goToClassView.putExtra("list", list5);
                goToClassView.putExtra("C5", class_five);
                finish();
                startActivity(goToClassView);
            }
            else if(s == class_six){
                goToClassView.putExtra("idString", "Class Six");
                goToClassView.putExtra("listString", "List Six");
                goToClassView.putExtra("list", list6);
                goToClassView.putExtra("C6", class_six);
                finish();
                startActivity(goToClassView);
            }
            else{
                goToClassView.putExtra("idString", "Class Seven");
                goToClassView.putExtra("listString", "List Seven");
                goToClassView.putExtra("list", list7);
                goToClassView.putExtra("C7", class_seven);
                finish();
                startActivity(goToClassView);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            progressBar.setProgress(Integer.parseInt(values[0]));
        }


    }

}

