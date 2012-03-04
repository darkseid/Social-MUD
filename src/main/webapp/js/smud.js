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
					$("#board").append("<span class=" + response.responses[i].color + ">" + response.responses[i].text + "</span>").append("<br/>");
				}
			}
		});
	};

	console.log('passou');

}());

console.log("passou tb" );