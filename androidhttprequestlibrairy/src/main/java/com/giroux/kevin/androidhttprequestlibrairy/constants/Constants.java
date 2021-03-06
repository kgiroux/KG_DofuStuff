package com.giroux.kevin.androidhttprequestlibrairy.constants;

/**
 * Created by kevin on 18/04/2016. ForeCast Application
 */
public class Constants {

    public static final boolean DEBUG = false;


    public static final String TAG_MOVIE_TASK = "MOVIE TASK";
    public static final String VIEW_HOLDER_MOVIE = "VIEW HOLDER MOVIE" ;
    public static final String TAG_POPULAR_MOVIE = "POPULAR MOVIE";
    public static final String TAG_MOVIE_CONTRACTOR = "MOVIE_CONTRACTOR";
    public static final String TAG_DETAILS_ACTIVITY = "DETAILS_ACTIVITY";
    public static final String MOVIE_PROVIDER = "MOVIE_PROVIDER";
    public static final String TAG_MOVIE_DETAIL_TASK = "MOVIE_DETAIL TASK";
    public static final String TAG_MOVIE_TRAILER = "MOVIE TRAILER TASK" ;
    public static final String TAG_MOVIE_REVIEWS = "MOVIE_REVIEWS_TASK";
    public static final String TAG_WELCOME_ACTIVITY = "WELCOME ACTIVITY";
    public static final String MOVIE_ADAPTER ="Movie Adapter" ;
    public static final String TAG_MOVIE_FULL = "MOVIE_FULL";
    public static final String TAG_UTILITY = "UTILITY" ;
    public static final String TAG_CURSOR_DELETE_UPDATE = "ASYNC_DELETE_UPDATE";

    private Constants() throws InstantiationException{throw new InstantiationError();}
    public static final String TAG_ANDROID_HTTP_REQUEST = "Android HTTP request";
    public static final String DEFAULT_ENCODING_ANDROID_HTTP_REQUEST = "UTF-8";
    public static final int DEFAULT_TIMEOUT = 15000;
    public static final int DEFAULT_SIZE = 4096;
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET" ;
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_RESULT_OK = "OK";
    public static final String CST_TIMEOUT ="Timeout for the request : {} with the parameter {} . Try to check the URL or increase the timeout value";
    public static final String CST_NO_CONTENT = "No content to return from the server";
    public static final String CST_HTTP_UNAUTHORIZED = "Access to this API UNAUTHORIZED {}";
    public static final String CST_BAD_REQUEST = "There is a problem with the request {} with {} parameter. Please check the result" ;
    public static final String CST_INTERNAL_ERROR = "Serveur Error. Please check the status of the server";
    public static final String CST_OTHER_ERROR = "Another error appear during the request : {}";
    public static final String CST_BAD_METHOD = " This request {} not allowed to use {} method. Please check the server configuration or the method on the AndroidHttpRequest declaration";
    public static final String CST_NO_FOUND = "Nothing found for the following url {} with these parameter {} for this method {}";

    public static String createLog(String cst, String ...params){
        String retunrString = cst;
        if(cst != null){
            for (String s: params) {
                retunrString = cst.replaceFirst("\\{",s).replaceFirst("\\}","");
            }
        }


        return retunrString;
    }
}
