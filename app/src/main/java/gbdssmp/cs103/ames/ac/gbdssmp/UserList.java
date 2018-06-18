package gbdssmp.cs103.ames.ac.gbdssmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserList extends AppCompatActivity {

    private DatabaseReference userDatabase;
    private TextView listUser;
    private String userdata, builtUpUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        listUser = (TextView)findViewById(R.id.userList);

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                     userdata = dataSnapshot.getValue().toString();
                     listUser.setText(userdata);

                     String builtUpUsers = "";
                     for (DataSnapshot datas: dataSnapshot.getChildren()) {
                         String userName = datas.getKey();
                         builtUpUsers = builtUpUsers + userName + "\n";
                     }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listUser.setText(builtUpUsers);


    }
}
