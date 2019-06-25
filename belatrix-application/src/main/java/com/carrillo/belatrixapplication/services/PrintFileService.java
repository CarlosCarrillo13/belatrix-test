package com.carrillo.belatrixapplication.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.carrillo.belatrixapplication.configuration.commons.constants.PathConstants;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContext;
import com.carrillo.belatrixapplication.configuration.commons.context.MessageContextItem;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;
import com.carrillo.belatrixapplication.entities.DocumentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

@Component
@RequiredArgsConstructor
@Slf4j
public class PrintFileService implements Service {

	@Override
	public void execute(MessageContext messageContext) {

		try {
			DocumentResponse documentResponse = (DocumentResponse) messageContext
					.getitem(MessageContextItem.DOCUMENTRESPONSE, DocumentResponse.class);
			fileBuilder(documentResponse);

		} catch (MessageContextException e) {
			log.error("error recuperando el contexto "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("error escribiendo el documento " +e.getMessage());
			e.printStackTrace();
		}

	}

	private void fileBuilder(DocumentResponse documentResponse) throws IOException {
		String path = createPath(documentResponse.getPageName());
		if (documentResponse.getHashtagFilteredDocument() != null
				&& !documentResponse.getHashtagFilteredDocument().isEmpty()) {
			buildFile(documentResponse.getHashtagFilteredDocument(), PathConstants.HASHTAGFILEPATH, path);
		}
		if (documentResponse.getMentionFilteredDocument() != null
				&& !documentResponse.getMentionFilteredDocument().isEmpty()) {
			buildFile(documentResponse.getMentionFilteredDocument(), PathConstants.MENTIONFILEPATH, path);
		}
		if (documentResponse.getProperNamesFilteredDocument() != null
				&& !documentResponse.getProperNamesFilteredDocument().isEmpty()) {
			buildFile(documentResponse.getProperNamesFilteredDocument(), PathConstants.PROPERFILEPATH, path);
		}

	}

	private void buildFile(String documentResponse, String filePath, String path) throws IOException {
		File file = new File(PathConstants.WRITEFILEPATH + path + filePath);
		try (Sink fileSink = Okio.sink(file); BufferedSink bufferedSink = Okio.buffer(fileSink)) {
			bufferedSink.writeUtf8(documentResponse);

		}
		log.info("the file "+PathConstants.WRITEFILEPATH + path + filePath+" was added successfully");
	}

	private String createPath(String pageName) {

		return pageName.replaceAll("(https?:\\/\\/)", "").replaceAll("(www\\.)", "")
				.replaceAll(".(com|org)(\\.[a-z]{2,3})?", "").replaceAll("/", "");
	}

}
