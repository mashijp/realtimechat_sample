<html>
<head>

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		jQuery(function($){
			var ws = new WebSocket("ws://localhost:8080/realtimechat_sample/api/chat/web_socket");
			ws.onopen = function(){
				console.log("websocket open.");
			};
		
			$("#messageForm").submit(function(event){
				ws.send($("#message").val());
				$("#message").val("");
				$("#message").focus();
				return false;
			});
			
			ws.onmessage = function(event){
				$("#log").prepend($("<div>").text(event.data));
				while($("#log>div").size() > 100){
					$("#log>div:last").remove();
				}
			};			
		});
	</script>
</head>
<body>
	<h2>Hello World!</h2>
	
	<form id="messageForm">
		<input type="text" id="message" style="width: 400px;">
		<input type="submit" id="send" value="send">
	</form>
	
	<div id="log">
	
	</div>
	
</body>
</html>
