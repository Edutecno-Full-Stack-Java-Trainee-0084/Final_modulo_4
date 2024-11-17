package vistas;

import servicios.AlumnoServicio;
import servicios.ArchivoServicio;
import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;

import java.io.File;
import java.util.List;

public class Menu extends MenuTemplate {
    private AlumnoServicio alumnoServicio = new AlumnoServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();

    @Override
    protected void cargarArchivo() {
        System.out.println("--- Cargar Archivo ---");
        System.out.print("Ingresa la ruta del archivo: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);
        archivoServicio.cargarDatos(archivo, alumnoServicio);
        System.out.println("--- ¡Datos cargados correctamente! ---");
    }

    @Override
    protected void crearAlumno() {
        System.out.println("--- Crear Alumno ---");
        System.out.print("Ingresa RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingresa nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingresa dirección: ");
        String direccion = scanner.nextLine();

        Alumno alumno = new Alumno(rut, nombre, apellido, direccion);
        alumnoServicio.crearAlumno(alumno);
        System.out.println("--- ¡Alumno agregado! ---");
    }

    @Override
    protected void listarAlumnos() {
        System.out.println("--- Listar Alumnos ---");
        List<Alumno> alumnos = alumnoServicio.listarAlumnos();
        for (Alumno alumno : alumnos) {
            System.out.println("RUT: " + alumno.getRut() + ", Nombre: " + alumno.getNombre() + ", Apellido: " + alumno.getApellido());
            for (Materia materia : alumno.getMaterias()) {
                System.out.println("Materia: " + materia.getNombre() + ", Notas: " + materia.getNotas());
            }
        }
    }

    @Override
    protected void agregarMateria() {
        System.out.println("--- Agregar Materia ---");
        System.out.print("Ingresa rut del Alumno: ");
        String rutAlumno = scanner.nextLine();
        System.out.println("Selecciona una materia:");
        for (MateriaEnum materia : MateriaEnum.values()) {
            System.out.println(materia.ordinal() + 1 + ". " + materia);
        }
        int seleccion = scanner.nextInt();
        MateriaEnum materiaSeleccionada = MateriaEnum.values()[seleccion - 1];
        alumnoServicio.agregarMateria(rutAlumno, new Materia(materiaSeleccionada));
        System.out.println("--- ¡Materia agregada! ---");
    }

    @Override
    protected void agregarNotaPasoUno() {
        System.out.println("--- Agregar Nota ---");
        System.out.print("Ingresa rut del Alumno: ");
        String rutAlumno = scanner.nextLine();
        List<Materia> materias = alumnoServicio.materiasPorAlumnos(rutAlumno);
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).getNombre());
        }
        System.out.print("Selecciona materia: ");
        int seleccion = scanner.nextInt();
        Materia materiaSeleccionada = materias.get(seleccion - 1);
        System.out.print("Ingresa nota: ");
        double nota = scanner.nextDouble();
        materiaSeleccionada.agregarNota(nota);
        System.out.println("--- ¡Nota agregada a " + materiaSeleccionada.getNombre() + "! ---");
    }

    @Override
    protected void exportarDatos() {
        System.out.println("--- Exportar Datos ---");
        System.out.print("Ingresa la ruta para exportar el archivo: ");
        String ruta = scanner.nextLine();
        archivoServicio.exportarDatos(alumnoServicio.listarAlumnos(), ruta);
        System.out.println("Datos exportados correctamente.");
    }

    @Override
    protected void terminarPrograma() {
        System.out.println("Programa terminado.");
    }
}
