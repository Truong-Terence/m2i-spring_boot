# m2i-spring_boot

- Utilisateur non connecté
  - S'inscrire (nom, prénom, email, photo, mot de passe) ✅
  - Se connecter (email, mot de passe) ✅
- Utilisateur connecté
  - Se déconnecter ✅
  - Visualiser les contacts ✅
  - Rechercher un contact par mots-clés🟠
  - Ajouter un contact ✅
  - Éditer un contact 🟠
  - Supprimer un contact 🟠
  - Éditer son profil (nom, prénom, photo, mot de passe) 🔴
- Partie Web service
  - Mettre en place l'API Restful pour les contacts (d'un utilisateur identifiable par son id) ✅


## Properties :
#Configuration

spring.datasource.url=jdbc:mysql://localhost:3306/tp_jpa
spring.datasource.username=********
spring.datasource.password=*********
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.thymeleaf.enabled=true
