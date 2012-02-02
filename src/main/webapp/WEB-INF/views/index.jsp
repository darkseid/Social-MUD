<html>
<head>
<title>Social MUD</title>
<link rel="stylesheet" type="text/css" href="../css/mud.css" />
<script src="http://code.jquery.com/jquery-1.7.1.js"></script>

<script>

	function sendCommand() {

		var command = $("#command").attr("value");

		$.ajax({
			type : "GET",
			url : 'command.do',
			data : "command=" + command,
			complete : function(data) {
				//print results as appended 
				console.log(data.items);
				var response = $.parseJSON(data.responseText);
				$("#board").append(response.responses[0]).append("<br/>");
			}
		});
	}

</script>


</head>
<body>

	<div class="journalbox" id="board">

		<h2>MUD</h2>

		Bem vindo<br> <br>

	</div>

  <fieldset>
    <label for="command">Type Something:</label>
    <input id="command" type="text" />
  </fieldset>

</body>


</html>

<script>
$("#command").bind("keypress", function(e) {
	var code = (e.keyCode ? e.keyCode : e.which);
	if (code == 13) { //Enter keycode
		console.log("enter pressed");
		sendCommand();
		$("#command").val("");
	}
});
</script>