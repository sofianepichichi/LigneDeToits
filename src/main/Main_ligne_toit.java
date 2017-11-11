package main;

import Toits.Ligne_De_Toit;

class Main_Ligne_toits {

    public static void main(String[] args) {


        /**
         * declarer mes deux ligne de toits a fusionner
         */
        Integer[][] ligne_de_toit1 = {{1,10},{5,6},{8,0},{10,8},{12,0}};
        Integer[][] ligne_de_toit2 = {{2,12},{7,0},{9,4},{11,2},{14,0}};

        Ligne_De_Toit f = new Ligne_De_Toit(ligne_de_toit1, ligne_de_toit2);
        f.fusion_De_Lignes();
        f.Display_resulat();
        f.Display_SVG("le résultat");

    }
}