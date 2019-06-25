package com.carrillo.belatrixapplication.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carrillo.belatrixapplication.configuration.commons.pipeline.DocumentPipeline;
import com.carrillo.belatrixapplication.configuration.commons.usecase.Handler;

@Configuration
public class PipelineConfiguration {

	@Bean(name = "parseUrlPipeline")
	public DocumentPipeline parseUrlPipeline(Handler urlParserHandler) {
		List<Handler> handlers = new ArrayList<>();
		handlers.add(urlParserHandler);
		return new DocumentPipeline(handlers);
	}

	@Bean(name = "retrieveUrlPipeline")
	public DocumentPipeline retrieveUrlPipeline(Handler urlRetrieverHandler) {
		List<Handler> handlers = new ArrayList<>();
		handlers.add(urlRetrieverHandler);
		return new DocumentPipeline(handlers);
	}

	@Bean(name = "printFilePipeline")
	public DocumentPipeline printFilePipeline(Handler printFileHandler) {
		List<Handler> handlers = new ArrayList<>();
		handlers.add(printFileHandler);
		return new DocumentPipeline(handlers);
	}

}
