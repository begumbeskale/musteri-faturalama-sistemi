package com.staj.faturalama.service;

import com.staj.faturalama.entity.BillingAccount;
import com.staj.faturalama.entity.Invoice;
import com.staj.faturalama.repository.BillingAccountRepository;
import com.staj.faturalama.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor

public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BillingAccountRepository billingAccountRepository;

    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
         return invoiceRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Fatura bulunamadı! ID: " +id));
    }

    public Invoice createInvoice(Long billingAccountId, Invoice invoice){
        BillingAccount billingAccount = billingAccountRepository.findById(billingAccountId)
                .orElseThrow(() -> new RuntimeException("Faturalama hesabı bulunamadı ID: " + billingAccountId));

        invoice.setBillingAccount(billingAccount);

        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        Invoice existingInvoice = getInvoiceById(id);

        existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
        existingInvoice.setAmount(updatedInvoice.getAmount());
        existingInvoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
        existingInvoice.setDueDate(updatedInvoice.getDueDate());
        existingInvoice.setPaid(updatedInvoice.isPaid());

        if(updatedInvoice.getFilePath() !=null) {
            existingInvoice.setFilePath(updatedInvoice.getFilePath());
        }
        return invoiceRepository.save(existingInvoice);
    }

    public void deleteInvoice(Long id){
        Invoice invoice = getInvoiceById(id);
        invoiceRepository.delete(invoice);
    }

    public Invoice uploadInvoiceFile(Long id, MultipartFile file) throws IOException {
        Invoice invoice = getInvoiceById(id);

        String uploadDir = "uploads/invoices/";
        Files.createDirectories(Paths.get(uploadDir));

        String fileName = id + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        invoice.setFilePath(filePath.toString());
        return invoiceRepository.save(invoice);
    }
}