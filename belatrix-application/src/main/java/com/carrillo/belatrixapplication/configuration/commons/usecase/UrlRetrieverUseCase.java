package com.carrillo.belatrixapplication.configuration.commons.usecase;

import org.springframework.stereotype.Service;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.pipeline.Pipeline;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlRetrieverUseCase implements UseCase {

	private final Pipeline retrieveUrlPipeline; 
	@Override
	public void handle(MessageContext messageContext) {
		try {
			retrieveUrlPipeline.execute(messageContext);
		} catch (MessageContextException e) {
			e.printStackTrace();
		}

	}

}
