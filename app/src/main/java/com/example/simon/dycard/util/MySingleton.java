package com.example.simon.dycard.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.model.Destinataire;
import com.example.simon.dycard.model.User;

/**
 * Created by Simon on 17/10/2016.
 */

public class MySingleton {
    private static MySingleton mInstances;
    private User user;
    private Commande commande;
    private RequestQueue requestQueue;
    private static Context mContext;

    private MySingleton(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstances == null){
            mInstances = new MySingleton(context);
        }
        return mInstances;
    }

    public <T>void addToRequestque(Request<T> request) {
        requestQueue.add(request);
    }
}
