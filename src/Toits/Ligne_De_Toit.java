package Toits;

import Shema.Point;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Ligne_De_Toit {



    private Integer[][] ligne_toit1;
    private Integer[][] ligne_toit2;
    public List<Point> Result_fusion=new ArrayList<>();



    public Ligne_De_Toit(Integer[][] ligne_toit1 , Integer[][] ligne_toit2){
        this.ligne_toit1=ligne_toit1;
        this.ligne_toit2=ligne_toit2;
    }



    public Integer[][] getLigne_toit1()
       {return ligne_toit1;}

    public void setLigne_toit1(Integer[][] ldt1)
    {	this.ligne_toit1 = ligne_toit1;}

    public Integer[][] getLigne_toit2()
    {	return ligne_toit2;}

    public void setLigne_toit2(Integer[][] ldt2)

    {	this.ligne_toit2 = ligne_toit2;}



    /**
     * Fonction pour determiner le  prochain point de ligne de toit
     * @return
     */


    public Point Determine_next_ligne(Integer P1x, Integer P1y, Integer P2x, Integer P2y){
        Point res;
        // Si ça va dans le sens croissant !!!
        if(  P1x < P2x && P1y> P2y ){
            res = new Point(P2x,P1y);
        }

        else{
            res = new Point(P2x,P2y);
        }

        return res;
    }

    /**
     * La Fonction qui sert a fusioner deux ligne de toits
     * pour pourvoir determiner la ligne de toit correspondant
     * à la fusion de deux lignes de toits
     */

    public void fusion_De_Lignes(){
        List<Point> p= new ArrayList<>();

		/* J'ajoute à P tous les couples de coordonnées des 2 listes de lignes de toit */
        for(int i=0;i<ligne_toit1.length;i++){
            p.add(new Point(this.getLigne_toit1()[i][0],this.getLigne_toit1()[i][1]));
        }

        for(int j=0;j<ligne_toit2.length;j++){
            p.add(new Point(this.getLigne_toit2()[j][0],this.getLigne_toit2()[j][1]));
        }

        /* tri la liste des coordonnées */
        tri(p);



		/* Init de la liste aveac la premier valeur */

        Result_fusion.add(p.get(0));

		/* Determine les points de ligne de toit suivant */

        for(int i=1,j=1;j<ligne_toit1.length+ligne_toit2.length;j++,i++){
            Result_fusion.add(Determine_next_ligne(p.get(j-1).x, p.get(j-1).y,p.get(j).x,p.get(j).y));

			/* Si point de ligne de toit inutile ( les point egaux) */
            if(Result_fusion.get(i).y==Result_fusion.get(i-1).y){

// on stock le point (0,0) quand on descent
                Result_fusion.get(i).x=0;
                Result_fusion.get(i).y=0;
            }
        }

        System.out.println("La Fusion des lignes à bien eté efféctué ");
    }


    /**
     * Fonction pour determiner le prochain point de ligne de toit
     * @return
     */

    /**
     * La fonction qui sert a trier  les couples
     */

    public static void tri(List<Point> p)
    {
        Collections.sort(p,new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.getX(), o2.getX());
            }
        });
    }

    /**
     * Fonction qui affiche dans le terminal les résultat stocké dans la tableau
     * des coordonnées fusionner
     */
    public void Display_resulat(){


        System.out.println("Les couple de la ligne de  toit fusionné  sont  :");
        for(int i=0;i<Result_fusion.size();i++)
            // si le resultat de la fusion n'est pas 0 on affiche les couple (x,y) obtenu aprés avoir fusionné
            if(Result_fusion.get(i).x!=0)
                System.out.println("("+Result_fusion.get(i).x+","+Result_fusion.get(i).y+")");
    }


    /**
     * La Fonction pour créer un fichier qui  contenant la ligne de toit fussioné
     *
     */
    public void Display_SVG(String nomFichier){

        try {
            File fichier = new File(nomFichier+".svg");
            FileOutputStream ficher;
            ficher = new FileOutputStream(fichier);

            // LA chaîne à rajouter - entete
            String CH = "<svg xmlns='http://www.w3.org/2000/svg' width='300' height='200' viewBox='-10 -150 200 150'>\n";

            // ligne de toit
            CH += "<polyline points='";
            CH += Result_fusion.get(0).x+","+0+" "+Result_fusion.get(0).x+","+Result_fusion.get(0).y+" ";
            for(int i=1;i<Result_fusion.size();i++)
                if(Result_fusion.get(i-1).x==0)
                    CH += Result_fusion.get(i).x+","+Result_fusion.get(i-2).y+" "+Result_fusion.get(i).x+","+Result_fusion.get(i).y+" ";
            else if ((Result_fusion.get(i).x!=0))
                CH += Result_fusion.get(i).x+","+ Result_fusion.get(i-1).y+" "+ Result_fusion.get(i).x+","+ Result_fusion.get(i).y+" ";

            CH += "' stroke='blue' stroke-width='1' fill='none' transform='scale(5,-5)'/></svg>";

            // l'écriture de la chaine dans le fichier crée

            ficher.write(CH.getBytes());
            ficher.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


