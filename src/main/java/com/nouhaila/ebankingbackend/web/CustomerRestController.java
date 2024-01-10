package com.nouhaila.ebankingbackend.web;

import com.nouhaila.ebankingbackend.dtos.CustomersDTO;
import com.nouhaila.ebankingbackend.exceptions.CustomerNotFoundException;
import com.nouhaila.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CustomerRestController {
    @Autowired
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER')")
    public List<CustomersDTO> customers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customers/search")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER')")

    public List<CustomersDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER')")

    public CustomersDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
    return bankAccountService.getCustomer(customerId);
    }
    @PostMapping("/customers")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")

    public CustomersDTO saveCustomer(@RequestBody CustomersDTO customersDTO){
    return bankAccountService.saveCustomer(customersDTO);

    }
    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")

    public CustomersDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomersDTO customersDTO){
        customersDTO.setId(customerId);
        return bankAccountService.updateCustomer(customersDTO);
    }
    @DeleteMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")

    public void deleteCustomer(@PathVariable Long customerId){
        bankAccountService.deleteCustomer(customerId);
    }
}
