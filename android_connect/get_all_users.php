<?php
 
/*
 * Following code will list all the users
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all users from users table
$result = mysql_query("SELECT username,current_location FROM users") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // users node
    $response["users"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $user = array();
        $user["username"] = $row["username"];
        $user["current_location"] = $row["current_location"];
 
        // push single user into final response array
        array_push($response["users"], $user);
    }
 
    // echoing JSON response
    echo json_encode($response);
} 
?>
