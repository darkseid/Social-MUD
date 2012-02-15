var SMUD = SMUD || {};

(function(){
	
	
	
	SMUD.sendCommand = function() {

		var command = $("#command").attr("value");

		$.ajax({
			type : "GET",
			url : 'command.do',
			data : "command=" + command,
			complete : function(data) {
				//print results as appended 
				var response = $.parseJSON(data.responseText);
				$("#board").append(response.responses[0]).append("<br/>");
			}
		});
	};

	console.log('passou');

}());

console.log("passou tb" );