package gbdssmp.cs103.ames.ac.gbdssmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Message extends AppCompatActivity {


    public ListView lv;

    public DatabaseReference userDatabase;
    public TextView listUser;
    public String userdata;
    public String builtUpUsers;
    public ArrayList<String> your_array_list = new ArrayList<String>();

    public String[] topics = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // set Variables to their view asset
        lv = (ListView) findViewById(R.id.messagesList);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).

        ///////////////////////////////////////////////////////////////////////////////////////////
        //How to fetch or query data from Firebase database?
        //Step 1: Declare a database reference
        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        //Step 2: Set listener for database reference
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Write our functions, codes here
                //dataSnapShot is an object containing all the "users" information under "users" node
                //getValue() method is to collect all information
                userdata = dataSnapshot.getValue().toString();

                String allUsers = "";

                //Loop through all the users in the "users" database reference
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    //"datas" represent the each "user"
                    //Get username of each user
                    String userName = datas.getKey();

                    //Assign "username" to "builtupUsers"
                    builtUpUsers = userName;
                    allUsers = allUsers + userName + "\n";

                    //Add userName to the array list
                    your_array_list.add(builtUpUsers.toString());
                }

                //Test
//                Toast.makeText(getApplicationContext(), "Users:\n" + allUsers, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Users:\n" + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();



                //Display all userNames on the listView
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Message.this, android.R.layout.simple_list_item_1, your_array_list);
                //Insert arrayAdapter to listview
                lv.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //When database reference has error, not existing, ...
            }
        });

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list);

        lv.setAdapter(arrayAdapter);

        // create action from onclick method to view each program
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                // print out title of program
                TextView tvItemClicked = (TextView) itemClicked;
                String strItemClicked = tvItemClicked.getText().toString();

                // Explicitly use intent to open new Activity
                Intent intent = new Intent(Message.this, Messages.class);


                intent.putExtra("program_name", your_array_list.toArray(new String[0])[position]);

                startActivity(intent);
            }
        });
    }
}
