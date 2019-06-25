package com.carrillo.belatrixapplication.configuration.commons.usecase;

import org.springframework.stereotype.Service;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.RequiredArgsConstructor;

@Service("urlRetrieverHandler")
@RequiredArgsConstructor
public class UrlRetrieverHandler implements Handler {

	private final UrlRetrieverAdapter urlRetrieverAdapter;
	
	@Override
	public void execute(MessageContext messageContext) throws MessageContextException {
		urlRetrieverAdapter.retrieveUrls(messageContext);

	}

}
