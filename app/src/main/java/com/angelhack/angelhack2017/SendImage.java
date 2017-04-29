package com.angelhack.angelhack2017;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
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

public class SendImage extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        String url = "http://192.168.4.34:5000/getImage";
        HttpURLConnection client = null;
        try {
            URL url_send = new URL(url);
            client = (HttpURLConnection) url_send.openConnection();
            client.setRequestMethod("POST");
            client.setRequestProperty("data","Hello world!");
            client.setDoOutput(true);
            OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
            outputPost.flush();
            outputPost.close();
            client.setChunkedStreamingMode(0);
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
