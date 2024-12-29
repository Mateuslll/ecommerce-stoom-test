package com.teste.stoom.ecommerce.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BrandResponse {

    private String id;
    private String name;
    private String logoUrl;
    private Boolean isActive;
}
