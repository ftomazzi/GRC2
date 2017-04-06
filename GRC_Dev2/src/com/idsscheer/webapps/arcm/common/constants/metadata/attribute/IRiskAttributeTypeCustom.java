package com.idsscheer.webapps.arcm.common.constants.metadata.attribute;

import com.idsscheer.webapps.arcm.config.metadata.objecttypes.IStringAttributeType;

public interface IRiskAttributeTypeCustom extends IRiskAttributeType {
	
	public static final String STR_RA_RESULT = "ra_result";
	public static final String STR_RA_CONTROL1LINE = "ra_control1line";
	public static final String STR_RA_CONTROL2LINE = "ra_control2line";
	public static final String STR_RA_CONTROL3LINE = "ra_control3line";
	public static final String STR_RA_CONTROLFINAL = "ra_controlfinal";
	public static final String STR_RA_RESIDUAL1LINE = "ra_residual1line";
	public static final String STR_RA_RESIDUAL2LINE = "ra_residual2line";
	public static final String STR_RA_RESIDUAL3LINE = "ra_residual3line";
	public static final String STR_RA_RESIDUALFINAL = "ra_residualfinal"; 
	
	public static final IStringAttributeType ATTR_RA_RESULT = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESULT);
	public static final IStringAttributeType ATTR_RA_CONTROL1LINE = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_CONTROL1LINE);
	public static final IStringAttributeType ATTR_RA_CONTROL2LINE = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_CONTROL2LINE);
	public static final IStringAttributeType ATTR_RA_CONTROL3LINE = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_CONTROL3LINE);
	public static final IStringAttributeType ATTR_RA_CONTROLFINAL = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_CONTROLFINAL);
	public static final IStringAttributeType ATTR_RA_RESIDUAL1LINE = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESIDUAL1LINE);
	public static final IStringAttributeType ATTR_RA_RESIDUAL2LINE = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESIDUAL2LINE);
	public static final IStringAttributeType ATTR_RA_RESIDUAL3LINE = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESIDUAL3LINE);
	public static final IStringAttributeType ATTR_RA_RESIDUALFINAL = (IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESIDUALFINAL);

}
