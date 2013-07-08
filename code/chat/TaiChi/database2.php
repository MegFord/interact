<?
	$value = $_POST['func'];
	
	
	$con=mysql_connect("localhost", "ygutstein", "ygutstein");
       
	// Check connection
	if (!$con) {
  		die('Could not connect: ' . mysql_error());
  	} 

	mysql_select_db("db_tempt", $con); 
	
	switch($value) {
		case 1:
		//When the user visits the taichi website, the success is recorded in persuasion success
			$userID = $_POST['userID'];
			mysql_query("UPDATE PersuasionSuccess SET visitSite = 1 WHERE userID = '$userID'");
			mysql_query("INSERT INTO VisitSite (userID) VALUES ('$userID')");
			break;
			
		case 2:
		//When the user visits the clicks on a link for an online taichi video, the success is recorded in persuasion success
			$userID = $_POST['userID'];
			mysql_query("UPDATE PersuasionSuccess SET tcLink1 = 1 WHERE userID = '$userID'");		
			echo $userID;
			break;
			
		case 3:
		//When the user visits the clicks on a link for an online taichi video, the success is recorded in persuasion success
			$userID = $_POST['userID'];
			mysql_query("UPDATE PersuasionSuccess SET tcLink2 = 1 WHERE userID = '$userID'");		
			echo $userID;
			break;
			
		case 4:
		//When the user visits the clicks on a link for an online taichi video, the success is recorded in persuasion success
			$userID = $_POST['userID'];
			mysql_query("UPDATE PersuasionSuccess SET tcLink3 = 1 WHERE userID = '$userID'");		
			echo $userID;
			break;
			
		case 5:
		//When the user visits the clicks on a link for an online taichi video, the success is recorded in persuasion success
			$userID = $_POST['userID'];
			mysql_query("UPDATE PersuasionSuccess SET tcLink4 = 1 WHERE userID = '$userID'");		
			echo $userID;
			break;
	}
?>
