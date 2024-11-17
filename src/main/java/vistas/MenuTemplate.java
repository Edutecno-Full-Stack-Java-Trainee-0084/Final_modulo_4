package vistas;

import java.util.Scanner;

public abstract class MenuTemplate {
    protected Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        while (true) {
            System.out.println("1. Cargar Archivo");
            System.out.println("2. Crear Alumnos");
            System.out.println("3. Listar Alumnos");
            System.out.println("4. Agregar Materias");
            System.out.println("5. Agregar Notas");
            System.out.println("6. Exportar Datos");
            System.out.println("7. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1: cargarArchivo(); break;
                case 2: crearAlumno(); break;
                case 3: listarAlumnos(); break;
                case 4: agregarMateria(); break;
                case 5: agregarNotaPasoUno(); break;
                case 6: exportarDatos(); break;
                case 7: terminarPrograma(); return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    protected abstract void cargarArchivo();
    protected abstract void crearAlumno();
    protected abstract void listarAlumnos();
    protected abstract void agregarMateria();
    protected abstract void agregarNotaPasoUno();
    protected abstract void exportarDatos();
    protected abstract void terminarPrograma();
}
