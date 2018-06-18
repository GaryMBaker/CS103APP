package gbdssmp.cs103.ames.ac.gbdssmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Forum extends AppCompatActivity {

    private ListView lv;

    private String[] topics = {
            "Animation Techniques",
            "Student Social Events",
            "Android Discussion",
            "Web Development",
            "AR/VR",
            "Game Development",
            "UX Discussion",
            "Latest News",
            "Tutor Talk Back"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        // set Variables to their view asset
        lv = (ListView) findViewById(R.id.messagesList);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).

        final List<String> your_array_list = new ArrayList<String>();

        for (int i=0; i<topics.length; i++) {
            your_array_list.add(topics[i]);
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


                intent.putExtra("program_name", topics[position]);

                startActivity(intent);
            }
        });
    }
}
