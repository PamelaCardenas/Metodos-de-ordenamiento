/*
 * 
 */

/**
 *
 * @author INTEGRANTES DEL EQUIPO:

	---> Luisa María González Ordóñez 18550352
	---> Karla Pamela Cárdenas Leyva 18550338
	---> Josué David Martínez García 18550360
	---> Roberto Antonio Pedroza Fernández 18550354
	---> Irving Gerardo Mancera Mejía 18550317
 * 
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    static final int MAX_LEVELS = 1000;
    public static void main(String[] args) {
        // TODO code application logic here
        int[] aiDatos = new int[1000];
        int[] aiCopia = new int[1000];
        int[] nuevaCopia = new int[1000];
        int[] otraCopia = new int[1000];
        int[] copia2 = new int[1000];
        for (int i = 0; i < aiDatos.length; i++) {
            aiDatos[i] = (int) (Math.random() * 100) + 1;
            otraCopia[i] = aiDatos[i];
            aiCopia[i] = aiDatos[i];
            nuevaCopia[i] = aiDatos[i];
            copia2[i] = aiDatos[i];
        }


        long iIni, iFin, iResu;

        imprimir(aiDatos);
        System.out.println("\n\nARREGLO ORDENADO SELECTION");
        iIni = System.nanoTime();
        selectionSort(aiDatos);
        iFin = System.nanoTime();
        iResu = iFin - iIni;
        System.out.println("Tiempo de Selection = " + iResu);
        imprimir(aiDatos);

        System.out.println("\n\nARREGLO ORDENADO INSERTION");
        iIni = System.nanoTime();
        insertionSort(aiCopia);
        iFin = System.nanoTime();
        iResu = iFin - iIni;
        System.out.println("Tiempo de Insertion = " + iResu);
        imprimir(aiCopia);

        System.out.println("\n\nARREGLO ORDENADO BUBBLESORT");
        iIni = System.nanoTime();
        bubbleSort(nuevaCopia);
        iFin = System.nanoTime();
        iResu = iFin - iIni;
        System.out.println("Tiempo de BubbleSort = " + iResu);
        imprimir(nuevaCopia);
        
        System.out.println("\n\nARREGLO ORDENADO QUICKSORT");
        iIni = System.nanoTime();
        quickSort(otraCopia, 0, otraCopia.length-1);
        iFin = System.nanoTime();
        iResu = iFin - iIni;
        System.out.println("Tiempo de QuickSort = " + iResu);
        imprimir(otraCopia);
        
        System.out.println("\n\nARREGLO ORDENADO QUICKSORT PARA 1000000 ELEMENTOS");
        iIni = System.nanoTime();
        quickSortSinRecursividad(copia2, MAX_LEVELS);
        iFin = System.nanoTime();
        iResu = iFin - iIni;
        System.out.println("Tiempo de QuickSort = " + iResu);
        imprimir(otraCopia);
    }

    public static void imprimir(int[] aiDatos) {
        for (int i = 0; i < aiDatos.length; i++) {
            System.out.print("[" + aiDatos[i] + "]");

        }
    }

    //Eficiencia es o(n^2) --> Comparaciones
    //Eficiencia es o(n) --> Intercambio
    public static void selectionSort(int[] aiDatos) {
        for (int i = 0; i < aiDatos.length - 1; i++) {
            int iMin = i;
            for (int j = i + 1; j < aiDatos.length; j++) {
                if (aiDatos[iMin] > aiDatos[j]) {
                    iMin = j;
                }

            }
            if (iMin != i) {
                int iTemp = aiDatos[i];
                aiDatos[i] = aiDatos[iMin];
                aiDatos[iMin] = iTemp;
            }

        }
    }

    //Eficiencia es o(n) --> Comparaciones
    //Eficiencia es o(n^2) --> Intercambio
    public static void insertionSort(int[] aiDatos) {
        for (int i = 1; i < aiDatos.length; i++) {
            int temp = aiDatos[i];
            int j = i-1;

            while ((j >=0) && (aiDatos[j]>temp)) {

                aiDatos[j+1] = aiDatos[j];
                j--;
            }
            aiDatos[j+1] = temp;
        }
    }

    //EFICIENCIA:
    //COMPARACIONES O(N^2)
    //INTERCAMBIO O(N^2)
    public static void bubbleSort(int[] aiDatos) {
        for (int i = 0; i < aiDatos.length; i++) {
            for (int j = 1; j < aiDatos.length-i; j++) {
                
                if (aiDatos[j-1] > aiDatos[j]) {
                    int temp = aiDatos[j-1];
                    aiDatos[j-1] = aiDatos[j];
                    aiDatos[j] = temp;
                }
            }

        }
    }
    
    public static void quickSort(int[] aiDatos, int valIzq, int valDer){
        int pivote = aiDatos[valIzq];
        int i = valIzq;
        int j = valDer;
        
        while (i<j){
            while(aiDatos[i] <= pivote && i<j){
                i++;
                if(i==valDer){
                    break;
                }
            }
            while(aiDatos[j] >= pivote){
                j--;
                if(j==valIzq){
                    break;
                }
            }
            
            if(i<j){
            int aux = aiDatos[i];
            aiDatos[i] = aiDatos[j];
            aiDatos[j] = aux;
            }
        }
        
        aiDatos[valIzq] = aiDatos[j];
        aiDatos[j] = pivote;
            
            if(valIzq < j-1){
                quickSort(aiDatos, valIzq, j-1);
            }
            if(j+1 < valDer){
                quickSort(aiDatos, j+1, valDer);
            }
    }
    
    public static boolean quickSortSinRecursividad(int[] arr, int elements) {
    int i=0,L,R,pivot;
    int[] beg = new int[MAX_LEVELS], end = new int[MAX_LEVELS];
    beg[0]=0;
    end[0]=elements;
    while(i>=0) {
        L=beg[i];
        R=end[i]-1;
        if(L<R) {
            pivot=arr[L]; if(i==MAX_LEVELS-1) return false;
            while(L<R) {
                while(arr[R]>=pivot&&L<R) R--; if(L<R) arr[L++]=arr[R];
                while(arr[L]<=pivot&&L<R) L++; if(L<R) arr[R--]=arr[L];
            }
            arr[L]=pivot;
            beg[i+1]=L+1;
            end[i+1]=end[i];
            end[i++]=L;
        } else {
            i--;
        }
    }
    return true;
}
    

}
