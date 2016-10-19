package com.example.simon.dycard;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Simon on 17/10/2016.
 */

public class MySingleton {
    private static MySingleton mInstances;
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
