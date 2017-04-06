package com.idsscheer.webapps.arcm.bl.component.riskmanagement.util;

public class CustomImpacttypeCopyHelper {
/*
	private static Map<String, String> translationMap = new HashMap();

	//private Log log = LogFactory.getLog(???.getClass());
	private final IChainContext chainContext;
	private final IUserContext userContext;

	public CustomImpacttypeCopyHelper(IChainContext p_chainContext, IUserContext p_userContext) {
		this.userContext = p_userContext;
		this.chainContext = p_chainContext;
		if (translationMap.isEmpty())
			initTranslationMap();
	}

	private synchronized void initTranslationMap() {
		if (!(translationMap.isEmpty()))
			return;
		IResourceBundle bundle = ResourceBundleFactory.getBundle(Locale.ENGLISH);

		for (QUOTATIONS quotation : QUOTATIONS.values()) {
			translationMap.put(
					"Q" + bundle.getString(new StringBuilder().append("riskmanagement.quotation.")
							.append(quotation.name().toLowerCase(Locale.ENGLISH)).append(".DBI").toString()),
					quotation.name().toLowerCase(Locale.ENGLISH));
		}
		for (FREQUENCIES frequency : FREQUENCIES.values())
			translationMap.put(
					"F" + bundle.getString(new StringBuilder().append("riskmanagement.frequency.")
							.append(frequency.name().toLowerCase(Locale.ENGLISH)).append(".DBI").toString()),
					frequency.name().toLowerCase(Locale.ENGLISH));
	}

	public IImpacttypeAppObj getCurrentSnapshot(IAppObj impactType, String clientSign) {
		long originalImpactTypeID = impactType.getVersionData().getOVID().getId();

		IAppObjFacade facade = FacadeFactory.getInstance().getAppObjFacade(this.userContext, ObjectType.IMPACTTYPE);
		IAppObjQuery query = facade.createQuery();
		query.addRestriction(QueryRestriction.eq(IImpacttypeAttributeType.ATTR_ORIGINALIMPACTTYPEOBJECTID,
				Long.valueOf(originalImpactTypeID)));
		query.addRestriction(QueryRestriction.eq(IImpacttypeAttributeType.ATTR_ISSNAPSHOT, Boolean.TRUE));
		query.addRestriction(QueryRestriction.eq(IImpacttypeAttributeType.ATTR_ISNEWESTSNAPSHOT, Boolean.TRUE));
		query.addClientRestriction(new String[] { clientSign });
		return ((IImpacttypeAppObj) query.getSingleResult());
	}

	public IAppObj createSnapshot(IImpacttypeAppObj impactType, IClientAppObj client) throws BLException {
		IImpacttypeAppObj newSnapshot = (IImpacttypeAppObj) this.chainContext.create(ObjectType.IMPACTTYPE, true);
		newSnapshot.getAttribute(IObjectAttributeType.BASE_ATTR_CLIENT_SIGN).setRawValue(client);

		ChainContext internalCC = new ChainContext((ChainContext) this.chainContext, newSnapshot, this.userContext);

		copy(impactType, newSnapshot, internalCC);

		markAsSnapshot(impactType, newSnapshot);

		saveAtCC(newSnapshot, internalCC);

		this.chainContext.save(newSnapshot, true);
		this.chainContext.releaseWriteLock(newSnapshot);
		return newSnapshot;
	}

	public void copy(IImpacttypeAppObj originalImpactType, IImpacttypeAppObj newSnapshot, IChainContext internalCC)
			throws BLException {
		IResourceBundle bundle = ResourceBundleFactory.getBundle(this.chainContext.getLocale());
		IClientAppObj rawValue = newSnapshot.getRawValue(IImpacttypeAttributeType.ATTR_RELATED_CLIENT);
		LanguageEnumerationItem languageItem;
		//LanguageEnumerationItem languageItem;
		if (rawValue == null) {
			IEnumAttribute languageEnumAttr = ServerContext.getInstance().getSite()
					.getAttribute(ISiteAttributeType.ATTR_LANGUAGE);
			languageItem = (LanguageEnumerationItem) languageEnumAttr.getRawValue().get(0);
		} else {
			IEnumAttribute languageEnumAttr = rawValue.getAttribute(IClientAttributeType.ATTR_LANGUAGE);
			languageItem = (LanguageEnumerationItem) languageEnumAttr.getRawValue().get(0);
		}

		boolean isDefault = originalImpactType.getRawValue(IImpacttypeAttributeType.ATTR_ISDEFAULT).booleanValue();
		String originalName;
		//String originalName;
		if (isDefault)
			originalName = translateImpactType(languageItem.getLocale());
		else {
			originalName = originalImpactType.getAttribute(IImpacttypeAttributeType.ATTR_NAME).getRawValue();
		}

		String copyName = bundle.getString("riskmanagement.impact.type.copy.name.DBI", new String[] { originalName });
		newSnapshot.getAttribute(IImpacttypeAttributeType.ATTR_NAME).setRawValue(copyName);
		AppObjUtility.copyAttributeValue(originalImpactType, newSnapshot, IImpacttypeAttributeType.ATTR_DESCRIPTION);
		AppObjUtility.copyAttributeValue(originalImpactType, newSnapshot,
				IImpacttypeAttributeType.ATTR_RELATEDOBJECTID);

		internalCC.setWorkObject("relatedObjectId", Long.valueOf(newSnapshot.getObjectId()));

		copyQuotations(originalImpactType, newSnapshot, internalCC, languageItem.getLocale());
		copyFrequencies(originalImpactType, newSnapshot, internalCC, languageItem.getLocale());
		new MatrixColorsUtils().migrateMatrixColors(originalImpactType, newSnapshot);
	}

	private void copyQuotations(IAppObj p_originalImpactType, IAppObj p_newSnapshot, IChainContext internalCC,
			Locale locale) throws BLException {
		List<IAppObj> elements = p_originalImpactType.getAttribute(IImpacttypeAttributeType.LIST_QUOTATIONS)
				.getElements(this.userContext);
		IListAttribute quotationsAttr = p_newSnapshot.getAttribute(IImpacttypeAttributeType.LIST_QUOTATIONS);
		for (IAppObj originalQuotation : elements) {
			IAppObj newQuotation = internalCC.create(ObjectType.QUOTATION, false);
			boolean isDefault = originalQuotation.getRawValue(IQuotationAttributeType.ATTR_ISDEFAULT).booleanValue();
			if (isDefault) {
				String originalName = originalQuotation.getRawValue(IQuotationAttributeType.ATTR_NAME);
				newQuotation.getAttribute(IQuotationAttributeType.ATTR_NAME)
						.setRawValue(translateQuotation(originalName, locale));
			} else {
				AppObjUtility.copyAttributeValue(originalQuotation, newQuotation, IQuotationAttributeType.ATTR_NAME);
			}
			AppObjUtility.copyAttributeValue(p_newSnapshot, newQuotation, IImpacttypeAttributeType.ATTR_RELATED_CLIENT,
					IQuotationAttributeType.ATTR_RELATED_CLIENT);
			AppObjUtility.copyAttributeValue(originalQuotation, newQuotation, IQuotationAttributeType.ATTR_DESCRIPTION);
			AppObjUtility.copyAttributeValue(originalQuotation, newQuotation, IQuotationAttributeType.ATTR_VALUE);
			AppObjUtility.copyAttributeValue(originalQuotation, newQuotation, IQuotationAttributeTypeCustom.ATTR_HEIGHT); //Customização GAP GRC29
			newQuotation.getAttribute(IQuotationAttributeType.ATTR_RELATEDOBJECTID)
					.setRawValue(p_newSnapshot.getObjectId());
			newQuotation.getAttribute(IQuotationAttributeType.ATTR_ORIGINALQUOTATION)
					.setRawValue(originalQuotation.getObjectId());
			quotationsAttr.addLastElement(newQuotation, internalCC.getUserContext());
		}
	}

	private void copyFrequencies(IAppObj originalImpactType, IAppObj newSnapshot, IChainContext internalCC,
			Locale locale) throws BLException {
		List<IAppObj> elements = originalImpactType.getAttribute(IImpacttypeAttributeType.LIST_FREQUENCIES)
				.getElements(this.userContext);
		IListAttribute frequencisAttr = newSnapshot.getAttribute(IImpacttypeAttributeType.LIST_FREQUENCIES);
		for (IAppObj originalFrequency : elements) {
			IAppObj newFrequency = internalCC.create(ObjectType.FREQUENCY, false);
			boolean isDefault = originalFrequency.getRawValue(IFrequencyAttributeType.ATTR_ISDEFAULT).booleanValue();
			if (isDefault) {
				String originalName = originalFrequency.getRawValue(IFrequencyAttributeType.ATTR_NAME);
				newFrequency.getAttribute(IFrequencyAttributeType.ATTR_NAME)
						.setRawValue(translateFreqency(originalName, locale));
			} else {
				AppObjUtility.copyAttributeValue(originalFrequency, newFrequency, IFrequencyAttributeType.ATTR_NAME);
			}
			AppObjUtility.copyAttributeValue(newSnapshot, newFrequency, IImpacttypeAttributeType.ATTR_RELATED_CLIENT,
					IFrequencyAttributeType.ATTR_RELATED_CLIENT);
			AppObjUtility.copyAttributeValue(originalFrequency, newFrequency, IFrequencyAttributeType.ATTR_DESCRIPTION);
			AppObjUtility.copyAttributeValue(originalFrequency, newFrequency, IFrequencyAttributeType.ATTR_VALUE);
			newFrequency.getAttribute(IFrequencyAttributeType.ATTR_RELATEDOBJECTID)
					.setRawValue(newSnapshot.getObjectId());
			newFrequency.getAttribute(IFrequencyAttributeType.ATTR_ORIGINALFREQUENCY)
					.setRawValue(originalFrequency.getObjectId());
			frequencisAttr.addLastElement(newFrequency, internalCC.getUserContext());
		}
	}

	private void markAsSnapshot(IAppObj originalImpactType, IAppObj newSnapshot) {
		AppObjUtility.copyAttributeValue(originalImpactType, newSnapshot, IImpacttypeAttributeType.ATTR_NAME);
		newSnapshot.getAttribute(IImpacttypeAttributeType.ATTR_ISSNAPSHOT).setRawValue(Boolean.TRUE);
		newSnapshot.getAttribute(IImpacttypeAttributeType.ATTR_ISNEWESTSNAPSHOT).setRawValue(Boolean.TRUE);
		newSnapshot.getAttribute(IImpacttypeAttributeType.ATTR_ORIGINALIMPACTTYPEOBJECTID)
				.setRawValue(originalImpactType.getObjectId());
		newSnapshot.getAttribute(IImpacttypeAttributeType.ATTR_ORIGINALIMPACTTYPEVERSION)
				.setRawValue(originalImpactType.getVersionData().getOVID().getVersion());
		for (IAppObj quotation : newSnapshot.getAttribute(IImpacttypeAttributeType.LIST_QUOTATIONS)
				.getElements(this.userContext)) {
			quotation.getAttribute(IQuotationAttributeType.ATTR_ISSNAPSHOT).setRawValue(Boolean.TRUE);
		}
		for (IAppObj frequency : newSnapshot.getAttribute(IImpacttypeAttributeType.LIST_FREQUENCIES)
				.getElements(this.userContext))
			frequency.getAttribute(IFrequencyAttributeType.ATTR_ISSNAPSHOT).setRawValue(Boolean.TRUE);
	}

	public void saveAtCC(IAppObj newSnapshot, IChainContext internalCC) throws BLException {
		for (IAppObj quotation : newSnapshot.getAttribute(IImpacttypeAttributeType.LIST_QUOTATIONS)
				.getElements(this.userContext)) {
			internalCC.save(quotation, true);
			internalCC.releaseWriteLock(quotation);
		}
		for (IAppObj frequency : newSnapshot.getAttribute(IImpacttypeAttributeType.LIST_FREQUENCIES)
				.getElements(this.userContext)) {
			internalCC.save(frequency, true);
			internalCC.releaseWriteLock(frequency);
		}
	}

	public String translateQuotation(String originalName, Locale locale) {
		IResourceBundle bundle = ResourceBundleFactory.getBundle(locale);
		String key = (String) translationMap.get("Q" + originalName);
		return bundle.getString("riskmanagement.quotation." + key + ".DBI");
	}

	public String translateFreqency(String originalName, Locale locale) {
		IResourceBundle bundle = ResourceBundleFactory.getBundle(locale);
		String key = (String) translationMap.get("F" + originalName);
		return bundle.getString("riskmanagement.frequency." + key + ".DBI");
	}

	public String translateImpactType(Locale locale) {
		IResourceBundle bundle = ResourceBundleFactory.getBundle(locale);
		return bundle.getString("riskmanagement.impact.type.name.default.DBI");
	}

	private static enum FREQUENCIES {
		AVERAGE, HIGH, LOW, VERY_HIGH, VERY_LOW;
	}

	private static enum QUOTATIONS {
		AVERAGE, CATASTROPHIC, HIGH, INSIGNIFICANT, LOW;
	}	
	*/
}
