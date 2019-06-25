package com.carrillo.belatrixapplication.configuration.commons.pipeline;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

public interface Pipeline {
	void execute(MessageContext messageContext) throws MessageContextException;
}
