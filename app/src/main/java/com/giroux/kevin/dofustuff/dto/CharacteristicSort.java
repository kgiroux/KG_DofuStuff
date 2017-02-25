package com.giroux.kevin.dofustuff.dto;

import com.giroux.kevin.dofustuff.database.PrimaryKeyFactory;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * File created by Kevin on 02/01/2015 for DofusPanoManager
 */
@RealmClass
public class CharacteristicSort extends RealmObject {
    @PrimaryKey
    private long id;
    private int id_sort;
    private int lv;
    private String probabilite_cc;
    private String nb_lancer_tour;
    private String nb_lancer_tour_joueur;
    private String nb_lancer_entre_deux_tour;
    private String portee_modifiable;
    private String ligne_vue;
    private String lancer_en_ligne;
    private String cellules_libres;
    private String effets_normaux;
    private String effets_critiques;
    private int pa;
    private String portee;

    public CharacteristicSort() {
        this.id = PrimaryKeyFactory.getInstance().nextKey(CharacteristicSort.class);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId_sort(int id_sort) {
        this.id_sort = id_sort;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public void setProbabilite_cc(String probabilite_cc) {
        this.probabilite_cc = probabilite_cc;
    }

    public void setNb_lancer_tour(String nb_lancer_tour) {
        this.nb_lancer_tour = nb_lancer_tour;
    }

    public void setNb_lancer_tour_joueur(String nb_lancer_tour_joueur) {
        this.nb_lancer_tour_joueur = nb_lancer_tour_joueur;
    }

    public void setNb_lancer_entre_deux_tour(String nb_lancer_entre_deux_tour) {
        this.nb_lancer_entre_deux_tour = nb_lancer_entre_deux_tour;
    }


    public void setPortee_modifiable(String portee_modifiable) {
        this.portee_modifiable = portee_modifiable;
    }

    public void setLigne_vue(String ligne_vue) {
        this.ligne_vue = ligne_vue;
    }

    public void setLancer_en_ligne(String lancer_en_ligne) {
        this.lancer_en_ligne = lancer_en_ligne;
    }

    public void setCellules_libres(String cellules_libres) {
        this.cellules_libres = cellules_libres;
    }

    public void setEffets_normaux(String effets_normaux) {
        this.effets_normaux = effets_normaux;
    }


    public void setEffets_critiques(String effets_critiques) {
        this.effets_critiques = effets_critiques;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }


    public void setPortee(String portee) {
        this.portee = portee;
    }

    @Override
    public String toString() {
        if (portee_modifiable.equals("0")) {
            portee_modifiable = "Non";
        } else {
            portee_modifiable = "Oui";
        }
        if (ligne_vue.equals("0")) {
            ligne_vue = "Non";
        } else {
            ligne_vue = "Oui";
        }

        if (lancer_en_ligne.equals("0")) {
            lancer_en_ligne = "Non";
        } else {
            lancer_en_ligne = "Oui";
        }
        if (cellules_libres.equals("0")) {
            cellules_libres = "Non";
        } else {
            cellules_libres = "Oui";
        }

        return "Pa = " + pa + "\n" +
                "Portée =" + portee + "\n" +
                "Effets Normaux : \n" + effets_normaux + "\n" +
                "Effets Critiques : \n" + effets_critiques + "\n" +
                "Probabilité Coup Critique = " + probabilite_cc + "\n" +
                "Nombre de lancer par tour = " + nb_lancer_tour + "\n" +
                "Nombre de lancer par Joueur = " + nb_lancer_tour_joueur + "\n" +
                "Nombre de tours entre deux lancers = " + nb_lancer_entre_deux_tour + "\n" +
                "Portée modifiable = " + portee_modifiable + "\n" +
                "Ligne de vue = " + ligne_vue + "\n" +
                "Lancer en ligne = " + lancer_en_ligne + "\n" +
                "Cellules libres = " + cellules_libres;
    }

    public int getId_sort() {
        return id_sort;
    }
}