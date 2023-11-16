/**
 * Este archivo contiene las funciones que se encargan de hacer las peticiones al servidor.
 * Para ello, importamos el cliente de axios que configuramos anteriormente en el archivo 
 * src/services/axios.js
 * 
*/
import axios from "./axios.js"; // Importamos el cliente de axios que configuramos anteriormente.

// Solicitud GET para obtener la lista de todas las tareas
export const getTareasRequest = () => axios.get("api/tarea");

// Solicitud POST para crear una nueva tarea
export const createTareaRequest = (tarea) => axios.post("api/tarea", tarea);

// Solicitud PUT para actualizar una tarea. Puede enviar solo los campos que se desean actualizar.
export const updateTareaRequest = (id, tarea) => axios.put(`api/tarea/${id}`, tarea);

// Solicitud DELETE para eliminar una tarea
export const deleteTareaRequest = (id) => axios.delete(`api/tarea/${id}`)