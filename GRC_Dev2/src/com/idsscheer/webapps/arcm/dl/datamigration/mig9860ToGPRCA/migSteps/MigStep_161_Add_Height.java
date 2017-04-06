package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_161_Add_Height extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_161_Add_Height(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_161_Add_Height.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 161;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step adds new INTEGER height to the RISKASSESSMENT tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
            String addFieldSt = "";
            addFieldSt = mapping.genAddField("A_RISKASSESSMENT_TBL", "height", 1, 2);
            mapping.executeSQL(addFieldSt);
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to add field height", (Throwable)e);
        }
	}

}
