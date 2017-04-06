package com.idsscheer.webapps.arcm.bl.component.issuemanagement.commands;

import java.util.Collections;

import com.idsscheer.webapps.arcm.bl.exception.BLException;
import com.idsscheer.webapps.arcm.bl.framework.command.CommandContext;
import com.idsscheer.webapps.arcm.bl.framework.command.CommandResult;
import com.idsscheer.webapps.arcm.bl.framework.command.ICommand;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.IAppObj;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.attribute.IEnumAttribute;
import com.idsscheer.webapps.arcm.common.constants.metadata.EnumerationsCustom;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IIssueAttributeTypeCustom;
import com.idsscheer.webapps.arcm.common.util.ARCMCollections;
import com.idsscheer.webapps.arcm.config.metadata.enumerations.IEnumerationItem;

public class CustomUpdateOwnerStatus implements ICommand {

	@Override
	public CommandResult execute(CommandContext cc) throws BLException {
		// TODO Auto-generated method stub
		
		IAppObj issueAppObj = cc.getChainContext().getTriggeringAppObj();
		
		IEnumAttribute actionTypeAttr = issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_ACTIONTYPE);
		if(!(actionTypeAttr.equals(null))){
			IEnumerationItem actionTypeItem = ARCMCollections.extractSingleEntry(actionTypeAttr.getRawValue(), true);
			if(actionTypeItem.getId().equals("issue")){
				issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_OWNER_STATUS).setRawValue(
						Collections.singletonList(EnumerationsCustom.CENUM_IS_OWNER_STATUS.IN_REVIEW)
				);
			}
			if(actionTypeItem.getId().equals("actionplan")){
				issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_AP_CREATOR_STATUS).setRawValue(
						Collections.singletonList(EnumerationsCustom.CENUM_AP_OWNER_STATUS.IN_PROGRESS)
				);
			}
		}
		
		return CommandResult.OK;
		//return null;
	}

}
