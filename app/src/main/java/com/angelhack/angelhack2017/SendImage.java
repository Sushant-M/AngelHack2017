package com.angelhack.angelhack2017;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sushant on 29/4/17.
 */

public class SendImage extends AsyncTask<String,Void,Void> {

    String TAG = getClass().getSimpleName();

    @Override
    protected Void doInBackground(String... strings) {

        try {
            URL url = new URL("http://192.168.4.34:5000/getImage");

            /*URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write(strings[0]);
            Log.d(TAG,"Sending data");
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            String response  = sb.toString();
            Log.d(TAG,response);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imageString", strings[0]);
            String data = jsonObject.toString();
            //String yourURL = "http://54.169.88.65/events/eventmain/upload_image.php";
            //URL url = new URL(yourURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setFixedLengthStreamingMode(data.getBytes().length);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();
            connection.connect();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }}
