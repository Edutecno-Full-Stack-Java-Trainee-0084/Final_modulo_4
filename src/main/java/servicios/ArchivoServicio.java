package servicios;

import modelos.Alumno;
import modelos.Materia;
import java.io.*;
import java.util.List;
import java.util.Map;

public class ArchivoServicio {
    public void cargarDatos(File archivo, AlumnoServicio alumnoServicio) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Alumno alumno = new Alumno(datos[0], datos[1], datos[2], datos[3]);
                alumnoServicio.crearAlumno(alumno);

            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public void exportarDatos(List<Alumno> alumnos, String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Alumno alumno : alumnos) {
                writer.write("Alumno: " + alumno.getRut() + " - " + alumno.getNombre());
                for (Materia materia : alumno.getMaterias()) {
                    double promedio = calcularPromedio(materia.getNotas());
                    writer.write(", Materia: " + materia.getNombre() + ", Promedio: " + promedio);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }

    private double calcularPromedio(List<Double> notas) {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
