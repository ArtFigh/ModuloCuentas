package com.mx.cuentas.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUENTAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    @Id
    private long numeroCuenta;
    private double saldo;
    private String adeudo;
    private String tipo;
    private String curpCliente;
    private int idBanco;
}
