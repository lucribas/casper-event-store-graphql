
## Casper Hackathon Closing Ceremonies Interview


### 1) Could you tell us a little about you, or about your team?

I am eletronic engineer with over 18 years of experience working for HW/SW embedded systems and firmware/FPGA.
Currently, I am technical leader in CERTI Foundation that is a technology-based institution, located at South of Brazil with development of projects in the Aerospace and Defense, Consumer Electronics and Electro medical Products sectors. CERTI encourages its employees to take on new projects and challenges.
I am passionate about the crypto world and its potential to improve the world.


### 2) Could you tell us about what you built?

I built an event storage for Casper. An event can be emitted whenever a new block is minted, a transaction is executed, or a condition occurs in the smart contract.
The storage stores these events in a database (MongoDB) and makes it available to applications via GraphQL. Then applications can query present and past events.
In this way, it is easy to build a scalable architecture for many users, allowing new applications and uses of the Casper protocol.

### 3) How did you like the experience of this hackathon?

My experience with the hackathon was super positive. Casper documentation and examples are excellent and well structured. CasperLab's support via Discord was essential to clarify my doubts.
I am very happy to have participated in the hackathon and to have contributed to the development of Casper.


### 4) What did you think about the Casper protocol?

The casper protocol, in addition to its main advantages such as POS, predictable gas and enterprise-grade solution, supports contracts written in Rust language and compiled to WebAssembly.
In this way the Casper protocol uses the proven state-of-the-art technology to provide an open and rich environment for developers.

### 5) Do you have any advice for other builders?

Studying the documentation and taking the tutorials is essential for beginners.
During development a little automation will help and you can also use containers to ensure more predictability of building and testing your environment.
In my project repository I made scripts for installing and initializing the database and a Casper test network using containers.
