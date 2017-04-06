package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_050_Add_ControlExecStatus extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_050_Add_ControlExecStatus(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_050_Add_ControlExecStatus.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 40;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step adds new INTEGER custom_controlexecstatus to the CONTROLEXECUTION tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
            String addFieldSt = "";
            addFieldSt = mapping.genAddField("A_CONTROLEXECUTION_TBL", "custom_controlexecstatus", 1, 1);
            mapping.executeSQL(addFieldSt);
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to add field custom_controlexecstatus" + e.getMessage());
        }
	}

}
