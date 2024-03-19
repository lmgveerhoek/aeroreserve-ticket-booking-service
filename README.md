# README

# Deploy

Generate new .jar files using: 

```
mvn package -Pbeanstalk
```
Then deploy using: 

```
eb deploy
```