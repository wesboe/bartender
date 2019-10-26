#!/bin/bash
while(true) do curl -w "\n" http://localhost:8080/menu; sleep $1s;  done
