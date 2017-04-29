package com.angelhack.angelhack2017;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by sushant on 29/4/17.
 */

public class SendImage extends AsyncTask<Byte,Void,Void> {

    @Override
    protected Void doInBackground(Byte... bytes) {
        try{
            Socket socket = new Socket("192.168.4.34",8000);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            
            socket.close();
            return null;}
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
