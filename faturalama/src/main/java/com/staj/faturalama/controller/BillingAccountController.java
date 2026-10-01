package com.staj.faturalama.controller;

import com.staj.faturalama.entity.BillingAccount;
import com.staj.faturalama.entity.BillingAddress;
import com.staj.faturalama.service.BillingAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BillingAccountController {

    private final BillingAccountService billingAccountService;

    @GetMapping
    public ResponseEntity<List<BillingAccount>> getAllAccounts(){
        return ResponseEntity.ok(billingAccountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingAccount> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(billingAccountService.getAccountById(id));
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<BillingAccount> createAccount(@PathVariable Long customerId, @RequestBody BillingAccount account){
        BillingAddress address = account.getBillingAddress();
        BillingAccount savedAccount = billingAccountService.createAccount(customerId, account, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillingAccount> updateAccount(@PathVariable Long id, @RequestBody BillingAccount account){
        return ResponseEntity.ok(billingAccountService.updateAccount(id, account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        billingAccountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
