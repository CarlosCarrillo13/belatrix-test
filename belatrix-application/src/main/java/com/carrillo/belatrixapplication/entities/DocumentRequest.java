package com.carrillo.belatrixapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentRequest {

	private String path;
	private ParserFilter filter;
}
