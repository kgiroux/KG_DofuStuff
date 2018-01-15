package com.giroux.kevin.dofustuff.network;

import com.giroux.kevin.androidhttprequestlibrairy.AndroidHttpRequest;

import java.util.Map;

/**
 * Created by giroux.kevin on 06/01/2018.
 */

public class TokenGenerateTask extends AndroidHttpRequest {


  public TokenGenerateTask(String url, String method, Map<String, String> paramStr) {
    super(url, method, paramStr);
  }
}
