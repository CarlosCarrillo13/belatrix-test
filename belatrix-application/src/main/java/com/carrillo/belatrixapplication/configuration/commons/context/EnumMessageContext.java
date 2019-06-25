package com.carrillo.belatrixapplication.configuration.commons.context;

import java.util.EnumMap;
import java.util.Map;

import com.carrillo.belatrixapplication.configuration.commons.constants.ErrorConstants;
import com.carrillo.belatrixapplication.configuration.exceptions.MessageContextException;

public class EnumMessageContext<I extends Enum<I>> implements MessageContext<I> {

	private Map<I, Object> context;
	
	public EnumMessageContext(Class<I> keyType) {
		context = new EnumMap<>(keyType); 
	}

	@Override
	public void addItem(I key, Object item) {
		context.put(key, item);
	}

	@Override
	public Object getitem(I key, Class expectedClass) throws MessageContextException {
		Object item = context.getOrDefault(key,  null);
		if (item == null) {
			throw new MessageContextException(String.format(ErrorConstants.CONTEXTERROR, key), new NullPointerException());
		}
		if (!expectedClass.isInstance(item)) {
			throw new MessageContextException(String.format(ErrorConstants.CONTEXTERROR, key, expectedClass, item.getClass()), new ClassCastException());
		}
		return item;
	}

	@Override
	public void clear() {
		context.clear();
	}



}
