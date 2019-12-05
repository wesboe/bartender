## Running Prometheus and Grafana
To run both applications you need to have docker installed.  
Go to the prometheus folder and execute: 
```bash
docker compose up  
```
Applications can be reached on:
- Prometheus http://localhost:9090/  
- Grafana http://localhost:3000/  

Log in credentials for Grafana are admin/secret   

## Getting Started
Start Bartender application in your IDE or use `mvn spring-boot:start`  
In Grafana import dashboard `https://grafana.com/grafana/dashboards/4701`  

## Generating traffic 
Start load script `./mtls_run_load_seconds 1` to generate traffic with a tps of 1.  
You can also add delays in the `BartenderController` using the `Delayer`

## Helpfull links
* [Grafana](https://grafana.com/)
* [Prometheus](https://prometheus.io/)  
* [Micrometer](http://micrometer.io/)  
* [Spring Boot Actuator production ready metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics)  
* [Spring boot blog about metrics](https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector)  
