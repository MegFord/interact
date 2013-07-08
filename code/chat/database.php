<?
session_start();
	$value = $_POST['func'];
	
	
	$con=mysql_connect("localhost", "ygutstein", "ygutstein");
       
	// Check connection
	//if($con)
		//echo('Successful '.$pos.' '.$age);
	if (!$con) {
  		die('Could not connect: ' . mysql_error());
  	} 

	mysql_select_db("db_tempt", $con); 

	
	switch($value) {
		case 0:
		//insert data into Demographics and Session
			$pos = $_POST['position']; 
			$gender = $_POST['gender'];
			$ethnic = $_POST['ethnicity'];
			$age = $_POST['age'];
			$social = $_POST['socialnetwork'];
			$ex1 = $_POST['exercise1'];
			$ex2 = $_POST['exercise2'];
			$pre1 = $_POST['preNeed'];
			$pre2 = $_POST['preInterest'];
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
			
			$id = rand(2,9999);
			
			function findExists($id) {
				$idExists = false;
				$currentIDs = mysql_query("SELECT userID from Demographics");
				while($currentIdRow = mysql_fetch_array($currentIDs)) {
					if($currentIdRow['userID'] == $id) {
						$id = rand(2,9999);
						findExists($id);
					}
					else
						return $id;
				}
			}
					
			mysql_query("INSERT INTO Demographics (userID, position, gender, ethnicity, ageRange, facebook, twitter, exerciseRange, exerciseType) VALUES('$id','$pos','$gender','$ethnic','$ageGroup','$fbook','$tweet','$exVal','$exType')");
			
			$curUser = mysql_query("SELECT userID AS currentUser FROM Demographics WHERE insertTime = (SELECT MAX(insertTime) FROM Demographics)");
			$row = mysql_fetch_array($curUser);
			
			mysql_query("INSERT INTO Session (userID, isComputer, persuadeCondition, exitChat) VALUES('$row[currentUser]',0,1,0) ");
			mysql_query("INSERT INTO PersuasionSuccess (userID) VALUES('$row[currentUser]') ");
			mysql_query("INSERT INTO PreChatQuestionnaire (userID, exerciseNeed, taiChiInterest) VALUES ('$row[currentUser]', '$pre1', '$pre2')");
			//mysql_query("INSERT INTO PostChatQuestionnaire (userID) VALUES ('$row[currentUser]')");
			echo ($row[currentUser]);
			break;
		
		//case 1:
		////get userID if admin login
			//$curUser = mysql_query("SELECT userID AS currentUser FROM Demographics WHERE insertTime = (SELECT MAX(insertTime) FROM Demographics)");
			//$row = mysql_fetch_array($curUser);
			//echo ($row[currentUser]);
			//break;
		
		case 1:
		//when the admin logs in, a query is made to determine if a new user exists.
		//if a new user is found, is is assigned to the admin.
			$agentName = $_POST['agent'];
			$curUser = mysql_query("SELECT * FROM Session WHERE insertTime = (SELECT MAX(insertTime) FROM Session)");

			$row = mysql_fetch_array($curUser);
			if($row[exitChat] == 0) {
				echo ($row[userID]);	
				if($row[agentName] == null)
					mysql_query("UPDATE Session SET agentName =  '$agentName' WHERE userID = '$row[userID]'");
			}
			else
				echo "notFound";
			break;
			
		case 2:
		//Insert Agent message into Conversation
			$msg = $_POST['message'];
			$userID = $_POST['userNow'];
			$adminStrat= $_POST['strategies'];
			$why = $_POST['whySwitch'];
			$other = $_POST['otherCmnts'];
			$time = date("g:i A");
			if($msg != "") {
				//if($_SESSION['name']=='admin') {
					mysql_query("INSERT INTO Conversation (sessionID, receiverID, message, displayTime) VALUES('$userID', '$userID', '$msg', '$time')");
					mysql_query("INSERT INTO ConvoStrategies (receiverID, message, strategies, whySwitch, otherComments) VALUES('$userID', '$msg', '$adminStrat', '$why', '$other')");
				//}
				//else {
					//$loggedIn = mysql_query("SELECT exitChat FROM Session WHERE userID = '$userID'");
					//$row = mysql_fetch_array($loggedIn);
					//if($row['exitChat'] == 0) {
						//mysql_query("INSERT INTO Conversation (sessionID, receiverID, message, displayTime)  VALUES('$userID', '$userID', '$msg', '$time')");
					//}
				//}
				echo $msg;
			}
			break;
			
		case 3:
		//Insert User message into Conversation
			$msg = $_POST['message'];
			$userID = $_POST['userNow'];
			$time = date("g:i A");
			if($msg != "") {
				//if($_SESSION['name']=='admin') {
					//mysql_query("INSERT INTO Conversation (sessionID, receiverID, message, displayTime) VALUES('$userID', 0, '$msg', '$time')");
				//}
				//else {
					$loggedIn = mysql_query("SELECT exitChat FROM Session WHERE userID = '$userID'");
					$row = mysql_fetch_array($loggedIn);
					if($row['exitChat'] == 0) {
						mysql_query("INSERT INTO Conversation (sessionID, receiverID, message, displayTime)  VALUES('$userID', 0, '$msg', '$time')");
					}
				//}
				echo $msg;
			}
			break;
			
		case 4:
		//Display conversation if not exiting
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
			
		case 5:
		//when admin hits the exit button
			$userID = $_POST['userNow'];
			mysql_query("UPDATE Session SET exitChat=1 WHERE userID='$userID'");
			break;
			
		case 6:
		//submitting the first post chat form
			$userID = $_POST['userNow'];
			$flyer = $_POST['printFlyer'];
			mysql_query("UPDATE PersuasionSuccess SET print = '$flyer' WHERE userID='$userID'");
			echo $flyer.": flyer";
			break;
			
		case 7:
		//submitting the second post chat form
			//$userID = $_POST['userNow'];
			//$cam = $_POST['chatUnderstoodMe'];
			//$uc = $_POST['understoodChat'];
			//$eNeed = $_POST['exerciseNeed'];
			//$tcInt= $_POST['taiChiInterest'];
			//$tcConv = $_POST['taiChiConvinced'];
			////mysql_query("INSERT INTO PostChatQuestionnaire (userID, exerciseNeed, taiChiInterest, taiChiConvinced, understoodChat, chatUnderstoodMe) VALUES ('$userID', '$eNeed', '$tcInt', '$tcConv', '$uc', '$cam')");
			//mysql_query("UPDATE PostChatQuestionnaire SET taiChiInterest =  '$tcInt' WHERE userID='$userID'");
			//echo $userID." ".$eNeed." ".$tcInt." ".$tcConv." ".$uc." ".$cam;
			//break;
			
		//submitting the first post chat form
			$userID = $_POST['userNow'];
			$cam = $_POST['chatUnderstoodMe'];
			$uc = $_POST['understoodChat'];
			$eNeed = $_POST['exerciseNeed'];
			$tcInt= $_POST['taiChiInterest'];
			$tcConv = $_POST['taiChiConvinced'];
			mysql_query("INSERT INTO PostChatQuestionnaire (userID, exerciseNeed, taiChiInterest, taiChiConvinced, understoodChat, chatUnderstoodMe) VALUES ('$userID', '$eNeed', '$tcInt', '$tcConv', '$uc', '$cam')");
			echo $userID." ".$eNeed." ".$tcInt." ".$tcConv." ".$uc." ".$cam;
			break;
		
		case 8:
		//sets the typing attribute to true
			$userID = $_POST['userNow'];
			if($_SESSION['name']!='admin') {
				mysql_query("UPDATE Session SET userType = 1 WHERE userID = '$userID'");
			}
			else {
				mysql_query("UPDATE Session SET compType = 1 WHERE userID = '$userID'");
			}
			break;
			
		case 9:
		//sets the typing attribute to false
			$userID = $_POST['userNow'];
			if($_SESSION['name']!='admin') {
				mysql_query("UPDATE Session SET userType = 0 WHERE userID = '$userID'");
			}
			else {
				mysql_query("UPDATE Session SET compType = 0 WHERE userID = '$userID'");
			}
			break;
		
		case 10:
		//makes a query to determine if someone is typing and if so, displays that on the OTHER screen
			$userID = $_POST['userNow'];
			if($_SESSION['name']!='admin') {
				$type = mysql_query("SELECT compType FROM Session WHERE userID = '$userID'");
				$row = mysql_fetch_array($type);
				echo $row['compType'];
			}
			else  {
				$type = mysql_query("SELECT userType FROM Session WHERE userID = '$userID'");
				$row = mysql_fetch_array($type);
				echo $row['userType'];
			}
			break;
	}
?>
