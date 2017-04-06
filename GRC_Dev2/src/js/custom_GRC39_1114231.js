$(document).ready( function(){
	var progress = $('#progress').val();
	if(progress === undefined || progress === ''){
		aam_objectcommand('customriskload');
		return;
	}
})
/*
$(document).ready( function(){
	aam_objectcommand('cache');
})

*/
//setTimeout(function(){ aam_objectcommand('cache'); }, 2000);