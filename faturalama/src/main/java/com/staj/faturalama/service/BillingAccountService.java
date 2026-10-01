package com.staj.faturalama.service;

import com.staj.faturalama.entity.BillingAccount;
import com.staj.faturalama.entity.BillingAddress;
import com.staj.faturalama.entity.Customer;
import com.staj.faturalama.repository.BillingAccountRepository;
import com.staj.faturalama.repository.BillingAddressRepository;
import com.staj.faturalama.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BillingAccountService {
    private final BillingAccountRepository billingAccountRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final CustomerRepository customerRepository;

    public List<BillingAccount> getAllAccounts(){
        return billingAccountRepository.findAll();
    }

    public BillingAccount getAccountById(Long id){
        return billingAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faturalama hesabı bulunamadı ID: "+ id));
    }
    public BillingAccount createAccount(Long customerId, BillingAccount account, BillingAddress address) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı ID: " + customerId));

        BillingAddress savedAddress = billingAddressRepository.save(address);

        account.setCustomer(customer);
        account.setBillingAddress(savedAddress);

        return billingAccountRepository.save(account);
    }

    public BillingAccount updateAccount(Long id, BillingAccount updatedAccount){
        BillingAccount existingAccount = getAccountById(id);
        existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
        return billingAccountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        BillingAccount account = getAccountById(id);
        billingAccountRepository.delete(account);
    }
}
