<html lang="en">
	<head>
	<title>Adjusting</title>
	</head>
	
	<body>
		<?php
			session_start();
			
			$enc_lvl=$_REQUEST['choice'];
			$db=$_REQUEST['db'];
			$table=$_REQUEST['table'];
			$field=$_REQUEST['field'];
			$onion=$_REQUEST['onion'];
			$level=$_REQUEST['level'];
			
			echo 'choice: ' . $enc_lvl;
			echo 'level: ' .$level;
			
			if ($enc_lvl==$level) {
				header("location:encryptions.php");
			}
			else {
				echo 'enc_level: ' . $enc_lvl;
				$link = mysqli_connect($_SESSION['host'], $_SESSION['user'], $_SESSION['pass'], NULL, $_SESSION['port']);
				if($link == false) {
					echo 'ERROR: Could not connect. ' . mysqli_connect_error();
					header("location:index.php");
				}
				
				//onion adjustments do not work
				//possibly because the connection is terminated immediately
				$query = "SET @cryptdb='adjust', @database='".$db."', @table='".$table."', @field='".$field."', @".$onion."='".$enc_lvl."';";
				mysqli_query($link, $query);

				header("location:encryptions.php");
			}
		?>
	</body>
</html>
