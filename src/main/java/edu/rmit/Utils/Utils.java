package edu.rmit.Utils;

import java.util.Date;

public class Utils {
    public static Long getUID()
    {
        return new Date().getTime();
    }   
}
