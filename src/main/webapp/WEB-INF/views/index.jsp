<html>
<head>
<title>Social MUD</title>
<link rel="stylesheet" type="text/css" href="../css/mud.css" />
<script src="../js/smud.js"></script>
<script src="http://code.jquery.com/jquery-1.7.1.js"></script>

</head>
<body onload="SMUD.retrieveMessages()">

	<div class="journalbox" id="board">

		<h2>MUD</h2>

		Bem vindo<br> <br>

	</div>

  <fieldset>
    <label for="command">Type Something:</label>
    <input id="command" type="text" />
  </fieldset>

</body>


<script>
$("#command").bind("keypress", function(e) {
	var code = (e.keyCode ? e.keyCode : e.which);
	if (code == 13) { //Enter keycode
		console.log("enter pressed");
		SMUD.sendCommand();
		$("#board").append("<span class=WHITE>" + $("#command").attr("value") + "</span><br/>");
		$("#command").val("");
	}
});
</script>
</html>
