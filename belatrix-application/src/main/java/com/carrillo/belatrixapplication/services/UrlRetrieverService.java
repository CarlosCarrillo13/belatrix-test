package com.carrillo.belatrixapplication.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.carrillo.belatrixapplication.configuration.commons.constants.CommonConstants;
import com.carrillo.belatrixapplication.configuration.commons.constants.PathConstants;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContextItem;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlRetrieverService implements Service {

	@Override
	public void execute(MessageContext messageContext) {
		String filePath = validatePath(messageContext);
		List<String> urls = new ArrayList<>();
		try {
			urls = getUrls(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageContext.addItem(MessageContextItem.URLS, urls);

	}

	private List<String> getUrls(File file) throws IOException {

		List<String> urls = new ArrayList<>();

			try (Source fileSource = Okio.source(file); BufferedSource bufferedSource = Okio.buffer(fileSource)) {

				while (true) {
					String line = bufferedSource.readUtf8Line();
					if (line == null)
						break;

					else if(validateUrl(line)) {
						
						urls.add(line.replaceAll("[\uFEFF-\uFFFF]", ""));
					}
				}

			}
		return urls;
	}



	private boolean validateUrl(String line) {

			try {
				new URL(line).toURI();
				return true;
			}
			catch (Exception e) {
				return false;
				
			}
			
	}

	private String validatePath(MessageContext messageContext) {
		String filePath = "";
		try {
			filePath = (String) messageContext.getitem(MessageContextItem.PATH, String.class);
		} catch (MessageContextException e) {
			log.warn(CommonConstants.PATHWARNING);
			filePath = PathConstants.DEFAULTFILEPATH;
		}

		return filePath;

	}

}
