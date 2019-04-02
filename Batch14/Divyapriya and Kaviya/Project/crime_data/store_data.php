<?php
session_start();
include("include/dbconnect.php");
extract($_REQUEST);

if (isset($argv[1]))
	{
		$Filepath = $argv[1];
	}
	elseif (isset($_GET['File']))
	{
		$Filepath = $_GET['File'];
	}
	else
	{
		if (php_sapi_name() == 'cli')
		{
			echo 'Please specify filename as the first argument'.PHP_EOL;
		}
		else
		{
			echo 'Please specify filename as a HTTP GET parameter "File", e.g., "/test.php?File=test.xlsx"';
		}
		exit;
	}

	// Excel reader from http://code.google.com/p/php-excel-reader/
	require('spreadsheet-reader-master/php-excel-reader/excel_reader2.php');
	require('spreadsheet-reader-master/SpreadsheetReader.php');

	date_default_timezone_set('UTC');

	$StartMem = memory_get_usage();
	//echo '---------------------------------'.PHP_EOL;
	//echo 'Starting memory: '.$StartMem.PHP_EOL;
	//echo '---------------------------------'.PHP_EOL;

	try
	{
		$Spreadsheet = new SpreadsheetReader($Filepath);
		$BaseMem = memory_get_usage();

		$Sheets = $Spreadsheet -> Sheets();

		//echo '---------------------------------'.PHP_EOL;
		//echo 'Spreadsheets:'.PHP_EOL;
		//print_r($Sheets);
		//echo '---------------------------------'.PHP_EOL;
		//echo '---------------------------------'.PHP_EOL;

		//foreach ($Sheets as $Index => $Name)
		//{
			//echo '---------------------------------'.PHP_EOL;
			//echo '*** Sheet '.$Name.' ***'.PHP_EOL;
			//echo '---------------------------------'.PHP_EOL;

			$Time = microtime(true);
			$k=0;
			$Spreadsheet -> ChangeSheet($Index);
			$sum1=0;
			$sum2=0;
			$sum3=0;
			$sum4=0;
			$sum5=0;
			$sum6=0;
			$district[0]="";
			$year[0]="";
			
			foreach ($Spreadsheet as $Key => $Row)
			{
				//echo $Key.': ';
				
				if ($Row)
				{
				
					if($k>0 && $Row[0]=="TAMIL NADU")
					{
					
							if (in_array($Row[1], $district))
  								{
								}
								else
								{
								$district[]=$Row[1];
								}
							
							
							$dis=$Row[1];
							$yr=$Row[2];
								
								if($dis==$Row[1] && $yr==$Row[2])
								{
									if($dis!="TOTAL")
									{
							$sum1=$Row[3];
							$arr_d1[$dis][$yr][0]=$sum1;	
								//echo $dis."-".$yr."-".$sum1."<br>";
								
								$q2=mysql_query("select * from crime_data where district='$dis' && year='$yr'");
								$n2=mysql_num_rows($q2);
								if($n2==0)
								{
								$mq = mysql_query("select max(id) as maxid from crime_data");
        $mr = mysql_fetch_array($mq);
        $id = $mr['maxid']+1;
		
										
						
					$q1="insert into crime_data(id,state,district,year,crime1,crime2,crime3,crime4,crime5,crime6,latitude,longitude) values($id,'$Row[0]','$dis','$yr','$Row[3]','$Row[6]','$Row[9]','$Row[14]','$Row[19]','$Row[21]','$Row[32]','$Row[33]')";
					mysql_query($q1);
								}
					
					
									}
								}
					
						
						/*for($i=0;$i<count($Row);$i++)
						{
							if($i=="0" || $i=="1" || $i=="2" || $i=="3" || $i=="6" || $i=="9" || $i=="14" || $i=="19" || $i=="21")
							{
							
								
							}
						}*/
						
					
					}//if
					
				}//Row
				$k++;
			}//for
			
			
		//}
		
		
		
		///////////////////////
		
		
	}
	catch (Exception $E)
	{
		//echo $E -> getMessage();
	}
?>
<script language="javascript">
//alert("Uploaded Successfully");
window.location.href="view_data5.php";
</script>
<?php