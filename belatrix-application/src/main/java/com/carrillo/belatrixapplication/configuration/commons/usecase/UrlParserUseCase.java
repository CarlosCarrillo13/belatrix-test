package com.carrillo.belatrixapplication.configuration.commons.usecase;

import org.springframework.stereotype.Service;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.pipeline.Pipeline;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlParserUseCase implements UseCase {

	private final Pipeline parseUrlPipeline; 
	
	@Override
	public void handle(MessageContext messageContext) {
		try {
			parseUrlPipeline.execute(messageContext);
		} catch (MessageContextException e) {
			e.printStackTrace();
		}

	}

}
