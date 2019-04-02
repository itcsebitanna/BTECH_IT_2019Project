<div class="topnav" id="myTopnav">
  <a href="index.php" class="active">Home</a>
  <!--<div class="dropdown">
    <button class="dropbtn">My Accounts 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="profile.php">My Profile</a>
      <a href="changepass.php">Change Password</a>
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn">Academis 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="view_table.php">Timetable</a>
      <a href="view_att.php">Attendance</a>
	  <a href="view_eva.php">Evaluation</a>
    </div>
  </div> -->
 
  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onClick="myFunction()">&#9776;</a>
</div>


<script>
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
</script>