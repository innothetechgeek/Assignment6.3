package www.cput.za.exampreparation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import www.cput.za.exampreparation.domain.Customer;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        Button btnDisplay = (Button) findViewById(R.id.btnDisplay);

        final EditText fId = (EditText) findViewById(R.id.txtId);
        final EditText fname = (EditText) findViewById(R.id.txtName);
        final EditText fsurname = (EditText) findViewById(R.id.txtSurname);
        final EditText fage = (EditText) findViewById(R.id.txtAge);


        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String Id = fId.getText().toString();
                 String name = fname.getText().toString();
                 String surname = fsurname.getText().toString();
                 String age = fage.getText().toString();

                 Customer cust = new Customer.Builder()
                         .id(Long.parseLong(Id))
                         .name(name)
                         .surname(surname)
                         .age(age)
                         .build();


             Intent myIntent = new Intent(view.getContext(), DisplayActivity.class);
             myIntent.putExtra("CustomerValue",cust);
             startActivity(myIntent);


            }
        });


    }
}
