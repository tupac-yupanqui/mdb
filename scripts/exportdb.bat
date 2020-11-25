@echo off

set mydate=%date:~6,2%_%date:~3,2%_%date:~0,2%__%time:~0,2%_%time:~3,2%
set mydate=%mydate: =0%

c:\xampp\mysql\bin\mysqldump -uroot -hlocalhost musicdb > db\mdb_%mydate%.sql