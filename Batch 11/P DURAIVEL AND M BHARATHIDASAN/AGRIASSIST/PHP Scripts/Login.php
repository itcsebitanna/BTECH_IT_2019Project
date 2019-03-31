<?php
if($_SERVER["REQUEST_METHOD"]=="POST")
{
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "sample";
$Phone_no=$_POST["Farmer_ID"];
$Password=$_POST["Password"];
login($Phone_no,$Password);
}
else
{
echo "It doesnot support GET Method";
}
function login($Phone_No,$Password){
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "sample";
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$sqlid = "SELECT Phone_No FROM users WHERE Phone_No='$Phone_No'and password='$Password'";
$resultid = $conn->query($sqlid);
$id=0;
if ($resultid->num_rows > 0)
	{
      http_response_code(200);
      $_SESSION['active']="1";
  
    }
 else 
 {
	 http_response_code(401);
	echo "Incorrect Username or Password";
	
    
 }

$conn->close();
}
?>

