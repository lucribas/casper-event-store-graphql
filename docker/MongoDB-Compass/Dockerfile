FROM ubuntu:18.04

# Install EDK dependencies
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends build-essential uuid-dev iasl git g++-5 gcc-5 gdb nasm python python3-distutils nano curl figlet libgmp-dev python-dev

# Install mongodb compass on ubuntu
# Source: https://docs.mongodb.com/compass/current/install/
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install libnotify4 libnss3 xdg-utils libgconf-2-4 libsecret-1-0 gnome-keyring wget \
	&& apt --fix-broken install \
    && wget https://downloads.mongodb.com/compass/mongodb-compass_1.26.0_amd64.deb \
    && dpkg -i mongodb-compass_1.26.0_amd64.deb

ENTRYPOINT ["bash"]

# # Install MongoDB
# # Source: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu-tarball/#install-mongodb-community-edition
# RUN mkdir -p /tmp/mongodb && \
#     cd /tmp/mongodb && \
#     wget -qOmongodb.tgz https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-ubuntu2004-4.4.2.tgz && \
#     tar xf mongodb.tgz && \
#     cd mongodb-* && \
#     sudo cp bin/* /usr/local/bin/ && \
#     rm -rf /tmp/mongodb && \
#     sudo mkdir -p /data/db && \
#     sudo chown gitpod:gitpod -R /data/db

