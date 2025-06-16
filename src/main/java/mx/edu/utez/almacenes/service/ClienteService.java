package mx.edu.utez.almacenes.service;

import mx.edu.utez.almacenes.dto.ClienteRequestDto;
import mx.edu.utez.almacenes.exception.DuplicateResourceException;
import mx.edu.utez.almacenes.exception.ResourceNotFoundException;
import mx.edu.utez.almacenes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.almacenes.dto.ClienteResponseDto;
import mx.edu.utez.almacenes.models.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository clientRepository;

    public ClienteResponseDto createClient(ClienteRequestDto request) {
        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Client", "email", request.getEmail());
        }

        if (clientRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Client", "phone number", request.getPhoneNumber());
        }

        Cliente client = Cliente.builder()
                .nombre_completo(request.getFullName().trim())
                .numero_tel(request.getPhoneNumber().trim())
                .email(request.getEmail().toLowerCase().trim())
                .build();

        Cliente savedClient = clientRepository.save(client);
        return mapToResponseDTO(savedClient);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteResponseDto getClientById(Long id) {
        Cliente client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        return mapToResponseDTO(client);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDto getClientByEmail(String email) {
        Cliente client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "email", email));
        return mapToResponseDTO(client);
    }

    public ClienteResponseDto updateClient(Long id, ClienteRequestDto request) {
        Cliente existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));

        if (!existingClient.getEmail().equals(request.getEmail())) {
            if (clientRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateResourceException("Client", "email", request.getEmail());
            }
        }

        if (!existingClient.getNumero_tel().equals(request.getPhoneNumber())) {
            if (clientRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new DuplicateResourceException("Client", "phone number", request.getPhoneNumber());
            }
        }

        existingClient.setNombre_completo(request.getFullName().trim());
        existingClient.setNumero_tel(request.getPhoneNumber().trim());
        existingClient.setEmail(request.getEmail().toLowerCase().trim());

        Cliente updatedClient = clientRepository.save(existingClient);
        return mapToResponseDTO(updatedClient);
    }


    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", "id", id);
        }
        clientRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDto> searchClientsByName(String name) {
        return clientRepository.findByFullNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ClienteResponseDto mapToResponseDTO(Cliente client) {
        return ClienteResponseDto.builder()
                .id(client.getId())
                .fullName(client.getNombre_completo())
                .phoneNumber(client.getNumero_tel())
                .email(client.getEmail())
                .build();
    }
}
