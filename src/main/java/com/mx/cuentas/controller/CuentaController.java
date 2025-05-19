package com.mx.cuentas.controller;

import com.mx.cuentas.dto.Respuesta;
import com.mx.cuentas.entidad.Cuenta;
import com.mx.cuentas.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cuentas")
@CrossOrigin
public class CuentaController {
    @Autowired
    CuentaService cuentaService;

    @GetMapping("listar")
    public ResponseEntity<List<Cuenta>> listar(){
        return cuentaService.getAllCuentas();
    }

    @PostMapping("guardar")
    public Respuesta guardar(@RequestBody Cuenta cuenta){
        return cuentaService.guardar(cuenta);
    }

    @PutMapping("editar")
    public Respuesta editar(@RequestBody Cuenta cuenta){
        return cuentaService.editar(cuenta);
    }

    @DeleteMapping("eliminar")
    public Respuesta eliminar(@RequestBody Cuenta cuenta){
        return cuentaService.eliminar(cuenta);
    }

    @GetMapping("buscarNumeroCuenta")
    public ResponseEntity<Cuenta> buscar(@RequestParam("numeroCuenta") long numeroCuenta){
        return cuentaService.getCuenta(numeroCuenta);
    }

    @GetMapping("buscarCurp")
    public ResponseEntity<List<Cuenta>> buscarCurp(@RequestParam("curp") String curp){
        return cuentaService.buscarPorCurp(curp);
    }

    @GetMapping("buscarIdBanco")
    public ResponseEntity<List<Cuenta>> buscarIdBanco(@RequestParam("idBanco") int idBanco){
        return cuentaService.buscarPorIdBanco(idBanco);
    }
}
