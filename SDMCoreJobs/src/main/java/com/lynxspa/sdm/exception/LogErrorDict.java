package com.lynxspa.sdm.exception;

import com.lynxspa.sdm.exception.adapter.LogDictAdapter;


public enum LogErrorDict implements LogDictAdapter,SDMExceptionMessage{

	//Configuration
	FORMAT_LOAD_FAIL("error.format.loading.fail","Format {0} loading failed."),
	FORMAT_NOT_INSTALLED("error.format.not.installed","Format {0} is not installed."),
	MESSAGETYPE_LOAD_FAIL("error.message.type.loading.fail","MessageType {0} of format {1} loading failed."),
	MESSAGETYPE_NOT_INSTALLED("error.message.type.not.installed","MessageType {0} of format {1} is not installed."),
	PROCESSOR_CLASS_NOT_FOUND("error.processor.class.not.found","Processor class [{0}] not found."),
	PROCESSOR_DOMAIN_CLUSTER_LOAD_FAIL("error.domain.cluster.loading.fail","Cluster Format {0} in domain {1} can''t be loaded."),
	PROCESSOR_DOMAIN_FORMAT_LOAD_FAIL("error.domain.format.loading.fail","Format {0} in domain {1} can''t be loaded."),
	//Processors
	PORTFOLIO_UPDATER_FAIL("error.update.portfolio","Portfolio updater failed processing message {0}."),
	NORMALIZER_FAIL("error.normalizer.fail","Normalizer failed processing message {0} for {1}."),
	COMPLETEANALYZER_FAIL("error.complete.analyzer.fail","Complete analyzer failed processing message {0} for {1}."),
	//Processes
	UNKOWN_FAIL("error.unkown.error","Unknown error at process {0}."),
	//	Imports
	IMPORT_SAVE_FAIL("error.import.save.fail","{0} import from {1} failed at message {2} attempting to save into database."),
	IMPORT_FAIL("error.import.fail","{0} import from {1} failed at message {2}."),
	PARSING_EXCEPTION("error.import.message.parser.fail","{0} import from {1} failed at {2} of message {3}."),
	//	Security Imports
	SECURITY_IMPORT_SAVE_FAIL("error.security.import.save.fail","{0} security import from {1} failed attempting to save into database."),
	SECURITY_IMPORT_FAIL("error.security.import.fail","{0} security import from {1} failed."),
	SECURITY_FUNDS_IMPORT_SAVE_FAIL("error.security.funds.import.save.fail","{0} security funds import from {1} failed attempting to save into database."),
	SECURITY_FUNDS_IMPORT_FAIL("error.security.import.fail","{0} security import from {1} failed."),
	//	PreNormalization
	UNKNOWN_OPERATION("error.prenormalization.unknown.operation","Operation {0} is unknown for message {1} of type {2}."),
	UNKNOWN_PROVIDER("error.prenormalization.unknown.provider","Provider {0} is unknown for message {1} of type {2}."),
	SECURITYTYPE_NOT_FOUND("error.prenormalization.security.type","Security type {0} found in message {1} is not accepted."),
	SECURITY_NOT_FOUND("error.prenormalization.security","Message {0} haven''t any security defined."),
	PRENORMALIZATION_FAIL("error.prenormalization.fail","Prenormalization fail."),
	//	Normalization
	PREVIOUS_NEW_MESSAGE_NOT_FOUND("error.normalization.previous.new.not.found","Normalization of message {0} fail. Previous message NEW not found, can't update event."),
	PREVIOUS_NEW_MESSAGE_NOT_NORMALIZED("error.normalization.previous.new.not.normalized","Normalization of message {0} fail. Previous message NEW {1} is not yet normalized, can't update event."),
	NORMALIZATION_SCRIPTING_ERROR("error.normalization.scripting","Normalization of message {0} has failed due a script exception."),
	NORMALIZATION_RESULT_NULL("error.null.normalization.result","Normalization of message {0} has resturned a null result."),
	NORMALIZATION_FAIL("error.normalization.fail","Normalization fail."),
	NORMALIZATION_UNKNOWN_FIELD("error.normalization.unknown.field","Event field {0} unknown."),
	NORMALIZATION_EVENT_FIELD_TYPE("error.normalization.field.type","Event field {0} type wrong."),
	//  PostNormalization
	POSTNORMALIZATION_FAIL("error.postnormalization.fail","Postnormalization fail."),
	//	Matching
	MATCHING_FAIL("error.matching.fail","Matching fail."),
	CANT_GROUP_EVENT("error.matching.cant.group.event","Can't match group for event {0}."),
	//	Master record
	MASTERRECORD_FAIL("error.masterrecord.fail","Matching fail."),
	//	Authorization
	AUTHORIZATION_FAIL("error.authorization.fail","Authorization fail."),
	//	Historification
	HISTORIFICATION_BLOCK_FAIL("error.historification.block.fail","Historification block from message {0} to message {1} fail. Status:\n	Messages historified: {2}\n	Message fields historified: {3}\n	Historified message fields updated: {4}\n	Message fields deleted: {5}\n Messages deleted: {6}\n	Historified messages updated: {7}"),
	DAYS_TO_HISTORIFY_FAIL("error.historification.margin.days.recover.fail","Error getting days to historify({0}). {1} days has been stablished to historify."),
	HISTORIFICATION_EVENTS_FAIL("error.historification.events.fail","Historification events fail."),
	DAYS_TO_HISTORIFY_EVENTS_FAIL("error.historification.events.days.recover.fail","Error getting days to historify({0}). {1} days has been stablished to historify."),
	LIVETIME_EVENTS_HISTORIFIED_FAIL("error.delete.events.historified","Error deleting events historified."),
	//	Planning
	GENERATE_FILE_ERROR("error.planning.generate.file.fail","Error generating file {0} with content {1}"),
	EXECUTE_COMMAND_ERROR("error.planning.execute.command.fail","Error executing command {0}"),
	//	SendBackOffice
	SEND_BACK_OFFICE_ERROR("error.send.back.office","Sending Back Office Fail"),
	//	Monitor
	MONITOR_CLEARCACHE_ERROR("error.monitor.clear.cache","Error cleaning cache"),
	//Notification Manager
	NOTIFICATION_MANAGER_ERROR("error.notification.manager","Notification to Manager Fails"),
	//Auxs Tables Load
	AUX_TABLE_LOAD_ERROR("erro.aux.tables.load","Error loading table {0} into registry with id {1}. Not deleted"),
	//Customer Upload Data
	CUSTOMERUPLOADDATA_FILE_FORMAT_ERROR("error.customeruploaddata.fileformat","Error in the file format for upload customer data: {0}"),
		
	//STATIC
	ERROR_IMPORT_STATICDATA("error.import.static.data","Error importing static data message. This message has been discarded: {0}")
	;
	
	
	private String messageKey=null;
	private String defaultMessage=null;
	
	
	LogErrorDict(String _key,String _defaultMessage){
		this.messageKey=_key;
		this.defaultMessage=_defaultMessage;
	}

	
	public String getMessageKey() {
		return this.messageKey;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public LogLevel getLevel() {
		return LogLevel.ERROR;
	}
	public SDMExceptionType getType() {
		return SDMExceptionType.ERROR;
	}
}
