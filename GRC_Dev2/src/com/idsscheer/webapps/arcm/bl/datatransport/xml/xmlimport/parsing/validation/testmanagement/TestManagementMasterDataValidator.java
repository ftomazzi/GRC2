package com.idsscheer.webapps.arcm.bl.datatransport.xml.xmlimport.parsing.validation.testmanagement;

import com.idsscheer.webapps.arcm.bl.datatransport.xml.AAMMLLogger;
import com.idsscheer.webapps.arcm.bl.datatransport.xml.resources.AAMMLResourceBundle;
import com.idsscheer.webapps.arcm.bl.datatransport.xml.xmlimport.adapter.IValidationAdapter;
import com.idsscheer.webapps.arcm.bl.datatransport.xml.xmlimport.parsing.validation.ValidationContentHandler;
import com.idsscheer.webapps.arcm.dl.framework.BusSessionException;
import com.idsscheer.webapps.arcm.dl.framework.DataLayerObjectType;
import com.idsscheer.webapps.arcm.dl.framework.IDataLayerTransaction;
import com.idsscheer.webapps.arcm.dl.framework.dbms.CDBMSException;
import com.idsscheer.webapps.arcm.dl.framework.dbms.DBMSLayer;
import com.idsscheer.webapps.arcm.dl.framework.dbms.IDBMSAccess;
import com.idsscheer.webapps.arcm.dl.framework.dbms.IDBMSLayer;
import com.idsscheer.webapps.arcm.dl.framework.dbms.IQuery;
import com.idsscheer.webapps.arcm.dl.framework.dbms.IQueryEngine;
import com.idsscheer.webapps.arcm.dl.framework.dbms.logic.CompareOperatorEnum;
import com.idsscheer.webapps.arcm.dl.framework.dbms.logic.CompoundCondition;
import com.idsscheer.webapps.arcm.dl.framework.dbms.logic.Condition;
import com.idsscheer.webapps.arcm.dl.framework.dbms.logic.ConditionConnectEnum;
import com.idsscheer.webapps.arcm.dl.framework.dbms.logic.JoinCondition;
import com.idsscheer.webapps.arcm.dl.framework.dbms.logic.QueryTable;
import com.idsscheer.webapps.arcm.dl.framework.dbms.mapping.IMapping;
import com.idsscheer.webapps.arcm.dl.framework.dbms.parameter.LongParameter;
import com.idsscheer.webapps.arcm.dl.framework.dbms.parameter.StringParameter;
import com.idsscheer.webapps.arcm.dl.framework.dllogic.DataLayerTransaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

public abstract class TestManagementMasterDataValidator implements IValidationAdapter {
	private ValidationContentHandler validator;
	protected String parent_obj_type = "";
	protected String child_obj_type = "";
	protected Long parent2child_link_id = Long.valueOf(0L);

	protected boolean xmlInvalid = false;

	protected Map<String, String> hmXMLObjID2GUID = new HashMap();
	protected Map<String, String> hmChildGUID2ParentGUID = new HashMap();
	protected String currentParentGUID;
	protected Map<String, Integer> hmParentGUID2XMLLine = new HashMap();

	protected final AAMMLResourceBundle rsBundle = AAMMLResourceBundle.getBundle(null);

	public TestManagementMasterDataValidator() {
		initParams();
	}

	public void setHandler(ValidationContentHandler validator) {
		this.validator = validator;
	}

	public void setAttr(String obj_type, String name, String value) {
	}

	public void startElement(String obj_type, String tname, Attributes attrs) {
		if (this.child_obj_type.equals(obj_type)) {
			if (tname.equals("Object")) {
				String childXMLObjID = attrs.getValue("obj_id");
				String childGUID = attrs.getValue("guid");
				this.hmXMLObjID2GUID.put(childXMLObjID, childGUID);
			}

		} else if (this.parent_obj_type.equals(obj_type)) {
			if (tname.equals("Object")) {
				this.currentParentGUID = attrs.getValue("guid");
				this.hmParentGUID2XMLLine.put(this.currentParentGUID, Integer.valueOf(this.validator.getLine()));
			}

			if (!(tname.equals("Link")))
				return;
			String linkId = attrs.getValue("id");

			if (this.parent2child_link_id.toString().equals(linkId)) {
				String childXMLObjId = attrs.getValue("obj_id");
				String childGUID = (String) this.hmXMLObjID2GUID.get(childXMLObjId);
				
				
				if(obj_type.equals("RISK")){ //--> GAP GRC39
					this.hmChildGUID2ParentGUID.put(childGUID, this.currentParentGUID); //--> GAP GRC39
				}else{ //--> GAP GRC39
					//Inicio Standard
					if (this.hmChildGUID2ParentGUID.containsKey(childGUID)) {
						this.validator.reportError(this.rsBundle.getMessage(getMultipleParentsInXMLErrorKey()),
								this.validator.getLine(), this.validator.getColumn());
						this.xmlInvalid = true;
					} else {
						this.hmChildGUID2ParentGUID.put(childGUID, this.currentParentGUID);
					}
					//Fim Standard
				} //--> GAP GRC39

			}

		} else {
			return;
		}
	}

	public void validate(IDataLayerTransaction transaction, String obj_type, int call, String tname) {
		if ((call != 1001) || (!(tname.equals("Objects"))) || (!(obj_type.equalsIgnoreCase(this.parent_obj_type)))) {
			return;
		}
		
		//Inicio GAP39
		if(obj_type.equals("RISK"))
			return;
		
		//Fim GAP39

		validation(transaction);
	}

	public void validation(IDataLayerTransaction transaction) {
		if (this.xmlInvalid)
			return;

		for (String childGUID : this.hmChildGUID2ParentGUID.keySet()) {
			ResultSet resultSet = null;
			try {
				resultSet = executeView(transaction, childGUID, resultSet);

				if (resultSet.next()) {
					String parentGUIDInXML = (String) this.hmChildGUID2ParentGUID.get(childGUID);
					Integer parentLine = (Integer) this.hmParentGUID2XMLLine.get(parentGUIDInXML);
					this.validator.reportError(this.rsBundle.getMessage(getDifferentParentInDatabaseErrorKey()),
							parentLine.intValue(), 0);
				}
			} catch (Exception e) {
				AAMMLLogger.error(super.getClass().getName(), this.rsBundle.getMessage("error.view.execute.ZZZ"), e);
			} finally {
				try {
					resultSet.getStatement().close();
					resultSet.close();
				} catch (SQLException e) {
					AAMMLLogger.error(super.getClass().getName(),
							this.rsBundle.getMessage("error.result.set.closing.ZZZ"), e);
				}

			}

		}

		this.hmXMLObjID2GUID = new HashMap();
		this.hmChildGUID2ParentGUID = new HashMap();
		this.currentParentGUID = null;
	}

	private ResultSet executeView(IDataLayerTransaction transaction, String childGUID, ResultSet resultSet)
			throws CDBMSException, BusSessionException {
		IQueryEngine qe = DBMSLayer.getInstance().getMapping().getAccess().createQueryEngine();
		IQuery query = qe.query("importValidationQuery", 2, null);

		QueryTable mainObject = new QueryTable(query, null, "OBJ2OBJ", false);
		mainObject.setTableAlias("o2o_table");

		QueryTable parentObject = new QueryTable(query, null, DataLayerObjectType.OBJECT.getName(), false);
		parentObject.setTableAlias("parent_object_table");
		JoinCondition parentJoinCondition = new JoinCondition(parentObject, "OBJ_ID", mainObject, "PARENT_ID",
				JoinCondition.INNER, null, CompareOperatorEnum.EQ);
		query.addTable(parentObject, parentJoinCondition);
		QueryTable childObject = new QueryTable(query, null, DataLayerObjectType.OBJECT.getName(), false);
		childObject.setTableAlias("child_object_table");
		JoinCondition childJoinCondition = new JoinCondition(childObject, "OBJ_ID", mainObject, "CHILD_ID",
				JoinCondition.INNER, null, CompareOperatorEnum.EQ);
		query.addTable(childObject, childJoinCondition);

		Condition childGUIDCondition = new Condition(childObject, "GUID", CompareOperatorEnum.EQ,
				new StringParameter(childGUID, false), null, false, false);
		Condition linkTypeCondition = new Condition(mainObject, "LINK_TYPE", CompareOperatorEnum.EQ,
				new LongParameter(this.parent2child_link_id, false), null, false, false);
		Condition noVersionOutCondition = new Condition(mainObject, "PARENT_VERSION_NUMBER_OUT",
				CompareOperatorEnum.ISNULL, null, null, false, false);
		String parentGUID = (String) this.hmChildGUID2ParentGUID.get(childGUID);
		Condition parentGUIDCondition = new Condition(parentObject, "GUID", CompareOperatorEnum.NE,
				new StringParameter(parentGUID, false), null, false, false);

		CompoundCondition condition = new CompoundCondition(childGUIDCondition, ConditionConnectEnum.AND,
				linkTypeCondition);
		condition = new CompoundCondition(condition, ConditionConnectEnum.AND, noVersionOutCondition);
		condition = new CompoundCondition(condition, ConditionConnectEnum.AND, parentGUIDCondition);

		query.setCondition(condition);

		resultSet = DBMSLayer.getInstance().executeViewQuery(query, ((DataLayerTransaction) transaction).getSession(),
				false);
		return resultSet;
	}

	public abstract String getMultipleParentsInXMLErrorKey();

	public abstract String getDifferentParentInDatabaseErrorKey();

	public abstract void initParams();
}