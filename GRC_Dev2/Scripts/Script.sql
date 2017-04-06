select * from a_object_tbl where obj_type = 'QUOTATION'

select * from a_obj2obj_tbl where link_type = 5800

select * from a_quotation_tbl

select * from a_link_type_tbl where "name" like '%sys%' order by id 

insert into arcm_default_rba.a_link_type_tbl(id,name) values('99000','customsys');

select startdate, enddate from a_risk_tbl where guid = '457042c1-95ff-11d5-ae93-000476109cf8';

select startdate from a_risk_tbl where risk_id = 'RISK01';

select a_risk_tbl.ra_result, version_active from a_risk_tbl where risk_id = 'RP12';

alter table a_risk_tbl alter column cause type text;

alter table a_risk_tbl alter column consequence type text;