package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.dsktp.sora.displayjokelib.displayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;

import java.io.IOException;

/**
 * This file created by Georgios Kostogloudis
 * and was last modified on 12/6/2018.
 * The name of the project is TellaJoke and it was created as part of
 * UDACITY ND programm.
 */
public class GetJokeAsyncTask extends AsyncTask<Context, Void, String> {


    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context ... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];


        try
        {
            return myApiService.showJoke().execute().getData();
        }
        catch (IOException e)
        {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        Intent startDisplayActivity = new Intent(context,displayActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(displayActivity.JOKE_BUNDLE_KEY, result);
        startDisplayActivity.putExtras(bundle);
        context.startActivity(startDisplayActivity);
    }
}