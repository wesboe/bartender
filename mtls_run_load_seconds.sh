#!/bin/bash
while(true)
do
curl -w "\n" --cacert keystore/ca.crt \
     --key keystore/cid.key \
     --cert keystore/cid.crt:changeit \
     https://localhost:8080/menu
  sleep $1s;
done
