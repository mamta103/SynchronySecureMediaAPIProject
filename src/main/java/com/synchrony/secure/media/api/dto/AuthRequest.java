package com.synchrony.secure.media.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AuthRequest(String username, String password) {}
