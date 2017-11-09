$client = New-Object -ComObject RecorderAPI2COM.CClient

"Connecting to Recorder API...";
$res = $client.Connect("RecorderAPI", "RecorderAPI", "192.168.45.125", 8024 );
$res;

"Fetching client api..."
$client | Get-Member

"Fetching server capabilities...";
$capabilties = New-Object -ComObject RecorderAPI2COM.Model.CServerCapabilities

$res = $client.GetServerCapabilities([ref] $capabilties);

$capabilties

"Disconnecting...";
$client = $client.Disconnect()


