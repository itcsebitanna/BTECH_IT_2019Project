<?php
session_start();
include("include/dbconnect.php");
extract($_REQUEST);

//$qry=mysql_query("select * from upload where id=$id");
//$row=mysql_fetch_array($qry);
$fname="dataset.xlsx";
	
if(isset($btn))
{
?>
<script language="javascript">
window.location.href="store_data.php?File=spreadsheet-reader-master/upload/<?php echo $fname; ?>";
</script>
<?php 
}	
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><?php include("include/title.php"); ?></title>
<link rel="shortcut icon" href="img/icon.ico">
<style type="text/css">
<!--
.box {
	background-color: #F3F3F3;
	height: 270px;
	width: 270px;
	border: 1px solid #A8A8A8;
	padding:10px;
}
.box1 {
	background-color: #F3F3F3;
	height: 100px;
	width: 270px;
	border: 1px solid #A8A8A8;
	padding:10px;
}
.box2 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333333;
	padding:10px;
}
.box3 {
padding:10px;
}
.t1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 24px;
	font-weight: bold;
	color: #0066CC;
	font-variant: small-caps;
	text-transform: none;
}
.msg {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #FF0000;
}
.box4
{
width:250px;
height:35px;
}
-->

</style>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<link rel="shortcut icon" href="favicon.ico">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Simple Line Icons -->
	<link rel="stylesheet" href="css/simple-line-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	<!-- Style -->
	<link rel="stylesheet" href="css/style.css">


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

<style>
body {margin:0;font-family:Arial}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.active {
  background-color:#CC0066;
  color: white;
}

.topnav .icon {
  display: none;
}

.dropdown {
    float: left;
    overflow: hidden;
}

.dropdown .dropbtn {
    font-size: 17px;    
    border: none;
    outline: none;
    color: white;
    padding: 14px 16px;
    background-color: inherit;
    font-family: inherit;
    margin: 0;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    float: none;
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.topnav a:hover, .dropdown:hover .dropbtn {
  background-color: #555;
  color: white;
}

.dropdown-content a:hover {
    background-color: #ddd;
    color: black;
}

.dropdown:hover .dropdown-content {
    display: block;
}

@media screen and (max-width: 600px) {
  .topnav a:not(:first-child), .dropdown .dropbtn {
    display: none;
  }
  .topnav a.icon {
    float: right;
    display: block;
  }
}

@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive .icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }
  .topnav.responsive .dropdown {float: none;}
  .topnav.responsive .dropdown-content {position: relative;}
  .topnav.responsive .dropdown .dropbtn {
    display: block;
    width: 100%;
    text-align: left;
  }
}
</style>	
</head>

<body>
   <?php include("link_admin.php"); ?>
 <div class="panel panel-default">
  <div class="panel-heading" align="center">DATA - YEARWISE </div>
  <div class="panel-body">
   <h3 align="center"></h3>
  </div>
</div>
 <!--start content area-->
  <?php

if($_REQUEST['act']=="wrong")
{
?>
<div class="row">
			<div class="col-lg-4">
				
				<!-- A grey horizontal navbar that becomes vertical on small screens -->
			</div>
			
			
			
            <div class="col-lg-4">
<div class="alert alert-warning">
  <strong>Warning!</strong> This Username or Password Incorrect!
</div>
			</div>
</div>			
<?php
}
?>			
<div class="row">
			<div class="col-lg-4">
				
				<!-- A grey horizontal navbar that becomes vertical on small screens -->
			</div>
			
			
			
            <div class="col-lg-4">
              
			</div>
</div>			
<!--end content area--><br /><br />


<div class="row">
	<div class="col-lg-2">
	</div>
	<div class="col-lg-8">
			  	<div  align="center">
				
				
				<form name="name" method="post">
                   
					<p>DISTRICT: <?php echo $dis; ?></p>
					<table width="561" border="1">
                      <tr>
                        <th width="39" class="bg-primary" scope="col">Sno</th>
                        <th width="49" class="bg-primary" scope="col">Year</th>
                        <th width="67" class="bg-primary" scope="col">Murder</th>
                        <th width="50" class="bg-primary" scope="col">Rape</th>
                        <th width="106" class="bg-primary" scope="col">kidnapping</th>
                        <th width="74" class="bg-primary" scope="col">robbery</th>
                        <th width="42" class="bg-primary" scope="col">riots</th>
                        <th width="82" class="bg-primary" scope="col">cheating</th>
                      </tr>
					  <?php
					  $i=0;
					  $q1=mysql_query("select * from crime_data where district='$dis'");
					  while($r1=mysql_fetch_array($q1))
					  {
					  $i++;
					  
					 
					  
					  if($sum<=500)
					  {
					  $cla="bgcolor=#009900";
					  }
					  else if($sum<=1500)
					  {
					  $cla="bgcolor=#FFFF33";
					  }
					  else if($sum<=2500)
					  {
					  $cla="bgcolor=#FF6600";
					  }
					  else
					  {
					  $cla="bgcolor=#FF0000";
					  }
					  ?>
                      <tr>
                        <th scope="row"><?php echo $i; ?></th>
                        <td><?php echo $r1['year']; ?></td>
                        <td><?php echo $r1['crime1']; ?></td>
                        <td><?php echo $r1['crime2']; ?></td>
                        <td><?php echo $r1['crime3']; ?></td>
                        <td><?php echo $r1['crime4']; ?></td>
                        <td><?php echo $r1['crime5']; ?></td>
                        <td><?php echo $r1['crime6']; ?></td>
                      </tr>
					  <?php
					  }
					  ?>
                    </table>
                    
                  <p>&nbsp;</p>
                    <p>&nbsp;</p>
                    <div class="form-group">       
                      
                  </div>
					
                  </form>
			</div>	
  	</div>
	<div class="col-lg-2">
		</div>
</div>
  <p align="center" class="msg"><?php
  if($msg!="")
  {
  echo $msg;
  }
  ?></p>
  <p>&nbsp;</p>
</body>
</html>