package com.lynxspa.sdm.jobs.util;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.lynxspa.sdm.entities.job.SDMJobType;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.SDMJobTypeDAO;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

//import com.lynxspa.entities.UpdateAuditor;

public class SDMUtils {

	public final static String commitDirectory = "H:\\develop\\workspace_NEWSDM\\SDM_LynxIberica_Madrid\\files";
	public final static String inputDirectory = "D:\\develop\\workspace_NEWSDM\\SDM_LynxIberica_Madrid\\files";
	public final static String rollbackDirectory = "H:\\develop\\workspace_NEWSDM\\SDM_LynxIberica_Madrid\\files";

	// TODO: Pasar y recoger los datos especificos para cada job en el map

	public static SDMJobType getJobType(ApplicationContext applicationContext,
			Map<String, String> infoJob) {
		// SDMJobType jobt = new
		// SDMJobTypeDAO(session).findByName(infoJob.get("name"));

		SDMJobTypeDAO jobTypeDAO = (SDMJobTypeDAO) applicationContext
				.getBean("jobTypeDAO");
		SDMJobType jobt = jobTypeDAO.findAllByProperty("name",
				infoJob.get("name")).get(0);

		if (jobt == null) {
			jobt = new SDMJobType();
			jobt.setName(infoJob.get("name"));
			jobt.setAllOrNothing(false);
			jobt.setAllowMultithreading(true);
			jobt.setClassExe("");
			jobt.setCommitDirectory(commitDirectory);
			jobt.setCommitSuffix(".done");
			jobt.setCronExpression("1/10 * * * * ?");
			jobt.setDescription("Bloomberg Security Fields");
			jobt.setInputDirectory(inputDirectory);
			jobt.setPattern(".sdm");
			jobt.setRollbackDirectory(rollbackDirectory);
			jobt.setRollbackSuffix(".error");
			jobt.setTemporalSuffix(".tmp");
			jobt.setVersion(0);
			jobt.setAuditor(new UpdateAuditor(infoJob.get("user")));

			new SDMJobTypeDAO(session).insert(jobt);
		}

		return jobt;
	}

	public static SDMJobType getJobType(ApplicationContext applicationContext,
			Map<String, String> infoJob, String path) {

		SDMJobType jobt = new SDMJobTypeDAO(session).findByPath(path);

		if (jobt == null) {
			jobt = new SDMJobType();
			jobt.setName(infoJob.get("name"));
			jobt.setAllOrNothing(false);
			jobt.setAllowMultithreading(true);
			jobt.setClassExe("");
			jobt.setCommitDirectory(path);
			jobt.setCommitSuffix(".done");
			jobt.setCronExpression("1/10 * * * * ?");
			jobt.setDescription("Bloomberg Security Fields");
			jobt.setInputDirectory(path);
			jobt.setPattern(".sdm");
			jobt.setRollbackDirectory(path);
			jobt.setRollbackSuffix(".error");
			jobt.setTemporalSuffix(".tmp");
			jobt.setVersion(0);
			jobt.setAuditor(new UpdateAuditor(infoJob.get("user")));

			new SDMJobTypeDAO(session).insert(jobt);
		}

		return jobt;
	}

	// TODO check if it neccesary
	// public static State getState(Session session, String codeState) {
	// StateId stateId = null;
	// State state;
	// Flow flow = (Flow) session.load(Flow.class,
	// StaticDataWorkflow.STATICMESSAGE.getId());
	//
	// if (codeState.equalsIgnoreCase(CAStatesEVENTMESSAGEFlow.PRSD.getId())) {
	// stateId = new StateId(flow, CAStatesEVENTMESSAGEFlow.PRSD.getId());
	// }
	// state = (State) session.load(State.class, stateId);
	//
	// return state;
	// }
}
