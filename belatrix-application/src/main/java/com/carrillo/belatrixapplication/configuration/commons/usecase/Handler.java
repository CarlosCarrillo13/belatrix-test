package com.carrillo.belatrixapplication.configuration.commons.usecase;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

public interface Handler {
	
	void execute (MessageContext messageContext)throws MessageContextException;

}
