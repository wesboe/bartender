#!/bin/bash
while(true)
do
curl -w "\n" --cacert keystore/ca.crt \
     --key keystore/hipster2.key \
     --cert keystore/hipster2.crt:changeit \
     https://localhost:8080/menu
  sleep $1s;
done
