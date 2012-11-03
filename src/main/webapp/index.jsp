<html>
<body>
	<h2>Hello World!</h2>
	<script type="text/javascript">
		var ws = new WebSocket("ws://localhost:8080/realtimechat_sample/api/chat/web_socket");
		ws.onopen = function(){
			alert("open!");
			setInterval(function(){
				ws.send("now time is "+new Date());
			},1000);
		};
	</script>
</body>
</html>
