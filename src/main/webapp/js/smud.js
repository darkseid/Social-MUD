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
					$("#board").scrollTop($("#board")[0].scrollHeight);
				}
			}
		});
	};

	SMUD.retrieveMessages = function() {
		$.ajax({
			type : "GET",
			url : 'retrieveMessages.do',
			complete : function(data) {
				//print results as appended 
				var response = $.parseJSON(data.responseText);
				for (var i = 0; i < response.responses.length; i++) {
					$("#board").append("<span class=" + response.responses[i].color + ">" + response.responses[i].text + "</span>").append("<br/>");
					$("#board").scrollTop($("#board")[0].scrollHeight);
				}
				setTimeout("SMUD.retrieveMessages()",1000);
			}
		});
	};

}());