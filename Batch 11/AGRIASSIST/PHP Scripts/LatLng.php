<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname ="sample";
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error)
	{
    die("Connection failed: " . $conn->connect_error);
    } 
	   $products_arr=array();
    $products_arr["Farmers"]=array();
$sql = "SELECT *FROM users";
$result = $conn->query($sql);
if ($result->num_rows > 0)
	{
		$i=0;
    while($row = $result->fetch_assoc())
	{
		$i++;
		
	$product_item=array(
			"Name" => $row["Name"],
			"Phoneno" =>$row["Phone_No"],
			"Address" => $row["Address"],
			"FarmerID" => $row["Farmer_ID"],
			"Latitude" => $row["Latitude"],
			"Longitude" => $row["Longitude"],
			);

		array_push($products_arr["Farmers"], $product_item);
    
    }
http_response_code(200);
header('Content-type: application/json');
	 echo json_encode($products_arr);
    }
 else 
 {
  echo "No History";	
http_response_code(500);
 
   
 }
$conn->close();
?>
 