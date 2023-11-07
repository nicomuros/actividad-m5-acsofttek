package com.softtek.m5.controlador;

import com.softtek.m5.modelos.dto.TareaRequestDTO;
import com.softtek.m5.modelos.dto.TareaResponseDTO;
import com.softtek.m5.servicio.TareaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarea") // Este controlador solo va a escuchar desde esa ruta en adelante
public class TareaControlador {

    private final TareaServicio tareaServicio;
    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    /**
     * Método para escuchar solicitudes GET en la ruta "/api/tarea". Es capaz de devolver una lista de DTOs de Tarea
     * @return Lista de TareaReponseDTO
     */
    @GetMapping
    public List<TareaResponseDTO> listarTodasLasTareas(){
        return tareaServicio.listarTareas();
    }

    /**
     * Método para escuchar solicitudes de POST en la ruta "/api/tarea". Su finalidad es la de agregar una tarea
     * siempre que se cumpla con los requisitos.
     * @param tareaRequestDTO DTO con los datos necesarios para crear la tarea
     */
    @PostMapping
    public void registrarTarea(@RequestBody TareaRequestDTO tareaRequestDTO){
        tareaServicio.crearTarea(tareaRequestDTO);
    }

    /**
     * Método que escucha solicitudes DELETE en la ruta "/api/tarea". Cambia el estado "disponible" de la tarea de
     * true a false.
     * @param tareaId ID de la tarea a eliminar
     */
    @DeleteMapping("{tareaId}")
    public void eliminarTarea(
            @PathVariable Integer tareaId
    ){
        tareaServicio.eliminarTarea(tareaId);
    }

    /**
     * Método que escucha solicitudes tipo PUT en la ruta "/api/tarea". Es capaz de modificar tanto estados unitarios
     * como todos los estados de la tarea.
     * @param tareaId ID de la tarea a modificar
     * @param tareaRequestDTO Body de la solicitud con los datos a modificar.
     */
    @PutMapping("{tareaId}")
    public void modificarTarea(
            @PathVariable Integer tareaId,
            @RequestBody TareaRequestDTO tareaRequestDTO
    ){
        tareaServicio.modificarTarea(tareaId, tareaRequestDTO);
    }
}
