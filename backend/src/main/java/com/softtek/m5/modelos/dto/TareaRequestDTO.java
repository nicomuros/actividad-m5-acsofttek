package com.softtek.m5.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Objeto de Transferencia de Datos (DTO) utilizado para transportar información
 * en las solicitudes de <b>creación</b> y <b>modificación</b> de Tareas.
 * Este DTO contiene datos como el título y la descripción de una Tarea.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaRequestDTO {
    private String titulo;
    private String descripcion;
    private LocalDate fechaFinalizacion; // Se almacena la fecha en la que el usuario elije cuando termina la tarea
    private Boolean terminada; // true si la tarea se marcó como terminada
}
