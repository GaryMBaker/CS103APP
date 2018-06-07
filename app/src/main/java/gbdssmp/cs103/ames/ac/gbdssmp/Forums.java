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

    Button sendBtn = (Button) findViewById(R.id.submitBtn);
    EditText input = (EditText) findViewById(R.id.newPost);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference() .push()
                        .setValue(new ChatMessage(input.getText().toString(), FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName()));
                input.setText("blah");
            }
        });

    }
}
