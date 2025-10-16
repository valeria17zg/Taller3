package co.edu.uniquindio.fx10.repositories;

import co.edu.uniquindio.fx10.models.Inmueble;

import java.util.ArrayList;

/**
 * Repositorio centralizado para gestionar los productos
 * Singleton para garantizar una única instancia en toda la aplicación
 */
public class InmuebleRepository {
    private static InmuebleRepository instancia;
    private ArrayList<Inmueble> inmuebles;

    private InmuebleRepository() {
        inmuebles = new ArrayList<>();
        cargarDatosEjemplo();
    }

    /**
     * Obtiene la instancia única del repositorio
     */
    public static InmuebleRepository getInstancia() {
        if (instancia == null) {
            instancia = new InmuebleRepository();
        }
        return instancia;
    }

    /**
     * Carga algunos productos de ejemplo
     */
    private void cargarDatosEjemplo() {
        inmuebles.add(new Inmueble("casa", "Armenia", "4", "2", 2000000));
        inmuebles.add(new Inmueble("apartamento", "Cali", "3", "1", 1500000));
        inmuebles.add(new Inmueble("finca", "Manizales", "5", "2", 4000000));
        inmuebles.add(new Inmueble("local", "Armenia", "2", "1", 1650000));

    }


    public ArrayList<Inmueble> getInmuebles() {

        return inmuebles;
    }

    /**
     * Agrega un nuevo producto
     */
    public void agregarInmueble(Inmueble inmueble) {

        inmuebles.add(inmueble);

    }


    public boolean eliminarInmueble(Inmueble inmueble) {

        return inmuebles.remove(inmueble);

    }


    public int getCantidadInmuebles() {
        return inmuebles.size();
    }
}

