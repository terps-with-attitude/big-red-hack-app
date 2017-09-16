package twa.lectureme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HostActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageButton muteButton, questionsButton, usersButton, endButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        muteButton = (ImageButton) findViewById(R.id.activity_host_mute_button);
        questionsButton = (ImageButton) findViewById(R.id.activity_host_questions_button);
        usersButton = (ImageButton) findViewById(R.id.activity_host_users_button);
        endButton = (ImageButton) findViewById(R.id.activity_host_end_button);

    }

    @Override
    public void onClick(View view) {
        if (view == muteButton) {

        } else if (view == questionsButton) {

        } else if (view == usersButton) {

        } else if (view == endButton) {

        }

    }




}
