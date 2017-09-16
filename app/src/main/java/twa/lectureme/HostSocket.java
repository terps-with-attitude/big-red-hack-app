package twa.lectureme;


import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

public class HostSocket {

    private static Socket outputSocket;

    public static void sendData() {
        if (outputSocket == null || outputSocket.isClosed()) {
            return;
        }

    }

    public static void connect() throws IOException {
        outputSocket = new Socket("34.232.48.85", 3000);
    }

}
