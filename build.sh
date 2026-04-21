#!/bin/bash
# Build script for Render deployment

echo "Running Maven build..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo "JAR file created at: target/demo-0.0.1-SNAPSHOT.jar"
else
    echo "Build failed!"
    exit 1
fi
