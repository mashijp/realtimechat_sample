<html>
<body>
	<h2>Hello World!</h2>
	<script type="text/javascript">
		var ws = new WebSocket("ws://localhost:8080/realtimechat_sample/api/chat/web_socket");
		ws.onopen = function(){
			alert("open!");
			setTimeout(function(){
				var message = "";
				for(var i=0; i<1000; i++){
					message += "hogefugahoge\nhoge";
				}
				ws.send(message);
			},1000);
		};
	</script>
</body>
</html>
