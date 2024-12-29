package com.teste.stoom.ecommerce.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageFilter {
	private Integer page;
	@Max(value = 20, message = "pageSize cannot exceed the limit: 20\"")
	private Integer pageSize;
	private String search;
	private Boolean flag;

	public Integer getPage() {
		if(page == null)
			return 0;

		return page;
	}

	public Integer getPageSize() {
		if(pageSize == null)
			return 10;

		return pageSize;
	}
}
