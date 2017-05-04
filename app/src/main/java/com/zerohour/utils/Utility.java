package com.zerohour.utils;

import android.content.Context;
import android.graphics.Typeface;

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

    /**
     * MATERIAL ICONS REGULAR TYPEFACE
     * This method is used to set the icons in Material Icons Regular
     **/
    public static Typeface getMaterialIconsRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/matireal_icons_regular.ttf");
    }

}
