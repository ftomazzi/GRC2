package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_170_Add_Origemteste extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_170_Add_Origemteste(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_170_Add_Origemteste.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 170;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step adds new INTEGER origemteste to the A_TESTDEFINITION_TBL tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
            String addFieldSt = "";
            addFieldSt = mapping.genAddField("A_TESTDEFINITION_TBL", "origemteste", 1, 2);
            mapping.executeSQL(addFieldSt);
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to add field origemteste" + e.getMessage());
        }
	}

}
