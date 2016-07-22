<html>

<head>
	<title> Welcome to Tokbox webRTC </title>
	
	<style>
	h1 {background-color: green; padding:15px;}
	div {background-color: lightblue;}
	form {background-color: lightblue; padding:15px;}
	button {background-color: pink; padding:5px;}
	</style>

</head>

<body>
	<h1> Welcome to WebRTC Application </h1>
	
	<button type = "button" name = "btn" id = "createSession" onclick = "this.disabled=true; document.getElementById('createSessionDiv').hidden=false; document.getElementById('createSession').hidden=true;"> Let's Begin </button>
    
    <div id="createSessionDiv" hidden="true">
       	<form action="CreateSession">
       	 Enter the room name: <input type = "text" name = "room" onblur = "checkTextField(this);">
		 <input type = "submit" name = "create" id = "createBTN" value = "Create" disabled>
	    </form>
    </div>
	
	<script type = "text/javascript">
	
	 function checkTextField(field) {
	     if (field.value == '') {
	         alert("Room name cannot be empty");
		     document.getElementById('createBTN').disabled   = true;
	     }
	     else{
	    	document.getElementById('createBTN').disabled   = false;
	     }
	 }
	 
	</script>
        
</body>
</html>