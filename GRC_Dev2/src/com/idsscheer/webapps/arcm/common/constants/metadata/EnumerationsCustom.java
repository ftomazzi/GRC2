package com.idsscheer.webapps.arcm.common.constants.metadata;

import com.idsscheer.webapps.arcm.config.metadata.enumerations.EnumerationWrapper;
import com.idsscheer.webapps.arcm.config.metadata.enumerations.IEnumeration;
import com.idsscheer.webapps.arcm.config.metadata.enumerations.IEnumerationItem;

public class EnumerationsCustom extends Enumerations {

	public EnumerationsCustom() {
		// TODO Auto-generated constructor stub
	}
	
	/*private static <T extends IEnumeration> T lookupEnumeration(String enumerationId) {
		return Metadata.getMetadata().getEnumeration(enumerationId);
	}*/
	
	private static <T extends IEnumerationItem> T lookupEnumerationItem(IEnumeration enumeration, String itemId) {
		return enumeration.getItemById(itemId);
	}
	
	public static abstract interface CENUM_IS_CREATOR_STATUS {
		public static final EnumerationWrapper ENUM = new EnumerationWrapper("cenum_is_creator_status");
		public static final IEnumerationItem NEW = EnumerationsCustom.lookupEnumerationItem(ENUM, "new");
		public static final IEnumerationItem IN_REVIEW = EnumerationsCustom.lookupEnumerationItem(ENUM, "in_review");
	}
	
	public static abstract interface CENUM_IS_OWNER_STATUS {
		public static final EnumerationWrapper ENUM = new EnumerationWrapper("cenum_is_owner_status");
		public static final IEnumerationItem IN_REVIEW = EnumerationsCustom.lookupEnumerationItem(ENUM, "in_review");
		public static final IEnumerationItem OPEN = EnumerationsCustom.lookupEnumerationItem(ENUM, "open");
		public static final IEnumerationItem IN_PROGRESS = EnumerationsCustom.lookupEnumerationItem(ENUM, "in_progress");
		public static final IEnumerationItem FUP = EnumerationsCustom.lookupEnumerationItem(ENUM, "fup");
		public static final IEnumerationItem OPEN_FOR_EXECUTION = EnumerationsCustom.lookupEnumerationItem(ENUM, "open_for_execution");
		public static final IEnumerationItem ATTENDED = EnumerationsCustom.lookupEnumerationItem(ENUM, "attended");
		public static final IEnumerationItem RISK_ASSUMED = EnumerationsCustom.lookupEnumerationItem(ENUM, "risk_assumed");
		public static final IEnumerationItem SETTLED = EnumerationsCustom.lookupEnumerationItem(ENUM, "settled");
	}
	
	public static abstract interface CENUM_IS_REVIEWER_STATUS {
		public static final EnumerationWrapper ENUM = new EnumerationWrapper("cenum_is_reviewer_status");
		public static final IEnumerationItem TO_BE_REVIEWED = EnumerationsCustom.lookupEnumerationItem(ENUM, "to_be_reviewed");
		public static final IEnumerationItem ATTENDED = EnumerationsCustom.lookupEnumerationItem(ENUM, "attended");
		public static final IEnumerationItem RISK_ASSUMED = EnumerationsCustom.lookupEnumerationItem(ENUM, "risk_assumed");
		public static final IEnumerationItem SETTLED = EnumerationsCustom.lookupEnumerationItem(ENUM, "settled");
		public static final IEnumerationItem FUP = EnumerationsCustom.lookupEnumerationItem(ENUM, "fup");
	}
	
	public static abstract interface CENUM_AP_CREATOR_STATUS {
		public static final EnumerationWrapper ENUM = new EnumerationWrapper("cenum_ap_creator_status");
		public static final IEnumerationItem NEW = EnumerationsCustom.lookupEnumerationItem(ENUM, "new");
		public static final IEnumerationItem PENDING = EnumerationsCustom.lookupEnumerationItem(ENUM, "pending");
		public static final IEnumerationItem IN_PROGRESS = EnumerationsCustom.lookupEnumerationItem(ENUM, "in_progress");
		public static final IEnumerationItem FUP = EnumerationsCustom.lookupEnumerationItem(ENUM, "fup");
		public static final IEnumerationItem RISK_ASSUMED = EnumerationsCustom.lookupEnumerationItem(ENUM, "risk_assumed");
	}
	
	public static abstract interface CENUM_AP_OWNER_STATUS {
		public static final EnumerationWrapper ENUM = new EnumerationWrapper("cenum_ap_owner_status");
		public static final IEnumerationItem IN_PROGRESS = EnumerationsCustom.lookupEnumerationItem(ENUM, "in_progress");
		public static final IEnumerationItem PENDING = EnumerationsCustom.lookupEnumerationItem(ENUM, "pending");
		public static final IEnumerationItem FUP = EnumerationsCustom.lookupEnumerationItem(ENUM, "fup");
		public static final IEnumerationItem RISK_ASSUMED = EnumerationsCustom.lookupEnumerationItem(ENUM, "risk_assumed");
	}
	
	public static abstract interface CENUM_AP_REVIEWER_STATUS {
		public static final EnumerationWrapper ENUM = new EnumerationWrapper("cenum_ap_reviewer_status");
		public static final IEnumerationItem FUP = EnumerationsCustom.lookupEnumerationItem(ENUM, "fup");
		public static final IEnumerationItem NOT_APPROVED = EnumerationsCustom.lookupEnumerationItem(ENUM, "not_approved");
		public static final IEnumerationItem SETTLED = EnumerationsCustom.lookupEnumerationItem(ENUM, "settled");
		public static final IEnumerationItem ATTENDED = EnumerationsCustom.lookupEnumerationItem(ENUM, "attended");
		public static final IEnumerationItem RISK_ASSUMED = EnumerationsCustom.lookupEnumerationItem(ENUM, "risk_assumed");
	}

}
