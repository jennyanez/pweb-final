package cu.edu.cujae.pweb.utils;

import java.util.List;

public interface ServiceImplementation {
    /*** metodo creado para que los metodos a implementar
     * en los servicios se guien por una sola interfaz y no tener
     * tantas interfaces repetidas que hacen basicamente lo mismo
     */

    //Metodo para obtener todos los registros de la tabla
    <T> List<T> getAll();

    //Metodo para obtener un registro de la tabla
    Object getById(Long id);

    //Metodo para insertar un registro en la tabla
    String create(Object dto);

    //Metodo para actualizar un registro en la tabla
    String update(Object dto);

    //Metodo para eliminar un registro de la tabla
    String delete(Long id);
}
