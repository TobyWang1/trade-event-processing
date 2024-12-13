$Command = Read-Host -Prompt "Command name (or nothing for help)"

if (-not $Command) {
    $Command = "help"
}

flyway -configFiles="src/main/resources/application.properties" $Command
