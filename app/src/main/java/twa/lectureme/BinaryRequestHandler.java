package twa.lectureme;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 9/16/17.
 */

public class BinaryRequestHandler {


    public void handleRequest(final List<short[]> list, final int read, final Context c) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] soundBytes;
                ByteBuffer byteBuf = ByteBuffer.allocate(read*2);
                for(short[] l : list) {
                    for (short s : l) {
                        byteBuf.putShort(s);
                    }
                }
                soundBytes = byteBuf.array();
                final String b64 = Base64.encodeToString(soundBytes, Base64.URL_SAFE);

                Log.d("MEMES",b64);
                Log.d("MEMEMEMEMEMES", String.valueOf(read));
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
}
