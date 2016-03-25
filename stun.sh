#!/bin/bash
echo "CSID: "
read csid
#echo "Student Number: "
#read stdnum
#    ssh -t $(csid)
#    ssh -t -l $csid -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 remote.ugrad.cs.ubc.ca
    ssh -nNT -L 1522:dbhost.ugrad.cs.ubc.ca:1522 "$csid"@remote.ugrad.cs.ubc.ca 


