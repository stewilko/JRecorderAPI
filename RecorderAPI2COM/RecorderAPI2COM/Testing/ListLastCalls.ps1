$client = New-Object -ComObject RecorderAPI2COM.CClient
$searchReq = New-Object -ComObject RecorderAPI2COM.Model.CCallSearchRequest

"Connecting to Recorder API...";
$res = $client.Connect("RecorderAPI", "RecorderAPI", "192.168.45.125", 8024 );
$res;