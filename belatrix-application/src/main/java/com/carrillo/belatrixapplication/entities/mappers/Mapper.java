package com.carrillo.belatrixapplication.entities.mappers;

public interface Mapper<I, O> {
	
	O map(I input);
}
