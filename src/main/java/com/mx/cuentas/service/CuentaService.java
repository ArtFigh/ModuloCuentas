package com.mx.cuentas.service;

import com.mx.cuentas.dao.CuentaDao;
import com.mx.cuentas.entidad.Cuenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuestaService {

    private CuentaDao cuentaDao;

    public CuestaService(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    public ResponseEntity<List<Cuenta>> getAllCuentas(){
        if (cuentaDao.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cuentaDao.findAll());
    }

}
