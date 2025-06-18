package org.lukawska.openfeign.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id, String username) {
}
