import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;// para pilha 

public class MetodosOrdenação {
    private static Random rand = new Random();

    // para reverter o vetor
    public static <T> void reverterVetor(T[] vetor) {
        int esquerda = 0;
        int direita = vetor.length - 1;
        while (esquerda < direita) {
            T temp = vetor[esquerda];
            vetor[esquerda] = vetor[direita];
            vetor[direita] = temp;
            esquerda++;
            direita--;
        }
    }
    
    public static <T extends Comparable<T>> void BubbleSort (T vetor[]){
        int n = vetor.length;
        int i, j, troca;
        T aux;
        
        for(i = n-1;i > 0;i--){
            troca = 0;
            for(j = 0;j < i;j++){

                if(vetor[j].compareTo(vetor[j+1]) > 0){
                    aux = vetor[j];
                    vetor[j] = vetor[j+1];
                    vetor[j+1] = aux;
                    troca = 1;
                }
                
            }
            if(troca==0) break;
        }
    }

    public static <T extends Comparable<T>> void SelectionSort (T vetor[]){
        int n = vetor.length;
        int  i, j, i_menor;
        
        // Seleção
        for(i = 0;i < n-1;i++){
            i_menor = i;
            
            // Encontrar o menor elemento na parte não ordenada
            for(j = i+1;j < n;j++){
                if(vetor[j].compareTo(vetor[i_menor]) < 0){
                    i_menor = j;
                }
            }
            // Trocar o menor elemento encontrado com o primeiro elemento da parte não ordenada
            if(i_menor != i){
                T aux = vetor[i];
                vetor[i] = vetor[i_menor];
                vetor[i_menor] = aux;
            }
        }
    }

    // Método público para iniciar o Merge Sort
    public static <T extends Comparable<T>> void mergeSort(T[] vetor){
        int tamanho = vetor.length;

        if(tamanho < 2) return;

        int meio = tamanho / 2;
        T[] esquerda = (T[]) new Comparable[meio];
        T[] direita = (T[]) new Comparable[tamanho - meio];

        for(int i = 0; i < meio; i++){
            esquerda[i] = vetor[i];
        }
        for(int i = meio; i < tamanho; i++){
            direita[i - meio] = vetor[i];
        }

        mergeSort(esquerda);
        mergeSort(direita);

        //merge
        merge(vetor, esquerda, direita);
        
    }

    // Método para mesclar dois subvetorays
    private static <T extends Comparable<T>> void merge(T[] vetor, T[] esquerda, T[] direita){
        int tamanhoEsquerda = esquerda.length;
        int tamanhoDireita = direita.length;

        int i = 0, j = 0, k = 0;

        while ( i < tamanhoEsquerda && j < tamanhoDireita) {
            if(esquerda[i].compareTo(direita[j]) <= 0) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            k++;
        }
        while (i < tamanhoEsquerda) {
            vetor[k] = esquerda[i];
            i++;
            k++;            
        }
        while (j < tamanhoDireita) {
            vetor[k] = direita[j];
            j++;
            k++;            
        }
    }

    

    // Método público para iniciar o Quick Sort
    public static <T extends Comparable<T>> void quickSort(T[] vetor) {
        quickSort(vetor, 0, vetor.length - 1);
    }

    // Método recursivo do Quick Sort
    private static <T extends Comparable<T>> void quickSort(T[] vetor, int left, int right) {
        if (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);// Escolhe um pivô aleatório
            swap(vetor, pivotIndex, right);

            int p = partition(vetor, left, right);

            quickSort(vetor, left, p - 1);
            quickSort(vetor, p + 1, right);
        }
    }

    // Particiona o vetoray em torno do pivô
    private static <T extends Comparable<T>> int partition(T[] vetor, int left, int right) {
        T pivot = vetor[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (vetor[j].compareTo(pivot) < 0) {
                swap(vetor, i, j);
                i++;
            }
        }

        swap(vetor, i, right);
        return i;
    }

    // Troca dois elementos no vetoray
    private static <T> void swap(T[] vetor, int i, int j) {
        T temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    public static ArrayList<AstroObjeto> lerCSV(String caminho, int tamanho) {
        ArrayList<AstroObjeto> lista = new ArrayList<>();
        File arquivo = new File(caminho);
        int i = 0;
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado em: " + arquivo.getAbsolutePath());
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine(); // pula cabeçalho (se houver)
            while ((linha = br.readLine()) != null && i < tamanho) {
                String[] cols = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                try {
                    int neo_id = cols.length > 0 && !cols[0].isBlank() ? Integer.parseInt(cols[0].replaceAll("^\"|\"$", "")) : 0;
                    String name = cols.length > 1 ? cols[1].replaceAll("^\"|\"$", "") : "";
                    double absolute_magnitude = cols.length > 2 && !cols[2].isBlank() ? Double.parseDouble(cols[2].replaceAll("^\"|\"$", "")) : 0.0;
                    double estimated_diameter_min = cols.length > 3 && !cols[3].isBlank() ? Double.parseDouble(cols[3].replaceAll("^\"|\"$", "")) : 0.0;
                    double estimated_diameter_max = cols.length > 4 && !cols[4].isBlank() ? Double.parseDouble(cols[4].replaceAll("^\"|\"$", "")) : 0.0;
                    String orbiting_body = cols.length > 5 ? cols[5].replaceAll("^\"|\"$", "") : "";
                    double relative_velocity = cols.length > 6 && !cols[6].isBlank() ? Double.parseDouble(cols[6].replaceAll("^\"|\"$", "")) : 0.0;
                    double miss_distance = cols.length > 7 && !cols[7].isBlank() ? Double.parseDouble(cols[7].replaceAll("^\"|\"$", "")) : 0.0;
                    boolean is_hazardous = cols.length > 8 && !cols[8].isBlank() ? Boolean.parseBoolean(cols[8].replaceAll("^\"|\"$", "")) : false;

                    AstroObjeto obj = new AstroObjeto(neo_id, name, absolute_magnitude,
                            estimated_diameter_min, estimated_diameter_max,
                            orbiting_body, relative_velocity, miss_distance, is_hazardous);
                    lista.add(obj);

                    i++;
                } catch (NumberFormatException e) {
                    // linha com dados inválidos — pular
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void imprimirResultados(MetodosOrdenação metodos, AstroObjeto[] vetor_bubble, AstroObjeto[] vetor_selection, AstroObjeto[] vetor_merge, AstroObjeto[] vetor_Quick) {

                //------------------Bubble Sort---------------------
        long inicio = System.nanoTime();
        metodos.BubbleSort(vetor_bubble);
        long fim = System.nanoTime() - inicio;
        System.out.println("\n=== BubbleSort ===");
        System.out.printf("Tempo: %d ns | %.2f s\n", fim, fim / 1000000000.0);
            
        //------------------Selection Sort-------------------
        inicio = System.nanoTime();
        metodos.SelectionSort(vetor_selection);
        fim = System.nanoTime() - inicio;
        System.out.println("\n=== SelectionSort ===");
        System.out.printf("Tempo: %d ns | %.2f s\n", fim, fim / 1000000000.0);


        //--------------------Merge Sort---------------------
        inicio = System.nanoTime();
        metodos.mergeSort(vetor_merge);
        fim = System.nanoTime() - inicio;
        System.out.println("\n=== MergeSort ===");
        System.out.printf("Tempo: %d ns | %.2f s\n", fim, fim / 1000000000.0);
            
        // -------------------- QuickSort --------------------
        inicio = System.nanoTime();
        metodos.quickSort(vetor_Quick);
        fim = System.nanoTime() - inicio;
        System.out.println("\n=== QuickSort ===");
        System.out.printf("Tempo: %d ns | %.2f s\n", fim, fim / 1000000000.0);
        
    }

    
    public static void main(String[] args) {

        String caminho = "C:\\Users\\user\\OneDrive\\Engenharia da computação\\Periodo 3\\Estrutura de Dados\\Trabalho 2\\nearest-earth-objects(1910-2024).csv";

        int[] tamanhos = {50000, 100000, 150000, 200000, 250000, 300000, 350000};
        
        for (int tamanho : tamanhos) {

            ArrayList<AstroObjeto> lista = lerCSV(caminho, tamanho);
            AstroObjeto[] array = lista.toArray(new AstroObjeto[0]);

            MetodosOrdenação metodos = new MetodosOrdenação();    
                
            AstroObjeto[] vetor_bubble = array.clone();
            AstroObjeto[] vetor_selection = array.clone();
            AstroObjeto[] vetor_Quick  = array.clone();
            AstroObjeto[] vetor_merge = array.clone();

            System.out.println("======================================");
            System.out.println("        Tamanho: " + tamanho + " elementos"  );
            System.out.println("======================================");

            System.out.println("======================================");
            System.out.println("               ALEATÓRIO            "  );
            System.out.println("======================================");
            
            metodos.imprimirResultados(metodos, vetor_bubble, vetor_selection, vetor_merge, vetor_Quick);
            
            
            System.out.println("======================================");
            System.out.println("               ORDENADO            ");
            System.out.println("======================================");

            metodos.imprimirResultados(metodos, vetor_bubble, vetor_selection, vetor_merge, vetor_Quick);
            
            
            System.out.println("======================================");
            System.out.println("               REVERSO            "  );
            System.out.println("======================================");            
            
            metodos.reverterVetor(vetor_bubble);
            metodos.reverterVetor(vetor_selection);
            metodos.reverterVetor(vetor_merge);
            metodos.reverterVetor(vetor_Quick); 

            metodos.imprimirResultados(metodos, vetor_bubble, vetor_selection, vetor_merge, vetor_Quick);
        }
    }
}