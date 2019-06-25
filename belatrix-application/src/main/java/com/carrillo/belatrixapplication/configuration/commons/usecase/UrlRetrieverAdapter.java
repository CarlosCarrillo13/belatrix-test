package com.carrillo.belatrixapplication.configuration.commons.usecase;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.services.Service;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UrlRetrieverAdapter {
	
private final Service urlRetrieverService;
	
	public void retrieveUrls(MessageContext messageContext) {
		urlRetrieverService.execute(messageContext);
	}

}
