### Android Screenshot Library ###
##################################
###       Startup script       ###

"Android Screenshot Library -- initializing..." | Out-Host

try
{
	# Check whether ANDROID environmental variable is set
	if ([String]::IsNullOrEmpty($Env:ANDROID))	{
		"*** Android SDK not found. ***" | Out-Host
		"Make sure ANDROID variable is pointing to Android SDK root directory." | Out-Host
	}
	else
	{
		$adb = [IO.Path]::Combine([IO.Path]::Combine($Env:ANDROID, "platform-tools"), "adb.exe")
		
		# Check whether device is connected and wait for one
		$adbState = (& $adb get-state)
		if ($adbState -ne "device") {
			"Device not found -- connect one to continue..." | Out-Host
			& $adb wait-for-device
			"Device connected." | Out-Host
		}
		
		# Install service
		"Installing native service..." | Out-Host
		& $adb push ./asl-native /data/local/asl-native
		& $adb shell /system/bin/chmod 0777 /data/local/asl-native
		
		# If the service is already running, kill it
		$ps = (& $adb shell ps) | Where-Object { $_.Contains("asl-native") }
		if (-not [String]::IsNullOrEmpty($ps)) {
			"Service already running -- do you want to restart it?" | Out-Host
			$answer = Read-Host -Prompt "(Y)es/(N)o"
			if ($answer.ToLower()[0] -ne 'y')	{ return }
			
			# Get PID of the service's process
			"Terminating service..." | Out-Host
			for ($i = 0; $i -lt $ps.Length; ++$i)
			{
				$psLine = $ps[$i].ToString().Split(" ", [StringSplitOptions]::RemoveEmptyEntries)
				& $adb shell kill -9 $psLine[1]
			}
			"Service terminated." | Out-Host
		}
		
		# Start the service
		"Starting service..." | Out-Host
		cmd /c start /B  $adb shell "/data/local/asl-native /data/local/asl-native.log"
		"Service started successfully." | Out-Host
	}
}
catch [Exception]
{
	"*** An error has occured ***" | Out-Host
	$_ | Out-Host
}