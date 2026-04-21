# Render Deployment Configuration

This application is configured to deploy on Render.com

## Prerequisites

- Java 21
- Maven 3.9+
- GitHub account connected to Render

## Deployment Steps

1. Go to https://render.com and sign up
2. Click "New" → "Web Service"
3. Select "David04eng/Actividad-11" repository
4. Configure the following settings:

   - **Root Directory**: Leave empty
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/demo-0.0.1-SNAPSHOT.jar`
   - **Instance Type**: Free (512 MB RAM, 0.1 CPU)

5. Add Environment Variables (optional):
   - `PORT`: 8080
   - `JAVA_OPTS`: -Xmx512m

6. Click "Create Web Service"

## Deployment will:

- Build the Spring Boot application with Maven
- Create a JAR file
- Start the application on port 8080
- Deploy to Render's infrastructure

## Access Your Application

Once deployed, your app will be available at:
```
https://<service-name>.onrender.com
```

The H2 console will be available at:
```
https://<service-name>.onrender.com/h2-console
```

## Notes

- The application uses H2 in-memory database
- First deployment takes 3-5 minutes
- Render provides free tier with automatic spin-down after 15 minutes of inactivity
- Paid plans offer better performance and persistent storage
