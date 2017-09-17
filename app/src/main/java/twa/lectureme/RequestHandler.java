package twa.lectureme;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by anton on 9/16/17.
 */

public class RequestHandler {

    public void handleRequest(String file, Context con) {

        final String f = file;
        final Context c = con;

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] soundBytes;
                try {
                    File input = new File(f);
                    FileInputStream fileInputStream = new FileInputStream(input);
                    soundBytes = new byte[fileInputStream.available()];
                    fileInputStream.read(soundBytes);
                    fileInputStream.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                    soundBytes = new byte[0];
                }
                final String b64 = Base64.encodeToString(soundBytes, Base64.URL_SAFE);
                Log.d("MEMES",b64);
                RequestQueue queue = Volley.newRequestQueue(c);
                String url = "http://34.234.131.204:3000/newaudio";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("rid", "YOUMANK");
                        params.put("data",b64 );

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        }).start();

    }

    private void convertBytesToFile(byte[] bytearray) {

    }
}
