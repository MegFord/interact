<?
session_start();

if(isset($_GET['logout'])){            

                
				
							$fp = fopen("log.html", 'a');
								fwrite($fp, "<div class='msgln'><i>You have left the chat session.</i><br></div>");
								fclose($fp);
								
                session_destroy();

                header("Location: index.php"); //Redirect the user }
 }
 
 function loginForm(){

                echo'

                <div id="loginform">

                <form action="index.php" method="post">

                                <p>Please enter your id to continue:</p>

                                <label for="name">ID:</label>

                                <input type="text" name="name" id="name" />

                                <input type="submit" name="enter" id="enter" value="Enter" />

                </form>

                </div>

                ';

}

  

if(isset($_POST['enter'])){

                if($_POST['name'] != ""){

                                $_SESSION['name'] = stripslashes(htmlspecialchars($_POST['name']));

                }

                else{

                                echo '<span class="error">Please type in your ID</span>';

                }

}

  

        ?>

<!DOCTYPE html>
<html>
	<head>
		<title>A Healthy Choice</title>
		<link type="text/css" rel="stylesheet" href="chatStyle.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
		</script>
		<?php

if(!isset($_SESSION['name'])){

                loginForm();

}

else{
?>
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
				$("#continue").on('click',function(){
					$("#welcome").hide();
					$("#QuestFormat").show();
					$("#ChatFormat").hide();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();
				});
				
				$("#QuestSubmit").click(function(){
                if($("#SocialNetwork").val() == "" || $("#Age").val() == "" || $("#Position").val() == "" || $("#Ethnicity").val() == "" || $("#Gender").val() == "" ) {
                    $("#errorMessage").html("Please fill out all fields and try again.").show();
                }
                else {
                    $("#welcome").hide();
					$("#QuestFormat").hide();
					$("#ChatFormat").show();
					$("#PostQuest").hide();
					$("#PostQuest2").hide();
					
					setInterval (loadLog, 2500);	//Reload file every 2.5 seconds
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
					
					var refreshIntervalId = setInterval (loadLog, 2500);	//Reload file every 2.5 seconds
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
				$("#submitemail").click(function(){
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
				var oldscrollHeight = $("#mainchatbox").attr("scrollHeight") - 20;
				$.ajax({
					url: "log.html",
					cache: false,
					success: function(html){
					$("#mainchatbox").html(html); //Insert chat log into the #chatbox div					
				var newscrollHeight = $("#mainchatbox").attr("scrollHeight") - 20;
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
			<div class="container" id="chatbox">
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
							<option value = "Asian">Asian</option>
							<option value = "MiddleEastern">Middle Eastern</option>
							<option value = "PacificIslander">Pacific Islander</option>
							<option value = "NativeAmerican">Native American</option>
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
				<div style="clear:both"></div>
			</div>	
			<div class="container" id ="chatbox">
			
					<p id="printmsg">Thank you for your participantion.  </p>
					<p id="printmsg">Are you interested in receiving more information? </p>
				
					<input class="submitbutton" id="printing"  type="submit" value="Print Flyer">
			</div>
		</div>

			<div class="wrapper" id="PostQuest2">
			<div id="menu">
				<div style="clear:both"></div>
			</div>	
			<div class="container" id ="chatbox">
			
			<p id="printmsg">
					Would you like to submit your email address to receive information by email as well?   
				</p>
				<form name="emailmessage" action="">						
				<input class="bottom" name="useremail" type="text" id="useremail" size="63" />
				<input  class = "submitbutton" name="submitemail" type="submit"  id="submitemail" value="Submit Email" />				
			</form>
			</div> 
		
		</div>
		
	<?php

}

?>
	
	</body>
</html>
