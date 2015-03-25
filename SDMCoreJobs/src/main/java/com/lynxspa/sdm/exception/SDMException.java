package com.lynxspa.sdm.exception;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.lynxspa.sdm.util.SDMStringUtils;

public class SDMException extends Exception {

	private static final long serialVersionUID = 1688637499642500093L;
	private static final String ERROR_FORMATTING_MESSAGE = "Error formatting message";

	private static final Logger logger = Logger.getLogger(SDMException.class);

	protected SDMExceptionMessage message = null;
	protected Object[] arguments = null;

	public SDMException() {
		this(SDMBasicErrorDictionary.UNEXPECTED_ERROR, null, null);
	}

	public SDMException(Throwable _cause) {
		this(SDMBasicErrorDictionary.UNEXPECTED_ERROR, null, _cause);
	}

	public SDMException(SDMExceptionMessage _key) {
		this(_key, null, null);
	}

	public SDMException(SDMExceptionMessage _key, Throwable _cause) {
		this(_key, null, _cause);
	}

	public SDMException(SDMExceptionMessage _key, Object[] _arguments) {
		this(_key, _arguments, null);
	}

	public SDMException(SDMExceptionMessage _key, Object[] _arguments,
			Throwable _cause) {
		super(_cause);
		this.message = _key;
		if ((SDMBasicErrorDictionary.UNEXPECTED_ERROR.equals(_key))
				&& (_arguments == null) && (_cause != null)) {
			this.arguments = new Object[] { _cause.getMessage() };
		} else {
			if (_arguments != null) {
				this.arguments = _arguments.clone();
			}
		}
	}

	@Override
	public String getMessage() {

		String reply = null;

		if (this.message != null)
			reply = SDMStringUtils.formatMessage(
					this.message.getDefaultMessage(), this.arguments);
		else
			reply = super.getMessage();

		return reply;
	}

	public String getMessage(Object _bundle, Locale _locale) {

		String reply = null;

		try {
			if (this.message != null) {
				reply = SDMStringUtils.formatLocalizedMessage(_bundle,
						_locale, this.message.getMessageKey(),
						this.message.getDefaultMessage(), this.arguments);
			} else {
				reply = super.getMessage();
			}
		} catch (Exception e) {
			logger.error(SDMException.ERROR_FORMATTING_MESSAGE, e);
		}

		return reply;
	}

	public Object[] getArguments() {
		return (arguments != null) ? arguments.clone() : new Object[] {};
	}

	public SDMExceptionType getType() {
		return this.message.getType();
	}

	public String getMessageKey() {
		return this.message.getMessageKey();
	}

	public String getDefaultMessage() {
		return this.message.getDefaultMessage();
	}

}
