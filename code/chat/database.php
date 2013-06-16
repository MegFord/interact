<?
session_start();
	$value = $_POST['func'];
	
	
	$con=mysql_connect("localhost", "ygutstein", "ygutstein");
       
	// Check connection
	//if($con)
		//echo('Successful '.$pos.' '.$age);
	if (!$con)
  	{
  		die('Could not connect: ' . mysql_error());
  	} 

	mysql_select_db("db_tempt", $con); 

	
	switch($value) {
		case 0:
		//get userID if admin login
		$curUser = mysql_query("SELECT MAX(userID) as currentUser FROM Demographics");
		$row = mysql_fetch_array($curUser);
		echo ($row[currentUser]);
		break;
		case 1:
		//insert data into Demographics and Session
		$pos = $_POST['position']; 
	$gender = $_POST['gender'];
	$ethnic = $_POST['ethnicity'];
	$age = $_POST['age'];
	$social = $_POST['socialnetwork'];
	$ex1 = $_POST['exercise1'];
	$ex2 = $_POST['exercise2'];
	$userID = $_POST['userNow'];
	
	if($social == "Facebook") {
		$fbook = 1;
		$tweet = 0;
	}
	else if($social == "Twitter"){
		$fbook = 0;
		$tweet = 1;
	}
	else{
		$fbook = 1;
		$tweet = 1;
	}
	
	if($age == "EighteenToTwentyOne")
		$ageGroup = 1;
	else if($age == "TwentyTwoToTwentyFive")
		$ageGroup = 2;
	else if($age == "TwentySixToThirty")
		$ageGroup = 3;
	else if($age == "ThirtyOneToForty")
		$ageGroup = 4;
	else if($age == "FortyOneToFifty")
		$ageGroup = 5;
	else if($age == "FiftyOneToSixty")
		$ageGroup = 6;
	else
		$ageGroup = 7;

	if($ex1 == "0-1times")
		$exVal = 1;
	else if($ex1 == "2-3times")
		$exVal = 2;
	else
		$exVal = 3;
		
	if($ex2 == "Aerobicexercise")
		$exType = 1;
	else if($ex2 == "Anaerobicexercise")
		$exType = 3;
	else if($ex2 == "Flexibility")
		$exType = 4;
	else
		$exType = 'None';
		
			mysql_query("INSERT INTO Demographics (position, gender, ethnicity, ageRange, facebook, twitter, exerciseRange, exerciseType) VALUES('$pos','$gender','$ethnic','$ageGroup','$fbook','$tweet','$exVal','$exType')");
			
			$curUser = mysql_query("SELECT MAX(userID) as currentUser FROM Demographics");
			$row = mysql_fetch_array($curUser);
			mysql_query("INSERT INTO Session (userID, isComputer, persuadeCondition, exitChat) VALUES('$row[currentUser]',1,1,0) ");
			echo ($row[currentUser]);
			break;
		
		case 2:
		//Insert message into Conversation
			$msg = $_POST['message'];
			$userID = $_POST['userNow'];
			$time = date("g:i A");
			if($_SESSION['name']!='admin') {
				mysql_query("INSERT INTO Conversation (sessionID, receiverID, message, displayTime) VALUES('$userID', 0, '$msg', '$time')");
			}
			else {
				mysql_query("INSERT INTO Conversation (sessionID, receiverID, message, displayTime)  VALUES('$userID', '$userID', '$msg', '$time')");
			}
			echo $msg;
			break;
			
		case 3:
		//Display conversation	if not exiting
			$userID = $_POST['userNow'];
			$loggedIn = mysql_query("SELECT exitChat FROM Session WHERE userID = '$userID'");
			$row = mysql_fetch_array($loggedIn);
			if($row['exitChat'] == 0) {
				if($_SESSION['name']!='admin') {
					echo ("Please wait for your health Guru to enter the chat.<br>");
					echo ("<i>Health Guru is typing</i>");
				}
				$convo = mysql_query("SELECT* FROM Conversation WHERE sessionID = '$userID' order by timeSent");
				while($row = mysql_fetch_array($convo)) {			
					if($row['receiverID'] == $userID) {
						echo( "<div class='msgln'>(".$row['displayTime'].") <b>Health Guru: </b>".stripslashes(htmlspecialchars($row['message']))."<br></div>");
					}
					else {
						echo( "<div class='msgln'>(".$row['displayTime'].") <b>You: </b>".stripslashes(htmlspecialchars($row['message']))."<br></div>");
					}			
				}	
			}
			else
				echo ("exit");			
			break;
		case 4:
		//when admin hits the exit button
			$userID = $_POST['userNow'];
			mysql_query("UPDATE Session set exitChat=1 where userID='$userID'");
			break;
	}
?>
