@echo off
cd db
for /f %%i in ('dir /b/a-d/od/t:c') do set LAST=%%i
cd ..
c:\xampp\mysql\bin\mysql -uroot -hlocalhost musicdb < db\%LAST%