#!/usr/bin/env bash

shopt -s expand_aliases
source ~/.bashrc

cd ~/dev

python3 -m venv env
source env/bin/activate

source casper-node/utils/nctl/activate
nctl-compile
source ~/.bashrc
nctl-assets-setup
sleep 5