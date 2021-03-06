package com.lynxspa.sdm.jobs.normalization;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import bsh.Interpreter;

import com.lynxspa.sdm.dictionary.flow.state.StaticStatesSTATICMESSAGEFlow;
import com.lynxspa.sdm.entities.job.SDMRow;
import com.lynxspa.sdm.entities.job.SDMStaticRow;
import com.lynxspa.sdm.entities.job.SDMValue;
import com.lynxspa.sdm.entities.securities.assets.AssetDetails;
import com.lynxspa.sdm.entities.securities.assets.AssetType;
import com.lynxspa.sdm.entities.securities.assets.SecurityAsset;
import com.lynxspa.sdm.entities.securities.assets.provider.Provider;
import com.lynxspa.sdm.entities.securities.markets.SPMarket;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;
import com.lynxspa.sdm.processor.NormalizeScriptConfigBean;
import com.lynxspa.sdm.processor.StaticMessageField;

public class BeanShellStaticDataNormalizeProcessor implements
		NormalizeValuesAdapter {

	private StringBuffer buildScript(List<NormalizeScriptConfigBean> _scripts)
			throws Exception {

		StringBuffer reply = null;
		String[] fieldPathSplitted = null;
		String extension = null;
		String field = null;
		int position = 0;

		reply = new StringBuffer();
		// Field Normalization
		for (NormalizeScriptConfigBean config : _scripts) {
			if ((config.getScript() != null)
					&& (!"".equals(config.getScript()))) {
				fieldPathSplitted = config.getTableFieldPath().split(":");
				if (fieldPathSplitted.length == 2) {
					extension = "";
					field = fieldPathSplitted[0];
					position = Integer.parseInt(fieldPathSplitted[1]);
				} else {
					extension = fieldPathSplitted[0];
					field = (fieldPathSplitted.length >= 2) ? fieldPathSplitted[1]
							: null;
					position = (fieldPathSplitted.length == 3) ? Integer
							.parseInt(fieldPathSplitted[2]) : 0;
				}

				if (!config.isExtension()) {

					reply.append("Object get" + extension + field + position
							+ "(StaticEventMessageWrapper _message){\n");
					reply.append("	\ntry{\n");
					reply.append("		" + config.getScript());
					reply.append("	\n}catch(FieldNotFoundException e){\n");
					reply.append("		print(e);");
					reply.append("	\n}\n");
					reply.append("\n	return null;");
					reply.append("\n}\n");
					if ("BODY".equals(extension)) {
						reply.append("Object value" + extension + field
								+ position + "=get" + extension + field
								+ position + "(initialContext);\n");
					} else {
						reply.append("Object " + extension + field + position
								+ "=get" + extension + field + position
								+ "(initialContext);\n");
					}
				}
			}
		}
		// Field Recovery data by extension
		for (NormalizeScriptConfigBean config : _scripts) {
			if ((config.getScript() != null)
					&& (!"".equals(config.getScript()))) {
				fieldPathSplitted = config.getTableFieldPath().split(":");
				extension = fieldPathSplitted[0];
				if (config.isExtension()) {
					reply.append("List get" + extension
							+ "(StaticEventMessageWrapper _message){");
					reply.append("	" + config.getScript());
					reply.append("\n}\n");
					reply.append("List value" + extension + "Fields=get"
							+ extension + "(initialContext);\n");
					reply.append("List value" + extension
							+ "=new ArrayList();\n");
					reply.append("for(int ic1=0;ic1<value" + extension
							+ "Fields.size();ic1++){\n");
					reply.append("	Map content=new HashMap();\n");
					for (NormalizeScriptConfigBean extensionConfigs : _scripts) {
						if ((!extensionConfigs.isExtension())
								&& (extensionConfigs.getScript() != null)
								&& (!"".equals(extensionConfigs.getScript()))) {
							String[] extensionfieldPathSplitted = extensionConfigs
									.getTableFieldPath().split(":");
							if (extension.equals(extensionfieldPathSplitted[0])) {
								field = (extensionfieldPathSplitted.length >= 2) ? extensionfieldPathSplitted[1]
										: null;
								position = (extensionfieldPathSplitted.length == 3) ? Integer
										.parseInt(extensionfieldPathSplitted[2])
										: 0;
								reply.append("	content.put(\""
										+ field
										+ position
										+ "\",get"
										+ extension
										+ field
										+ position
										+ "(new StaticEventMessageWrapper(session,value"
										+ extension
										+ "Fields.get(ic1),initialContext.getNormalizedOperation(),initialContext.getNormalizedProvider(),initialContext.getNormalizedEventType())));\n");
							}
						}
					}
					reply.append("	value" + extension + ".add(content);\n");
					reply.append("\n}\n");
				}
			}
		}

		return reply;
	}

	private Interpreter prepareContext(Session _session, SDMRow row,
			PrintStream _stream) throws Exception {

		Interpreter reply = null;
		List<StaticMessageField> fields = null;
		StringBuffer initialScript = null;

		_session.lock(row, LockMode.NONE);
		initialScript = new StringBuffer();
		initialScript.append("import java.util.*;\n");
		initialScript.append("import java.text.*;\n");
		initialScript.append("import org.hibernate.Session;\n");
		initialScript.append("import com.lynxspa.entities.jobs.SDMJobField;\n");
		initialScript
				.append("import com.lynxspa.sdm.processors.normalize.utils.StaticMessageField;\n");
		initialScript
				.append("import com.lynxspa.sdm.processors.normalize.utils.StaticEventMessageWrapper;\n");
		// initialScript.append("import com.lynxspa.entities.securities.assets.messages.AssetMessageType;\n");
		initialScript
				.append("import com.lynxspa.sdm.processors.normalize.utils.FieldNotFoundException;\n");
		initialScript
				.append("import com.lynxspa.sdm.processors.normalize.utils.SDMSwiftParser;\n");
		initialScript.append("import com.prowidesoftware.swift.*;\n");
		initialScript.append("Session session=null;\n");
		initialScript.append("List fields=null;\n");
		// initialScript.append("AssetMessageType messageType=null;\n");
		initialScript
				.append("StaticEventMessageWrapper initialContext=null;\n");
		reply = new Interpreter(null, _stream, _stream, false);
		reply.eval(initialScript.toString());
		reply.set("session", _session);
		fields = new ArrayList<StaticMessageField>();

		for (SDMValue sdmValue : row.getSdmValues()) {

			if ((sdmValue.getValue() != null)
					&& (sdmValue.getValue().trim().length() > 0)) {

				if (sdmValue.getJobField() == null) {
					System.out.println("El valor " + sdmValue.getId()
							+ "del row " + row.getId()
							+ " no tiene field asociado");
				} else {
					fields.add(new StaticMessageField(sdmValue.getJobField(),
							sdmValue.getValue()));
				}
			}
		}

		if (fields.size() > 0) {

			reply.set("fields", fields);
			// reply.set("normalizedProvider",_session.load(Provider.class,row.getJob().getJobType().getFields().get("ProviderId").getFieldName()));
			// reply.set("normalizedAssetType",_session.load(AssetType.class,row.getJob().getJobType().getFields().get("AssetTypeId").getFieldName()));
			reply.set("originalMessage", row.getValue());
			// reply.eval("initialContext=new StaticEventMessageWrapper(session,fields,normalizedProvider,normalizedAssetType,originalMessage);\n\n\n");
			System.out.println();
			reply.eval("initialContext=new StaticEventMessageWrapper(session,fields,originalMessage);\n\n\n");
		} else {
			System.out.println("Row " + row.getId() + " value: "
					+ row.getValue() + " No tiene fields");
		}
		return reply;
	}

	private void setExtensionFieldValues(CAEventDetailExtended _extensions,
			Map<String, Object> _values) throws Exception {

		Object value = null;

		for (String _valueName : _values.keySet()) {
			value = _values.get(_valueName);
			if (value != null) {
				_extensions.set(_valueName, value);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private NormalizeValueResultBean recoverDataFromScriptContext(
			NormalizeValueResultBean _result,
			List<NormalizeScriptConfigBean> _scripts, Interpreter _context)
			throws Exception {

		NormalizeValueResultBean reply = null;
		String[] fieldPathSplitted = null;
		String extension = null;
		String field = null;
		int position = 0;
		Object value = null;

		reply = _result;
		for (NormalizeScriptConfigBean config : _scripts) {
			if ((config.getScript() != null)
					&& (!"".equals(config.getScript()))) {
				fieldPathSplitted = config.getTableFieldPath().split(":");
				if (fieldPathSplitted.length == 2) {
					extension = "";
					field = fieldPathSplitted[0];
					position = Integer.parseInt(fieldPathSplitted[1]);
					if ((value = _context.get(extension + field + position)) != null)
						reply.getDetail().set(field + position, value);
				} else {
					extension = fieldPathSplitted[0];
					if (!config.isExtension()) {
						if ("BODY".equals(extension)) {
							field = (fieldPathSplitted.length >= 2) ? fieldPathSplitted[1]
									: null;
							position = (fieldPathSplitted.length == 3) ? Integer
									.parseInt(fieldPathSplitted[2]) : 0;
							if ((value = _context.get("value" + extension
									+ field + position)) != null)
								reply.getDetail().set(field + position, value);
						}
					}
				}
			}
		}

		return reply;
	}

	protected NormalizeValueResultBean scriptsProcessing(
			ApplicationContext applicationContext,
			List<NormalizeScriptConfigBean> _scripts, SDMRow row)
			throws Exception {

		NormalizeValueResultBean reply = null;
		Interpreter beanshellContext = null;
		StringBuffer script = null;
		PrintStream stream = null;
		ByteArrayOutputStream outputStream = null;

		reply = new NormalizeValueResultBean();
		if ((_scripts != null) && (_scripts.size() > 0)) {
			// Prepare bean shell script
			script = buildScript(_scripts);
			reply.setGeneratedScript(script.toString());
			// Evaluate Script
			outputStream = new ByteArrayOutputStream();
			stream = new PrintStream(outputStream, false, "ISO-8859-1");
			beanshellContext = prepareContext(_session, row, stream);
			// Evaluate Script
			try {
				beanshellContext.eval(script.toString());
				reply = recoverDataFromScriptContext(reply, _scripts,
						beanshellContext);
			} catch (Exception e) {
				reply.setException(e);
			}
			stream.flush();
			reply.setOutput(outputStream.toString());
			stream.close();
		}

		return reply;
	}

	public void normalize(ApplicationContext applicationContext, String _user,
			String _locale) throws Exception {

		NormalizeValueResultBean result = null;
		List<NormalizeScriptConfigBean> scripts = null;

		String query = "from SDMStaticRow as row where row.operationStatus.state.id.code=:status";
		Query _eventQuery = _session.createQuery(query);
		_eventQuery.setParameter("status",
				StaticStatesSTATICMESSAGEFlow.PRSD.getId());
		List<SDMStaticRow> sdmRows = new ArrayList<SDMStaticRow>();
		sdmRows = _eventQuery.list();

		result = new NormalizeValueResultBean();
		if (!sdmRows.isEmpty()) {
			// OperationStatus opeStatus =
			// (OperationStatus)_session.load(OperationStatus.class,
			// StaticStatesSTATICMESSAGEFlow.NORM.getId());
			int rowNormalized = 0;
			HibernateUtils.beguinTransaction(statelessSession);

			int count = 0;
			for (SDMStaticRow row : sdmRows) {
				try {
					count++;
					if (count > 2) {
						System.out.println("me salgo del for");
						break;
					}

					query = "from Provider where code=:codeProvider";
					_eventQuery = _session.createQuery(query);
					_eventQuery.setParameter("codeProvider", row.getJob()
							.getJobType().getFields().get("ProviderId")
							.getFieldName());
					Provider provider = (Provider) _eventQuery.uniqueResult();
					AssetType assetType = (AssetType) _session.load(
							AssetType.class, row.getJob().getJobType()
									.getFields().get("AssetTypeId")
									.getFieldName());

					scripts = StaticConfigManager.getInstance()
							.getNormalizationScripts(_session, provider,
									assetType,
									row.getJob().getJobType().getEnterprise());
					// call script processing
					if ((scripts != null) && (scripts.size() > 0)) {

						// Search for scripts configuration
						result = executeNormalization(_session, row, provider,
								assetType, scripts);
						// if(result==null)
						// throw new
						// FPMException(LogErrorDict.NORMALIZATION_RESULT_NULL,new
						// Object[]{row.getId()});
						// if(result.getException()!=null)
						// throw new
						// FPMException(LogErrorDict.NORMALIZATION_SCRIPTING_ERROR,new
						// Object[]{row.getId()},result.getException());
						if (result != null) {
							AssetDetails assetDetails = result.getDetail();
							HibernateUtils.customSave(statelessSession,
									assetDetails, _user);
							SecurityAsset asset = new SecurityAsset(_user);
							asset.setAssetDetail(assetDetails);
							asset.setAssetType(assetType);
							asset.setAuditor(new UpdateAuditor(_user));
							asset.setProvider(provider);
							asset.setOperationStatus(getOperationStatus(
									StaticStatesSTATICMESSAGEFlow.NORM,
									_session));
							asset.setName(row.getSecurityName());
							asset.setIsin(row.getSecurityIsin());
							asset.setSecurityMessageId(row.getId());

							if (row.getSecurityMic() != null) {
								query = "from SPMarket as market where market.mic=:mic";
								_eventQuery = _session.createQuery(query);
								_eventQuery.setParameter("mic",
										row.getSecurityMic());
								asset.setMarket((SPMarket) _eventQuery
										.uniqueResult());
							}

							HibernateUtils.customSave(statelessSession, asset,
									_user);

							row.setOperationStatus(getOperationStatus(
									StaticStatesSTATICMESSAGEFlow.NORM,
									_session));

							System.out.println("session: " + statelessSession);
							System.out.println("Row: " + row);
							System.out.println("User: " + _user);
							HibernateUtils.customUpdate(statelessSession, row,
									_user);
							HibernateUtils.commitTransaction(statelessSession);
							HibernateUtils.beguinTransaction(statelessSession);
							rowNormalized++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Error normalizando row " + row.getId()
							+ " " + e.getMessage());
				}
			}
			System.out.println("Actualizados " + rowNormalized + " mensajes");
			HibernateUtils.commitTransaction(statelessSession);

		}
	}

	private NormalizeValueResultBean executeNormalization(Session _session,
			SDMRow row, Provider provider, AssetType assetType,
			List<NormalizeScriptConfigBean> scripts) throws Exception {
		NormalizeValueResultBean reply = null;

		reply = scriptsProcessing(_session, scripts, row);

		return reply;
	}

	public NormalizeValueResultBean test(Session _session,
			List<NormalizeScriptConfigBean> _scripts, SDMRow row)
			throws Exception {
		return scriptsProcessing(_session, _scripts, row);
	}

	@Override
	public NormalizeValueResultBean test(ApplicationContext applicationContext,
			List<NormalizeScriptConfigBean> _scripts, SDMRow row)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO check if it is neccesary
//	public OperationStatus getOperationStatus(
//			StaticStatesSTATICMESSAGEFlow state, Session session) {
//		OperationStatus operationStatus;
//		Flow flow = (Flow) session.get(Flow.class,
//				StaticDataWorkflow.STATICMESSAGE.getId());
//		String code = state.getId();
//		StateId stateId = new StateId(flow, code);
//		operationStatus = new OperationStatus((State) session.get(State.class,
//				stateId));
//
//		return operationStatus;
//	}
}
