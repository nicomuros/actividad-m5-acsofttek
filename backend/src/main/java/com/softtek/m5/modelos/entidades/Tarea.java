package com.softtek.m5.modelos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity // Se marca como entidad para que JPA trabaje con ella
@Data // Getters, setters, hashcode, equals, toString
@AllArgsConstructor // Constructor de todos los argumentos
@NoArgsConstructor // Constructor sin argumentos
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion; // Se almacena la fecha en la que la tarea fue creada
    private Date fechaModificacion; // Se almacena la fecha de la última modificación
    private Date fechaFinalizacion; // Se almacena la fecha en la que el usuario elije cuando termina la tarea
    private boolean terminada = false; // true si la tarea se marcó como terminada
    private boolean disponible = true; // true si la tarea no se marcó como eliminada
}
