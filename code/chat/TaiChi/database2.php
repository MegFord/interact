<?
	$value = $_POST['func'];
	
	
	$con=mysql_connect("localhost", "ygutstein", "ygutstein");
       
	// Check connection
	if (!$con) {
  		die('Could not connect: ' . mysql_error());
  	} 

	mysql_select_db("db_tempt", $con); 
	
	switch($value) {
		case 1;
		//When the user visits the taichi website:
		//The database is checked to see if the userID was entered correctly.
		//If it was, the page is loaded, and the success is recorded in persuasion success
		//If there is a mistake, an error page is loaded.
			$userID = $_POST['userID'];
			$idExists = false;
			if($userID != "") {
				$currentIDs = mysql_query("SELECT * from PersuasionSuccess");
				while($currentIdRow = mysql_fetch_array($currentIDs)) {
					//if(($currentIdRow['userID'] == $userID) && ($currentIdRow['visitSite'] == 0)) {
					if($currentIdRow['userID'] == $userID) {
						echo "found";
						$idExists = true;
						break;
					}
				}
				if($idExists == false)
					echo "notFound";
				else {
					mysql_query("UPDATE PersuasionSuccess SET visitSite = 1 WHERE userID = '$userID'");
					mysql_query("INSERT INTO VisitSite (userID) VALUES ('$userID')");
				}
			}
			else
				echo "found";
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
