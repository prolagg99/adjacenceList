package adjacencelistproject;
import java.util.LinkedList;
import java.util.List;

class Graphe{
    
    class Edge{
        int v;
        public Edge(int v){
            this.v = v;
        }
        @Override
        public String toString(){
            return "(" + v + ")";
        }
    }
    
    int lenghtG;
    List<Edge> G[];
    public Graphe(int n){
        G = new LinkedList[n];
        lenghtG = G.length;
        for (int i = 0; i<G.length; i++){
            G[i] = new LinkedList<Edge>();
        }
        
    }
    
    int index;
    boolean isConnected(int u, int v){
        for(Edge i : G[u]){
            if(i.v == v){
                index = G[u].indexOf(i);
                return true;
            }
        }
        return false;    
    }
    
    void remove(int u,int in){
        G[u].remove(in);
    }
    
    void addEdg(int u, int v){
        G[u].add(0, new Edge(v));
    }
    
    boolean emptyEdge(int u){
        boolean result = false;
        if(G[u].isEmpty()){
            result = true; 
        }
        return result;
    }
    
    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i<G.length; i++){
            result +=  i+1 + "=>" + G[i] + "\n";
        }
        return result;
    }
    
    public void degréIntérieur(Graphe G, int[] degréIntérieur) {
        // degré intérierur de chaque sommet
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(G.isConnected(j, i+1) == true){
                    degréIntérieur[i] ++;
                }
            }
        }
        System.out.printf("%nDegré intérieur des sommets : \n");
        affichage();
        System.out.printf("%n   Degré intérieur : |");
        for(int i=0;i<8;i++){
            System.out.printf(degréIntérieur[i] + "|");
        }
    }

    public void affichage() {
        // affichage des sommet
        System.out.printf("%n            Sommet : |");
        for(int i=1;i<9;i++){
            System.out.printf(i + "|");
        }
    }
    
    public void triTopologique(int[] degréIntérieur) {
        // tri topologique de graphe
        System.out.println();
        System.out.printf("%ntri topologique du graphe :  ");
        for(int i = 0; i < 8; i++){
            for(int j=0; j < 8; j++){
                if (degréIntérieur[j] == i){
                    System.out.print(j+1 + " ");
                }
            }
        }
        System.out.println();
    }
        
    public void prédecesseur(Graphe G, Graphe P) {
        for(int j=1; j<9; j++){
            int p = 0;
            for(int i=0; i<G.lenghtG; i++){
                if(G.isConnected(i, j) == true){
                    p = i+1;
                    P.addEdg(j-1, p);
                }
            }
        }
    }   
    
    public void niveauDeSommet(Graphe G, Graphe P) {
        G.prédecesseur(G, P);
        int[] T = new int[8];
        System.out.printf("%nNiveau des sommet : \n");
        G.affichage();
        System.out.printf("%n            niveau : |");
        int p = 0;
        int sommet;
        int n = 0;
        for(int k=0; k<G.lenghtG; k++){
            for(int i=0; i<G.lenghtG; i++){
                p = i+1;
                if(P.emptyEdge(i)){
                    sommet = p;
                    P.addEdg(i, 0);
                    T[i] = n;
                }  
            }
            n++;
            for(int i=0; i<G.lenghtG; i++){
                if(P.isConnected(i,0)){
                    sommet = i+1;
                    for(int j=0; j<G.lenghtG; j++){
                        if(P.isConnected(j, sommet)){
                            P.remove(j, P.index);
                        }
                    } 
                }
            }
        } 
        for(int i=0; i<8; i++){
            System.out.printf(T[i] + "|");
        }
    }
}

public class AdjacenceListProject {
    public static void main(String[] args) {
        
        Graphe G = new Graphe(8);
        
        G.addEdg(0, 2);
        G.addEdg(1, 8);
        G.addEdg(2, 1); G.addEdg(2, 4); G.addEdg(2, 6);
        G.addEdg(3, 8);
        G.addEdg(4, 3);
        G.addEdg(5, 4); G.addEdg(5, 7); G.addEdg(5, 8);
        G.addEdg(6, 1); G.addEdg(6, 2);
        
        System.out.println("la list d'adjacence du graphe :");
        System.out.printf("%n" + G);
        
        int[] degréIntérieur = new int[8];
        G.degréIntérieur(G, degréIntérieur);
        G.triTopologique(degréIntérieur);
        
        Graphe P = new Graphe(8);
        G.niveauDeSommet(G, P);
        
        System.out.println();
    }
}