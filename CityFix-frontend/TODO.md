# TODO - Mise à jour du statut des signalements

## Étapes à compléter:

- [x] 1. Analyser le code existant (Controller, Service, Frontend)
- [x] 2. Confirmer le plan avec l'utilisateur
- [x] 3. Modifier details-signalements.html pour ajouter:
   - [x] Selecteur de statut (dropdown)
   - [x] Bouton de mise à jour du statut
   - [x] Logique JavaScript pour appeler l'API
- [x] 4. Corriger le problème d'affichage de l'adresse (localisation)
   - [x] Ajout de FetchType.EAGER sur les relations @ManyToOne et @OneToOne dans Signalement.java
   - [x] Modification du controller pour utiliser getSignalementAvecRelations()
- [x] 5. Redémarrer l'application backend pour appliquer les modifications


