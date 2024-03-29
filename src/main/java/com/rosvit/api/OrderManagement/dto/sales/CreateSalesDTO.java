package com.rosvit.api.OrderManagement.dto.sales;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSalesDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8170542720216362052L;

    @NotNull(message = "The value can't be null")
    private Double value;
    private Boolean paid;
    private String description;
    private Boolean delivery;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
