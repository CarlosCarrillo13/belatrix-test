package com.carrillo.belatrixapplication.configuration.commons.usecase;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;

public interface UseCase {

	void handle(MessageContext messageContext);
}
