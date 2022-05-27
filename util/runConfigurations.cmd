@ECHO OFF

:Start

CLS

ECHO.
ECHO.
ECHO 1.Iniciar a aplicação
ECHO 2.Iniciar debug
ECHO 3.Sair
ECHO.
ECHO.

SET choice=
SET /p choice="Selecionar: "
ECHO.

IF '%choice%'=='1' GOTO Start_Application
IF '%choice%'=='2' GOTO Debug_Application
IF '%choice%'=='3' GOTO Sair

ECHO
ECHO
ECHO "%choice%" item invalido, tente novamente

PAUSE
GOTO Start


:Start_Application

call mvn org.springframework.boot:spring-boot-maven-plugin:run
GOTO End

:Debug_Application
call mvn org.springframework.boot:spring-boot-maven-plugin:run -Dspring-boot.run.fork=false
GOTO End

:End
pause
GOTO Start

:Sair
EXIT