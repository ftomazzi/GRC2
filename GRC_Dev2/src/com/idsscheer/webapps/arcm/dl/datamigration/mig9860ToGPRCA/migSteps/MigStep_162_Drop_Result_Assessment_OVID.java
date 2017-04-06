package com.idsscheer.webapps.arcm.dl.datamigration.mig9860ToGPRCA.migSteps;

import java.sql.SQLException;

import com.idsscheer.webapps.arcm.dl.datamigration.exception.DDLMigrationException;
import com.idsscheer.webapps.arcm.dl.datamigration.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.IMigrationStep;
import com.idsscheer.webapps.arcm.dl.datamigration.migrationstep.stepImpl.BaseMigrationStep;

public class MigStep_162_Drop_Result_Assessment_OVID extends BaseMigrationStep implements IMigrationStep {
	
	public MigStep_162_Drop_Result_Assessment_OVID(String resourcePath) throws DDLMigrationException {
		// TODO Auto-generated constructor stub
		super(MigStep_162_Drop_Result_Assessment_OVID.class.getSimpleName(), resourcePath);
	}

	public int getStepOrderNumber() {
        return 162;
    }
	
	@Override
	public void execute(IMapping mapping) {
		// TODO Auto-generated method stub
		this.adaptSchema(mapping);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This Migration Step drops result_assessment_ovid from the RISKASSESSMENT tables";
		//return null;
	}
	
	public void adaptSchema(IMapping mapping) throws DDLMigrationException{
		try {
            String dropFieldSt = "ALTER TABLE A_RISKASSESSMENT_TBL DROP result_assessment_ovid";
            //dropFieldSt = mapping.genDeleteField("A_RISKASSESSMENT_TBL", "result_assessment_ovid");
            //addFieldSt = mapping.genAddField("A_RISKASSESSMENT_TBL", "height", 1, 2);
            mapping.executeSQL(dropFieldSt);
        }
        catch (SQLException e) {
            throw new DDLMigrationException("failed to drop field result_assessment_ovid" + e.getMessage() );
        }
	}

}
