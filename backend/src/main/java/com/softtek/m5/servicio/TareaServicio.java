package com.softtek.m5.servicio;

import com.softtek.m5.excepciones.DatosInvalidosException;
import com.softtek.m5.modelos.dto.TareaRequestDTO;
import com.softtek.m5.modelos.dto.TareaResponseDTO;
import com.softtek.m5.modelos.entidades.Tarea;
import com.softtek.m5.modelos.mapper.TareaMapper;
import com.softtek.m5.repositorio.TareaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

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
     * @param tareaRequestDTO DTO con los datos de la entidad tarea a ser cargada
     */
    public void crearTarea(TareaRequestDTO tareaRequestDTO){

        // Validar datos
        validarNuevaTarea(tareaRequestDTO);

        // Conversión de DTO a Tarea
        Tarea tarea = tareaMapper.DtoATarea(tareaRequestDTO);

        // Se agrega la fecha de creación
        tarea.setFechaCreacion(LocalDate.now());

        // Se configura como disponible al crearse la tarea
        tarea.setDisponible(true);
        tarea.setTerminada(false);

        // Se agrega al repositorio
        tareaDao.crearTarea(tarea);
    }

    public List<TareaResponseDTO> listarTareas(){
        List<Tarea> listaDeTareas = tareaDao.obtenerTareas()
                .stream()
                .filter(Tarea::getDisponible)
                .toList();
        return tareaMapper.listaTareaAListaDto(listaDeTareas);
    }

    public void modificarTarea(Integer id, TareaRequestDTO tareaRequestDTO){
        Tarea tarea = tareaDao.obtenerTareaPorId(id)
                .orElseThrow(() -> new DatosInvalidosException("No se encontró una tarea con el id: " + id));

        boolean cambio = false;

        if (tareaRequestDTO.getTitulo() != null
                && !tareaRequestDTO.getTitulo().equals(tarea.getTitulo())){
            validarTitulo(tareaRequestDTO.getTitulo());
            tarea.setTitulo(tareaRequestDTO.getTitulo());
            cambio = true;
        }

        if (tareaRequestDTO.getDescripcion() != null
                && !tareaRequestDTO.getDescripcion().equals(tarea.getDescripcion())){
            validarDescripcion(tareaRequestDTO.getDescripcion());
            tarea.setDescripcion(tareaRequestDTO.getDescripcion());
            cambio = true;
        }

        if (tareaRequestDTO.getFechaFinalizacion() != null
                && tareaRequestDTO.getFechaFinalizacion() != tarea.getFechaFinalizacion()){
            validarFechaFinalizacion(tareaRequestDTO.getFechaFinalizacion());
            tarea.setFechaFinalizacion(tareaRequestDTO.getFechaFinalizacion());
            cambio = true;
        }

        if (tareaRequestDTO.getTerminada() != null &&
                !tareaRequestDTO.getTerminada().equals(tarea.getTerminada())){
            tarea.setTerminada(tareaRequestDTO.getTerminada());
            cambio = true;
        }

        if (!cambio) throw new DatosInvalidosException("No se proporcionaron datos modificados");

        tarea.setFechaModificacion(LocalDate.now());
        tareaDao.actualizarTarea(tarea);
    }

    public void eliminarTarea(Integer id){
        Tarea tarea = tareaDao.obtenerTareaPorId(id)
                .orElseThrow(() -> new DatosInvalidosException("No se encontró una tarea con el id: " + id));

        tarea.setDisponible(false);
        tarea.setFechaModificacion(LocalDate.now());
        tareaDao.actualizarTarea(tarea);
    }

    /**
     * Este método se encarga de validar el DTO de una solicitud de nueva tarea. Realiza las siguientes validaciones:
     * <ol>
     *     <li>El <b>título</b> no debe ser nulo y debe contener entre 3 y 15 caracteres</li>
     *     <li>La <b>descripción</b> no debe ser nula y debe contener entre 3 y 50 caracteres</li>
     *     <li>La <b>fecha de finalización</b> no debe ser nula ni debe ser previa a la fecha de creación</li>
     * </ol>
     *
     * @param tareaRequestDTO DTO con los datos a ser validados
     */
    private void validarNuevaTarea(TareaRequestDTO tareaRequestDTO){

        if (tareaRequestDTO.getTitulo().isBlank())
            throw new DatosInvalidosException("No se proporcionó un título.");

        if (tareaRequestDTO.getDescripcion().isBlank())
            throw new DatosInvalidosException("No se proporcionó una descripción.");

        if (tareaRequestDTO.getFechaFinalizacion() == null)
            throw new DatosInvalidosException("No se proporcionó una fecha de finalización");

        validarTitulo(tareaRequestDTO.getTitulo());

        validarDescripcion(tareaRequestDTO.getDescripcion());

        validarFechaFinalizacion(tareaRequestDTO.getFechaFinalizacion());
    }

    private void validarTitulo(String titulo){
        if (titulo.length() < 3 || titulo.length() > 15){
            throw new DatosInvalidosException("El título debe contener entre 3 y 15 caracteres");
        }
    }

    private void validarDescripcion(String descripcion){
        if (descripcion.length() < 3 || descripcion.length() > 50){
            throw new DatosInvalidosException("La deescripción debe contener entre 3 y 50 caracteres");
        }
    }

    private void validarFechaFinalizacion(LocalDate fechaFinalizacion){
        if (fechaFinalizacion.isBefore(LocalDate.now())){
            throw new DatosInvalidosException("La fecha de finalización es anterior a la fecha del servidor");
        }
    }

}
