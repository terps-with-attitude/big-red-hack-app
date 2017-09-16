package twa.lectureme;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class StudentInterfaceActivity extends AppCompatActivity {

    private String url ="http://www.google.com";
    private static final String TEST_ID  = "0123456789";
    private static final String TEST_RID = "room0";
    private RequestQueue queue;
    private EditText qText;
    private ImageButton qSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_interface);
        qText = (EditText) findViewById(R.id.qText);
        qSubmit = (ImageButton) findViewById(R.id.submitQ);
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    private void onSubmit(View v)
    {
        String text = qText.getText().toString();
        if(text.length() == 0)
        {
            Toast empty = Toast.makeText(getApplicationContext(),"Don't send empty questions nitwit!",Toast.LENGTH_SHORT);
            empty.show();
            return;
        }
        JSONObject question = new JSONObject();

        try {
            question.put("uid",TEST_ID);
            question.put("question",qText.getText().toString());
            question.put("rid",TEST_RID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest postQuestion = new JsonObjectRequest(Request.Method.POST, url, question, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast success = Toast.makeText(getApplicationContext(),"Sent Question",Toast.LENGTH_SHORT);
                success.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(postQuestion);
    }
}
