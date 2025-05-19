package com.mx.cuentas.dao;

import com.mx.cuentas.entidad.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaDao extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByIdBanco(long idBanco);
    List<Cuenta> findByCurpCliente(String curpCliente);
}
