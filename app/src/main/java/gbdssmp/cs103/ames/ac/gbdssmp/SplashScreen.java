package gbdssmp.cs103.ames.ac.gbdssmp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class SplashScreen extends MainActivity {

    /** Duration of wait **/
//    private final int S/LASH_DISPLAY_LENGTH = 1000;

    //1: Declare variables
    public static Boolean started = false;
    private FirebaseAuth mAuth;
    private Button mLogin, mRegistration;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen_activity);

        mLogin = (Button) findViewById(R.id.login);
        mRegistration = (Button) findViewById(R.id.registration);
        //At the beginning, make these two button invisible
         mLogin.setVisibility(View.INVISIBLE);
         mRegistration.setVisibility(View.INVISIBLE);

         //////////////////////////////////////////////////////////////////////////////
        if (mAuth.getCurrentUser() != null) {

            //User already logged in yet, go straight to chat layout
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
            //3: Check whether the userID exists in the Authentication database. If yes, go straight to
            //"ChatActivity". If not, display "Login" and "Registration" buttons for users options
        } else {

            //User hasn't logged in yet, make two buttons visible
             mLogin.setVisibility(View.VISIBLE);
             mRegistration.setVisibility(View.VISIBLE);
        }

        //////////////////////////////////////////////////////////////////////////////
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
                return;
        } });

        //////////////////////////////////////////////////////////////////////////////
        //4: Set listener for "Registration" button, open RegistrationActivity when clicking it

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), RegistrationActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}