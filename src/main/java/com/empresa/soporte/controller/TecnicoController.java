package com.empresa.soporte.controller;

import com.empresa.soporte.dto.ApiResponse;
import com.empresa.soporte.dto.TecnicoDTO;
import com.empresa.soporte.exception.ResourceNotFoundException;
import com.empresa.soporte.model.Tecnico;
import com.empresa.soporte.persistence.InMemoryDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST auxiliar para gestionar Técnicos de prueba en el sistema.
 * Permite interactuar con los datos simulados de técnicos y su disponibilidad.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/tecnicos")
public class TecnicoController {

    private final InMemoryDatabase database;

    public TecnicoController(InMemoryDatabase database) {
        this.database = database;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TecnicoDTO>>> getAll() {
        List<TecnicoDTO> tecnicos = database.findAllTecnicos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        ApiResponse<List<TecnicoDTO>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Listado de técnicos recuperado con éxito.",
                tecnicos
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TecnicoDTO>> getById(@PathVariable Long id) {
        Tecnico tecnico = database.findTecnicoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con el ID: " + id));
        ApiResponse<TecnicoDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Técnico recuperado con éxito.",
                convertToDTO(tecnico)
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TecnicoDTO>> create(@Valid @RequestBody TecnicoDTO dto) {
        Tecnico tecnico = new Tecnico(null, dto.getNombre(), dto.getEspecialidad(), dto.isDisponible());
        Tecnico guardado = database.saveTecnico(tecnico);
        ApiResponse<TecnicoDTO> response = ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Técnico creado con éxito en persistencia local.",
                convertToDTO(guardado)
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!database.findTecnicoById(id).isPresent()) {
            throw new ResourceNotFoundException("No se puede eliminar el técnico porque no existe con el ID: " + id);
        }
        database.deleteTecnico(id);
        return ResponseEntity.noContent().build();
    }

    private TecnicoDTO convertToDTO(Tecnico tecnico) {
        return new TecnicoDTO(tecnico.getId(), tecnico.getNombre(), tecnico.getEspecialidad(), tecnico.isDisponible());
    }
}
