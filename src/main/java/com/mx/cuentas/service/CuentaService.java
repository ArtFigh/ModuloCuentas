package com.mx.cuentas.service;

import com.mx.cuentas.dao.CuentaDao;
import com.mx.cuentas.dto.Respuesta;
import com.mx.cuentas.entidad.Cuenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuentaService {

    private CuentaDao cuentaDao;

    public CuentaService(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    public ResponseEntity<List<Cuenta>> getAllCuentas(){
        if (cuentaDao.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cuentaDao.findAll());
    }

    public Respuesta guardar(Cuenta cuenta){
        Respuesta rs = new Respuesta();
        try {
            if (cuentaDao.existsById(cuenta.getNumeroCuenta())){
                rs.setMensaje("La cuenta ya existe");
                rs.setSuccess(false);
                rs.setObj(cuenta);
                rs.setFecha(LocalDateTime.now());
                return rs;
            }
            cuentaDao.save(cuenta);
            rs.setMensaje("La cuenta ha sido guardada");
            rs.setSuccess(true);
            rs.setObj(cuenta);
            rs.setFecha(LocalDateTime.now());
            return rs;
        } catch (Exception e) {
            rs.setMensaje(e.getMessage());
            rs.setSuccess(false);
            rs.setObj(cuenta);
            rs.setFecha(LocalDateTime.now());
            return rs;
        }
    }

    public Respuesta editar(Cuenta cuenta){
        Respuesta rs = new Respuesta();
        try {
            if (cuentaDao.existsById(cuenta.getNumeroCuenta())){
                cuentaDao.save(cuenta);
                rs.setMensaje("La cuenta ha sido editada");
                rs.setSuccess(true);
                rs.setObj(cuenta);
                rs.setFecha(LocalDateTime.now());
                return rs;
            }
            rs.setMensaje("La cuenta no existe");
            rs.setSuccess(false);
            rs.setObj(cuenta);
            rs.setFecha(LocalDateTime.now());
            return rs;
        } catch (Exception e) {
            rs.setMensaje(e.getMessage());
            rs.setSuccess(false);
            rs.setObj(cuenta);
            rs.setFecha(LocalDateTime.now());
            return rs;
        }
    }

    public Respuesta eliminar(Cuenta cuenta){
        Respuesta rs = new Respuesta();
        try {
            if (cuentaDao.existsById(cuenta.getNumeroCuenta())){
                cuenta = cuentaDao.findById(cuenta.getNumeroCuenta()).orElse(null);
                rs.setObj(new Cuenta(cuenta.getNumeroCuenta(),
                        cuenta.getSaldo(),
                        cuenta.getAdeudo(),
                        cuenta.getTipo(),
                        null,
                        0));
                cuentaDao.delete(cuenta);
                rs.setMensaje("La cuenta ha sido eliminada");
                rs.setSuccess(true);
                rs.setObj(cuenta);
                rs.setFecha(LocalDateTime.now());
                return rs;
            }
            rs.setMensaje("La cuenta no existe");
            rs.setSuccess(false);
            rs.setObj(cuenta);
            rs.setFecha(LocalDateTime.now());
            return rs;
        } catch (Exception e) {
            rs.setMensaje(e.getMessage());
            rs.setSuccess(false);
            rs.setObj(cuenta);
            rs.setFecha(LocalDateTime.now());
            return rs;
        }
    }

    public ResponseEntity<Cuenta> getCuenta(long numeroCuenta){
        Cuenta cuenta = cuentaDao.findById(numeroCuenta).orElse(null);
        if (cuenta == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cuenta);
    }

    public ResponseEntity<List<Cuenta>> buscarPorCurp(String curp){
        List<Cuenta> cuentas = cuentaDao.findByCurpCliente(curp);
        if (cuentas.isEmpty() || cuentas == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cuentas);
    }

    public ResponseEntity<List<Cuenta>> buscarPorIdBanco(int idBanco){
        List<Cuenta> cuentas = cuentaDao.findByIdBanco(idBanco);
        if (cuentas.isEmpty() || cuentas == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cuentas);
    }

}
