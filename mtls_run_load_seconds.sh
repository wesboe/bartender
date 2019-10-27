#!/bin/bash
while(true)
do
curl -w "\n" --cacert keystore/ca.crt \
     --key keystore/hipster1.key \
     --cert keystore/hipster1.crt:changeit \
     https://localhost:8080/menu
  sleep $1s;
done
