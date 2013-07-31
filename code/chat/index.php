<?
	session_start();

	if(isset($_GET['logout'])){            									
		session_destroy();
		header("Location: index.php"); //Redirect the user }
	}
	if(isset($_GET['admin'])){
		$_SESSION['name'] ='admin';
	}
	else{
		$_SESSION['name'] ='user';
	}
?>

<!DOCTYPE html>
<html>
	<head>
		<title>A Healthy Choice</title>
		<link type="text/css" rel="stylesheet" href="chatStyle.css" />		
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!--
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
-->
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>		
		<script>
			var user = "";
			var agentName;
			var agentPass;
			var userPass;
			$(document).ready(function(){
				var ip;
				var refreshIntervalId;
				var refreshIntervalType;
				var refreshintervalFindUser;
				var refreshSwitchid;
				var strat;
				//var why;
										
			 <?php 
				if($_SESSION['name']!='admin'){
			 ?>
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#dialog").hide();
					$("#commentsFormatAdmin").hide();				
					$("#whySwitch").hide();
					$("#whyNoSwitch").hide();
					$("#PostQuest").hide();
					$("#PostQuest3").hide();
					$( "#userDialog" ).dialog({
						modal: true,
						buttons: {
						Login: function() {
							if($("#userPassword").val().toLowerCase() == "chat" || $("#userPassword").val().toLowerCase() == "test" || $("#userPassword").val().toLowerCase() == "pilot" || $("#userPassword").val().toLowerCase() == "face" || $("#userPassword").val().toLowerCase() == "dialogue")  {
								userPass = $("#userPassword").val().toLowerCase();
								$( this ).dialog( "close" ); 
									$("#dialog").hide();
									$("#welcome").show();
									$("#QuestFormat").hide();
									$("#ChatFormat").hide();
									$("#ChatFormatAdmin").hide();
									$("#commentsFormatAdmin").hide();	
									$("#PostQuest").hide();
									$("#PostQuest3").hide();     
								}
								else
									alert("Incorrect Password. Please enter again.");
							}							
						}
					});		
					
					$("#continue").mouseenter(function(){
						$("#continue").fadeTo("fast", 1.0);
					});
					$("#continue").mouseleave(function(){
						$("#continue").fadeTo("fast", 0.5);
					});
		
					$("#continue").on('click',function(){
						$("#welcome").hide();
						$("#QuestFormat").show();
						$("#ChatFormat").hide();
						$("#PostQuest").hide();
						$("#PostQuest3").hide();
					});
					
					$("#QuestSubmit").mouseenter(function(){
						$("#QuestSubmit").fadeTo("fast", 1.0);
					});
					$("#QuestSubmit").mouseleave(function(){
						$("#QuestSubmit").fadeTo("fast", 0.5);
					});
					
					$("#QuestSubmit").click(function(){
						if($("#SocialNetwork").val() == "" || $("#Age").val() == "" || $("#Position").val() == "" || $("#Ethnicity").val() == "" || $("#Gender").val() == "" || $("#Exercise1").val() == "" || $("#Exercise2").val() == "" || ($('input[name=taichi1]:checked').length == 0)  || ($('input[name=taichi2]:checked').length == 0)){
							$("#errorMessage1").html("Please fill out all fields and try again.").show();
						}
						else {
							var pos = $("#Position").val();
							var sex = $("#Gender").val();
							var race = $("#Ethnicity").val();
							var age = $("#Age").val();
							var ex1 = $("#Exercise1").val();
							var ex2 = $("#Exercise2").val();
							var soc = $("#SocialNetwork").val();
							var pre1 = $('input:radio[name=taichi1]:checked').val();
							var pre2 = $('input:radio[name=taichi2]:checked').val();			
											
/*		THE VALUES FROM THE PRE-CHAT QUESTIONNAIRE WILL BE SENT TO AND
 *		INSERTED IN THE DATABASE.
 */							
							var request0 = $.ajax({ 
								url: "database.php", 
								async: true, 
								type: "POST", 
								data: {pass: userPass, position: pos, gender: sex, ethnicity: race, age: age, exercise1: ex1, exercise2: ex2, socialnetwork: soc, preNeed: pre1, preInterest: pre2, func: 0}, 
								dataType: "html" 
							}).success(function(data) { 
								user = data;
							})//end success
							
							$("#welcome").hide();
							$("#QuestFormat").hide();
							$("#ChatFormat").show();					
							$("#PostQuest").hide();
							$("#PostQuest3").hide();
							
							refreshIntervalId = setInterval (loadLog, 1000);	//Reload convo every 1 seconds
							refreshIntervalType = setInterval (displayTyping, 1000);	//Reload typing message every 1 seconds
						}
						return false;
					});	
													
				<?php 
				}

/*		WHEN THE ADMIN LOGS IN, THE DATABASE IS CALLED TO ASSIGN THE
 *  		ADMIN TO A USER.
 */					
				else{
				?>		
					$("#userDialog").hide();
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormatAdmin").show();
					$("#commentsFormatAdmin").hide();				
					$("#whySwitch").hide();
					$("#whyNoSwitch").show();
					$("#PostQuest").hide();
					$("#PostQuest3").hide();
					$( "#dialog" ).dialog({
						modal: true,
						buttons: {
						Login: function() {
							if($("#adminPassword").val().toLowerCase() == "chat" || $("#adminPassword").val().toLowerCase() == "test" || $("#adminPassword").val().toLowerCase() == "pilot" || $("#adminPassword").val().toLowerCase() == "face" || $("#adminPassword").val().toLowerCase() == "dialogue")  {
								agentName = $("#adminName").val();
								agentPass = $("#adminPassword").val().toLowerCase();
								$( this ).dialog( "close" ); 
								$("#welcome").hide();
								$("#QuestFormat").hide();
								$("#ChatFormatAdmin").show();	
								$("#commentsFormatAdmin").hide();							
								$("#PostQuest").hide();
								$("#PostQuest3").hide();      
							}
							else
								alert("Incorrect Password. Please enter again.");
							}
						}
					});			
					$(".notify").html("Still waiting for a user to log in...");
									
					refreshintervalFindUser = setInterval(findUser, 1000); //checks every second to determine if a user is logged in						
					refreshIntervalId = setInterval (loadLog, 1000);	//Reload convo every 1 seconds
					strat = "RationalPositive";
					$("#switchNo").prop('checked',true);
					$("#strategies").mouseup(function() {
						//alert(strat);
						if(strat == $("#strategies").val()) {
							$("#switchNo").prop('checked',true);
							$("#whySwitch").hide();
							$("#whyNoSwitch").show();
						}
						else {
							$("#switchYes").prop('checked',true);
							$("#whyNoSwitch").hide();
							$("#whySwitch").show();
						}
					});

			<?php			
				}
			?>

/*		DETERMINE IF THERE IS A NEW USER TO ASSIGN TO THE ADMIN
 *
  */ 
			function findUser() {
				// test, chat, dialogue, face, pilot
				if(agentPass == "chat" || agentPass == "test" ||agentPass == "face" || agentPass == "pilot" || agentPass == "dialogue" ) {
					var request1 = $.ajax({
						url: "database.php",
						async: true,
						type: "POST",
						data: {agent: agentName, pass: agentPass, func: 1},
						dataType: "html"
					}).success(function(data) {
						if(data != "notFound") {	
							user = data;		
							$(".notify").hide();
							clearInterval(refreshintervalFindUser);				
							alert("User has logged in. UserID = " + data);				
							refreshIntervalType = setInterval (displayTyping, 1000);	//Reload typing message every 1 seconds			
						}
						else
							user = "notFound";
					})//end success	
				}
				else if(agentPass == "test") {
					var request11 = $.ajax({
						url: "database.php",
						async: true,
						type: "POST",
						data: {agent: agentName, func: 11},
						dataType: "html"
					}).success(function(data) {
						if(data != "notFound") {	
							user = data;		
							$(".notify").hide();						
							clearInterval(refreshintervalFindUser);				
							alert("User has logged in. UserID = " + data);		
							refreshIntervalType = setInterval (displayTyping, 1000);	//Reload typing message every 1 seconds					
						}
						else
							user = "notFound";
					})//end success	
				}				
			}
			
/*		KEYDOWN FUNCTION TO DISPLAY TYPING MESSAGE
 *			
 */	
				$('#usermsg').add('#otherComments').keydown(checkType); // When key pressed
				$('#strategies').add('#whySwitch').mousedown(checkType); // When mouse is clicked
				
				function checkType() {
					var timer = null;
					var request8 = $.ajax({  //turn the clicked attribute to true
						url: "database.php", 
						async: true, 
						type: "POST", 
						data: {userNow: user, func: 8}, 
						dataType: "html" 
					}).success(function(data) { 
					})//end success			
				
					if (timer) {
						clearTimeout(timer); // Clear any previous timer
					}
					timer = setTimeout(function() { // Start timer to expire in 1 seconds
						var request9 = $.ajax({  //turn the clicked attribute to false
							url: "database.php", 
							async: true, 
							type: "POST", 
							data: {userNow: user, func: 9}, 
							dataType: "html" 
						}).success(function(data) { 
						})//end success	
					}, 1000);
				};						
				
				function displayTyping() {
					var request10 = $.ajax({  //turn the clicked attribute to false
						url: "database.php", 
						async: true, 
						type: "POST", 
						data: {userNow: user, func: 10}, 
						dataType: "html" 
					}).success(function(data) { 
						//alert(data);
						if(data == 1 && user != "notFound") {
						<?php
							if($_SESSION['name'] == 'admin') {
						?>
								$('.notify').html('User is typing...').fadeIn('slow'); // Show notification
						<?php	
							}
							else {
						?>
								$('.notify').html('Please wait...').fadeIn('slow'); // Show notification
						<?php
							}
						?>
						}
						else if(data == 0 && user != "notFound")
							$('.notify').fadeOut(1000); // Hide notification
					})//end success	
					
				}	
						
/*		THE FUNCTION FOR WHEN THE 'USER SUBMITS THEIR MESSAGE - THE MESSAGE
 *			INFORMATION IS SENT TO THE DATABASE.
 */
				$("#submitmsg").click(function(){	
				<?php
					if($_SESSION['name'] == 'admin') {
				?>
						var decision = $(":radio[name=switchYesNo]:checked").val();
						var why;
						var clientmsg;
						var erroritem;
						strat  = $("#strategies").val();
						if(decision == 0) {
							if($("#usermsg").val() == "" || $("#whyNoSwitch").val() == "") {
								if($("#usermsg").val() == "")
									errorItem = "write a message to the user."
								else
									errorItem = "give a reason for NOT SWITCHING.";
								$("#errorMessage3").html("***** Please " + errorItem + " *****").show();
							}
							else {
								$("#errorMessage3").hide();
								why = $("#whyNoSwitch").val();
								clientmsg = $("#usermsg").val();
								if($("#whyNoSwitch").val() == "Other") {
									if($("#otherComments").val() == "") {
										errorItem = "make a comment why you DIDN'T SWITCH.";
										$("#errorMessage3").html("***** Please " + errorItem + " *****").show();
									}
									else {
										var other = $("#otherComments").val();
										var request2a = $.ajax({ 
											url: "database.php", 
											async: true, 
											type: "POST", 
											data: {userNow: user, message: clientmsg, strategies: strat, switchYN: decision, whySwitch: why, otherCmnts: other, func: 2}, 
											dataType: "html" 
										}).success(function(data) { 
											//$("#mainchatbox").html(data); 
											//alert("message =  " + data);
										})//end success			
										$("#usermsg").val("");
										$("#switchNo").prop('checked',true);
										$("#whySwitch").val($("#whySwitch option:first").val());
										$("#whyNoSwitch").val($("#whyNoSwitch option:first").val());
										$("#otherComments").val("");
									} //end of else
								}
								else {
									$("#errorMessage3").hide();
									other = $("#otherComments").val();
									if($("#otherComments").val() == "")
										other = "NULL";
									//alert(decision);
									var request2b = $.ajax({ 
										url: "database.php", 
										async: true, 
										type: "POST", 
										data: {userNow: user, message: clientmsg, strategies: strat, switchYN: decision, whySwitch: why, otherCmnts: other, func: 2}, 
										dataType: "html" 
									}).success(function(data) { 
										//$("#mainchatbox").html(data); 
										//alert("message =  " + data);
									})//end success			
									$("#usermsg").val("");
									$("#switchNo").prop('checked',true);
									$("#whySwitch").val($("#whySwitch option:first").val());
									$("#whyNoSwitch").val($("#whyNoSwitch option:first").val());
									$("#otherComments").val("");
								}
							}
						}
						else if(decision == 1) {
							$("#errorMessage3").hide();
							if($("#usermsg").val() == "" || $("#whySwitch").val() == ""){
								if($("#usermsg").val() == "")
									errorItem = "write a message to the user."
								else
									errorItem = "give a reason for SWITCHING.";
								$("#errorMessage3").html("***** Please " + errorItem + " *****").show();
							}
							else {
								$("#errorMessage3").hide();
								why = $("#whySwitch").val();
								clientmsg = $("#usermsg").val();
								if($("#whySwitch").val() == "Other") {
									if($("#otherComments").val() == "") {
										errorItem = "make a comment why you SWITCHED.";
										$("#errorMessage3").html("***** Please " + errorItem + " *****").show();
									}
									else {
										other = $("#otherComments").val();
										var request2c = $.ajax({ 
											url: "database.php", 
											async: true, 
											type: "POST", 
											data: {userNow: user, message: clientmsg, strategies: strat, switchYN: decision, whySwitch: why, otherCmnts: other, func: 2}, 
											dataType: "html" 
										}).success(function(data) { 
											//$("#mainchatbox").html(data); 
											//alert("message =  " + data);
										})//end success			
										$("#usermsg").val("");
										$("#switchNo").prop('checked',true);
										$("#whySwitch").val($("#whySwitch option:first").val());
										$("#whyNoSwitch").val($("#whyNoSwitch option:first").val());
										$("#otherComments").val("");
									} //end else
								}
								else {
									$("#errorMessage3").hide();
									other = $("#otherComments").val();
									if($("#otherComments").val() == "")
										other = "NULL";
									//alert(decision);
									var request2d = $.ajax({ 
										url: "database.php", 
										async: true, 
										type: "POST", 
										data: {userNow: user, message: clientmsg, strategies: strat, switchYN: decision, whySwitch: why, otherCmnts: other, func: 2}, 
										dataType: "html" 
									}).success(function(data) { 
										//$("#mainchatbox").html(data); 
										//alert("message =  " + data);
									})//end success			
									$("#usermsg").val("");
									$("#switchNo").prop('checked',true);
									$("#whySwitch").val($("#whySwitch option:first").val());
									$("#whyNoSwitch").val($("#whyNoSwitch option:first").val());
									$("#otherComments").val("");
									$("#whySwitch").hide();
									$("#whyNoSwitch").show();
								}							
							}
						}
						

				<?php	
					}
					else {
				?>
						clientmsg = $("#usermsg").val();
						var request2c = $.ajax({ 
							url: "database.php", 
							async: true, 
							type: "POST", 
							data: {userNow: user, message: clientmsg, func: 3}, 
							dataType: "html" 
						}).success(function(data) { 
							//$("#mainchatbox").html(data); 
							//alert("message =  " + data);
						})//end success			
						$("#usermsg").val("");
				<?php
					}
				?>
					return false;
				});

				
/*		EVERY SECOND, THE CHATBOX IS REFRESHED TO DISPLAY THE DIALOG
 *			BETWEEN THE 'ADMIN' AND THE 'USER'.
 */
				function loadLog(){		
					var oldscrollHeight = $("#mainchatbox").prop("scrollHeight") - 20;
					var oldscrollHeightAdmin = $("#mainchatboxAdmin").prop("scrollHeight") - 20;
					var request3 = $.ajax({ 
						url: "database.php", 
						async: true, 
						type: "POST", 
						data: {userNow: user, func: 4}, 
						dataType: "html" 
					}).success(function(data) { 
						if(data != "exit") {
							$("#mainchatbox").html(data); 
							$("#mainchatboxAdmin").html(data); 
						}
						else {
							//stop refreshing chatbox 							
							clearInterval(refreshIntervalId);
							clearInterval(refreshIntervalType);
						<?php 
							if($_SESSION['name']!='admin') {
						?>
								$("#welcome").hide();
								$("#QuestFormat").hide();
								$("#ChatFormat").hide();
								$("#PostQuest").show();
								$("#PostQuest3").hide();
						<?php
							}
						?>
												
						}
					})//end success	
					<?php 
						if($_SESSION['name']!='admin') {
					?>				
					var newscrollHeight = $("#mainchatbox").prop("scrollHeight") - 20;
					if(newscrollHeight > oldscrollHeight){
						$("#mainchatbox").animate({ scrollTop: newscrollHeight }, 'normal'); //Autoscroll to bottom of div
					}
					<?php
						}
						else {
					?>
					var newscrollHeightAdmin = $("#mainchatboxAdmin").prop("scrollHeight") - 20;
					if(newscrollHeightAdmin > oldscrollHeightAdmin){
						$("#mainchatboxAdmin").animate({ scrollTop: newscrollHeightAdmin }, 'normal'); //Autoscroll to bottom of div
					}
					<?php
						}
					?>				
				}		
				
/*		THE FUNCTION FOR WHEN THE 'ADMIN' CLICKS ON EXIT - THE ADMIN WILL BE
 *			SENT TO THE ADDITIONAL COMMENTS SCREEN
 */
				$("#exit").click(function(){
					var exit = confirm("Are you sure you want to end the session?");
					if(exit==true){
						//stop refreshing chatbox 
						clearInterval(refreshIntervalId);
						clearInterval(refreshIntervalType);
						var request4 = $.ajax({ 
							url: "database.php", 
							async: true, 
							type: "POST", 
							data: {userNow: user, func: 5}, 
							dataType: "html" 
						}).success(function(data) { 
							user = data;
						})//end success
						$("#welcome").hide();
						$("#QuestFormat").hide();
						$("#ChatFormatAdmin").hide();
						$("#commentsFormatAdmin").show();
						$("#PostQuest").hide();
						$("#PostQuest3").hide();
					}
				});
				 				
/*		THE FUNCTION FOR WHEN THE 'ADMIN' SUBMITS THE ADDITIONAL COMMENTS - 
 *			THE ADMIN WILL BE SENT TO A BLANK SCREEN AND REFRESH FOR THE NEXT SESSION
 */				
				$("#submitcmmts").click(function(){
					var addcmt = $("#addCmmts").val();
					//alert(user);
					//alert($("#addCmmts").val());
					var request13 = $.ajax({ 
							url: "database.php", 
							async: true, 
							type: "POST", 
							data: {userNow: user, message: addcmt, func: 13}, 
							dataType: "html" 
						}).success(function(data) { 
						})//end success
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormatAdmin").hide();
					$("#commentsFormatAdmin").hide();
					$("#PostQuest").hide();
					$("#PostQuest3").hide();
				});
				
/*		AFTER THE CHAT IS OVER, THE USER MAY SELECT TO EITHER PRINT A FLIER
*  		WITH MORE INFORMATION OR DECLINE.
 *			THIS CHOICE IS SENT TO THE DATABASE.
 *			
 *			ADDITIONALLY, THIS FUNCTION ALSO PRINTS OUT FLYER.PHP, WHICH
 *			CONTAINS MORE INFORMATION ABOUT TAI CHI AS WELL AS CONTAINS
 *			THE USER'S ID NUMBER.
 */				
				$("#printing").on('click',function(){
					var printYesNo = 1;			
					$('body').append('<iframe src="flyer.php?userNow='+user+'" id="printIFrame" name="printIFrame"></iframe>');
					$('#printIFrame').bind('load', 
						function() { 
							window.frames['printIFrame'].focus(); 
							window.frames['printIFrame'].print(); 
						}
					);
					var request6 = $.ajax({ 
						url: "database.php", 
						async: true, 
						type: "POST", 
						data: {userNow: user, printFlyer: printYesNo, func: 6}, 
						dataType: "html" 
					}).success(function(data) { 
					})//end success
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();		
					$("#PostQuest3").show();							
				});
					
				$("#nothank").on('click',function(){
					var printYesNo = 0;
					var request6 = $.ajax({ 
						url: "database.php", 
						async: true, 
						type: "POST", 
						data: {userNow: user, printFlyer: printYesNo, func: 6}, 
						dataType: "html" 
					}).success(function(data) { 
					})//end success
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest3").show();					
				});
				
				$("#Thankyouscreen").on('click',function(){
				
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest3").show();					
				});

	
/*		THE FINAL SCREEN THE USER ENCOUNTERS IS A SERIES
 *  		OF QUESTIONS ASKING THEM TO RANK THEIR OPINION
 *			FROM 1 - 5. THEY THEN CLICK SUBMIT AND ARE FINISHED.
 */			
				$("#PostQuestsubmit").on('click',function(){
					if(($('input[name=Q1]:checked').length == 0)  || ($('input[name=Q2]:checked').length == 0) || ($('input[name=Q3]:checked').length == 0) || ($('input[name=Q4]:checked').length == 0) || ($('input[name=Q5]:checked').length == 0)){
						$("#errorMessage2").html("Please fill out all fields and try again.").show();
					}
					else {
						var post1 = $('input:radio[name=Q1]:checked').val();
						var post2 = $('input:radio[name=Q2]:checked').val();
						var post3 = $('input:radio[name=Q3]:checked').val();
						var post4 = $('input:radio[name=Q4]:checked').val();
						var post5 = $('input:radio[name=Q5]:checked').val();
						var request7 = $.ajax({ 
							url: "database.php", 
							async: true, 
							type: "POST", 
							data: {userNow: user, chatUnderstoodMe: post1, understoodChat: post2, exerciseNeed: post3, taiChiInterest: post4, taiChiConvinced: post5, func: 7}, 
							dataType: "html" 
						}).success(function(data) { 
							window.location = 'index.php?logout=true'; 
						})//end success
						.error(function() {
							alert("fail");
						})
						return false;	
					}				
				});
				
			});//end of document.ready(function)	

		</script>		
		
		
							
	</head>

	<body>
		<div id="userDialog" title="User Login">
			<p>Please enter your password: </p>
			<input id="userPassword" type="text">
		</div>
		
		<div id="dialog" title="Admin Login">
			<p>Admin, please enter your name: </p>
			<input id="adminName" type="text">
			<p>Please enter your password: </p>
			<input id="adminPassword" type="text">
		</div>
		
		
<!--
		-------------------------------------------------------------------------------------------------------------
		BEGINNING OF WELCOME SCREEN
		-------------------------------------------------------------------------------------------------------------
-->	

		<div class="wrapper" id="welcome">
			<div class="top">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id="introbox">
				<p>
					Hi there! I am your Healthy Choice Guru.<br>
					Together we will try and make a choice that
					is good for you.
				</p>
				<h3>Instructions:</h3>
				
				<p>
					On the next screen there will be a questionnaire. Please select an option for each question.</p>
					<p>Upon completion of the questionnaire, please click "Begin Chat" to start the chat with our agent.<br> 
				</p>
				<h2>Thank you for your participation!</h2>

				<div class = "buttonHolder">
					<input class="submitbutton" id="continue"  type="button" value="Continue">
				</div>		
	
			</div>
		</div>

<!--
		-------------------------------------------------------------------------------------------------------------
		BEGINNING OF PRE-CHAT QUESTIONNAIRE
		-------------------------------------------------------------------------------------------------------------
-->
		<div class="wrapper" id="QuestFormat">
			<div class="top">
			</div>	
			<div class="container" id="questbox">			
				<h4>Please take a moment to answer a few quick questions. Thank you.</h4>
				<span id="errorMessage1"></span>
				<h4>What is your role?
					<select  id = "Position" required>
						<option value = "Select" selected>-Select-</option>
						<option value = "UndergraduateStudent">Undergraduate Student</option>
						<option value = "graduateStudent">Graduate Student</option>
						<option value = "Faculty">Faculty</option>
						<option value = "Staff">Staff</option>
					</select>
				</h4>
				<h4>What is your gender?
					<select id = "Gender" required>
						<option value selected>-Select-</option>
						<option value = "Male">Male</option>
						<option value = "Female">Female</option>
					</select>
				</h4>
				
				<h4>What is your ethnicity?
					<select id = "Ethnicity" required>
						<option value selected>-Select-</option>
						<option value = "Caucasian">Caucasian</option>
						<option value = "Hispanic/Latino">Hispanic / Latino</option>
						<option value = "MiddleEastern">Middle Eastern</option>
						<option value = "AsianPacificIslander">Asian/Pacific Islander</option>
						<option value = "Black/AfricanAmerican">Black/African American</option>
						<option value = "Other">Other</option>
					</select>
				</h4>
				<h4>What is your age range?
					<select id = "Age" required>
						<option value selected>-Select-</option>
						<option value = "EighteenToTwentyOne">18 -21</option>
						<option value = "TwentyTwoToTwentyFive">22 - 25</option>
						<option value = "TwentySixToThirty">26 - 30</option>
						<option value = "ThirtyOneToForty">31 - 40</option>
						<option value = "FortyOneToFifty">41 - 50</option>
						<option value = "FiftyOneToSixty">51 - 60</option>
						<option value = "SixtyOneAndOlder">61 and Older</option>
					</select>
				</h4>
				
				<h4>How often do you exercise per week?
					<select id = "Exercise1" required>
						<option value selected>-Select-</option>
						<option value = "0-1times">0-1 times</option>
						<option value = "2-3times">2-3 times</option>
						<option value = "4+">4+ times</option>
					</select>
				</h4>
			
				<h4>Which type of exercises?
					<select id = "Exercise2" width = "20">
						<option value selected>-Select-</option>
						<option value = "Aerobicexercise">Aerobic exercise(running, cycling)</option>
						<option value = "Anaerobicexercise">Anaerobic exercise(weight lifting)</option>
						<option value = "Flexibility">Flexibility(yoga)</option>
						<option value = "None">None</option>
					</select>
				</h4>
			
				<h4>What social network sites do you use?
					<select id = "SocialNetwork" required>
						<option value selected>-Select-</option>
						<option value = "Facebook" >Facebook</option>
						<option value = "Twitter">Twitter</option>
						<option value = "Both">Both</option>
					</select>			
				</h4>
				<h4>Please state whether you agree or disagree with the following statements.</h4>
				
				<h5>Strongly Disagree=1 and Strongly Agree=5.</h5>
				
				<h4>I feel the need to exercise more often.
					<form id="pretest1">
						<input type="radio" name="taichi1" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi1" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi1" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi1" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi1" value="5">5					
					</form>
				</h4>
				
				<h4>I am interested in learning Tai Chi.
					<form id="pretest2">
						<input type="radio" name="taichi2" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi2" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi2" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi2" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="taichi2" value="5">5					
					</form>
				</h4>		
			</div>
			<div class = "buttonHolder">
				<input class = "submitbutton" id = "QuestSubmit" type = "button" value = "Begin Chat">
			</div>
		</div>		
<!--
		-------------------------------------------------------------------------------------------------------------
		BEGINNING OF ADMIN CHAT SCREEN
		-------------------------------------------------------------------------------------------------------------
-->
	<?php 
		if($_SESSION['name']=='admin'){
	?>
		<div class="wrapper" id="ChatFormatAdmin">
			<div class="top">
				<div class="notify"></div>
				<a href="#" id="exit" class="logout"> Exit </a>
			</div>	
			<div class="container" id="mainchatboxAdmin">
					
			</div>
			<form name="message" action="">					
				<input class="bottom" name="usermsg" type="text" id="usermsg"/>
				<input class = "submitbutton" name="submitmsg" type="button"  id="submitmsg" value="Send" />
				<div id="adminComments">
					<span id="errorMessage3"></span>
					<div id="left">
						<select id = "strategies">
							<option value = "RationalPositive">Rational Positive</option>
							<option value = "RationalNegative">Rational Negative</option>
							<option value = "EmotionalPositive">Emotional Positive</option>
							<option value = "EmotionalNegative">Emotional Negative</option>
						</select>
					</div>				
					<div id="center">
						<input type="radio" id="switchNo" name="switchYesNo" value="0" disabled>No Switch<br>
						<input type="radio" id="switchYes" name="switchYesNo" value="1" disabled>Switch<br>
					</div>
					<div id="right">
						<select id = "whyNoSwitch">
							<option value selected>-Select-</option>
							<option value = "LotsOfInterest">Very Interested</option>
							<option value = "SomeInterest">Somewhat Interested</option>
							<option value = "Intro">Introduction</option>
							<option value = "Conclusion">Conclusion</option>
							<option value = "Other">Other</option>
						</select>
						<select id = "whySwitch">
							<option value selected>-Select-</option>
							<option value = "LackOfInterest">No Interest</option>
							<option value = "MarginalInterest">Marginal Interest</option>
							<option value = "Other">Other</option>
						</select>
						<input id="otherComments" placeholder="Additional Comments" type="text">	
					</div>				
				</div>
			</form>
		
		</div>

<!--
		-------------------------------------------------------------------------------------------------------------
		BEGINNING OF ADMIN COMMENTS SCREEN
		-------------------------------------------------------------------------------------------------------------
-->		
		<div class="wrapper" id="commentsFormatAdmin">
			<div class="top">
			</div>	
<!--
			<form name="message" action="">					
-->
				<textarea class="container" name="usermsg" id="addCmmts" placeholder="Enter additional comments here (Leave blank if not applicable):"></textarea>
				<div class = "buttonHolder">
					<input class = "submitbutton" type="button"  id="submitcmmts" value="Send" />
				</div>				
<!--
			</form>
-->
		
		</div>
	<?php
		}
		else {
	?>
	
<!--
		-------------------------------------------------------------------------------------------------------------
		BEGINNING OF USER CHAT CHAT SCREEN
		-------------------------------------------------------------------------------------------------------------
-->
		<div class="wrapper" id="ChatFormat">
			<div class="top">
				<div class="notify"></div>
			</div>	
			<div class="container" id="mainchatbox">
				
			</div>
			<form name="message" action="">						
				<input class="bottom" name="usermsg" type="text" id="usermsg"/>
				<input class = "submitbutton" name="submitmsg" type="submit"  id="submitmsg" value="Send" />
			</form>

		</div>	
	<?php
		}
	?>
<!--
		-------------------------------------------------------------------------------------------------------------
		BEGINNING OF POST-CHAT QUESTIONNAIRE SCREEN #1
		-------------------------------------------------------------------------------------------------------------
-->		
		<div class="wrapper" id="PostQuest">
			<div class="top">
			</div>	
			<div class="container" id ="end" >		
				<p id="printmsg">Thank you for your participantion.<br>
					Are you interested in receiving more information? </p>
				
				<br><br>
				<div class = "buttonHolder">	
					<input class="submitbutton" id="printing"  type="button" value="Print Flyer" >
					<br><br>
					<input class="submitbutton" id="nothank"  type="button" value="No Thanks" >
				</div>
			</div>				
		</div>
		
<!--
		BEGINNING OF POST QUESTIONS SCREEN
-->		
		<div class="wrapper" id="PostQuest3">
			<div class="top">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id="questbox">
				<h4>Please state whether you agree or disagree with the following statements.</h4>
				<span id="errorMessage2"></span>
				<h5>Strongly Disagree=1 and Strongly Agree=5.</h5>				
				<h4>The chat program understood what I was saying.
					<form id="posttest1">
							<input type="radio" name="Q1" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q1" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q1" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q1" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q1" value="5">5
					</form>		
				</h4>
				<h4>I understood what the chat program was saying to me.
					<form id="posttest2">
							<input type="radio" name="Q2" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q2" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q2" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q2" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q2" value="5">5				
					</form>
				</h4>
				<h4>After this chat,</h4>
				<h4>I feel the need to exercise more often.
					<form id="posttest3">							
							<input type="radio" name="Q3" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q3" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q3" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q3" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q3" value="5">5					
					</form>
				</h4>
				<h4>I am interested in learning Tai chi.
					<form id="posttest4">
							<input type="radio" name="Q4" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q4" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q4" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q4" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q4" value="5">5					
					</form>
				</h4>
				<h4>This chat persuaded me to find out more about Tai Chi.
					<form id="posttest5">
							<input type="radio" name="Q5" value="1">1&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q5" value="2">2&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q5" value="3">3&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q5" value="4">4&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="Q5" value="5">5				
					</form>
				</h4>
			</div>
			<div class = "buttonHolder">
				<input class="submitbutton" id="PostQuestsubmit"  type="button" value="Submit">
			</div>		
		</div>
		
	</body>
</html>
