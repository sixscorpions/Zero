package com.zerohour.utils;

import android.content.Context;

/**
 * Created by Shankar on 5/4/2017.
 */

public class Utility {

    /**
     * GET Resources String
     *
     * @param context Context of the class
     * @param id      Id of the resource
     * @return String
     */
    public static String getResourcesString(Context context, int id) {
        String value = null;
        if (context != null && id != -1) {
            value = context.getResources().getString(id);
        }
        return value;
    }

}
