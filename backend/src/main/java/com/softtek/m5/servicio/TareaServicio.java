package com.softtek.m5.servicio;

import com.softtek.m5.excepciones.DatosInvalidosException;
import com.softtek.m5.modelos.dto.TareaRequestDTO;
import com.softtek.m5.modelos.entidades.Tarea;
import com.softtek.m5.modelos.mapper.TareaMapper;
import com.softtek.m5.repositorio.TareaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TareaServicio {
    TareaDao tareaDao;

    TareaMapper tareaMapper;

    @Autowired
    public TareaServicio(TareaDao tareaDao, TareaMapper tareaMapper){
        this.tareaDao = tareaDao;
        this.tareaMapper = tareaMapper;
    }

    /**
     * Método para gestionar la creación de la tarea. Se encarga de realizar las validaciones necesarias, crear la entidad
     * Tarea y solicitarle al Dao que la agregue a la base de datos.
     * @param tareaRequestDTO
     */
    public void crearTarea(TareaRequestDTO tareaRequestDTO){

        // Obtener fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Validar datos
        validarNuevaTarea(tareaRequestDTO, fechaActual);

        // Conversión de DTO a Tarea
        Tarea tarea = tareaMapper.DtoATarea(tareaRequestDTO);

        // Se agrega la fecha de creación
        tarea.setFechaCreacion(fechaActual);

        // Se configura como disponible al crearse la tarea
        tarea.setDisponible(true);

        // Se agrega al repositorio
        tareaDao.crearTarea(tarea);
    }

    /**
     * Este método se encarga de validar el DTO de una solicitud de nueva tarea. Realiza las siguientes validaciones:
     * <ol>
     *     <li>El <b>título</b> no debe ser nulo y debe contener entre 3 y 15 caracteres</li>
     *     <li>La <b>descripción</b> no debe ser nula y debe contener entre 3 y 50 caracteres</li>
     *     <li>La <b>fecha de finalización</b> no debe ser nula ni debe ser previa a la fecha de creación</li>
     * </ol>
     *
     * @param tareaRequestDTO
     * @param fechaActual
     */
    private void validarNuevaTarea(TareaRequestDTO tareaRequestDTO, LocalDate fechaActual){

        if (tareaRequestDTO.getTitulo().isBlank())
            throw new DatosInvalidosException("No se proporcionó un título.");

        if (tareaRequestDTO.getDescripcion().isBlank())
            throw new DatosInvalidosException("No se proporcionó una descripción.");

        if (tareaRequestDTO.getFechaFinalizacion() == null)
            throw new DatosInvalidosException("No se proporcionó una fecha de finalización");

        if (tareaRequestDTO.getFechaFinalizacion().isBefore(fechaActual))
            throw new DatosInvalidosException("La fecha de finalización es anterior a la fecha del servidor");

        if (tareaRequestDTO.getTitulo().length() < 3 || tareaRequestDTO.getTitulo().length() > 15)
            throw new DatosInvalidosException("El título debe contener entre 3 y 15 caracteres");

        if (tareaRequestDTO.getDescripcion().length() < 3 || tareaRequestDTO.getDescripcion().length() > 50)
            throw new DatosInvalidosException("La deescripción debe contener entre 3 y 50 caracteres");
    }
}
