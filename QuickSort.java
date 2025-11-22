import java.io.*;
import java.util.*;

public class QuickSort {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa la ruta del archivo de entrada:");
        String archivoEntrada = scanner.nextLine();

        List<Integer> numeros = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
            String linea;

            while ((linea = br.readLine()) != null) {
                for (String num : linea.split("\\s+")) {
                    if (!num.isEmpty()) {
                        numeros.add(Integer.parseInt(num));
                    }
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        // ------------------------------------------------------
        //  AQUÍ VA LA PARTE QUE COLOCASTE FUERA DEL MÉTODO
        // ------------------------------------------------------

        System.out.println("Ingresa la ruta donde quieres guardar el archivo ordenado:");
        String archivoSalida = scanner.nextLine();

        int[] arreglo = numeros.stream().mapToInt(i -> i).toArray();

        quickSort(arreglo, 0, arreglo.length - 1);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));
            for (int n : arreglo) {
                bw.write(n + "\n");
            }
            bw.close();

            System.out.println("Archivo ordenado creado exitosamente: " + archivoSalida);

        } catch (Exception e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    // ------------------------------------------------------
    //               MÉTODOS QUICKSORT
    // ------------------------------------------------------

    public static void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int indiceParticion = particion(arr, inicio, fin);

            quickSort(arr, inicio, indiceParticion - 1);
            quickSort(arr, indiceParticion + 1, fin);
        }
    }

    private static int particion(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;

        return i + 1;
    }
}