package com.mx.cuentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    private String mensaje;
    private boolean success;
    private Object obj;
    private LocalDateTime fecha;
}
