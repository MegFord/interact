<?
session_start();
	$text = $_POST['text'];
	
	$fp = fopen("log.html", 'a');
    if($_SESSION['name']=='admin'){
	fwrite($fp, "<div class='msgln'>(".date("g:i A").") <b>Health Guru: </b>".stripslashes(htmlspecialchars($text))."<br></div>");
    }
	else{
		fwrite($fp, "<div class='msgln'>(".date("g:i A").") <b>You: </b>".stripslashes(htmlspecialchars($text))."<br></div>");
	}
	fclose($fp);
?>