package com.softtek.m5.modelos.mapper;

import com.softtek.m5.modelos.dto.TareaRequestDTO;
import com.softtek.m5.modelos.entidades.Tarea;
import org.springframework.stereotype.Component;

/**
 * Clase TareaMapper: Utilizada para convertir entre objetos Tarea, TareaRequestDTO y TareaResponseDTO.
 */
@Component // Se etiqueta como componente para que entre al pool de beans de Spring
public class TareaMapper {

    /**
     * Convierte un objeto TareaRequestDTO en un objeto Tarea.
     * @param requestDTO El objeto TareaRequestDTO a convertir.
     * @return El objeto Tarea resultante.
     */
    public Tarea DtoATarea(TareaRequestDTO requestDTO){
        Tarea tarea = new Tarea();

        tarea.setTitulo(requestDTO.getTitulo());
        tarea.setDescripcion(requestDTO.getDescripcion());
        tarea.setFechaFinalizacion(requestDTO.getFechaFinalizacion());
        tarea.setTerminada(requestDTO.isTerminada());
        tarea.setDisponible(requestDTO.isDisponible());

        return tarea;
    }
}
