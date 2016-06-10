package www.cput.za.exampreparation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import www.cput.za.exampreparation.domain.Customer;
import www.cput.za.exampreparation.repository.Repository;
import www.cput.za.exampreparation.repository.customer.CustomerRepo;
import www.cput.za.exampreparation.repository.customer.imp.CustomerRepoImpl;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button submit = (Button)findViewById(R.id.btnSubmit);

        Intent myIntent = getIntent();

        final Customer cust = (Customer)myIntent.getSerializableExtra("CustomerValue");
        Toast.makeText(DisplayActivity.this, cust.toString(),Toast.LENGTH_LONG).show();

        EditText myText = (EditText) findViewById(R.id.txtDisplay);
        myText.setText(cust.toString());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerRepo myRepo = new CustomerRepoImpl(getApplicationContext());
                Customer mycust = myRepo.save(cust);

                Intent myIntent = new Intent(v.getContext(),DatabaseView.class);
                startActivity(myIntent);

            }
        });
    }
}
