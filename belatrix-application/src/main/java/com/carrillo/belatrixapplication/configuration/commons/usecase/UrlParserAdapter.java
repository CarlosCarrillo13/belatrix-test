package com.carrillo.belatrixapplication.configuration.commons.usecase;


import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.services.Service;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UrlParserAdapter {

	private final Service urlParserService;
	
	public void parseUrl(MessageContext messageContext) {
		urlParserService.execute(messageContext);
	}
}
