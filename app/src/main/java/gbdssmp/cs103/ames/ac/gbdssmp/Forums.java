package gbdssmp.cs103.ames.ac.gbdssmp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Forums extends AppCompatActivity {


    private FloatingActionButton sendBtn;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);

        sendBtn = (FloatingActionButton) findViewById(R.id.sendBtn);
        input = (EditText) findViewById(R.id.input);

        ///////////////////////////////////////////////////////////////////////////////
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.getText().toString().isEmpty()) {
                    //Post a chat message - 1: Declare variables
                    // Post a chat message - 2: Find references for UI widgets
                    // Post a chat message - 3: Set click listener for the sendBtn
                    //Check if the input field is not empty, send the message & delete it after sending out
                    // Read the input field and push a new instance of ChatMessage to the Firebase database
                    //Clear the input
                    FirebaseDatabase.getInstance().getReference().push()
                            .setValue(new ChatMessage(input.getText().toString(), FirebaseAuth.getInstance()
                                    .getCurrentUser()
                                    .getDisplayName()));
                    input.setText("");
                }
            }
        });
    }
}
