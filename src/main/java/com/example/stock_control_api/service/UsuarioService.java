package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.usuario.UsuarioRequestDTO;
import com.example.stock_control_api.dto.usuario.UsuarioResponseDTO;
import com.example.stock_control_api.mapper.UsuarioMapper;
import com.example.stock_control_api.model.Usuario;
import com.example.stock_control_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCargo(dto.getCargo());

        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
