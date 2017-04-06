package com.idsscheer.webapps.arcm.bl.component.issuemanagement.commands;

import java.util.Collections;
import java.util.List;

import com.idsscheer.webapps.arcm.bl.authentication.context.IUserContext;
import com.idsscheer.webapps.arcm.bl.exception.BLException;
import com.idsscheer.webapps.arcm.bl.framework.command.CommandContext;
import com.idsscheer.webapps.arcm.bl.framework.command.CommandResult;
import com.idsscheer.webapps.arcm.bl.framework.command.ICommand;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.IAppObj;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.attribute.IEnumAttribute;
import com.idsscheer.webapps.arcm.common.constants.metadata.EnumerationsCustom;
import com.idsscheer.webapps.arcm.common.constants.metadata.ObjectType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IIssueAttributeType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IIssueAttributeTypeCustom;
import com.idsscheer.webapps.arcm.common.util.ARCMCollections;
import com.idsscheer.webapps.arcm.config.metadata.enumerations.IEnumerationItem;
import com.idsscheer.webapps.arcm.ui.framework.common.IUIEnvironment;

public class CustomUpdateIssueStatus implements ICommand {

	public CustomUpdateIssueStatus() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResult execute(CommandContext cc) throws BLException {
		// TODO Auto-generated method stub
//		return null;
		
		//Variáveis de contagem para mudança de status do Apontamento
		int ctOpen = 0;
		int ctGoing = 0;
		int ctFup = 0;
		int ctAttended = 0;
		int ctRiskAssumed = 0;
		int ctSettled = 0;
		
		//Instancia do Objeto de Plano de Ação Corrente
		IAppObj issueAppObj = cc.getChainContext().getTriggeringAppObj();
		IUserContext userCtx = cc.getChainContext().getUserContext();
//		IUIEnvironment currEnv = 
		
		IEnumAttribute issueTypeAttr = issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_ACTIONTYPE);
		IEnumerationItem issueTypeItem = ARCMCollections.extractSingleEntry(issueTypeAttr.getRawValue(), true);
		if(!(issueTypeItem.getId().equals("actionplan"))){
			return CommandResult.OK;
		}
		
		//Atributo - Status do Criador do Plano de Ação
		IEnumAttribute creatorStatusAttr = issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_AP_CREATOR_STATUS);
		IEnumerationItem creatorStatus = ARCMCollections.extractSingleEntry(creatorStatusAttr.getRawValue(), true);
		
		//Atributo - Status do Proprietário do Plano de Ação
		IEnumAttribute ownerStatusAttr = issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_AP_OWNER_STATUS);
		IEnumerationItem ownerStatus = ARCMCollections.extractSingleEntry(ownerStatusAttr.getRawValue(), true);
		
		//Atributo - Status do Revisor do Plano de Ação
		IEnumAttribute reviewerStatusAttr = issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_AP_REVIEWER_STATUS);
		IEnumerationItem reviewerStatus = ARCMCollections.extractSingleEntry(reviewerStatusAttr.getRawValue(), true);		
		
		List<IAppObj> iroList = issueAppObj.getAttribute(IIssueAttributeType.LIST_ISSUERELEVANTOBJECTS).getElements(userCtx);
		for(IAppObj iroAppObj : iroList){
			if(!(iroAppObj.getObjectType().equals(ObjectType.ISSUE)))
				continue;
			
			IEnumAttribute iroTypeAttr = iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_ACTIONTYPE);
			IEnumerationItem iroTypeItem = ARCMCollections.extractSingleEntry(iroTypeAttr.getRawValue(), true);
			if(!(iroTypeItem.getId().equals("issue")))
				continue;
			
			//Se Apontamento é "Em Elaboração", então Plano de Ação é "Em Aberto"
			if(creatorStatus.getId().equals("new"))
				iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.CENUM_IS_OWNER_STATUS.OPEN)
			);
			
			//Se Apontamento é "Pendente", então Plano de Ação é "Em Aberto"
			if(creatorStatus.getId().equals("new"))
				iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.CENUM_IS_OWNER_STATUS.OPEN)
			);
			
			//Se Apontamento é "Em Andamento", então Plano de Ação é "Em Andamento"
			if(creatorStatus.getId().equals("new"))
				iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.CENUM_IS_OWNER_STATUS.IN_PROGRESS)
			);
			
			//Se Apontamento é "Em Andamento", então Plano de Ação é "Em Andamento"
			if(creatorStatus.getId().equals("new"))
				iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.CENUM_IS_OWNER_STATUS.IN_PROGRESS)
			);				
				
			
			
			
			if(ownerStatus.getId().equals("pending")){
				ctOpen += 1;
			}
			
			if((creatorStatus.getId().equals("new")) || (creatorStatus.getId().equals("fup")) || (creatorStatus.getId().equals("risk_assumed"))){
				ctGoing += 1;
			}
			
			if((ownerStatus.getId().equals("in_progress")) || (ownerStatus.getId().equals("fup")) || (ownerStatus.getId().equals("risk_assumed"))){
				ctGoing += 1;
			} 
			
			if((reviewerStatus.getId().equals("settled")) || (reviewerStatus.getId().equals("fup")) || (reviewerStatus.getId().equals("risk_assumed")) || (reviewerStatus.getId().equals("attended"))){
				ctGoing += 1;
			}
			
			if((creatorStatus.getId().equals("fup")) || (creatorStatus.getId().equals("fup"))){
				ctFup += 1;
			}
			
			if((ownerStatus.getId().equals("fup")) || (ownerStatus.getId().equals("fup"))){
				ctFup += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("fup")) || (iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("fup")) || (iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("settled")) || (iroReviewerStatus.getId().equals("fup")) || (iroReviewerStatus.getId().equals("risk_assumed")) || (iroReviewerStatus.getId().equals("attended"))) ){
				ctFup += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("settled")) || (iroReviewerStatus.getId().equals("risk_assumed")) || (iroReviewerStatus.getId().equals("attended"))) ){
				ctAttended += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("risk_assumed"))) ){
				ctRiskAssumed += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("settled"))) &&
				((iroOwnerStatus.getId().equals("settled"))) &&
				((iroReviewerStatus.getId().equals("settled"))) ){
				ctSettled += 1;
			}
			
		}
		
		if(ctOpen > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.OPEN)
			);
			return CommandResult.OK;
		}
		
		if(ctGoing > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.ON_GOING)
			);
			return CommandResult.OK;
		}
		
		if(ctFup > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.FUP)
			);
			return CommandResult.OK;
		}
		
		if(ctAttended > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.ATTENDED)
			);
			return CommandResult.OK;
		}
		
		if(ctRiskAssumed > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.RISK_ASSUMED)
			);
			return CommandResult.OK;
		}
		
		if(ctSettled > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.SETTLED)
			);
			return CommandResult.OK;
		}
				
		return CommandResult.OK;
		//return null;
		 
	}

}
