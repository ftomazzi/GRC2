package com.idsscheer.webapps.arcm.common.constants.metadata.attribute;

import com.idsscheer.webapps.arcm.config.metadata.objecttypes.IEnumAttributeType;

public interface IIssueAttributeTypeCustom extends IIssueAttributeType {
	
	/*public static final String STR_CLASSIFTYPE = "classification";
	public static final IEnumAttributeType ATTR_CLASSIFICATION = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_CLASSIFTYPE);*/
	
	/*public static final String STR_TESTE_ATTR = "testeAttr";
	public static final IStringAttributeType ATTR_TESTE_ATTR = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_TESTE_ATTR);*/
	
	public static final String STR_ACTIONTYPE = "action_type";
	public static final IEnumAttributeType ATTR_ACTIONTYPE = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_ACTIONTYPE);
	
	public static final String STR_IS_CREATOR_STATUS = "custom_is_creator_status";
	public static final IEnumAttributeType ATTR_IS_CREATOR_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_IS_CREATOR_STATUS);
	
	public static final String STR_IS_OWNER_STATUS = "custom_is_owner_status";
	public static final IEnumAttributeType ATTR_IS_OWNER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_IS_OWNER_STATUS);
	
	public static final String STR_IS_REVIEWER_STATUS = "custom_is_reviewer_status";
	public static final IEnumAttributeType ATTR_IS_REVIEWER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_IS_REVIEWER_STATUS);
	
	public static final String STR_AP_CREATOR_STATUS = "custom_ap_creator_status";
	public static final IEnumAttributeType ATTR_AP_CREATOR_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_AP_CREATOR_STATUS);

	public static final String STR_AP_OWNER_STATUS = "custom_ap_owner_status";
	public static final IEnumAttributeType ATTR_AP_OWNER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_AP_OWNER_STATUS);
	
	public static final String STR_AP_REVIEWER_STATUS = "custom_ap_reviewer_status";
	public static final IEnumAttributeType ATTR_AP_REVIEWER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_AP_REVIEWER_STATUS);

}
