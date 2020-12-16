@echo off

SET path_to_grammar=%~dp0..\..\src\main\antlr4\dev\conductor\cql\cql.g4
SET path_to_package=%~dp0..\..\src\main\java\dev\conductor\centra\domain\search\cql
SET ANTLR_HOME=%~dp0..\antlr\antlr-4.9-complete.jar

echo %path_to_grammar%
echo %path_to_package%
echo %ANTLR_HOME%

@java -Xmx500M -jar %ANTLR_HOME% -o %path_to_package%  -Dlanguage=Java -package "dev.conductor.centra.domain.search.cql" -encoding UTF-8 -listener -visitor %path_to_grammar%

pause