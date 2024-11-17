package servicios;

import java.util.List;

public class PromedioServicioImp {
    // Método para calcular el promedio de una lista de notas
    public double calcularPromedio(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0; // Retorna 0 si no hay notas
        }
        double suma = 0.0;
        for (double nota : notas) {
            suma += nota; // Suma todas las notas
        }
        return suma / notas.size(); // Retorna el promedio
    }
}
