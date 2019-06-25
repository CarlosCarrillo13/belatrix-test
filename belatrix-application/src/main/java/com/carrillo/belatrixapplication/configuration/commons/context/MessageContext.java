package com.carrillo.belatrixapplication.configuration.commons.context;

import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

public interface MessageContext<I> {

	void addItem(I key, Object item);
	
	Object getitem(I key, Class expectedClass) throws MessageContextException;
	
	void clear();
}
