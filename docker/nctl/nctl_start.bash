#!/usr/bin/env bash

shopt -s expand_aliases
source ~/.bashrc
cd ~/dev
source env/bin/activate
source casper-node/utils/nctl/activate

# START
figlet "Starting"
nctl-start
sleep 1
nctl-status