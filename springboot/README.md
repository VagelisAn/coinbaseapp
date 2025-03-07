# springboot-coinBase

Άνοιξε τα παρακάτω:
   Υπηρεσία	URL	Default Login
   Prometheus	http://localhost:9090	Δεν χρειάζεται
   Grafana	http://localhost:3000	admin / admin
   
Grafana
   Σύνδεση σε http://localhost:3000
   Settings → Data Sources
   Επέλεξε Prometheus
   URL: http://prometheus:9090
   Save & Test

Endpoint

Περιγραφή

/actuator/health

Κατάσταση Υγείας

/actuator/caches

Κατάσταση Cache

/actuator/prometheus

Metrics για Prometheus