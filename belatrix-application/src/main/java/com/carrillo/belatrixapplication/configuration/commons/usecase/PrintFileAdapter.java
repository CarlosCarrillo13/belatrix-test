package com.carrillo.belatrixapplication.configuration.commons.usecase;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.services.Service;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class PrintFileAdapter {
	
private final Service printFileService;
	
	public void printFile(MessageContext messageContext) {
		printFileService.execute(messageContext);
	}

}
