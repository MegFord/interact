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
                    $("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").show();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();
					
					setInterval (loadLog, 1000);	//Reload file every 1 seconds
                }
				});
				<?php 
				}
				
				else{
				?>
				    $("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").show();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();
					
					var refreshIntervalId = setInterval (loadLog, 1000);	//Reload file every 1 seconds
				<?php
				}
				
				?>
            
				$("#exit").click(function(){

                                var exit = confirm("Are you sure you want to end the session?");
								if(exit==true){
									//stop refreshing chatbox 
									clearInterval(refreshIntervalId);
									$("#welcome").hide();
									$("#QuestFormat").hide();
									$("#ChatFormat").hide();
									$("#PostQuest").show();
									$("#PostQuest2").hide();
								}                             
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
				$.post("post.php", {text: clientmsg});				
				$("#usermsg").val("");
				return false;
			});
		
			function loadLog(){		
				var oldscrollHeight = $("#mainchatbox").prop("scrollHeight") - 20;
				$.ajax({
					url: "log.html",
					cache: false,
					success: function(html){
					$("#mainchatbox").html(html); //Insert chat log into the #chatbox div					
				var newscrollHeight = $("#mainchatbox").prop("scrollHeight") - 20;
				if(newscrollHeight > oldscrollHeight){
					$("#mainchatbox").animate({ scrollTop: newscrollHeight }, 'normal'); //Autoscroll to bottom of div
				}				
		  	},
		});
		}
		});
		</script>
		
<!--
		<script type="text/javascript" src="chat.js"></script>
-->
	<script type="text/javascript">
		function validate() {
			if(document.IntroQuest.Position.value == "Select") {
				alert("Please select a position");
				return false;
			}
			return true;
		}
	</script>
			
		<style type="text/css">
		.auto-style1 {
			margin-left: 31px;
		}
		.auto-style2 {
			border-left: 1px solid #CCC;
			border-right: 1px solid #CCC;
			border-top: 1px solid #CCC;
			width: 9%;
			min-width: 55px;
			border-radius: 5px;
			height: 44px;
			padding: 5px;
			background-color: #EEEEEE;
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #cccccc));
			background-image: -webkit-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -ms-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -o-linear-gradient(top, #eeeeee, #cccccc);
			background-image: url('linear-gradient(top,%20#eeeeee, #cccccc)');
			border-bottom: 1px solid #BBB;
			color: #333;
			font: bold 13px / 1 "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana, sans-serif;
			text-align: center;
			text-shadow: 0 1px 0 #EEE;
			margin-top: 0px;
			margin-left: 40px;
			margin-right: 0px;
		}
		.auto-style3 {
			border-left: 1px solid #CCC;
			border-right: 1px solid #CCC;
			border-top: 1px solid #CCC;
			width: 9%;
			min-width: 55px;
			border-radius: 5px;
			height: 40px;
			padding: 5px;
			background-color: #EEEEEE;
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #cccccc));
			background-image: -webkit-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -ms-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -o-linear-gradient(top, #eeeeee, #cccccc);
			background-image: linear-gradient(top, #eeeeee, #cccccc);
			border: 1px solid #CCC;
			border-bottom: 1px solid #BBB;
			color: #333;
			font: bold 13px / 1 "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana, sans-serif;
			text-align: center;
			text-shadow: 0 1px 0 #EEE;
			margin-left: 42px;
			margin-right: 0%;
			margin-bottom:0px;
		}
		
		.auto-style4 {
			border-left: 1px solid #CCC;
			border-right: 1px solid #CCC;
			border-top: 1px solid #CCC;
			width: 9%;
			min-width: 55px;
			border-radius: 5px;
			height: 40px;
			padding: 5px;
			background-color: #EEEEEE;
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #cccccc));
			background-image: -webkit-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -ms-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -o-linear-gradient(top, #eeeeee, #cccccc);
			background-image: linear-gradient(top, #eeeeee, #cccccc);
			border: 1px solid #CCC;
			border-bottom: 1px solid #BBB;
			color: #333;
			font: bold 13px / 1 "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana, sans-serif;
			text-align: center;
			text-shadow: 0 1px 0 #EEE;
			margin-left: 17px;
			margin-right: 0%;
			margin-bottom: 0px;
		}
		.auto-style5 {
			border-left: 1px solid #CCC;
			border-right: 1px solid #CCC;
			border-top: 1px solid #CCC;
			width: 9%;
			min-width: 55px;
			border-radius: 5px;
			height: 40px;
			padding: 5px;
			background-color: #EEEEEE;
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #cccccc));
			background-image: -webkit-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -ms-linear-gradient(top, #eeeeee, #cccccc);
			background-image: -o-linear-gradient(top, #eeeeee, #cccccc);
			background-image: url('linear-gradient(top,%20#eeeeee, #cccccc)');
			border-bottom: 1px solid #BBB;
			color: #333;
			font: bold 13px / 1 "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana, sans-serif;
			text-align: center;
			text-shadow: 0 1px 0 #EEE;
			margin-left: 15px;
			margin-right: 0%;
		}
		.auto-style3, .auto-style4:active {
		border: 1px solid #AAA;
		border-bottom: 1px solid #888;
		-webkit-box-shadow: inset 0 0 5px 2px #aaaaaa, 0 1px 0 0 #eeeeee;
		box-shadow: inset 0 0 5px 2px #AAAAAA, 0 1px 0 0 #EEEEEE;
		}

		.auto-style6 {
			margin-left: 0px;
			margin-right: 0px;
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
<!--
				<form name = "IntroQuest" method="post" onSubmit="return (validate())">
-->
					<h4>Please take a moment to answer a few quick questions. Thank you.</h4>
					<h4>What is your position?
						<select name = "Position" required>
							<option value = "Select" selected>-Select-</option>
							<option value = "Staff">Staff</option>
							<option value = "Faculty">Faculty</option>
							<option value = "Student">Student</option>
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
					<input class = "submitbutton" id = "QuestSubmit" type = "submit" value = "Begin Chat">
<!--
				</form>
-->
			</div>

		</div>
		
		<div class="wrapper" id="ChatFormat">
			<div id="menu">
				<div style="clear:both"></div>
			   <a href="#" id="exit" class="logout"> Exit </a>
			</div>	
			<div class="container" id="mainchatbox">
			
			<?php
	if(file_exists("log.html") && filesize("log.html") > 0){
		$handle = fopen("log.html", "r");
		$contents = fread($handle, filesize("log.html"));
		fclose($handle);
		
		echo $contents;
	}
	?>
				
			</div>
			<form name="message" action="">						
				<input class="bottom" name="usermsg" type="text" id="usermsg" size="63" />
				<input class = "submitbutton" name="submitmsg" type="submit"  id="submitmsg" value="Send" />
			</form>
		
		</div>
		
		
		<div class="wrapper" id="PostQuest">
			<div id="menu">
				<div style="clear:both"></div>`
			</div>	
			<div class="container" id ="chatbox">
			
					<p id="printmsg"><font face="Arial" size="3" color="black">Thank you for your participantion. </font></p>
					<p id="printmsg"><font face="Arial" size="3" color="black">Are you interested in receiving more information? 
					</font></p>
				
					<br><br>&nbsp;
					<input class="auto-style3" id="printing"  type="submit" value="Print Flyer" style="width: 35%"><input class="auto-style4" id="nothank"  type="submit" value="No Thanks" style="width: 35%"></div>
		</div>

			<div class="wrapper" id="PostQuest2">
			<div id="menu">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id ="chatbox">
			
			<p id="printmsg"><font face="Arial" size="3" color="black">
					Would you like to submit your email address to receive information by email as well?   
				</font></p>
				<form name="emailmessage" action="">						
					<br>
					<input id="useremail" class="auto-style1" name="useremail" size="63" type="text" style="height: 35px">
				<br>
				&nbsp;<br><br><br>&nbsp; 
				<input  class = "auto-style2" name="submitemail" type="submit"  id="submitemail" value="Submit" style="height: 40px; width: 35%;" /><input class="auto-style5" id="nothank2"  type="submit" value="No Thanks" style="width: 35%"><br><br></form>
			</div> 
		
		</div>
	
	</body>
</html>
