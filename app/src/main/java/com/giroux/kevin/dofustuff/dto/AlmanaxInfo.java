package com.giroux.kevin.dofustuff.dto;

/**
 * Created by kevin on 26/02/2017.
 */

public class AlmanaxInfo {
    private String alamanaxBoss;
    private String alamanaxTitle;
    private String effectTitle;
    private String effectContent;
    private  String bonus;
    private String subBonus;
    private  String quest;
    private String questContent;
    private String dayNumber;
    private String dayMonth;

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getSubBonus() {
        return subBonus;
    }

    public void setSubBonus(String subBonus) {
        this.subBonus = subBonus;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getQuestContent() {
        return questContent;
    }

    public void setQuestContent(String questContent) {
        this.questContent = questContent;
    }

    public String getAlamanaxBoss() {
        return alamanaxBoss;
    }

    public void setAlamanaxBoss(String alamanaxBoss) {
        this.alamanaxBoss = alamanaxBoss;
    }

    public String getAlamanaxTitle() {
        return alamanaxTitle;
    }

    public void setAlamanaxTitle(String alamanaxTitle) {
        this.alamanaxTitle = alamanaxTitle;
    }
    public String getEffectTitle() {
        return effectTitle;
    }

    public void setEffectTitle(String effectTitle) {
        this.effectTitle = effectTitle;
    }

    public String getEffectContent() {
        return effectContent;
    }

    public void setEffectContent(String effectContent) {
        this.effectContent = effectContent;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(String dayMonth) {
        this.dayMonth = dayMonth;
    }
}
