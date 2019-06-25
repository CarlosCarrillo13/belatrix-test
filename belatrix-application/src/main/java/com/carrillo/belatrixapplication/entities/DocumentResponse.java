package com.carrillo.belatrixapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentResponse {

	private String hashtagFilteredDocument;
	
	private String mentionFilteredDocument;
	
	private String properNamesFilteredDocument;
	
	private String pageName;
	
	private int documentCounter;
}
