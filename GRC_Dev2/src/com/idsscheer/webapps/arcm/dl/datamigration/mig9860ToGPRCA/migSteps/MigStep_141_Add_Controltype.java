package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_141_Add_Controltype extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_141_Add_Controltype(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_141_Add_Controltype.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 142;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step adds new INTEGER control_execution to the CONTROL tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
            String addFieldSt = "";
            addFieldSt = mapping.genAddField("A_CONTROL_TBL", "control_execution", 1, 1);
            mapping.executeSQL(addFieldSt);
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to add field control_execution", (Throwable)e);
        }
	}

}
