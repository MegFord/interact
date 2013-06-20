<?
session_start();
$text = $_POST['text'];
	
	$fp = fopen("log.html", 'a');
	
	$con=mysql_connect("localhost", "teststudent", "teststudent");
       
	// Check connection
	if (!$con)
  	{
  		die('Could not connect: ' . mysql_error());
  	} 

	mysql_select_db("db_teststudent", $con); 

	//collect data
	$result=mysql_query("SELECT * FROM Student");

	if (!$result) {
		die('Invalid query: ' . mysql_error());
	}
	//put in array each row
	while($row = mysql_fetch_array($result))
	{
		if($_SESSION['name']=='admin'){
			fwrite($fp, "<div class='msgln'>(".date("g:i A").") <b>Health Guru: </b>".stripslashes(htmlspecialchars($row['studentName']))."<br></div>");
		}
		else{
			fwrite($fp, "<div class='msgln'>(".date("g:i A").") <b>You: </b>".stripslashes(htmlspecialchars($row['studentName']))."<br></div>");
		}
	}
	fclose($fp);
?>