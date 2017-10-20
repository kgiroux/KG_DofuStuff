package com.giroux.kevin.dofustuff.network;

import android.util.Log;

import com.giroux.kevin.androidhttprequestlibrairy.AndroidHttpRequest;
import com.giroux.kevin.dofustuff.adapter.ItemAdapter;
import com.giroux.kevin.dofustuff.dto.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by girouxkevin on 01/10/2017.
 */

public class ItemLoader extends AndroidHttpRequest {

    public ItemLoader(String url, String method, Map<String, String> paramStr) {
        super(url, method, paramStr);
    }


    @Override
    protected void onPostExecute(Object o) {
        List<Item> itemList = new ArrayList<>();
        if(o instanceof String){
            JsonParser jsonParser = new JsonParser();
            JsonElement element =jsonParser.parse((String)o);
            if(element.isJsonArray()){
                JsonArray jsonArray = element.getAsJsonArray();
                for(JsonElement jsonElement : jsonArray){
                    JsonObject object = jsonElement.getAsJsonObject();
                    Item item = new Item();
                    item.setName(object.get("name").getAsString());
                    item.setLevel(object.get("level").getAsInt());
                    if(object.has("imageId")){
                        item.setItemId(object.get("imageId").getAsInt());
                    }

                    itemList.add(item);
                }
                ItemAdapter itemAdapter = (ItemAdapter) this.getListObject().get("adapter");
                itemAdapter.updateData(itemList);
            }
        }
    }
}
