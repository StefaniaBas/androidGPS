<?php 
/*
 * Following code will create a new user row
 * All user details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['username']) && isset($_POST['current_location'])) {
    $username = $_POST['username'];
    $current_location = $_POST['current_location'];
	
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO users ( username, current_location) VALUES ( '$username', '$current_location')");
}
?>
