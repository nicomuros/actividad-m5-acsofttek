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

    @DeleteMapping("{tareaId}")
    public void eliminarTarea(
            @PathVariable Integer tareaId
    ){
        tareaServicio.eliminarTarea(tareaId);
    }

    @PutMapping("{tareaId}")
    public void modificarTarea(
            @PathVariable Integer tareaId,
            @RequestBody TareaRequestDTO tareaRequestDTO
    ){
        tareaServicio.modificarTarea(tareaId, tareaRequestDTO);
    }
}
