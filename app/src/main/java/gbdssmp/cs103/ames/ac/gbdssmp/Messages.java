package gbdssmp.cs103.ames.ac.gbdssmp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Messages extends AppCompatActivity {

    private FloatingActionButton sendBtn;
    private EditText input;

    private FirebaseListAdapter<ChatMessage> adapter, adapterReceive;
    private ListView listOfMessages;
    private TextView selected_program;

    private String program_name, name, currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        program_name = getIntent().getExtras().getString("program_name");


        selected_program = (TextView) findViewById(R.id.selected_program);
        sendBtn = (FloatingActionButton) findViewById(R.id.sendBtn);
        input = (EditText) findViewById(R.id.input);

        currentUser = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address etc
            String name = user.getDisplayName();
            selected_program.setText(name);

//            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
        }
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!input.getText().toString().isEmpty()) {

                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Messaging")
                            .child(program_name = getIntent()
                                    .getExtras()
                                    .getString("program_name") + "_" + FirebaseAuth.getInstance().getCurrentUser().getDisplayName())
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName()));

                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Messaging")
                            .child( currentUser + "_" + getIntent().getExtras().getString( "program_name"))
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName()));

                    input.setText("");
                }
            }
        });

        listOfMessages = (ListView) findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this,
                ChatMessage.class,
                R.layout.message,
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("Messaging")
                        .child(program_name = getIntent()
                                .getExtras()
                                .getString("program_name") + "_" + currentUser)
        ) {
            @Override
            protected void populateView(View view, ChatMessage model, int position) {

                TextView messageText = (TextView) view.findViewById(R.id.message_text);
                TextView messageUser = (TextView) view.findViewById(R.id.message_user);
                TextView messageTime = (TextView) view.findViewById(R.id.message_time);

                TextView userName = (TextView) findViewById(R.id.userName);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                userName.setText(model.getMessageUser());

                messageTime.setText(android.text.format.DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}