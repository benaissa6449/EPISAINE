Set WshShell = CreateObject("WScript.shell")
WshShell.Run chr(34) & "exec_ihm.bat" & Chr(34), 0
Set WshShell = Nothing