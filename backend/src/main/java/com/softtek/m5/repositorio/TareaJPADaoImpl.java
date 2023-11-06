package com.softtek.m5.repositorio;

import com.softtek.m5.modelos.entidades.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementación concreta de TareaDao, utiliza JPA para gestionar las transacciones con la base de datos.
 */
@Repository
public class TareaJPADaoImpl implements TareaDao{


    private final TareaRepository tareaRepository;

    @Autowired
    public TareaJPADaoImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public void crearTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }
}
