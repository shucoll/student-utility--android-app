package com.example.studentutility;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GPAActivity extends AppCompatActivity {

    private EditText Credit,Grade;
    private Button addCourse,seeGpa,erase;
    private TextView gpaView,dataViewT,dataViewN,dataViewP;
    double counter=0,total_credit=0,credit=0,grade=0;
    int total_course=0;
    String gradeSt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_a);
        Credit = findViewById(R.id.editText1);
        Grade = findViewById(R.id.editText2);
        addCourse = findViewById(R.id.button);
        seeGpa = findViewById(R.id.button2);
        erase = findViewById(R.id.button3);
        gpaView = findViewById(R.id.gpaView);
        dataViewT = findViewById(R.id.dataViewT);
        dataViewN = findViewById(R.id.dataViewN);
        dataViewP = findViewById(R.id.dataViewP);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    credit = Double.parseDouble(Credit.getText().toString());
                    gradeSt = Grade.getText().toString();
                    if (!(gradeSt.equalsIgnoreCase("A")   ||
                            gradeSt.equalsIgnoreCase("B") ||
                            gradeSt.equalsIgnoreCase("C") ||
                            gradeSt.equalsIgnoreCase("D") ||
                            gradeSt.equalsIgnoreCase("F")))

                        throw new ArithmeticException("Invalid Grade Input");

                    switch (gradeSt) {

                        case "A" :
                            grade = 4;
                            break;

                        case "B" :
                            grade = 3;
                            break;

                        case "C" :
                            grade = 2;
                            break;

                        case "D" :
                            grade = 1;
                            break;

                        case "F" :
                            grade = 0;
                            break;
                    }
                    counter += (grade*credit);
                    total_credit += credit;
                    total_course++;
                    Toast.makeText(getApplicationContext(),"Course Added\nCredit : " + credit + "\nGrade : " + grade,Toast.LENGTH_SHORT).show();
                    Credit.setText("");
                    Grade.setText("");
                    dataViewT.setText("Total Credit : " + String.valueOf(total_credit));
                    dataViewN.setText("Total Courses added : " + String.valueOf(total_course));
                    dataViewP.setText("Total Grade Points : " + String.valueOf(counter));
                }
                catch(ArithmeticException e) {
                    Toast.makeText(GPAActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(GPAActivity.this , "Error adding course" , Toast.LENGTH_SHORT).show();
                }

            }
        });
        seeGpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double result = counter/total_credit;
                    gpaView.setText("Your GPA : " + Math.round(result*100d)/100d);
                }
                catch(Exception e) {
                    Toast.makeText(GPAActivity.this , "Error generating GPA" , Toast.LENGTH_SHORT).show();
                }

            }
        });
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter=0;
                total_credit=0;
                total_course=0;
                credit=0;
                grade=0;
                Credit.setText("");
                Grade.setText("");
                gpaView.setText("");
                dataViewT.setText("");
                dataViewN.setText("");
                dataViewP.setText("");
            }
        });

    }
}