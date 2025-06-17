package org.lukawska.backend.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id, String username) {
}
