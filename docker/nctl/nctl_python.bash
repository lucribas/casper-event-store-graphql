#!/usr/bin/env bash
shopt -s expand_aliases
source ~/.bashrc

cd ~/dev

python3 -m venv env

source env/bin/activate

pip install --upgrade pip
pip install jq
pip install supervisor
pip install toml
