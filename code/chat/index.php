<?
session_start();

if(isset($_GET['logout'])){            

                
				
						/*	$fp = fopen("log.html", 'a');
								fwrite($fp, "<div class='msgln'><i>You have left the chat session.</i><br></div>");
								fclose($fp);*/
								
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
		</script>
		
		<script>
			var user = "";

			$(document).ready(function(){
				
			var refreshIntervalId;
					
					
			 <?php 
				if($_SESSION['name']!='admin'){
			 ?>
					$("#welcome").show();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();
					
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
						$("#PostQuest2").hide();
					});
					$("#QuestSubmit").mouseenter(function(){
						$("#QuestSubmit").fadeTo("fast", 1.0);
					});
					$("#QuestSubmit").mouseleave(function(){
						$("#QuestSubmit").fadeTo("fast", 0.5);
					});

						
					$("#QuestSubmit").click(function(){
						if($("#SocialNetwork").val() == "" || $("#Age").val() == "" || $("#Position").val() == "" || $("#Ethnicity").val() == "" || $("#Gender").val() == "" || $("#Exercise1").val() == "" || $("#Exercise2").val() == "") {
							$("#errorMessage").html("Please fill out all fields and try again.").show();
						}
						else {
							//var pos = $('#Position :selected').text();
							var pos = $("#Position").val();
							var sex = $("#Gender").val();
							var race = $("#Ethnicity").val();
							var age = $("#Age").val();
							var ex1 = $("#Exercise1").val();
							var ex2 = $("#Exercise2").val();
							var soc = $("#SocialNetwork").val();
							
							var request1 = $.ajax({ 
								url: "database.php", 
								async: true, 
								type: "POST", 
								data: {position: pos, gender: sex, ethnicity: race, age: age, exercise1: ex1, exercise2: ex2, socialnetwork: soc, func: 1}, 
								dataType: "html" 
							}).success(function(data) { 
								user = data;
							})//end success
							
							$("#welcome").hide();
							$("#QuestFormat").hide();
							$("#ChatFormat").show();					
							$("#PostQuest").hide();
							$("#PostQuest2").hide();
							
							refreshIntervalId = setInterval (loadLog, 1000);	//Reload file every 1 seconds
						}
						return false;
					});
				<?php 
				}
					
				else{
				?>
					var request1 = $.ajax({ 
								url: "database.php", 
								async: true, 
								type: "POST", 
								data: {func: 0}, 
								dataType: "html" 
							}).success(function(data) { 
								user = data;
							})//end success
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").show();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();
						
					refreshIntervalId = setInterval (loadLog, 1000);	//Reload file every 1 seconds
				<?php
				}
				?>
				
				$("#exit").click(function(){
					var exit = confirm("Are you sure you want to end the session?");
					if(exit==true){
						//stop refreshing chatbox 
						clearInterval(refreshIntervalId);
						var request1 = $.ajax({ 
								url: "database.php", 
								async: true, 
								type: "POST", 
								data: {userNow: user, func: 4}, 
								dataType: "html" 
							}).success(function(data) { 
							})//end success
					} 
					$("#welcome").show();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();					
				});
				
				$("#printing").on('click',function(){
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest2").show();					
				});
					
				$("#nothank").on('click',function(){
					$("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest2").show();					
				});

				$("#submitemail").click(function(){
					window.location = 'index.php?logout=true'; 
					return false;
				});
				
				$("#nothank2").click(function(){
					window.location = 'index.php?logout=true'; 
					return false;
				});

				//If user submits the form
				$("#submitmsg").click(function(){	
					var clientmsg = $("#usermsg").val();
					//alert(clientmsg);
					var request2 = $.ajax({ 
							url: "database.php", 
							async: true, 
							type: "POST", 
							data: {userNow: user, message: clientmsg, func: 2}, 
							dataType: "html" 
						}).success(function(data) { 
							//$("#mainchatbox").html(data); 
							//alert("message =  " + data);
						})//end success			
					$("#usermsg").val("");
					return false;
				});
							
				function loadLog(){		
					var oldscrollHeight = $("#mainchatbox").prop("scrollHeight") - 20;
					var request3 = $.ajax({ 
						url: "database.php", 
						async: true, 
						type: "POST", 
						data: {userNow: user, func: 3}, 
						dataType: "html" 
					}).success(function(data) { 
						if(data != "exit")
							$("#mainchatbox").html(data); 
						else {
							//stop refreshing chatbox 							
							clearInterval(refreshIntervalId);
							$("#welcome").hide();
							$("#QuestFormat").hide();
							$("#ChatFormat").hide();
							$("#PostQuest").show();
							$("#PostQuest2").hide();
						}
					})//end success					
					var newscrollHeight = $("#mainchatbox").prop("scrollHeight") - 20;
					if(newscrollHeight > oldscrollHeight){
						$("#mainchatbox").animate({ scrollTop: newscrollHeight }, 'normal'); //Autoscroll to bottom of div
					}				
				}		
			});
		</script>
			
							
		<style type="text/css">
		.auto-style10 {
			margin-left: 66px;
		}
		</style>
			
							
	</head>
	
	

	<body>
		<div class="wrapper" id="welcome">
			<div id="menu">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id="chatbox">
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
				
				<input class="submitbutton" id="continue"  type="submit" value="Continue">
			</div>

		</div>
		
		<div class="wrapper" id="QuestFormat">
			<div id="menu">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id="chatbox" style="left: 0px; top: 0px; width: 94%; height: 87%">

				<h4>Please take a moment to answer a few quick questions. Thank you.</h4>
				<h4>What is your position?
					<select  id = "Position" required>
						<option value = "Select" selected>-Select-</option>
						<option value = "Student">Student</option>
						<option value = "Stafffaculty">Staff/Faculty</option>
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

				
				<h4>Which type of exercises?<select id = "Exercise2" required class="auto-style6" name="D1" style="width: 219px">
						<option value selected>-Select-</option>
						<option value = "Aerobicexercise">Aerobic exercise(running, cycling)</option>
						<option value = "Anaerobicexercise">Anaerobic exercise(weight lifting)</option>
						<option value = "Flexibility">Flexibility(yoga)</option>
						<option value = "None">None</option>
					</select>&nbsp;
				</h4>

				
				<h4>What social network sites do you use?
					<select id = "SocialNetwork" required>
						<option value selected>-Select-</option>
						<option value = "Facebook" >Facebook</option>
						<option value = "Twitter">Twitter</option>
						<option value = "Both">Both</option>
					</select>			
				</h4>
				<span id="errorMessage" style="display:hidden;color:red"></span>
				<input class = "submitbutton" id = "QuestSubmit" type = "button" value = "Begin Chat">

			</div>

		</div>
		
		<div class="wrapper" id="ChatFormat">
			<div id="menu">
				<div style="clear:both"></div>
				<?php if($_SESSION['name']=='admin'){
				?>
			   <a href="#" id="exit" class="logout"> Exit </a>
			   <?php 
			   } 
			   ?>
			</div>	
			<div class="container" id="mainchatbox">
				
			</div>
			<form name="message" action="">						
				<input class="bottom" name="usermsg" type="text" id="usermsg" size="63" />&nbsp;
				<button name="Abutton1" id="submitmsg" style="width: 60px; height: 44px">Send
				</button>
			</form>
		</div>
	
		
		<div class="wrapper" id="PostQuest">
			<div id="menu">
				<div style="clear:both"></div>`
			</div>	
			<div class="container" id ="chatbox" style="left: 0px; top: 0px; width: 92%; height: 88%">
			
					<p id="printmsg"><font face="Arial" size="3" color="black">Thank you for your participantion. </font></p>
					<p id="printmsg"><font face="Arial" size="3" color="black">Are you interested in receiving more information? 
					</font></p>
				
					<br><br>&nbsp;
					<input class="submitbutton" id="printing"  type="submit" value="Print Flyer" style="width: 35%">
					<input class="submitbutton" id="nothank"  type="submit" value="No Thanks" style="width: 35%"></div>
		</div>

			<div class="wrapper" id="PostQuest2" style="width: 100%; height: 100%">
			<div id="menu">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id ="chatbox" style="left: 0px; top: 0px; width: 92%; height: 88%">
			
				<p><font face="Arial" size="3" color="black">
					Interested in finding a Tai Chi class. Please sign up to receive more information.   
				</font></p>
				<form name="emailmessage" action="" style="height: 241px; width: 381px">						
					<br>
				<div><font face="Arial" size="3" color="black">&nbsp;&nbsp;&nbsp; Email: </font>
					<input id="useremail" class="auto-style11" name="useremail" size="63" style="height: 31px; width: 67%;" type="text">
					<br><br>&nbsp;
					<input class="submitbutton" id="submitemail" type="submit" value="Sign Me Up" style="width: 35%"> 
					</div>
				<input class="submitbutton" id="nothank2"  type="submit" value="No Thanks" style="width: 35%"><br><br></form>
			
			<p id="printmsg">&nbsp;</p>
			</div> 
		
		</div>
	
	</body>
</html>
