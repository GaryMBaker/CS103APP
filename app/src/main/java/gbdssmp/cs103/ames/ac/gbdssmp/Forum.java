package gbdssmp.cs103.ames.ac.gbdssmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Forum extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        lv = (ListView) findViewById(R.id.messagesList);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();

        for (int i=0; i<1; i++) {
            your_array_list.add("Animation Techniques");
            your_array_list.add("Student Social Events");
            your_array_list.add("Android Discussion");
            your_array_list.add("Web Development");
            your_array_list.add("AR/VR");
            your_array_list.add("Game Development");
            your_array_list.add("UX Discussion");
            your_array_list.add("Latest News");
            your_array_list.add("Tutor Talk Back");
        }
        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);

        // create action from onclick method to view each program
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                // print out title of program
                TextView tvItemClicked = (TextView) itemClicked;
                String strItemClicked = tvItemClicked.getText().toString();

                // Explicitly use intent to open new Activity
                Intent intent = new Intent(Forum.this, Forums.class);


//                intent.putExtra("program_name", programs_array[position]);

                startActivity(intent);
            }
        });
    }
}
