package com.nouhaila.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class CustomersDTO {
    private Long id;
    private String name;
   private String email;

}