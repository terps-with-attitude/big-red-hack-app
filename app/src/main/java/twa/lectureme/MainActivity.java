package twa.lectureme;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private Button createRoomButton, joinRoomButton;
    private EditText roomIdText, usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        createRoomButton = findViewById(R.id.activity_main_createroom_button);
        joinRoomButton = findViewById(R.id.activity_main_join_room_button);
        roomIdText = findViewById(R.id.activity_main_room_id);
        usernameText = findViewById(R.id.activity_main_room_username);


    }


    public void onCreateRoom(View v) {
    }

    public void onJoinRoom(View v) {
        if (roomIdText.getText().toString().length() != 5) {
            roomIdText.setError("ID is a 5 length code.");
            Log.d("E", "Incorrect id entered.");
        }
        if (usernameText.getText().toString().length() < 3) {
            usernameText.setError("Name must be atleast 3 characters.");
            Log.d("E", "Invalid username.");
        }


        // process joining room
        Intent intent = new Intent(this, StudentInterfaceActivity.class);
        startActivity(intent);
    }
}