package com.giroux.kevin.dofustuff.activity.Almanax.network;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.giroux.kevin.androidhttprequestlibrairy.JSoupAndroidHttpRequest;
import com.giroux.kevin.dofustuff.adapter.AlmanaxAdapter;
import com.giroux.kevin.dofustuff.constants.AlmanaxConstant;
import com.giroux.kevin.dofustuff.dto.AlmanaxInfo;
import com.giroux.kevin.dofustuff.error.ErrorAlmanax;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

/**
 * Created by kevin on 26/02/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class AlmanaxTask extends JSoupAndroidHttpRequest {

    public AlmanaxTask(String url, String method, Map<String, String> paramStr) {
        super(url, method, paramStr);
    }

    @Override
    protected void onPostExecute(Object o) {
        if(o instanceof Document){
            AlmanaxInfo info = new AlmanaxInfo();
            Log.i("Info","Correct instance");
            Document doc = Document.class.cast(o);
            // Récupération du alamanaxBoss
            Element alamanaxBoss = doc.getElementById("almanax_boss_desc");
            if(alamanaxBoss != null && alamanaxBoss.getElementsByTag("span") != null ){
                info.setAlamanaxTitle(alamanaxBoss.getElementsByTag("span").get(0).text());
                info.setAlamanaxBoss(info.getAlamanaxTitle() + alamanaxBoss.text().replace(info.getAlamanaxTitle(),""));
            }else{
                throw new IllegalArgumentException(ErrorAlmanax.ERROR_ALMANAX_001.toString());
            }


            Element effectMeryde = doc.getElementById("almanax_meryde_effect");
            if(effectMeryde != null && effectMeryde.getElementsByTag("h3") != null && effectMeryde.getElementsByTag("p") != null){
                info.setEffectTitle(effectMeryde.getElementsByTag("h3").get(0).text());
                info.setEffectContent(effectMeryde.getElementsByTag("p").get(0).text());
            }else{
                throw new IllegalArgumentException(ErrorAlmanax.ERROR_ALMANAX_002.toString());
            }


            Element achivement = doc.getElementById("achievement_dofus");
            if(achivement != null
                    && achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MORE_INFOS) != null
                    && achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MORE_INFOS).get(0).getElementsByTag("p") != null){
                if(achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MORE_INFO_CONTENT) != null
                        && achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MORE_INFO_CONTENT).get(0).getElementsByTag("p") != null ){
                    info.setQuest(achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MORE_INFOS).get(0).getElementsByTag("p").get(0).text());
                    info.setQuestContent(achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MORE_INFO_CONTENT).get(0).getElementsByTag("p").get(0).text());

                }else{
                    throw new IllegalArgumentException(ErrorAlmanax.ERROR_ALMANAX_003.toString());
                }

                if(achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MID).get(0) != null
                        && achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MID).get(0).getElementsByClass(AlmanaxConstant.ALMANAX_MORE).get(0) != null){

                    info.setSubBonus(achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MID).get(0).getElementsByClass(AlmanaxConstant.ALMANAX_MORE).get(0).text());
                    info.setBonus(achivement.getElementsByClass(AlmanaxConstant.ALMANAX_MID).get(0).text().replace(info.getSubBonus(),""));

                }else{
                    throw new IllegalArgumentException(ErrorAlmanax.ERROR_ALMANAX_004.toString());
                }

            }else{
                throw new IllegalArgumentException(ErrorAlmanax.ERROR_ALMANAX_005.toString());
            }
            Element day = doc.getElementById("almanax_day");
            if(day != null &&
                    day.getElementsByClass("day-text").get(0) != null
                && day.getElementsByClass("day-number").get(0) != null){
                info.setDayMonth(day.getElementsByClass("day-text").get(0).text());
                info.setDayNumber(day.getElementsByClass("day-number").get(0).text());
            }

            if(this.getListObject().get("adapter") instanceof AlmanaxAdapter){
                AlmanaxAdapter almanaxAdapter = AlmanaxAdapter.class.cast(this.getListObject().get("adapter"));
                almanaxAdapter.addElementToList(info);
            }
        }
    }
}
