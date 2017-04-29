package com.angelhack.angelhack2017;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;

/**
 * Created by sushant on 29/4/17.
 */

public class SendImage extends AsyncTask<byte[],Void,Void> {

    String TAG = getClass().getSimpleName();

    @Override
    protected Void doInBackground(byte[]... bytes) {
        String url = "http://192.168.4.34:5000/getImage";
        HttpURLConnection client = null;
        try {
            URL url_send = new URL(url);
            client = (HttpURLConnection) url_send.openConnection();
            client.setRequestMethod("POST");
            client.setRequestProperty("data","Hello world");
            client.setDoOutput(true);
            DataOutputStream outputPost = new DataOutputStream(client.getOutputStream());
            outputPost.write(bytes[0]);
            outputPost.flush();
            outputPost.close();
            //client.setChunkedStreamingMode(0);
            int conn = client.getResponseCode();
            Log.d(TAG,String.valueOf(conn));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(client != null){
                client.disconnect();
            }
        }
        return null;
    }
}
