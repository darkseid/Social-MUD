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
				for (var i = 0; i < response.responses.length; i++) {
					$("#board").append(response.responses[i].text).append("<br/>");
				}
			}
		});
	};

	console.log('passou');

}());

console.log("passou tb" );