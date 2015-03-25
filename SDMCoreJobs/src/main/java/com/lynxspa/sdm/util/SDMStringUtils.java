package com.lynxspa.sdm.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.lynxspa.sdm.exception.SDMBasicErrorDictionary;
import com.lynxspa.sdm.exception.SDMException;

public class SDMStringUtils {

	/**
	 * Ensures that given string is not greater than max length
	 * @param _value string to convert
	 * @param _maxlength maximum output length
	 * @return String or the substring
	 */
	public static final String fitString(final String _value,final int _maxlength){
		
		String reply=_value;
		
		if((reply!=null)&&(reply.length()>_maxlength))
			reply=reply.substring(0,_maxlength);
		
		return reply;
	}
	
	public static final String formatLocalizedMessage(final Object _bundle,
			final Locale _locale, final String _messageKey,
			final String _defaultMessage, final Object[] _arguments)
			throws SDMException {

		String reply = null;

		try {
			// Recover bundle
			ResourceBundle bundle = null;
			if (_bundle instanceof ResourceBundle)
				bundle = (ResourceBundle) _bundle;
			if (bundle == null)
				try {
					bundle = ResourceBundle.getBundle(String.valueOf(_bundle),
							_locale);
				} catch (Throwable e) {
				}
			// if(bundle==null)
			// try{
			// bundle=I18nSystem.getInstance().getBundle(String.valueOf(_bundle),_locale);
			// }catch(Throwable e){}
			if (bundle == null)
				throw new SDMException(
						SDMBasicErrorDictionary.BUNDLE_NOT_FOUND,
						new Object[] { _bundle });
			// Translate arguments;
			Object[] args = null;
			if (_arguments != null) {
				args = new Object[_arguments.length];
				for (int ic1 = 0; ic1 < _arguments.length; ic1++) {
					if ((bundle != null) && (_arguments[ic1] instanceof String)) {
						try {
							args[ic1] = bundle.getString(String
									.valueOf(_arguments[ic1]));
						} catch (MissingResourceException e) {
							args[ic1] = _arguments[ic1];
						}
					} else {
						args[ic1] = _arguments[ic1];
					}
				}
			}
			// Translate message key
			String translatedKey = null;
			try {
				if ((bundle != null) && (_messageKey != null)) {
					translatedKey = bundle.getString(_messageKey);
					if (_messageKey.equals(translatedKey))
						translatedKey = String.valueOf(_defaultMessage);
				} else {
					translatedKey = String.valueOf(_defaultMessage);
				}
			} catch (MissingResourceException e) {
				translatedKey = String.valueOf(_defaultMessage);
			}
			reply = SDMStringUtils.formatMessage(translatedKey, args);
		} catch (SDMException e) {
			throw e;
		} catch (Exception e) {
			throw new SDMException(e);
		}

		return reply;
	}

	/**
	 * Format message replacing values between bracets with the corresponent
	 * argument
	 * 
	 * @param _messageKey
	 *            Message to format
	 * @param _arguments
	 *            arguments to use into replace
	 * @return Message with replaced parameters if there are enought
	 * @see java.text.MessageFormat
	 */
	public static final String formatMessage(final String _messageKey,
			final Object... _arguments) {

		String reply = null;

		final MessageFormat form = new MessageFormat(_messageKey);
		reply = form.format(((_arguments == null) ? new Object[] {}
				: _arguments));

		return reply;
	}

}
