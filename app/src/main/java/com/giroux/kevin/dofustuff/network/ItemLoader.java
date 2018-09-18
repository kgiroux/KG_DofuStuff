package com.giroux.kevin.dofustuff.network;

import com.giroux.kevin.androidhttprequestlibrairy.AndroidHttpRequest;
import com.giroux.kevin.dofustuff.adapter.ItemAdapter;
import com.giroux.kevin.dofustuff.commons.item.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        List<Item> listItem;
        if(o instanceof String){
            listItem = new Gson().fromJson((String)o,new TypeToken<List<Item>>(){}.getType());
                ItemAdapter itemAdapter = (ItemAdapter) this.getListObject().get("adapter");
                itemAdapter.updateData(listItem);

        }
    }
}
