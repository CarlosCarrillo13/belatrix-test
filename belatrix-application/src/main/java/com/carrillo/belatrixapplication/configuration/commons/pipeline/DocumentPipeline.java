package com.carrillo.belatrixapplication.configuration.commons.pipeline;

import java.util.List;

import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.usecase.Handler;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocumentPipeline implements Pipeline {

	@NonNull
	private List<Handler> handlers;
	@Override
	public void execute(MessageContext messageContext) throws MessageContextException {
		for(Handler handler:handlers) {
			handler.execute(messageContext);
		}
		
	}

}
