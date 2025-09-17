package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.usuario.UsuarioRequestDTO;
import com.example.stock_control_api.dto.usuario.UsuarioResponseDTO;
import com.example.stock_control_api.model.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCargo(dto.getCargo());
        return usuario;
    }

    public static UsuarioResponseDTO toDTO(Usuario entity) {
        if (entity == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCargo(entity.getCargo());
        dto.setCriadoEm(entity.getCriadoEm());
        return dto;
    }
}
