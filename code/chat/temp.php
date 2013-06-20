<?
session_start();
	$pos = $_POST['position']; 
	$gender = $_POST['gender'];
	$ethnic = $_POST['ethnicity'];
	$age = $_POST['age'];
	$social = $_POST['socialnetwork'];
	$ex1 = $_POST['exercise1'];
	$ex2 = $_POST['exercise2'];
	
	if($social == "Facebook") {
		$fbook == 1;
		$tweet == 0;
	}
	else if($social == "Twitter"){
		$fbook == 0;
		$tweet == 1;
	}
	else{
		$fbook == 1;
		$tweet == 1;
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
		$exType = 'Aerobicexercise';
	else if($ex2 == "Anaerobicexercise")
		$exType = 'Anaerobicexercise';
	else if($ex2 == "Flexibility")
		$exType = 'Flexibility';
	else
		$exType = 'None';
	
	//$fp = fopen("log.html", 'a');
	
	$con=mysql_connect("localhost", "ygutstein", "ygutstein");
       
	// Check connection
	if($con)
		echo('Successful '.$pos.' '.$age);
	if (!$con)
  	{
  		die('Could not connect: ' . mysql_error());
  	} 

	mysql_select_db("db_tempt", $con); 
	
	//insert data
	mysql_query("INSERT INTO Demographics (position, gender, ethnicity, ageRange, facebook, twitter, exerciseRange, exerciseType) VALUES('$pos','$gender','$ethnic','$ageGroup','$fbook','$tweet','$exVal','$exType')");
	
	$curUser = mysql_query("SELECT MAX(userID) FROM Demographics");
	
	mysql_query("INSERT INTO Session VALUES('$curUser',1,1,0) ");

	////collect data
	//$result=mysql_query("SELECT * FROM Student");

	//if (!$result) {
		//die('Invalid query: ' . mysql_error());
	//}
	////put in array each row
	//while($row = mysql_fetch_array($result))
	//{
		//if($_SESSION['name']=='admin'){
			echo("<div class='msgln'>(".date("g:i A").") <b>Health Guru: </b>".stripslashes(htmlspecialchars('user: '.$curUser))."<br></div>");
		//}
		//else{
			//fwrite($fp, "<div class='msgln'>(".date("g:i A").") <b>You: </b>".stripslashes(htmlspecialchars($pos))."<br></div>");
		//}
	//}
	//fclose($fp);
?>
