package com.lynspa.sdm.exception;

public enum SDMBasicErrorDictionary implements SDMExceptionMessage {

	// Database
	DATABASE_STATEFULL_ONLY("error.database.statefull.only",
			"Only statefull session allowed for this functionality"), DATABASE_SESSION_NULL(
			"error.database.session.null", "Session is null"), DATABASE_SESSION_TYPE(
			"error.database.session.type",
			"Error retrieving database session type of object {0}, should implement applicationContext"), DATABASE_SESSION_ERROR(
			"error.database.session",
			"Error retrieving database session reference"), DATABASE_QUERY_ERROR(
			"error.database.query", "Error retrieving data from query [{0}]"), DATABASE_QUERY_GET(
			"error.database.query.get",
			"Error retrieving query [{0}] from session {1}"), DATABASE_CRITERIA_GET(
			"error.database.criteria.get",
			"Error retrieving criteria [{0}] from session {1}"), DATABASE_SAVE_ERROR(
			"error.database.save",
			"Error saving entity {0} of type {1} of from session {2}"), DATABASE_DELETE_ERROR(
			"error.database.delete",
			"Error deleting entity {0} of type {1} of from session {2}"), DATABASE_UPDATE_ERROR(
			"error.database.ilegal.update",
			"Insertable entity {0} of type {1} can't be updated"), DATABASE_SAVEORUPDATE_ERROR(
			"error.database.saveorupdate",
			"Error saving or updating entity {0} of type {1} of from session {2}"), DATABASE_SAVEORUPDATE_STATELESS(
			"error.database.saveorupdate.stateless",
			"Can't save or update over stateless session because can't retrieve the id of the object"), DATABASE_NOT_ENTITY(
			"error.database.not.entity",
			"Error type {0} without {1} annotation"), DATABASE_NULL_ENTITY(
			"error.database.null.entity", "Entity can't be null"), DATABASE_IDENTIFIER_FOUND(
			"error.database.not.identifier.found",
			"Error type {0} without identifier"),
	// Generic
	UNEXPECTED_ERROR("error.unexpected", "Unexpected error: {0}"), BUNDLE_NOT_FOUND(
			"error.cant.find.bundle.in.any.scope",
			"Can''t find bundle [{0}] in any scope."),
	// Configuration
	CONFIG_EDITABLE_AND_NOT_UPDATABLE(
			"error.configuration.editable.and.not.updatable",
			"Configuration {0} is editable but not updatable."),
	// XWeb
	USER_NOT_LOGGED("error.user.is.not.logged", "User hasn''t logged!"),
	// Workflow
	STATE_CHANGE_OVER_NULL("error.changed.state.over.null",
			"Change state over null operation."), CHANGE_STATE_OVER_UNSAVED(
			"error.changed.state.over.unsaved",
			"Change state over unsaved operation of type {0}."), STATE_NOT_FOUND(
			"error.state.not.exist.in.flow", "State {0} not found in flow {1}."), TRANSITION_NOT_FOUND(
			"error.transition.not.exist.in.flow",
			"Transition from state {0} to {1} not found in flow {2}."), ILLEGAL_TRANSITION(
			"error.illegal.transition",
			"Operation flow {0} and Transition flow {1} are different."), EXPIRED_TRANSITION(
			"error.expired.transition",
			"Operation state has changed after trasition was launched. Initial state: {0} expected: {1}"), CANNOT_EXECUTE_TRANSITION(
			"error.executing.transition",
			"An error has produced executing transition {0} in flow {1} for operation {2}"), VERSIONABLE_TRANSITIONS_WITH_STATELESS(
			"error.versionable.with.stateless",
			"Transion {0} can''t be done. Transitions over versionable entities not allowed with stateless session"),
	// Utils
	DIRECTORYNULL("error.dir.null", "Directory is null"), DIRECTORYNOTEXIST(
			"error.dir.not.exist", "Directory {0} not exist"), DIRECTORYCANTREAD(
			"error.dir.cant.read", "Directory {0} can''t be read"), DIRECTORYCANTWRITE(
			"error.dir.cant.write", "Directory {0} can''t be writted"), DIRECTORYNOTDIRECTORY(
			"error.dir.not.directory", "Directory {0} is not directory"), FILENULL(
			"error.file.null", "File is null"), FILEALREADYEXIST(
			"error.file.already.exist", "File {0} already exist"), FILENOTEXIST(
			"error.file.not.exist", "File {0} not exist"), FILECANTREAD(
			"error.file.cant.read", "File {0} can''t be read"), FILECANTWRITE(
			"error.file.cant.write", "File {0} can''t be writted"), FILENOTFILE(
			"error.file.not.directory", "File {0} is not directory"), ZIPSTREAMNULL(
			"error.zip.stream.null", "Zip stream is null."), ZIPFILENAMENULL(
			"error.zip.filename.null", "Zip filename is null."), ZIPOBJECTNULL(
			"error.zip.object.null", "Zip object is null."), JAXBSTREAMNULL(
			"error.jaxb.stream.null", "JAXB stream is null."), JAXBOBJECTNULL(
			"error.jaxb.object.null", "JAXB object is null."), JAXBBASECLASSNULL(
			"error.jaxb.baseclass.null", "JAXB baseclass is null."), JAXBCONTEXTERROR(
			"error.jaxb.context.error",
			"JAXB context instance for {0} classes failed."), JAXBCONTEXTNULL(
			"error.jaxb.context.null", "JAXB context is null."), JAXBMARSHALLERROR(
			"error.jaxb.marshall.error", "JAXB marshall {0} object fail"), JAXBUNMARSHALLERROR(
			"error.jaxb.unmarshall.error", "JAXB unmarshall {0} file fail");

	private String key = null;
	private String defaultMessage = null;

	SDMBasicErrorDictionary(final String _key, final String _defaultMessage) {
		this.key = _key;
		this.defaultMessage = _defaultMessage;
	}

	public String getMessageKey() {
		return this.key;
	}

	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	public SDMExceptionType getType() {
		return SDMExceptionType.ERROR;
	}
}
