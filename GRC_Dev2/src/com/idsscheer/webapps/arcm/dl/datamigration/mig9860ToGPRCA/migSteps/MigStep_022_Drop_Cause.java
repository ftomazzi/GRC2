package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_022_Drop_Cause extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_022_Drop_Cause(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_022_Drop_Cause.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 22;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step drops VARCHAR cause from the RISK tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
			String dropFieldSt = "ALTER TABLE A_RISK_TBL DROP cause";
            //addFieldSt = mapping.genAddField("A_RISK_TBL", "cause", 0, 1023);
            mapping.executeSQL(dropFieldSt); 
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to add field cause" + e.getMessage());
        }
	}

}
