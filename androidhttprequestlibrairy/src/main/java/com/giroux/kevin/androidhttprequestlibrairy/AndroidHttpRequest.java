package com.giroux.kevin.androidhttprequestlibrairy;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import com.giroux.kevin.androidhttprequestlibrairy.constants.Constants;
import com.giroux.kevin.androidhttprequestlibrairy.constants.TypeMine;
import com.giroux.kevin.dofustuff.error.ErrorWrapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 18/04/2016. ForeCast Application
 */
public class AndroidHttpRequest extends AsyncTask<String[], Void, Object> {

  private boolean json;
  private String encoding;
  private Object object;
  private int timeout;
  private String url;
  private String method;
  private Map<String, Object> listObject;
  private Map<String, String> listParam;
  private String paramStr;
  private Uri.Builder builderURL;
  private int sizeBuffer;
  protected int errorCode;
  private ErrorWrapper errorWrapper;

  private Map<String, String> extraHeader;

  public void setTypeMine(TypeMine typeMine) {
    this.typeMine = typeMine;
  }

  private TypeMine typeMine;

  public TypeMine getTypeMine() {
    return typeMine;
  }


  private Map<String, String> getListParam() {
    return listParam;
  }

  private void setListParam(Map<String, String> listParam) {
    this.listParam = listParam;
  }

  public void setJson(boolean json) {
    this.json = json;
  }

  public String getEncoding() {
    return encoding;
  }

  private void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  private void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public void setListObject(Map<String, Object> listObject) {
    this.listObject = listObject;
  }

  protected String getUrl() {
    return url;
  }

  private void setUrl(String url) {
    this.url = url;
  }

  private void setMethod(String method) {
    this.method = method;
  }

  private boolean isJson() {
    return json;
  }

  private String getParamStr() {
    return paramStr;
  }


  /**
   * Method with all available parameter
   *
   * @param isJSON   if param is json
   * @param encoding if encoding is not UTF-8
   * @param object   return object type;
   * @param timeout  if default timeout is not 15000
   * @param url      URL for the request
   * @param method   Methode for the request
   * @param paramStr list of parameter Use createParamString static method for generate this paramter
   */
  public AndroidHttpRequest(boolean isJSON, String encoding, Object object, int timeout, String url, String method, Map<String, String> paramStr) {
    this.setJson(isJSON);
    this.setEncoding(encoding);
    this.object = (object);
    this.setTimeout(timeout);
    this.setUrl(url);
    this.setMethod(method);
    this.setListParam(paramStr);
    this.setListObject(new HashMap<String, Object>());
    this.extraHeader = new HashMap<>();
    builderURL = new Uri.Builder();
  }


  /**
   * Method with only minimal parameter
   *
   * @param url      url for the request
   * @param method   method for the request
   * @param paramStr list of parameter. Use createParamString static method for generate this paramter
   */
  protected AndroidHttpRequest(String url, String method, Map<String, String> paramStr) {
    this.url = (url);
    this.method = (method);
    this.json = true;
    this.encoding = Constants.DEFAULT_ENCODING_ANDROID_HTTP_REQUEST;
    this.timeout = Constants.DEFAULT_TIMEOUT;
    this.object = (null);
    this.listObject = new HashMap<>();
    this.listParam = (paramStr);
    builderURL = new Uri.Builder();
    this.typeMine = TypeMine.APPLICATION_JSON;
    this.extraHeader = new HashMap<>();
    this.sizeBuffer = Constants.DEFAULT_SIZE;
  }

  @Override
  protected Object doInBackground(String[]... params) {
    return executeRequest(createParamString(this.getListParam()));
  }


  /**
   * Method that perform the request
   *
   * @param uri url for the request
   * @return Object
   */
  private Object executeRequest(Uri uri) {
    if (Constants.DEBUG)
      Log.d(Constants.TAG_ANDROID_HTTP_REQUEST, "executeRequest: perform a request");
    displayParamaterForTheRequest();
    URL targetUrl;
    HttpURLConnection urlConnection = null;
    Object result = null;
    OutputStream os;
    try {
      //Création de la connexion et de l'url de la requête
      targetUrl = new URL(uri.toString());
      this.setUrl(new URL(uri.toString()).toString());
      urlConnection = (HttpURLConnection) targetUrl.openConnection();
      if (Constants.DEBUG)
        Log.d(Constants.TAG_ANDROID_HTTP_REQUEST, this.getUrl() + "    " + targetUrl);
      //Définition des paramètres de connexion
      urlConnection.setReadTimeout(this.timeout);
      urlConnection.setConnectTimeout(this.timeout);
      urlConnection.setDoInput(true);
      //Send parameters
      urlConnection.setRequestMethod(this.method);
      urlConnection.setRequestProperty("Content-Type", this.typeMine.toString() + ";charset=" + this.encoding);
      for(Map.Entry<String, String> entrySet : extraHeader.entrySet()){
        urlConnection.setRequestProperty(entrySet.getKey(), entrySet.getValue());
      }
      if (this.method.equals(Constants.METHOD_POST)) {
        urlConnection.setDoOutput(true);
        if (this.getParamStr() != null) {
          os = urlConnection.getOutputStream();
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, this.encoding));
          writer.write(this.getParamStr());
          writer.flush();
          writer.close();
          os.close();
        }
      }else if(this.method.equals(Constants.METHOD_PUT)){
        urlConnection.setDoOutput(true);
        if (this.getParamStr() != null) {
          os = urlConnection.getOutputStream();
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, this.encoding));
          writer.write(this.getParamStr());
          writer.flush();
          writer.close();
          os.close();
        }
      }

      //Récupération des iNformations de retour du serveur
      urlConnection.connect();
      result = performedCheckCodeMessage(urlConnection);
    } catch (IOException | JSONException ex) {
      Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, "Error decoding stream", ex);
    } finally {
      if (urlConnection != null)
        urlConnection.disconnect();
    }
    return result;
  }

  private Object performedCheckCodeMessage(HttpURLConnection urlConnection) throws IOException, JSONException {
    InputStream in;
    String str;
    Object json = null;
    if (this.paramStr == null) {
      this.paramStr = "";
    }
    in = new BufferedInputStream(urlConnection.getInputStream());
    switch (urlConnection.getResponseCode()) {
      //200
      case HttpURLConnection.HTTP_OK:
        Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, " Response OK");
        urlConnection.getResponseMessage();
        urlConnection.getContentType();
        if (urlConnection.getContentType().equals(TypeMine.APPLICATION_JSON.toString() + ";charset=" + this.encoding.toLowerCase())) {
          str = streamToString(in);
          json = new JSONObject(str);
        } else if (urlConnection.getContentType().equals(TypeMine.IMAGE_JPEG.toString()) || urlConnection.getContentType().equals(TypeMine.IMAGE_PNG.toString())) {
          byte[] bytes = new byte[sizeBuffer];
          int bytesRead;

          ByteArrayOutputStream bao = new ByteArrayOutputStream();

          while ((bytesRead = in.read(bytes)) != -1) {
            bao.write(bytes, 0, bytesRead);
          }
          json = bao.toByteArray();
        } else {
          str = streamToString(in);
          json = str;
        }
        in.close();
        errorCode = HttpURLConnection.HTTP_OK;
        break;
      // 204
      case HttpURLConnection.HTTP_NO_CONTENT:
        Log.d(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_NO_CONTENT));
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_NO_CONTENT;
        break;
      // 400
      case HttpURLConnection.HTTP_BAD_REQUEST:
        Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_BAD_REQUEST, url, this.method));
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_BAD_REQUEST;
        break;
      //404
      case HttpURLConnection.HTTP_NOT_FOUND:
        Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_NO_FOUND, url, this.paramStr, this.method));
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_NOT_FOUND;
        break;
      //401
      case HttpURLConnection.HTTP_UNAUTHORIZED:
        Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_HTTP_UNAUTHORIZED, String.valueOf(urlConnection.getResponseCode())));
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_UNAUTHORIZED;
        break;
      // 405
      case HttpURLConnection.HTTP_BAD_METHOD:
        Log.d(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_BAD_METHOD, url, this.method));
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_BAD_METHOD;
        break;
      // 408
      case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
        // Retry the request
        urlConnection.disconnect();
        urlConnection.connect();
        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
          Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_TIMEOUT, url, this.paramStr));
        }
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_CLIENT_TIMEOUT;
        break;
      // 500
      case HttpURLConnection.HTTP_INTERNAL_ERROR:
        Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_INTERNAL_ERROR, url, this.paramStr));
        errorWrapper = new Gson().fromJson(streamToString(in), ErrorWrapper.class);
        errorCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
        break;
      default:
        Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, Constants.createLog(Constants.CST_OTHER_ERROR, String.valueOf(urlConnection.getResponseCode())));
        break;
    }
    return json;
  }


  @Override
  public String toString() {
    return "AndroidHttpRequest{" +
        "isJson=" + json +
        ", encoding='" + encoding + '\'' +
        ", object=" + object +
        ", timeout=" + timeout +
        ", url='" + url + '\'' +
        ", method='" + method + '\'' +
        ", listObject=" + listObject +
        '}';
  }

  private void displayParamaterForTheRequest() {
    String toDisplay = "AndroidHttpRequest : " +
        "\njson activated : " + json +
        ", \nencoding : '" + encoding + '\'' +
        ", \nUI object : " + object +
        ", \nTimeout : " + timeout +
        ", \nUrl : '" + url + '\'' +
        ", \nMethod : '" + method + '\'' +
        ", \nparam : '" + paramStr + '\'' +
        ", \nlistObject : '" + listObject + '\'';
    Log.d(Constants.TAG_ANDROID_HTTP_REQUEST, toDisplay);
  }

  /**
   * Method that can help user to update view
   *
   * @param key   key on the result Object
   * @param value UI object that need to be update
   */
  public void addUIObjectToUpdate(String key, Object value) {
    this.listObject.put(key, value);
  }


  private String getMethod() {
    return method;
  }

  protected Map<String, Object> getListObject() {
    return listObject;
  }

  private Uri createParamString(Map<String, String> listParam) {
    Uri urlToParse = Uri.parse(this.getUrl());
    JSONObject resultObject = new JSONObject();
    builderURL.scheme(urlToParse.getScheme()).appendEncodedPath(urlToParse.getPath());
    if (this.isJson()) {
      putParametersIntoUrl(listParam, resultObject);
    } else {
      buildUrlWithParam(listParam);
    }
    return computeUrl(urlToParse);
  }

  private Uri computeUrl(Uri urlToParse) {
    if (this.getMethod().equals(Constants.METHOD_POST) && !this.isJson()) {
      this.paramStr = builderURL.build().getQuery();
    } else if (!this.isJson()) {
      builderURL.authority(urlToParse.getAuthority()).scheme(urlToParse.getScheme());
      this.paramStr = builderURL.build().getQuery();
      urlToParse = builderURL.authority(urlToParse.getAuthority()).build();
    }
    return urlToParse;
  }

  private void buildUrlWithParam(Map<String, String> listParam) {
    if (listParam != null && listParam.size() > 0) {
      for (Map.Entry<String, String> entrySet : listParam.entrySet()) {
        if (entrySet.getKey().equals("") || entrySet.getKey().equals("/")) {
          builderURL.appendEncodedPath(entrySet.getValue());
        } else {
          builderURL.appendQueryParameter(entrySet.getKey(), entrySet.getValue()).build();
        }
      }
    }
  }

  private void putParametersIntoUrl(Map<String, String> listParam, JSONObject resultObject) {
    try {
      if (listParam != null && listParam.size() > 0) {
        for (Map.Entry<String, String> entrySet : listParam.entrySet()) {
          resultObject.put(entrySet.getKey(), entrySet.getValue());
        }
        this.paramStr = resultObject.toString();
      }

    } catch (JSONException ex) {
      Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, "Error during rendering json", ex);
    }
  }

  /**
   * Method that convert a inputStream into a String
   *
   * @param in input stream
   * @return String with the result
   */
  private @NonNull String streamToString(@NonNull InputStream in) {
    InputStreamReader rd = new InputStreamReader(in);
    BufferedReader buffer = new BufferedReader(rd);
    StringBuilder strb = new StringBuilder();
    try {
      String str;
      while ((str = buffer.readLine()) != null)
        strb.append(str);
    } catch (Exception ex) {
      Log.e(Constants.TAG_ANDROID_HTTP_REQUEST, "Error decoding stream", ex);
    }
    return strb.toString();
  }

  /**
   * Recupération du code erreur
   * @return code d'erreur
   */
  public int getErrorCode() {
    return errorCode;
  }

  /**
   * Recupération de l'erreur
   * @return
   */
  public ErrorWrapper getErrorWrapper() {
    return errorWrapper;
  }

  public void setExtraHeader(Map<String, String> extraHeader) {
    this.extraHeader = extraHeader;
  }
}
