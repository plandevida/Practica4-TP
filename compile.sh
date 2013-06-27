#!/bin/bash

echo "Limpiando directorios de compilación"

rm -r bin/*

echo "------------ OK"

echo ""

echo "Generando la lista de fuentes a compilar"

find src/ -name *.java > source_list

echo "------------ OK"

echo ""

echo "Compilando las fuentes en el directorio 'bin'"

echo ""

javac -encoding UTF-8 -source 7 -cp forms-1.3.0.jar:src/ -d bin/ -sourcepath . @source_list

cp -r resources bin/
cp src/sistema/entrada/imagenes/*png bin/sistema/entrada/imagenes/

echo ""

echo "Utilice el script start.bat para iniciar la aplicación. Si este script no inicia la aplicación hágalo manualmente"
