package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.dto.SupplierData;
import com.domain.models.entity.Supplier;
import com.domain.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/data-tiket")
public class SupplierControllers {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    // Create a new supplier
    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    // Get all suppliers
    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    // Get a single supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findOne(@PathVariable("id") Long id) {
        Supplier supplier = supplierService.findOne(id);
        if (supplier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(supplier);
    }

    // Update a supplier
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Supplier>> update(@PathVariable("id") Long id, @Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier existingSupplier = supplierService.findOne(id);
        if (existingSupplier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        supplier.setId(id); // Ensure the ID is set for updating the correct entity
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }
}
