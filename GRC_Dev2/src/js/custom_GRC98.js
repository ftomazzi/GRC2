$(document).ready( function(){

	var ra_residual1line = $('#ra_residual1line').val();
	var ra_result = $('#ra_result').val();
	
	if(ra_result === undefined || ra_result === ''){
		console.log('RA_RESULT É VAZIO')
		aam_objectcommand('customcachersk');
		return;	
	}
	if(ra_residual1line === undefined || ra_residual1line === ''){
		console.log('RA_RESIDUAL1LINE É VAZIO')
		aam_objectcommand('customcachersk');
		return;
	}


	var risk_id = $('#risk_id').val();
	var risk_name = $('#name').val();
	
	$('#tr_residualrisk_id').find("td.TEXTFIELD_WRITE").text(risk_id);
	$('#tr_residualrisk_name').find("td.TEXTFIELD_WRITE").text(risk_name);

})


/*
$(document).ready( function(){
	aam_objectcommand('cache');
})

*/
//setTimeout(function(){ aam_objectcommand('cache'); }, 2000);