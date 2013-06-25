
<html>
	<head>
		<link type="text/css" rel="stylesheet" href="flyerStyle.css" />
	</head>
	<body>
		<div class="wrapper">
			<h1>Tai Chi</h1>

			<h2>Sign Up Now!</h2>

			<h3>Benefits of Tai Chi:</h3>
			<div id="benefits">
				<ul>
					<li>It enhances harmony between body and mind</li>
					<li>It improves your internal energy level</li>
					<li>Improves your muscle strength and endurance</li>
					<li>Reduces back pain</li>
					<li>Lowers daily stress</li>
					<li>Can slow down the aging process</li>
				</ul>
			</div>
			
			<div id="contact">
				For more information, please visit : http://cs.neiu.edu/~taichi/?user=<? echo $user = $_GET['userNow'] ?><br><br>
			</div>
			<div id="userID">
				<b><? echo UserID.": ".$user = $_GET['userNow'] ?></b>
			</div>
		</div>
	</body>

</html>

