package com.carrillo.belatrixapplication.entities;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ParserFilter {
	
	private boolean mention;
	private boolean properName;
	private boolean hashtag;

}
