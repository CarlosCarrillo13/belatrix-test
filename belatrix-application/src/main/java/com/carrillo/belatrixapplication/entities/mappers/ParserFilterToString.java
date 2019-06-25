package com.carrillo.belatrixapplication.entities.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.carrillo.belatrixapplication.configuration.commons.constants.FilterConstants;
import com.carrillo.belatrixapplication.entities.ParserFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParserFilterToString implements Mapper<ParserFilter, List<String>> {

	@Override
	public List<String> map(ParserFilter input) {
		List<String> filters = new ArrayList<>();

		if(input.isProperName()==true) {
			filters.add(FilterConstants.UPPER);
		}
		if (input.isMention()==true) {
			filters.add(FilterConstants.TWIITER);
		}
		if (input.isHashtag()==true) {
			filters.add(FilterConstants.HASHTAG);
		}
		return filters;
	}

}
