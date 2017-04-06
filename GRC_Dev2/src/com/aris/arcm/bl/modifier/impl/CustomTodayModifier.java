package com.aris.arcm.bl.modifier.impl;

import java.util.Date;

import com.idsscheer.webapps.arcm.bl.exception.BLInternalException;
import com.idsscheer.webapps.arcm.common.support.DateUtils;
import com.idsscheer.webapps.arcm.config.metadata.modifier.IModifier;
import com.idsscheer.webapps.arcm.config.metadata.modifier.IModifierContext;

public class CustomTodayModifier extends AbstractModifier implements IModifier {

	public CustomTodayModifier(String baseAttrID) {
		// TODO Auto-generated constructor stub
		super(baseAttrID);
	}
	
	@Override
	public void modify(IModifierContext ctx) {
		// TODO Auto-generated method stub
		try{
			
			Date today = new Date();
			Date modifiedDate = DateUtils.normalizeLocalDate(today, (DateUtils.Target)DateUtils.Target.BEGIN_OF_DAY);
			ctx.setModifiedValue(this.getAttrID(), (Object)modifiedDate);
			
		}catch(Exception e){
			throw new BLInternalException((Throwable)e,"today modifier error", new String[]{this.getAttrID()});
		}

	}

}
