package com.softtek.m5.controlador;

import com.softtek.m5.modelos.dto.TareaRequestDTO;
import com.softtek.m5.servicio.TareaServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarea") // Este controlador solo va a escuchar desde esa ruta en adelante
public class TareaControlador {

    private final TareaServicio tareaServicio;
    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
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
}
