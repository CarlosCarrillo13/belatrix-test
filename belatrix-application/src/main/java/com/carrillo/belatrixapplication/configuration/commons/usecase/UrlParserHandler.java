package com.carrillo.belatrixapplication.configuration.commons.usecase;

import org.springframework.stereotype.Service;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.RequiredArgsConstructor;

@Service("urlParserHandler")
@RequiredArgsConstructor
public class UrlParserHandler implements Handler {

	private final UrlParserAdapter urlParserAdapter;
	
	@Override
	public void execute(MessageContext messageContext) throws MessageContextException {
		urlParserAdapter.parseUrl(messageContext);

	}

}
