package com.carrillo.belatrixapplication.configuration.commons.usecase;

import org.springframework.stereotype.Service;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.RequiredArgsConstructor;

@Service("printFileHandler")
@RequiredArgsConstructor
public class PrintFileHandler implements Handler {

	private final PrintFileAdapter printFileAdapter;
	@Override
	public void execute(MessageContext messageContext) throws MessageContextException {
		printFileAdapter.printFile(messageContext);

	}

}
