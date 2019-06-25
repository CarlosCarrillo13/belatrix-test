package com.carrillo.belatrixapplication.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrillo.belatrixapplication.configuration.commons.constants.CommonConstants;
import com.carrillo.belatrixapplication.configuration.commons.constants.PathConstants;
import com.carrillo.belatrixapplication.configuration.commons.context.EnumMessageContext;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContextItem;
import com.carrillo.belatrixapplication.configuration.commons.usecase.UseCase;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;
import com.carrillo.belatrixapplication.entities.DocumentRequest;
import com.carrillo.belatrixapplication.entities.ParserFilter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PathConstants.ROOT)
@RequiredArgsConstructor
public class DocumentController {

	private final UseCase urlParserUseCase;
	private final UseCase urlRetrieverUseCase;
	private final UseCase printFileUseCase;

	@PostMapping(PathConstants.PROCESSDOCUMENT)
	public ResponseEntity<?> processDocument(@RequestBody(required = false) DocumentRequest request)
			throws MessageContextException, IOException {

		MessageContext messageContext = new EnumMessageContext<>(MessageContextItem.class);
		if (request != null && request.getPath() != null) {
			messageContext.addItem(MessageContextItem.PATH, request.getPath());
		}
		if (request != null && request.getFilter() != null) {
			messageContext.addItem(MessageContextItem.FILTER, request.getFilter());
		} else {
			request = buildDefaultRequest(request);
			messageContext.addItem(MessageContextItem.FILTER, request.getFilter());
		}
		messageContext.addItem(MessageContextItem.COUNTER, 0);
		getUrls(messageContext);
		parseUrls(messageContext);
		Integer result = (Integer) messageContext.getitem(MessageContextItem.COUNTER, Integer.class);

		return new ResponseEntity<>(String.format(CommonConstants.COUNTERRESULT, result), HttpStatus.OK);
	}

	private DocumentRequest buildDefaultRequest(DocumentRequest request) {
		
		ParserFilter parserFilter = ParserFilter.builder().hashtag(true).mention(true).properName(true).build();
		request.setFilter(parserFilter);
		return request;
	}

	private void parseUrls(MessageContext messageContext) {
		List<String> urls = new ArrayList<>();
		try {
			urls = (List<String>) messageContext.getitem(MessageContextItem.URLS, List.class);
		} catch (MessageContextException e) {
			e.printStackTrace();
		}
		for (String url : urls) {
			messageContext.addItem(MessageContextItem.URL, url);
			urlParserUseCase.handle(messageContext);
			printFileUseCase.handle(messageContext);

		}

	}

	private void getUrls(MessageContext messageContext) {
		urlRetrieverUseCase.handle(messageContext);

	}

}
