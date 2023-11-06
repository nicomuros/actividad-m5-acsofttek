package com.softtek.m5.repositorio;

import com.softtek.m5.modelos.entidades.Tarea;

/**
 * Esta interfaz sirve como una abstracción de las operaciones de acceso a datos y no depende de una implementación
 * concreta de la base de datos. Esto significa que si en el futuro se decide cambiar JpaRepository por otra implementación
 * (por ejemplo JDBC) se pueda hacer sin mayores dificultades
 */
public interface TareaDao {

    /**
     * Método para crear una entidad tipo Tarea en la base de datos.
     *
     * @param tarea entidad a ser cargada en la base de datos
     */
    void crearTarea(Tarea tarea);
}
