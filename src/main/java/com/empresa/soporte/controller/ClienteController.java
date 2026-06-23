package com.empresa.soporte.controller;

import com.empresa.soporte.dto.ApiResponse;
import com.empresa.soporte.dto.ClienteDTO;
import com.empresa.soporte.exception.ResourceNotFoundException;
import com.empresa.soporte.model.Cliente;
import com.empresa.soporte.persistence.InMemoryDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST auxiliar para gestionar Clientes de prueba en el sistema.
 * Permite interactuar con los datos simulados de clientes.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final InMemoryDatabase database;

    public ClienteController(InMemoryDatabase database) {
        this.database = database;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteDTO>>> getAll() {
        List<ClienteDTO> clientes = database.findAllClientes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        ApiResponse<List<ClienteDTO>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Listado de clientes recuperado con éxito.",
                clientes
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> getById(@PathVariable Long id) {
        Cliente cliente = database.findClienteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID: " + id));
        ApiResponse<ClienteDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Cliente recuperado con éxito.",
                convertToDTO(cliente)
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> create(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNombre(), dto.getEmail(), dto.getTelefono());
        Cliente guardado = database.saveCliente(cliente);
        ApiResponse<ClienteDTO> response = ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Cliente creado con éxito en persistencia local.",
                convertToDTO(guardado)
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!database.findClienteById(id).isPresent()) {
            throw new ResourceNotFoundException("No se puede eliminar el cliente porque no existe con el ID: " + id);
        }
        database.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }
}
