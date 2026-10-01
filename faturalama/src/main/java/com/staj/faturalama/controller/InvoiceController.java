package com.staj.faturalama.controller;

import com.staj.faturalama.entity.Invoice;
import com.staj.faturalama.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices(){
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @PostMapping("/account/{billingAccountId}")
    public ResponseEntity<Invoice> createInvoice(@PathVariable("billingAccountId") Long billingAccountId, @RequestBody Invoice invoice){
        Invoice savedInvoice = invoiceService.createInvoice(billingAccountId, invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice){
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<Invoice> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        Invoice updatedInvoice = invoiceService.uploadInvoiceFile(id, file);
        return ResponseEntity.ok(updatedInvoice);
    }
}

