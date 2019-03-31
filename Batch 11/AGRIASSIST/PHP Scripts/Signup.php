<?php

header("Access-Control-Allow-Origin: *");


	if($_SERVER["REQUEST_METHOD"]=="POST")
{
$name=$_POST["Username"];	
$pass=$_POST["Passw"];	
$mailid=$_POST["EmailID"];
$Phone_no=$_POST["Phoneno"];
$Address=$_POST["Address"];
signup($name,$pass,$mailid,$Phone_no,$Address);
}

else{
	echo "It doesnot support GET Method";
	
}
function signup($Doctor_Name,$CPassword,$Email_ID,$Phone_no,$Address)
{
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "sample";
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$phon=0;
$sqlphone = "SELECT Phone_No FROM users WHERE Phone_No='$Phone_no' ";
$resultphone = $conn->query($sqlphone);
if ($resultphone->num_rows > 0)
{
    while($rowphone = $resultphone->fetch_assoc())
    	{
	 $phon=$rowphone["Phone_No"];
		}
}    
$sql = "INSERT INTO `users`(`Name`, `Password`, `Email_ID`, `Phone_No`, `Address`) VALUES ('$Doctor_Name','$CPassword','$Email_ID','$Phone_no','$Address')";

if(strcmp($phon,$Phone_no))
{
	if ($conn->query($sql) === TRUE) 
	{
	http_response_code(200);
    echo "Registered Successfully";
	} 
else 
{	
http_response_code(500);
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}

else
{
	http_response_code(500);
	echo $Phone_no;
	echo " Mobile Number Already Registered";	
}
$conn->close();
}


?>

