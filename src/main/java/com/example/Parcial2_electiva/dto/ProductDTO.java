package com.example.Parcial2_electiva.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductDTO implements Serializable {
    public String name;
    public String description;
    public Integer price;
}
