package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_031_Add_Consequence_Short extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_031_Add_Consequence_Short(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_031_Add_Consequence_Short.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 31;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step adds new VARCHAR consequence_short to the RISK tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
			String addFieldSt = "";
            addFieldSt = mapping.genAddField("A_RISK_TBL", "consequence_short", 0, 4000);
            mapping.executeSQL(addFieldSt); 
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to add field consequence_short" + e.getMessage());
        }
	}

}
