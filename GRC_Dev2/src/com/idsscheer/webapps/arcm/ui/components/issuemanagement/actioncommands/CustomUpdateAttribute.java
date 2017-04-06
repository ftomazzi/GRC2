package com.idsscheer.webapps.arcm.ui.components.issuemanagement.actioncommands;

import com.idsscheer.webapps.arcm.ui.framework.actioncommands.object.BaseSaveActionCommand;

public class CustomUpdateAttribute extends BaseSaveActionCommand {
	
	protected void afterExecute(){
		
/*		super.afterExecute();
		
		IAppObj appObj = this.formModel.getAppObj();
		appObj.getAttribute(IIssueAttributeTypeCustom.ATTR_TESTE_ATTR).setRawValue("MUDEI");
*/		/*
		IListAttribute iroAttr = appObj.getAttribute(IIssueAttributeTypeCustom.LIST_ISSUERELEVANTOBJECTS);
		List<IAppObj> issueObjs = iroAttr.getElements(this.getUserContext());
		
		for(IAppObj issueObj : issueObjs){
			IObjectType iroObjType = issueObj.getObjectType();
			if(iroObjType.equals(ObjectType.ISSUE)){
				issueObj.getAttribute(IIssueAttributeTypeCustom.ATTR_TESTE_ATTR).setRawValue("MUDEI");
			}
		}
		*/
		
	}

}
