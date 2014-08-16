update equipmentclassification as a inner join equipmentclassification as b on a.parentid = b.classificationid
set a.parentid = b.comid where a.parentid != 0;

update equipment set classificationid = (select comid from equipmentclassification where classificationid = equipment.classificationid);