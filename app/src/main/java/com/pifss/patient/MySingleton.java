package com.pifss.patient;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SMARTANS on 3/27/2017.
 */

public class MySingleton {

    

    public RequestQueue requestQueue;
    private static final MySingleton ourInstance = new MySingleton();

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {
    }

    public  RequestQueue getRequestQueue(Context context) {

        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context);


        return requestQueue;
    }
}
