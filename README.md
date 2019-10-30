## Running Prometheus and Grafana
To run both applications you need to have docker installed.  
Go to the prometheus folder and execute: 
```bash
docker compose up  
```
Applications can be reached on
- Prometheus http://localhost:9090/  
- Grafana http://localhost:3000/  

Log in credentials for Grafana are admin/password   

## Getting Started
Start Bartender application in your IDE or use `mvn spring-boot:start`  
In Grafana import dashboard `https://grafana.com/grafana/dashboards/4701`   
Start load script `run_load_seconds.sh` to generate traffic

## Getting Started
https://grafana.com/  
https://prometheus.io/  
http://micrometer.io/  
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics  
https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector  
