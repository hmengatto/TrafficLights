package br.com.unquo.trafficlights;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by HenriqueM on 25/03/2018.
 */

public class MySingleton {
    private static MySingleton ourInstance;

    public FirebaseAuth auth;

    public LoadSemaforos semaforos;

    private MySingleton() {

    }

    // This method should be called first to do singleton initialization
    protected static synchronized MySingleton getInstance() {
//        Log.d("BrilhO", "getInstance");
        if (ourInstance == null) {
            ourInstance = new MySingleton();
        }
        return ourInstance;
    }

}