package com.carrillo.belatrixapplication.services;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.carrillo.belatrixapplication.configuration.commons.constants.CommonConstants;
import com.carrillo.belatrixapplication.configuration.commons.constants.FilterConstants;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContextItem;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;
import com.carrillo.belatrixapplication.entities.DocumentResponse;
import com.carrillo.belatrixapplication.entities.ParserFilter;
import com.carrillo.belatrixapplication.entities.mappers.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlParserService implements Service {

	private final Mapper<ParserFilter, List<String>> ParserFilterToString;

	@Override
	public void execute(MessageContext messageContext) {
		DocumentResponse documentResponse = new DocumentResponse();
		String url = "";
		try {
			url = (String) messageContext.getitem(MessageContextItem.URL, String.class);
			Integer result = (Integer) messageContext.getitem(MessageContextItem.COUNTER, Integer.class);
			List<String> filters = ParserFilterToString
					.map((ParserFilter) messageContext.getitem(MessageContextItem.FILTER, ParserFilter.class));

			String response = getResponse(url);

			for (String filter : filters) {
				String filteredResponse = filterResponse(response, filter);
				if (!filteredResponse.isEmpty() || filteredResponse != null) {
					documentResponse = handleResponse(filteredResponse, documentResponse, filter);
					result++;

				}
			}
			documentResponse.setPageName(url);
			messageContext.addItem(MessageContextItem.DOCUMENTRESPONSE, documentResponse);
			messageContext.addItem(MessageContextItem.COUNTER, result);

		} catch (MessageContextException e) {
			e.printStackTrace();
		}

	}

	private DocumentResponse handleResponse(String filteredResponse, DocumentResponse documentResponse, String filter) {
		switch (filter) {
		case FilterConstants.HASHTAG:
			documentResponse.setHashtagFilteredDocument(filteredResponse);
			break;
		case FilterConstants.TWIITER:
			documentResponse.setMentionFilteredDocument(filteredResponse);
			break;
		case FilterConstants.UPPER:
			documentResponse.setProperNamesFilteredDocument(filteredResponse);
		default:
			break;
		}
		return documentResponse;

	}

	private String filterResponse(String response, String filter) {
		StringBuilder patternResult = new StringBuilder();
		Pattern pattern = Pattern.compile(filter);
		Matcher matcher = pattern.matcher(response);
		while (matcher.find()) {
			patternResult.append(matcher.group(0)).append(System.lineSeparator());
		}

		return patternResult.toString();
	}

	private String getResponse(String url) {
		Document connection;
		try {
			connection = Jsoup.connect(url).timeout(CommonConstants.TIMEOUTCONNECTION).get();
			return connection.body().text();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
