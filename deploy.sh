#!/bin/bash

# Définition des variables
APP_NAME="gestionParc_1"
SRC_DIR="src"
WEB_DIR="webapp"
BUILD_DIR="build"
LIB_DIR="lib"

# TOMCAT_WEBAPPS="/Users/nomena/TAFF/S3/apache-tomcat-9.0.95/webapps"
TOMCAT_WEBAPPS="/home/hotiana/apache-tomcat-9/webapps"

# 1. On définit le Classpath pour inclure la Servlet API ET Gson pour la compilation
# Le ":" sert de séparateur sous Linux
CLASSPATH_COMPIL="$LIB_DIR/servlet-api.jar:$LIB_DIR/gson-2.11.0.jar"

# Nettoyage et création du répertoire temporaire
rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/WEB-INF/classes
mkdir -p $BUILD_DIR/WEB-INF/lib       # On crée le dossier lib de l'application web

# Compilation des fichiers Java
find $SRC_DIR -name "*.java" > sources.txt
javac -cp "$CLASSPATH_COMPIL" -d $BUILD_DIR/WEB-INF/classes @sources.txt
rm sources.txt

# Copier les fichiers web (web.xml, JSP, etc.)
cp -r $WEB_DIR/* $BUILD_DIR/

# 2. TRÈS IMPORTANT : Copier Gson dans le dossier lib du build pour que Tomcat y ait accès à l'exécution
cp $LIB_DIR/gson-2.11.0.jar $BUILD_DIR/WEB-INF/lib/

# Générer le fichier .war dans le dossier build
cd $BUILD_DIR || exit
jar -cvf $APP_NAME.war *
cd ..

# Déploiement dans Tomcat
cp -f $BUILD_DIR/$APP_NAME.war $TOMCAT_WEBAPPS/

echo ""
echo "Déploiement terminé. Redémarrez Tomcat si nécessaire."
echo ""