package www.cput.za.exampreparation;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import www.cput.za.exampreparation.domain.Customer;
import www.cput.za.exampreparation.repository.customer.CustomerRepo;
import www.cput.za.exampreparation.repository.customer.imp.CustomerRepoImpl;

public class DatabaseView extends AppCompatActivity {

    TableLayout tl;
    TableRow tr;
    TextView idTextView,nameTextView, surnameTextView, longIdTextView;
    Set<Customer> cust;



    /* REFERENCE FOR THIS CODE GO TO :
       http://www.coderzheaven.com/2011/12/26/show-data-in-columns-in-a-tableview-dynamically-in-android/
        */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String age;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);

        tl = (TableLayout)findViewById(R.id.maintable);
        Button btnDisplay = (Button)findViewById(R.id.btnDisplayFromDb);


        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerRepo myRepo = new CustomerRepoImpl(getApplicationContext());


                //Read All
                cust = myRepo.findAll();
                Toast.makeText(DatabaseView.this,cust.toString(),Toast.LENGTH_LONG).show();
                addHeaders();
                addData();

            }
        });
    }


    public void addHeaders()
    {
        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new ActionBar.LayoutParams(
                ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView adminid = new TextView(this);
        adminid.setText("ID_NO");
        adminid.setTextColor(Color.YELLOW);
        adminid.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        adminid.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        adminid.setPadding(5, 5, 5, 0);
        tr.addView(adminid);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView colname = new TextView(this);
        colname.setText("FirstName");
        colname.setTextColor(Color.YELLOW);
        colname.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        colname.setPadding(5, 5, 5, 0);
        colname.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(colname); // Adding textView to tablerow.


        /** Creating another textview **/
        TextView colSurname = new TextView(this);
        colSurname.setText("Lastname");
        colSurname.setTextColor(Color.YELLOW);
        colSurname.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        colSurname.setPadding(5, 5, 5, 0);
        colSurname.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(colSurname); // Adding textView to tablerow.


        /** Creating another textview **/
        TextView persanlNo = new TextView(this);
        persanlNo.setText("LongID");
        persanlNo.setTextColor(Color.YELLOW);
        persanlNo.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        persanlNo.setPadding(5, 5, 5, 0);
        persanlNo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(persanlNo); // Adding textView to tablerow.


        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));




    }


    /** This function add the data to the table **/
    public void addData()
    {
        for (Customer myCust : cust)
        {

            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            idTextView = new TextView(this);
            idTextView.setText(myCust.getId().toString());
            idTextView.setTextColor(Color.RED);
            idTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            idTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            idTextView.setPadding(5, 5, 5, 5);
            tr.addView(idTextView); // Adding textView to tablerow.


            /** Creating another textview **/
            nameTextView = new TextView(this);
            nameTextView.setText(myCust.getName().toString());
            nameTextView.setTextColor(Color.GREEN);
            nameTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            nameTextView.setPadding(5, 5, 5, 5);
            nameTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(nameTextView); // Adding textView to tablerow.*/


            /** Creating another textview **/
            surnameTextView = new TextView(this);
            surnameTextView.setText(myCust.getSurname().toString());
            surnameTextView.setTextColor(Color.GREEN);
            surnameTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            surnameTextView.setPadding(5, 5, 5, 5);
            surnameTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(surnameTextView); // Adding textView to tablerow.*/

            /** Creating another textview **/
            longIdTextView = new TextView(this);
            longIdTextView.setText(myCust.getAge().toString());
            longIdTextView.setTextColor(Color.GREEN);
            longIdTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            longIdTextView.setPadding(5, 5, 5, 5);
            longIdTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(longIdTextView); // Adding textView to tablerow.*/

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

        }


    }



}
