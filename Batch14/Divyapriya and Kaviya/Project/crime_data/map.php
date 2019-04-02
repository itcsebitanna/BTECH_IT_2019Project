<?php
session_start();
include("include/dbconnect.php");
extract($_REQUEST);

$q1=mysql_query("select * from crime_data where district like '%$src1%'");
		$r1=mysql_fetch_array($q1);
		$lat1=$r1['latitude'];
		$q11=mysql_query("select * from crime_data where district like '%$dest1%'");
		$r11=mysql_fetch_array($q11);
		$lat2=$r11['latitude'];
		
		if($lat1>$lat2)
		{
		$d1=$lat2;
		$d2=$lat1;
		}
		else
		{
		$d1=$lat1;
		$d2=$lat2;
		}
		
		
			$sq1=mysql_query("select district,latitude,longitude,sum(crime1),sum(crime2),sum(crime3),sum(crime4),sum(crime5),sum(crime6) from crime_data where latitude between $d1 and $d2 group by district");
			
			$sq11=mysql_query("select district,latitude,longitude,sum(crime1),sum(crime2),sum(crime3),sum(crime4),sum(crime5),sum(crime6) from crime_data where latitude between $d1 and $d2 group by district");
?>
<html>
<head>
  
  <title>Google Maps Multiple Markers</title>
  <script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
</head>
<body>

  <div id="map" style="height: 800px; width: 800px;">
</div>
<script type="text/javascript">
    var locations = [
		<?php
		

		
		
		
		while($sr1=mysql_fetch_array($sq1))
		{
		$s1=$sr1['sum(crime1)']+$sr1['sum(crime2)']+$sr1['sum(crime3)']+$sr1['sum(crime4)']+$sr1['sum(crime5)']+$sr1['sum(crime6)'];
			if($s1>1500)
			{
		?>
      ['<?php echo $sr1['district']; ?>, UNSAFE', <?php echo $sr1['latitude']; ?>, <?php echo $sr1['longitude']; ?>, 4],
	  	<?php
			}
			
		}
		?>
		
		<?php
		/*$q4=mysql_query("select * from ta_bookmark");
		while($r4=mysql_fetch_array($q4))
		{
		$q31=mysql_query("select * from ta_register where email='".$r4['uname']."'");
		$r31=mysql_fetch_array($q31);
		?>
      ['<?php echo $r31['name'].",".$r4['comment']; ?>', <?php echo $r4['latitude']; ?>, <?php echo $r4['longitude']; ?>, 4],
	  	<?php
		}*/
		?>
      /*['Coogee Beach', -33.923036, 151.259052, 5],
      ['Cronulla Beach', -34.028249, 151.157507, 3],
      ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
      ['Maroubra Beach', -33.950198, 151.259302, 1]*/
    ];
	
	
	var locations2 = [
		<?php
		while($sr11=mysql_fetch_array($sq11))
		{
		$s11=$sr11['sum(crime1)']+$sr11['sum(crime2)']+$sr11['sum(crime3)']+$sr11['sum(crime4)']+$sr11['sum(crime5)']+$sr11['sum(crime6)'];
			if($s11<=1500)
			{
		?>
      ['<?php echo $sr11['district']; ?>, SAFE', <?php echo $sr11['latitude']; ?>, <?php echo $sr11['longitude']; ?>, 4],
	  	<?php
			}
			
		}
		?>
		
		<?php
		/*$q4=mysql_query("select * from ta_bookmark");
		while($r4=mysql_fetch_array($q4))
		{
		$q31=mysql_query("select * from ta_register where email='".$r4['uname']."'");
		$r31=mysql_fetch_array($q31);
		?>
      ['<?php echo $r31['name'].",".$r4['comment']; ?>', <?php echo $r4['latitude']; ?>, <?php echo $r4['longitude']; ?>, 4],
	  	<?php
		}*/
		?>
      /*['Coogee Beach', -33.923036, 151.259052, 5],
      ['Cronulla Beach', -34.028249, 151.157507, 3],
      ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
      ['Maroubra Beach', -33.950198, 151.259302, 1]*/
    ];
	
	
	
	
	
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 10,
      center: new google.maps.LatLng(10.790483, 78.704673),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < locations.length; i++) { 
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map
      });
//marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }
	////////
	 for (i = 0; i < locations2.length; i++) { 
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations2[i][1], locations2[i][2]),
        map: map
      });
marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations2[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }
	
	
//////////////////////////////////////	
	
	
  </script> 
<a href="search.php">Back</a>
</body>
</html>