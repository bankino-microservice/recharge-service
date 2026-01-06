package com.marouane.rechargeservice.model.dto;
import com.marouane.rechargeservice.model.enumeration.Operator;
import com.marouane.rechargeservice.validation.MoroccanPhone;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeRequestDTO {

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @MoroccanPhone
    private String phoneNumber;
    @Min(value = 1, message = "Offre ID must be at least 1")
    @Max(value = 99, message = "Offre ID must be at most 99")
    private Integer offreId;
    @NotNull(message = "Operator is required")
    private Operator operator;
    @NotNull(message = "Amount is required")
    @Pattern(regexp = "5|10|20|30|50|100|200|500", message = "Montant invalide. Autorisé : 5,10,20,30,50,100,200,500")
    private String amount;
}
